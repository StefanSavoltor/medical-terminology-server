package com.jingyi.clinic.core.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import com.jingyi.clinic.core.view.View;
import com.jingyi.clinic.rest.converter.SearchAfterHelper;
import org.springframework.data.domain.Page;

import java.util.Collection;

public class ItemsPage<T> {
    private final Collection<T> items;
    private final long total;
    private final long limit;
    private final Long offset;
    private final String searchAfter;
    private final Object[] searchAfterArray;

    public ItemsPage(Collection<T> items) {
        this.items = items;
        this.limit = items.size();
        this.total = items.size();
        this.offset = 0L;
        this.searchAfter = null;
        this.searchAfterArray = null;
    }

    public ItemsPage(Collection<T> items, long total) {
        this.items = items;
        this.limit = items.size();
        this.total = total;
        this.offset = 0L;
        this.searchAfter = null;
        this.searchAfterArray = null;
    }

    @JsonView(View.SimpleView.class)
    public Collection<T> getItems() {
        return items;
    }

    @JsonView(View.SimpleView.class)
    public long getTotal() {
        return total;
    }

    @JsonView(View.SimpleView.class)
    public long getLimit() {
        return limit;
    }

    @JsonView(View.SimpleView.class)
    public Long getOffset() {
        return offset;
    }

    @JsonView(View.SimpleView.class)
    public String getSearchAfter() {
        return searchAfter;
    }

    @JsonView(View.SimpleView.class)
    public Object[] getSearchAfterArray() {
        return searchAfterArray;
    }
}
