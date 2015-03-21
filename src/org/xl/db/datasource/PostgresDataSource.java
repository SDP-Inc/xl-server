package org.xl.db.datasource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * A PostgreSQL DataSource Wrapper
 *
 * Created by meulmees on 2/14/2015.
 */
public class PostgresDataSource extends DataSource {

    /** CONSTANTS FOR DRIVER AND URL FORMATTING **/
    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private static final String URL_FORMAT = "jdbc:postgresql://%s:%s/%s";
    private static final String URL_FORMAT_NO_PORT = "jdbc:postgresql://%s/%s";

    /** DATASOURCE ATTRIBUTES **/
    private String dbServer;
    private int dbPort;
    private String dbName;
    private String username;
    private String password;

    public PostgresDataSource(String dbServer, int dbPort, String dbName, String username, String password) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(DRIVER_CLASS_NAME);
        ds.setUrl(String.format(URL_FORMAT, dbServer, dbPort, dbName));
        ds.setUsername(username);
        ds.setPassword(password);

        this.dataSource = ds;
    }

    public PostgresDataSource(String dbServer, String dbName, String username, String password) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(DRIVER_CLASS_NAME);
        ds.setUrl(String.format(URL_FORMAT_NO_PORT, dbServer, dbName));
        ds.setUsername(username);
        ds.setPassword(password);

        this.dataSource = ds;
    }

    public String getDbServer() {
        return dbServer;
    }

    public void setDbServer(String dbServer) {
        this.dbServer = dbServer;
    }

    public int getDbPort() {
        return dbPort;
    }

    public void setDbPort(int dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
