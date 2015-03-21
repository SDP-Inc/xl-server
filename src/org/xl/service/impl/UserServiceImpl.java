package org.xl.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xl.db.dao.UserDao;
import org.xl.model.User;
import org.xl.model.filter.UserFilter;
import org.xl.service.UserService;

import java.util.List;

/**
 * Created by meulmees on 3/18/2015.
 */
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> searchUsers(UserFilter filter) {
        logger.debug(String.format("Performing Search Users with Filter: %s", filter));
        List<User> users;

        try {
            users = userDao.searchUsers(filter);
        } catch (Throwable e) {
            String msg = String.format("UserDao Failed with Error: %s", e.getLocalizedMessage());
            logger.error(msg);
            throw new RuntimeException(msg, e);
        }

        return users;
    }

    @Override
    public User getUser(Long id) {
        User user = userDao.get(id);
        return user;
    }

    @Override
    public User createUser(User user) {
        User createdUser = userDao.create(user);
        return createdUser;
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.delete(id);
    }
}
