package org.xl.model.filter;

/**
 * A Generic Pageable and Sortable Filter which all other Model Filters can have
 * their Builder class extend to support paging and sorting.
 *
 * Created by meulmees on 3/20/2015.
 */
public class PageSortBuilder<T> {

    protected Integer page;
    protected Integer start;
    protected Integer limit;
    protected String sort;
    protected String dir;

    public T page(Integer page) {
        this.page = page;
        return (T) this;
    }

    public T start(Integer start) {
        this.start = start;
        return (T) this;
    }

    public T limit(Integer limit) {
        this.limit = limit;
        return (T) this;
    }

    public T sort(String sort) {
        this.sort = sort;
        return (T) this;
    }

    public T dir(String dir) {
        if (dir == null) {
            this.dir = dir;
        } else if (dir.toLowerCase().equals("asc")) {
            this.dir = "ASC";
        } else {
            this.dir = "DESC";
        }
        return (T) this;
    }

}
