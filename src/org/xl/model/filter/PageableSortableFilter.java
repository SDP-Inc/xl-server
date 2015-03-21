package org.xl.model.filter;

/**
 * Created by meulmees on 3/20/2015.
 */
public class PageableSortableFilter {

    protected Integer page;
    protected Integer start;
    protected Integer limit;
    protected String sort;
    protected String dir;

    public Integer getPage() {
        return page;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getLimit() {
        return limit;
    }

    public String getSort() {
        return sort;
    }

    public String getDir() {
        return dir;
    }
}
