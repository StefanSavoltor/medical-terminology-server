package com.jingyi.clinic.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.jingyi.clinic.core.domain.Concept;
import com.jingyi.clinic.core.domain.Description;
import com.jingyi.clinic.core.domain.Mapping;
import com.jingyi.clinic.core.domain.TerminologyComponent;
import com.jingyi.clinic.core.pojo.ItemsPage;
import com.jingyi.clinic.core.util.ControllerHelper;
import com.jingyi.clinic.core.view.View;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Mapping", description = "-")
@RequestMapping(value = "/mapping", produces = "application/json")
public class MappingController {
    @ApiOperation(value = "映射详情", notes = "获取映射详情", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mappingId", value = "映射标识符", required = true, paramType = "path", dataType = "string"),
    })
    @GetMapping("/{mappingId}")
    @JsonView(View.SimpleView.class)
    public Mapping findConcept(@PathVariable("mappingId") String mappingId){
        Mapping mapping = new Mapping();
        return mapping;
    }

    @ApiOperation(value = "删除映射", notes = "删除映射数据", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mappingId", value = "映射标识符", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "force", value = "是否强制删除", required = true, paramType = "query", dataType = "boolean"),
    })
    @RequestMapping(value = "/{mappingId}",method = RequestMethod.DELETE)
    @JsonView(View.SimpleView.class)
    public void deleteMapping(@PathVariable("mappingId") String mappingId,@RequestParam(defaultValue = "false") boolean force){

    }

    @ApiOperation(value = "查询映射集", notes = "多条件查询映射集", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "映射状态", required = false, paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(name = "omahaId", value = "omaha标识符", required = false, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "targetId", value = "映射目标标识符", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "targetDesc", value = "映射目标描述", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "searchMode", value = "查询模式", required = false, paramType = "query", dataType = "string"),
    })
    @GetMapping("/mappings")
    @JsonView(View.SimpleView.class)
    public ItemsPage<Mapping> findDetailedDescriptions(
            Pageable pageable,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) Long omahaId,
            @RequestParam(required = false) Long targetId,
            @RequestParam(required = false,defaultValue = "") String targetDesc,
            @RequestParam(defaultValue = "STANDARD") TerminologyComponent.SearchMode searchMode) {
        ControllerHelper.validatePageSize(pageable.getOffset(), pageable.getPageSize());
        List<Mapping> mappings = new ArrayList<>();
        ItemsPage<Mapping> mappingItemsPage = new ItemsPage<>(mappings);
        return mappingItemsPage;
    }
}
