package org.xl.db.dao;

import org.xl.model.User;
import org.xl.model.filter.UserFilter;

import java.util.List;

/**
 * Created by meulmees on 3/18/2015.
 */
public interface UserDao extends BaseDao<User, Long> {

    public List<User> searchUsers(UserFilter filter);

}
