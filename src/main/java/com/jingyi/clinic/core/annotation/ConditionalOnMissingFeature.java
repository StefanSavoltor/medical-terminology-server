package com.jingyi.clinic.core.annotation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;

@JsonIgnore
@ConditionalOnMissingClass
public @interface ConditionalOnMissingFeature {
    String value() default "";
}
