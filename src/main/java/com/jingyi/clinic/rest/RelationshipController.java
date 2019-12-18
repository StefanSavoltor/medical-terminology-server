package com.jingyi.clinic.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.jingyi.clinic.core.domain.ConceptMini;
import com.jingyi.clinic.core.domain.Description;
import com.jingyi.clinic.core.domain.Relationship;
import com.jingyi.clinic.core.pojo.ItemsPage;
import com.jingyi.clinic.core.util.ControllerHelper;
import com.jingyi.clinic.core.view.View;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@Api(tags = "Relationship", description = "-")
@RequestMapping(value = "/relationship", produces = "application/json")
public class RelationshipController {
    @ApiOperation(value = "关系详情", notes = "获取关系详情", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "relationshipId", value = "关系标识符", required = true, paramType = "path", dataType = "string"),
    })
    @GetMapping("/{relationshipId}")
    @JsonView(View.SimpleView.class)
    public Relationship fetchDescription(@PathVariable("relationshipId") String relationshipId){
        Relationship relationship = new Relationship();
        return relationship;
    }

    @ApiOperation(value = "删除关系", notes = "删除关系数据", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "relationshipId", value = "关系标识符", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "force", value = "是否强制删除", required = true, paramType = "query", dataType = "boolean"),
    })
    @RequestMapping(value = "/{relationshipId}",method = RequestMethod.DELETE)
    @JsonView(View.SimpleView.class)
    public void deleteDescription(@PathVariable("relationshipId") String relationshipId,@RequestParam(defaultValue = "false") boolean force){

    }


    @ApiOperation(value = "查询关系集", notes = "多条件查询关系集", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(name = "source", value = "起始概念标识符", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "destination", value = "目标概念标识符", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "relationshipType", value = "关系类型", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "relationshipGroup", value = "关系组", required = false, paramType = "query", dataType = "number"),
    })
    @GetMapping(value = "/relationships")
    @JsonView(value = View.SimpleView.class)
    public ItemsPage<Relationship> findRelationships(Pageable pageable,
            @RequestParam(required = false) Boolean status,
             @RequestParam(required = false) Long source,
             @RequestParam(required = false) Long destination,
             @RequestParam(required = false) String relationshipType,
             @RequestParam(required = false) Integer relationshipGroup) {
        ControllerHelper.validatePageSize(pageable.getOffset(), pageable.getPageSize());
        Set<Relationship> relationships = new HashSet<>();
        ItemsPage<Relationship> relationshipItemsPage = new ItemsPage<>(relationships);
        return relationshipItemsPage;
    }
}
