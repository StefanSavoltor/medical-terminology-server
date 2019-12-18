package com.jingyi.clinic.core.repositories.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jingyi.clinic.core.domain.Description;
import com.jingyi.clinic.core.domain.Mapping;
import com.jingyi.clinic.core.domain.Relationship;

import java.util.Set;

public abstract class ConceptStoreMixIn {
    @JsonIgnore
    abstract Long getId();
    @JsonIgnore
    abstract String getSemanticTagDesc();

    @JsonIgnore
    abstract Set<Description> getDescriptions();

    @JsonIgnore
    abstract Set<Relationship> getRelationships();

    @JsonIgnore
    abstract Set<Mapping> getMappings();

    @JsonIgnore
    abstract String getPt();

    @JsonIgnore
    abstract String getSt();
}
