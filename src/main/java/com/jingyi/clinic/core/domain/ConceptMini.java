package com.jingyi.clinic.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.jingyi.clinic.common.constant.TerminologyComponentMap;
import com.jingyi.clinic.core.util.DescriptionHelper;
import com.jingyi.clinic.core.view.View;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class ConceptMini implements Serializable {

    @ApiModelProperty(value = "概念标识符")
    @JsonView(value = View.SimpleView.class)
    private Long conceptId;

    @JsonView(value = View.SimpleView.class)
    @JsonFormat(pattern="yyyyMMdd",timezone = "GMT+8")
    @ApiModelProperty(value = "发布日期")
    private Date releaseDate;

    @JsonView(value = View.SimpleView.class)
    @ApiModelProperty(value = "状态")
    private Boolean status;

    /**
     * 语义标签
     */
    private String semanticTag;

    /**
     * 当前状态可用术语
     */
    private Set<Description> activeDescriptions;

    public ConceptMini() {
        activeDescriptions = new HashSet<>();
    }

    public ConceptMini(Long conceptId) {
        this();
        this.conceptId = conceptId;
    }

    public ConceptMini(Concept concept) {
        this(concept.getConceptId());
        releaseDate = concept.getReleaseDate();
        status = concept.getStatus();
        semanticTag = concept.getSemanticTag();
        Set<Description> descriptions = concept.getDescriptions();
        if (descriptions != null) {
            activeDescriptions = descriptions.stream().filter(TerminologyComponent::getStatus).collect(Collectors.toSet());
        }
    }

    public ConceptMini(Long conceptId, Date releaseDate, Boolean status, String semanticTag) {
        this.conceptId = conceptId;
        this.releaseDate = releaseDate;
        this.status = status;
        this.semanticTag = semanticTag;
    }

    public Long getConceptId() {
        return conceptId;
    }

    public void setConceptId(Long conceptId) {
        this.conceptId = conceptId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @JsonIgnore
    public String getSemanticTag() {
        return semanticTag;
    }

    public void setSemanticTag(String semanticTag) {
        this.semanticTag = semanticTag;
    }

    @ApiModelProperty(value = "标识符")
    @JsonView(value = View.SimpleView.class)
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
        return DescriptionHelper.getPtDescriptionTerm(activeDescriptions);
    }

    @ApiModelProperty(value = "许用术语")
    @JsonView(value = View.SimpleView.class)
    public String getSt(){
        return DescriptionHelper.getAtDescriptionTerm(activeDescriptions);
    }

    public void addActiveDescription(Description pt){
        activeDescriptions.add(pt);
    }

    public void addActiveDescriptions(Collection<Description> pts){
        activeDescriptions.addAll(pts);
    }
}
