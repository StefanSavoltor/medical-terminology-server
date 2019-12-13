package com.jingyi.clinic.core.util;

import com.jingyi.clinic.common.constant.TerminologyComponentMap;
import com.jingyi.clinic.core.domain.Description;

import java.util.*;
import java.util.function.Predicate;

public class DescriptionHelper {
    public static String getPtDescriptionTerm(Set<Description> descriptions) {
        return getPtDescription(descriptions).map(d -> d.getTerm()).orElse("");
    }

    public static Optional<Description> getPtDescription(Set<Description> descriptions) {
        return Optional.ofNullable(getBestDescription(descriptions, description -> TerminologyComponentMap.PREFERRED_TERM.equals(description.getTermType()) || TerminologyComponentMap.UNDETERMINED_PREFERRED_TERM.equals(description.getTermType())));
    }

    private static Description getBestDescription(Set<Description> descriptions, Predicate<Description> descriptionPredicate) {
        for (Description description : descriptions) {
            if (description.isActive() && descriptionPredicate.test(description)) {
                return description;
            }
        }
        return null;
    }

    public static String getAtDescriptionTerm(Set<Description> descriptions) {
        return getAtDescription(descriptions).map(d -> d.getTerm()).orElse("");
    }

    public static Optional<Description> getAtDescription(Set<Description> descriptions) {
        return Optional.ofNullable(getBestDescription(descriptions, description -> TerminologyComponentMap.ALLOWABLE_TERM.equals(description.getTermType())));
    }
}
