package com.jingyi.clinic.core.util;

import com.jingyi.clinic.core.data.SearchAfterPageRequest;
import com.jingyi.clinic.core.exception.NotFoundException;
import com.jingyi.clinic.rest.converter.SearchAfterHelper;
import org.elasticsearch.common.Strings;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ControllerHelper {

    static ResponseEntity<Void> getCreatedResponse(String id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }


    static String requiredParam(String value, String paramName) {
        if (Strings.isNullOrEmpty(value)) {
            throw new IllegalArgumentException(paramName + " is a required parameter.");
        }
        return value;
    }

    static <T> T requiredParam(T value, String paramName) {
        if (value == null) {
            throw new IllegalArgumentException(paramName + " is a required parameter.");
        }
        return value;
    }

    public static <T> T throwIfNotFound(String type, T component) {
        if (component == null) {
            throw new NotFoundException(type + " not found");
        }
        return component;
    }

    public static PageRequest getPageRequest(int offset, int limit) {
        return getPageRequest(offset, null, limit);
    }

    public static PageRequest getPageRequest(int offset, String searchAfter, int limit) {
        if (!Strings.isNullOrEmpty(searchAfter)) {
            return SearchAfterPageRequest.of(SearchAfterHelper.fromSearchAfterToken(searchAfter), limit);
        }
        if (offset % limit != 0) {
            throw new IllegalArgumentException("Offset must be a multiplication of the limit param in order to map to Spring pages.");
        }

        int page = ((offset + limit) / limit) - 1;
        int size = limit;
        return PageRequest.of(page, size);
    }

    public static List<String> getLanguageCodes(String acceptLanguageHeader) {
        List<Locale.LanguageRange> languageRanges = Locale.LanguageRange.parse(acceptLanguageHeader);

        List<String> languageCodes = new ArrayList<>(languageRanges.stream()
                .map(languageRange -> languageRange.getRange().contains("-") ? languageRange.getRange().substring(0, languageRange.getRange().indexOf("-")) : languageRange.getRange())
                .collect(Collectors.toCollection((Supplier<Set<String>>) LinkedHashSet::new)));

        // Always include en at the end if not already present because this is the default language.
        if (!languageCodes.contains("en")) {
            languageCodes.add("en");
        }
        return languageCodes;
    }

    public static void validatePageSize(long offset, int limit) {
        if ((offset + limit) > 10_000) {
            throw new IllegalArgumentException("Maximum offset + page size is 10,000.");
        }
    }
}
