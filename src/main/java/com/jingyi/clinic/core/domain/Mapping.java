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

@Document(indexName = "mapping",type = "mapping")
@JsonPropertyOrder({"mappingId",  "status", "releaseDate", "omahaId", "targetId", "mapPriority", "mapVocabulary"})
public class Mapping extends TerminologyComponent{
    public interface Fields extends TerminologyComponent.Fields {
        String MAPPING_ID = "mappingId";
        String OMAHA_ID = "omahaId";
        String TARGET_ID = "targetId";
        String MAP_PRIORITY = "mapPriority";
        String MAP_VOCABULARY = "mapVocabulary";
    }

    @JsonView(View.DetailView.class)
    @Field(type=FieldType.Keyword)
    @ApiModelProperty(value = "映射标识符")
    private Long mappingId;

    @JsonView(View.DetailView.class)
    @Field(type=FieldType.Keyword)
    @ApiModelProperty(value = "omaha构件标识符")
    private Long omahaId;

    @JsonView(View.SimpleView.class)
    @Field(type=FieldType.Keyword)
    @NotNull
    @ApiModelProperty(value = "映射目标标识符")
    private String targetId;

    @JsonView(View.SimpleView.class)
    @Field(type=FieldType.Keyword)
    @ApiModelProperty(value = "优先级")
    private Integer mapPriority;

    @JsonView(View.SimpleView.class)
    @Field(type=FieldType.Keyword)
    @NotNull
    @ApiModelProperty(value = "映射术语集")
    private String mapVocabulary;

    public Long getMappingId() {
        return mappingId;
    }

    public void setMappingId(Long mappingId) {
        this.mappingId = mappingId;
    }

    public Long getOmahaId() {
        return omahaId;
    }

    public void setOmahaId(Long omahaId) {
        this.omahaId = omahaId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public Integer getMapPriority() {
        return mapPriority;
    }

    public void setMapPriority(Integer mapPriority) {
        this.mapPriority = mapPriority;
    }

    public String getMapVocabulary() {
        return mapVocabulary;
    }

    public void setMapVocabulary(String mapVocabulary) {
        this.mapVocabulary = mapVocabulary;
    }

    @JsonView(value = View.SimpleView.class)
    @ApiModelProperty(value = "标识符")
    public Long getId(){
        return mappingId;
    }

    @JsonView(value = View.SimpleView.class)
    @ApiModelProperty(value = "映射术语集描述")
    public String getMapVocabularyDesc(){
        return TerminologyComponentMap.mapVocabulary.get(mapVocabulary);
    }

    public Mapping() {
    }

    public Mapping(Long mappingId, Long omahaId, String targetId, Integer mapPriority, String mapVocabulary) {
        this.mappingId = mappingId;
        this.omahaId = omahaId;
        this.targetId = targetId;
        this.mapPriority = mapPriority;
        this.mapVocabulary = mapVocabulary;
    }
}
