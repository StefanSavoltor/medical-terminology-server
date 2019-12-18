package com.jingyi.clinic.core.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;
import java.util.Set;

@JsonDeserialize(as = Concept.class)
public interface ConceptView {
    Long getConceptId();

    String getPt();

    String getSt();

    Date getReleaseDate();

    Boolean getStatus();

    String getSemanticTag();

    Description getDescription(String descriptionId);

    Set<Description> getDescriptions();

    Set<Relationship> getRelationships();

    Set<Mapping> getMappings();

}
