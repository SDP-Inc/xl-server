package org.xl.service;

import org.xl.model.User;
import org.xl.model.filter.UserFilter;

import java.util.List;

/**
 * Created by meulmees on 3/18/2015.
 */
public interface UserService {

    public List<User> searchUsers(UserFilter filter);

    public User getUser(Long id);

    public User createUser(User user);

    public void updateUser(User user);

    public void deleteUser(Long id);
}
