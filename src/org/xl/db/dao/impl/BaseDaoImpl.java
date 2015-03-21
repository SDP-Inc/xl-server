package org.xl.db.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;
import org.xl.db.dao.BaseDao;
import org.xl.db.datasource.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by meulmees on 3/18/2015.
 */
public abstract class BaseDaoImpl<T, I> implements BaseDao<T, I> {

    private static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private int batchSize = 500;

    public Connection getConnection() throws SQLException{
        DataSource dataSource = getDataSource();
        Connection connection;

        if (dataSource != null) {
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                String msg = String.format("Unable to retrieve connection from datasource: %s",
                        e.getLocalizedMessage());
                logger.warn(msg, e);
                throw new SQLException(msg);
            }
        } else {
            String msg = String.format("No datasource has been set on this Dao. Unable to retrieve connection");
            logger.warn(msg);
            throw new SQLException(msg);
        }

        return  connection;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        setJdbcTemplate(new JdbcTemplate(dataSource.getDataSource()));
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
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
                                   final PreparedStatementMapper<T> psMapper) throws RuntimeException {
        Connection connection;
        PreparedStatement ps;
        Iterator<T> iter;
        int count = 0;

        Assert.notNull(list, "A Collection<T> is required");
        Assert.notNull(sql, "A SQL Query is required");
        Assert.notNull(psMapper, "A BaseDao.PreparedStatementMapper<T> is required");

        if (list.size() > 0) {
            try {
                connection = getConnection();
                ps = connection.prepareStatement(sql);
                iter = list.iterator();
                while (iter.hasNext()) {
                    psMapper.prepareStatement(ps, iter.next());
                    ps.addBatch();
                    if (++count % getBatchSize() == 0) {
                        ps.executeBatch();
                    }
                }
                // Execute any remaining statements
                ps.executeBatch();

                connection.commit();
            } catch (Throwable e) {
                String msg = String.format("Error performing JDBC Batch Operation: %s", e.getLocalizedMessage());
                logger.error(msg, e);
                throw new RuntimeException(msg, e);
            }
        } else {
            logger.warn(String.format("%s.jdbcBatchOperation invoked with an empty collection. Skipping batch operation",
                    this.getClass().getCanonicalName()));
        }
    }

    abstract public T get(I id);

    abstract public T create(T t);

    abstract public void update(T t);

    abstract public void delete(I id);
}
