package org.xl.db.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by meulmees on 3/21/2015.
 */
public interface BaseDao<T, I> {

    public interface PreparedStatementMapper<T> {
        public void prepareStatement(PreparedStatement ps, T t) throws SQLException;
    }

    /**
     * This is an extremely efficient method for performing batch operations on a Collection of Objects.
     * <p>
     *     Example Usage:
     *     <pre>
     *         List<User> users = getUsersToUpdate();
     *         String sql = "UPDATE dbo.USERS SET Status=? WHERE UserID=?";
     *         dao.jdbcBatchOperation(user, sql, new BaseDao.PreparedStatementWrapper<User>() {
     *             @Override
     *             public void prepareStatement(ps, user) throws SQLException {
     *                 ps.setString(1, user.getStatus());
     *                 ps.setLong(2, user.getUserID());
     *             }
     *         });
     *     </pre>
     * </p>
     *
     * @param list
     * @param sql
     * @param psMapper
     */
    public void jdbcBatchOperation(final Collection<T> list,
                                   final String sql,
                                   final PreparedStatementMapper<T> psMapper) throws RuntimeException;

    public T get(I id);

    public T create(T t);

    public void update(T t);

    public void delete(I id);
}
