package com.jingyi.clinic.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.jingyi.clinic.core.view.View;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

public abstract class TerminologyComponent implements Serializable {
    public interface Fields {
        String RELEASE_DATE = "releaseDate";
        String STATUS = "status";
        String MODULE_ID = "moduleId";
        String RELEASED = "released";
        String RELEASE_HASH = "releaseHash";
    }
    public enum SearchMode {
        STANDARD, REGEX
    }

    @JsonView(View.SimpleView.class)
    @JsonFormat(pattern="yyyyMMdd",timezone = "GMT+8")
    @ApiModelProperty(value = "发布时间")
    private Date releaseDate;
    @JsonView(View.SimpleView.class)
    @ApiModelProperty(value = "状态(表示该组件是否有效，有效为`1`,无效为`0`)")
    private Boolean status;


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

    public TerminologyComponent(Date releaseDate, Boolean status) {
        this.releaseDate = releaseDate;
        this.status = status;
    }

    public TerminologyComponent() {
    }
}
