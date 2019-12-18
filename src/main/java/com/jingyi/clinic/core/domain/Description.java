package com.jingyi.clinic.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.jingyi.clinic.common.constant.TerminologyComponentMap;
import com.jingyi.clinic.core.view.View;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "description",type = "description")
@JsonPropertyOrder({"descriptionId",  "status", "releaseDate", "conceptId", "term", "termType"})
public class Description extends TerminologyComponent{
    public interface Fields extends TerminologyComponent.Fields {
        String DESCRIPTION_ID = "descriptionId";
        String TERM = "term";
        String TERM_TYPE = "termType";
        String CONCEPT_ID = "conceptId";
    }

    @JsonView(View.SimpleView.class)
    @ApiModelProperty(value = "术语标识符")
    @Field(type=FieldType.Keyword)
    private Long descriptionId;

    @JsonView(View.SimpleView.class)
    @ApiModelProperty(value = "概念标识符")
    @Field(type=FieldType.Keyword)
    private Long conceptId;

    @JsonView(View.SimpleView.class)
    @ApiModelProperty(value = "术语(概念的名称)")
    @Field(type=FieldType.Keyword)
    private String term;

    @JsonView(View.DetailView.class)
    @ApiModelProperty(value = "术语类型",example = "1")
    @Field(type=FieldType.Keyword)
    private Number termType;

    public Description() {
    }

    public Description(Long descriptionId, Long conceptId, String term, Number termType) {
        this.descriptionId = descriptionId;
        this.conceptId = conceptId;
        this.term = term;
        this.termType = termType;
    }

    public Long getDescriptionId() {
        return descriptionId;
    }

    public void setDescriptionId(Long descriptionId) {
        this.descriptionId = descriptionId;
    }

    public Long getConceptId() {
        return conceptId;
    }

    public void setConceptId(Long conceptId) {
        this.conceptId = conceptId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Number getTermType() {
        return termType;
    }

    public void setTermType(Number termType) {
        this.termType = termType;
    }

    @JsonIgnore
    public Boolean isActive(){
        return getStatus();
    }
    @JsonView(value = View.SimpleView.class)
    @ApiModelProperty(value = "标识符")
    public Long getId(){
        return descriptionId;
    }

    @JsonView(View.DetailView.class)
    @ApiModelProperty(value = "术语类型描述")
    public String getTermTypeDesc(){
        return TerminologyComponentMap.termTypeMap.get(termType);
    }
}
