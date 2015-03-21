package org.xl.db.dao.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import org.xl.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by meulmees on 3/21/2015.
 */
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();

        user.setId(rs.getLong(User.COLUMN_ID));
        user.setFirstName(rs.getString(User.COLUMN_FIRST_NAME));
        user.setLastName(rs.getString(User.COLUMN_LAST_NAME));

        return user;
    }
}
