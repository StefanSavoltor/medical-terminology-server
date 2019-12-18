package com.jingyi.clinic.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.jingyi.clinic.common.constant.TerminologyComponentMap;
import com.jingyi.clinic.core.util.DescriptionHelper;
import com.jingyi.clinic.core.view.View;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Document(indexName = "concept")
@JsonPropertyOrder({"conceptId",  "status", "releaseDate","semanticTag", "descriptions", "relationships"})
public class Concept extends TerminologyComponent implements ConceptView{

    public interface Fields extends TerminologyComponent.Fields {
        String CONCEPT_ID = "conceptId";
        String SEMANTIC_TAG = "semanticTag";
    }
    @JsonView(View.SimpleView.class)
    @ApiModelProperty(value = "概念标识符")
    @Field(type = FieldType.Keyword)
    @Id
    private Long conceptId;

    /**
     * 语义标签
     */
    @JsonIgnore
    @Field(type = FieldType.Keyword)
    @NotNull
    private String semanticTag;

    @Valid
    @JsonView(View.SimpleView.class)
    @ApiModelProperty(value = "术语集")
    private Set<Description> descriptions;

    @Valid
    @JsonView(View.SimpleView.class)
    @ApiModelProperty(value = "关系集")
    private Set<Relationship> relationships;

    @Valid
    @JsonView(View.SimpleView.class)
    @ApiModelProperty(value = "映射集")
    private Set<Mapping> mappings;

    public Long getConceptId() {
        return conceptId;
    }

    public void setConceptId(Long conceptId) {
        this.conceptId = conceptId;
    }

    public String getSemanticTag() {
        return semanticTag;
    }

    @Override
    public Description getDescription(String descriptionId) {
        for (Description description : descriptions) {
            if (descriptionId.equals(description.getDescriptionId())) {
                return description;
            }
        }
        return null;
    }

    public void setSemanticTag(String semanticTag) {
        this.semanticTag = semanticTag;
    }

    public Set<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public Set<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(Set<Relationship> relationships) {
        this.relationships = relationships;
    }

    @JsonIgnore
    public Boolean isActive(){
        return getStatus();
    }
    @JsonView(value=View.SimpleView.class)
    @ApiModelProperty(value = "标识符")
    public Long getId(){
        return conceptId;
    }
    @ApiModelProperty(value = "语义标签描述")
    @JsonView(value = View.SimpleView.class)
    public String getSemanticTagDesc(){
        return TerminologyComponentMap.segmanticTagMap.get(semanticTag);
    }

    @ApiModelProperty(value = "首选术语")
    @JsonView(value = View.SimpleView.class)
    public String getPt(){
        return DescriptionHelper.getPtDescriptionTerm(descriptions);
    }

    @ApiModelProperty(value = "许用术语")
    @JsonView(value = View.SimpleView.class)
    public String getSt(){
        return DescriptionHelper.getAtDescriptionTerm(descriptions);
    }

    public Set<Mapping> getMappings() {
        return mappings;
    }

    public void setMappings(Set<Mapping> mappings) {
        this.mappings = mappings;
    }

    public Concept() {
    }

    public Concept(Long conceptId) {
        this();
        this.conceptId = conceptId;
    }

    public Concept(Long conceptId, @NotNull String semanticTag, @Valid Set<Description> descriptions, @Valid Set<Relationship> relationships) {
        this.conceptId = conceptId;
        this.semanticTag = semanticTag;
        this.descriptions = descriptions;
        this.relationships = relationships;
    }
}
