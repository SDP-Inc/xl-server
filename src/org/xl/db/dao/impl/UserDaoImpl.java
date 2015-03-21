package org.xl.db.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.xl.db.dao.UserDao;
import org.xl.db.dao.rowmapper.UserRowMapper;
import org.xl.model.User;
import org.xl.model.filter.UserFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by meulmees on 3/18/2015.
 */
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    private final UserRowMapper userRowMapper = new UserRowMapper();

    @Override
    public List<User> searchUsers(UserFilter filter) {
        List<User> result = new ArrayList<>();
        User user = new User();

        user.setId(1234L);
        user.setFirstName("Derek");
        user.setLastName("Meulmeester");
        user.setLastAccess(new Date());

        result.add(user);

        user = new User();

        user.setId(4321L);
        user.setFirstName("Steve");
        user.setLastName("Meulmeester");
        user.setLastAccess(new Date());

        result.add(user);

        return result;
    }

    @Override
    public User get(Long userID) {
        logger.debug(String.format("Fetching User Object for ID[%s]", userID));
        User user;

        try {
            final String sql = String.format("SELECT * FROM %s WHERE %s=?;", User.TABLE_NAME, User.COLUMN_ID);
            user = getJdbcTemplate().queryForObject(sql, userRowMapper, userID);
        } catch (EmptyResultDataAccessException e) {
            logger.warn(String.format("No User Object was found for ID[%s]", userID));
            user = null;
        } catch (Throwable e) {
            String msg = String.format("Failed to retrieve User Object from DB: %s", e.getLocalizedMessage());
            logger.error(msg, e);
            throw new RuntimeException(msg, e);
        }

        return user;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Long userID) {
        logger.debug(String.format("Deleting User Object for ID[%s]", userID));

        try {
            final String sql = String.format("DELETE FROM %s WHERE %s=?;", User.TABLE_NAME, User.COLUMN_ID);
            getJdbcTemplate().update(sql, userID);

        } catch (Throwable e) {
            String msg = String.format("Failed to delete User Object from DB: %s", e.getLocalizedMessage());
            logger.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }
}
