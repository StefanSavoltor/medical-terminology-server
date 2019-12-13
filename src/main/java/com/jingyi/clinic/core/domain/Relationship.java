package com.jingyi.clinic.core.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.jingyi.clinic.common.constant.TerminologyComponentMap;
import com.jingyi.clinic.core.view.View;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotNull;

@Document(indexName = "relationship")
@JsonPropertyOrder({"relationshipId",  "status", "releaseDate", "conceptId1", "conceptId2", "relationshipType", "relationshipGroup"})
public class Relationship extends TerminologyComponent{
    public interface Fields extends TerminologyComponent.Fields {
        String RELATIONSHIP_ID = "relationshipId";
        String CONCEPT_ID_1 = "conceptId1";
        String CONCEPT_ID_2 = "conceptId2";
        String RELATIONSHIP_TYPE = "relationshipType";
        String RELATIONSHIP_GROUP = "relationshipGroup";
    }

    @JsonView(View.SimpleView.class)
    @ApiModelProperty(value = "关系标识符")
    @Field(type = FieldType.Keyword)
    private Long relationshipId;

    @JsonView(View.SimpleView.class)
    @ApiModelProperty(value = "起始概念标识符")
    @Field(type = FieldType.Keyword)
    private Long conceptId1;

    @JsonView(View.SimpleView.class)
    @ApiModelProperty(value = "目标概念标识符")
    @Field(type = FieldType.Keyword)
    private Long conceptId2;

    @JsonView(View.DetailView.class)
    @ApiModelProperty(value = "关系类型标识符")
    @Field(type = FieldType.Keyword)
    @NotNull
    private String relationshipType;

    @JsonView(View.SimpleView.class)
    @ApiModelProperty(value = "关系组")
    @Field(type = FieldType.Keyword)
    private Number relationshipGroup;

    @JsonView(View.SimpleView.class)
    @ApiModelProperty(value = "关系组")
    private ConceptMini target;

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public Long getConceptId1() {
        return conceptId1;
    }

    public void setConceptId1(Long conceptId1) {
        this.conceptId1 = conceptId1;
    }

    public Long getConceptId2() {
        return conceptId2;
    }

    public void setConceptId2(Long conceptId2) {
        this.conceptId2 = conceptId2;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public Number getRelationshipGroup() {
        return relationshipGroup;
    }

    public void setRelationshipGroup(Number relationshipGroup) {
        this.relationshipGroup = relationshipGroup;
    }

    @JsonView(value = View.SimpleView.class)
    @ApiModelProperty(value = "标识符")
    public Long getId(){
        return relationshipId;
    }

    @JsonView(value = View.SimpleView.class)
    @ApiModelProperty(value = "关系类型描述")
    public String getRelationshipTypeDesc(){
        return TerminologyComponentMap.relationshipTypeMap.get(relationshipType);
    }

    public ConceptMini getTarget() {
        return target;
    }

    public void setTarget(ConceptMini target) {
        this.target = target;
    }

    public Relationship() {
    }

    public Relationship(Long relationshipId, Long conceptId1, Long conceptId2, @NotNull String relationshipType, Number relationshipGroup) {
        this.relationshipId = relationshipId;
        this.conceptId1 = conceptId1;
        this.conceptId2 = conceptId2;
        this.relationshipType = relationshipType;
        this.relationshipGroup = relationshipGroup;
    }
}
