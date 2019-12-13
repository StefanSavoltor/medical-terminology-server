package com.jingyi.clinic.core.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import com.jingyi.clinic.core.domain.ConceptMini;
import com.jingyi.clinic.core.view.View;
import io.swagger.annotations.ApiModelProperty;

public class DescriptionSearchResult {
    @ApiModelProperty(value = "术语名称")
    private String term;
    @ApiModelProperty(value = "状态")
    private boolean status;
    @ApiModelProperty(value = "概念")
    private ConceptMini concept;

    public DescriptionSearchResult(String term, boolean status, ConceptMini concept) {
        this.term = term;
        this.status = status;
        this.concept = concept;
    }

    public DescriptionSearchResult() {
    }

    @JsonView(value = View.SimpleView.class)
    public String getTerm() {
        return term;
    }

    @JsonView(value = View.SimpleView.class)
    public boolean isActive() {
        return status;
    }

    @JsonView(value = View.SimpleView.class)
    public ConceptMini getConcept() {
        return concept;
    }
}
