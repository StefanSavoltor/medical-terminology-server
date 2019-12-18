package com.jingyi.clinic.core.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.jingyi.clinic.core.view.View;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "Person")
public class Person {
    @JsonView(View.SimpleView.class)
    @ApiModelProperty(value = "personId")
    @Field(type = FieldType.Keyword)
    @Id
    private Long personId;

    @JsonView(View.SimpleView.class)
    @ApiModelProperty(value = "姓名")
    @Field(type = FieldType.Keyword)
    private String name;

    @JsonView(View.SimpleView.class)
    @ApiModelProperty(value = "年龄")
    @Field(type = FieldType.Keyword)
    private Integer age;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person() {
    }

    public Person(Long personId, String name, Integer age) {
        this.personId = personId;
        this.name = name;
        this.age = age;
    }
}
