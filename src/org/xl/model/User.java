package org.xl.model;

import java.util.Date;

/**
 * Created by meulmees on 3/18/2015.
 */
public class User {

    /**************************************************************************
     * DATABASE RELATED CONSTANTS
     */
    public static final String TABLE_NAME = "\"USERS\"";
    public static final String COLUMN_ID = "UserID";
    public static final String COLUMN_FIRST_NAME = "FirstName";
    public static final String COLUMN_LAST_NAME = "LastName";
    /*************************************************************************/

    private Long id;
    private String firstName;
    private String lastName;
    private Date lastAccess;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    @Override
    public String toString() {
        return String.format("%s{" +
                    "id=%s, " +
                    "firstName=%s, " +
                    "lastName=%s, " +
                    "lastAccess=%s" +
                "}",
                this.getClass().getCanonicalName(),
                id,
                firstName,
                lastName,
                lastAccess);
    }
}
