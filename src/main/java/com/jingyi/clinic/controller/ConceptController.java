package com.jingyi.clinic.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/concept")
public class ConceptController {
    @ApiOperation(value = "概率术语名称", notes = "获取概率术语名称", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "conceptId", value = "概率标识符", paramType = "query", dataType = "string"),
    })
    @GetMapping("/termName/{conceptId}")
    public String getConceptTermName(@PathVariable("conceptId") String conceptId){
        String name = conceptId;
        return name;
    }
}
