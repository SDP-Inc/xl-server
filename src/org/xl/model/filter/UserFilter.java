package org.xl.model.filter;

/**
 * Created by meulmees on 3/20/2015.
 */
public class UserFilter extends PageableSortableFilter {

    private String firstName;
    private String lastName;

    private UserFilter(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.page = builder.page;
        this.start = builder.start;
        this.limit = builder.limit;
        this.sort = builder.sort;
        this.dir = builder.dir;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static class Builder extends PageSortBuilder<Builder> {
        private String firstName;
        private String lastName;

        public UserFilter build() {
            return new UserFilter(this);
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
    }

    @Override
    public String toString() {
        return String.format("%s{" +
                    "firstName=%s, " +
                    "lastName=%s, " +
                    "page=%s, " +
                    "start=%s, " +
                    "limit=%s, " +
                    "sort=%s, " +
                    "dir=%s" +
                "}",
                this.getClass().getCanonicalName(),
                firstName,
                lastName,
                page,
                start,
                limit,
                sort,
                dir);
    }
}
