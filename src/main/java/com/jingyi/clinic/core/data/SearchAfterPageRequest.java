package com.jingyi.clinic.core.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class SearchAfterPageRequest extends PageRequest {
    private final Object[] searchAfter;

    public SearchAfterPageRequest(Object[] searchAfter, int size) {
        super(0, size,Sort.unsorted());
        this.searchAfter = searchAfter;
    }

    public static SearchAfterPageRequest of(Object[] searchAfter, int size) {
        return new SearchAfterPageRequest(searchAfter, size);
    }

    public Object[] getSearchAfter() {
        return this.searchAfter;
    }
}
