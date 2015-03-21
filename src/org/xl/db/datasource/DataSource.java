package org.xl.db.datasource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Abstract Class for encapsulating all methods belonging to a DataSource
 * independent of DB Technology
 *
 * Created by meulmees on 2/14/2015.
 */
public abstract class DataSource {

    protected javax.sql.DataSource dataSource;

    /**
     * Retrieve the DataSource
     *
     * @return dataSource
     */
    public javax.sql.DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Return the DataSource Connection Object
     *
     * @return connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
