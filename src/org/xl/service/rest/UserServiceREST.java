package org.xl.service.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xl.model.User;
import org.xl.model.filter.UserFilter;
import org.xl.service.Service;
import org.xl.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by meulmees on 3/18/2015.
 */
@Path("/UserService")
public class UserServiceREST {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceREST.class);

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieve all org.xl.model.User Objects which match the specified QueryParam filters.
     *
     * ex: GET /rest/UserService/users?page=1&start=0&limit=50&lastName=meulmeester should
     * return the first 50 Users with the lastName meulmeester
     *
     * @param page
     * @param start
     * @param limit
     * @param firstName
     * @param lastName
     * @return List<org.xl.model.User> users
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users")
    public Service.Result<List<User>> getUsers(
            @QueryParam("page") @DefaultValue("1") String page,
            @QueryParam("start") @DefaultValue("0") String start,
            @QueryParam("limit") @DefaultValue("50") String limit,
            @QueryParam("sort") String sort,
            @QueryParam("dir") String dir,
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName
    ) {
        Service.Result<List<User>> result;
        List<User> users;
        UserFilter filter;

        try {
            filter = new UserFilter.Builder()
                    .page(Integer.valueOf(page))
                    .start(Integer.valueOf(start))
                    .limit(Integer.valueOf(limit))
                    .sort(sort)
                    .dir(dir)
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();

            users = userService.searchUsers(filter);
            logger.info(String.format("UserService.searchUsers RETURNED: %s results", users.size()));
            result = new Service.Result<>(users);

        } catch (Throwable e) {
            String msg = String.format("Error retrieving users: %s", e.getLocalizedMessage());
            logger.error(msg, e);
            result = new Service.Result<>(msg, e);
        }

        return result;
    }

    /**
     * Retrieve a org.xl.model.User Object by it's ID
     *
     * @param id
     * @return User Object
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{id}")
    public Service.Result<User> getUser(@PathParam("id") String id) {
        Service.Result<User> result;
        User user;
        Long userID;

        try {
            userID = Long.valueOf(id);
            user = userService.getUser(userID);
            result = new Service.Result<>(user);

        } catch (Throwable e) {
            String msg = String.format("Error retrieving user: %s", e.getLocalizedMessage());
            logger.error(msg, e);
            result = new Service.Result<>(msg, e);
        }

        return result;
    }

    /**
     * Creates a new User Record using the User Object information specified in the
     * Request Body. If a userID is specified in the Object it will be discarded,
     * this can essentially be used as a way of cloning an existing user.
     *
     * @param user
     * @return newly created User Object with ID
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user")
    public Service.Result<User> createUser(User user) {
        Service.Result<User> result;
        User createdUser;

        try {
            createdUser = userService.createUser(user);
            result = new Service.Result<>(createdUser);

        } catch (Throwable e) {
            String msg = String.format("Error creating user: %s", e.getLocalizedMessage());
            logger.error(msg, e);
            result = new Service.Result<>(msg, e);
        }

        return result;
    }

    /**
     * Updates all of the specified User Object's attributes based on it's ID
     *
     * @param user
     * @return Service.Void
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user")
    public Service.Void updateUser(User user) {
        Service.Void result;

        try {
            userService.updateUser(user);
            result = new Service.Void(true, String.format("Successfully Updated User ID: %s", user.getId()));

        } catch (Throwable e) {
            String msg = String.format("Error updating user: %s", e.getLocalizedMessage());
            logger.error(msg, e);
            result = new Service.Void(msg, e);
        }

        return result;
    }

    /**
     * Deletes the User Object specified by ID in the Path Parameter
     *
     * @param id
     * @return Service.Void
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{id}")
    public Service.Void deleteUser(@PathParam("id") String id) {
        Service.Void result;
        Long userID;

        try {
            userID = Long.valueOf(id);
            userService.deleteUser(userID);
            result = new Service.Void(true, String.format("Successfully Deleted User ID: %s", id));

        } catch (Throwable e) {
            String msg = String.format("Error deleting user: %s", e.getLocalizedMessage());
            logger.error(msg, e);
            result = new Service.Void(msg, e);
        }

        return result;
    }
}
