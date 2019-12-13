package com.jingyi.clinic.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.jingyi.clinic.core.domain.Description;
import com.jingyi.clinic.core.domain.TerminologyComponent;
import com.jingyi.clinic.core.pojo.DescriptionSearchResult;
import com.jingyi.clinic.core.pojo.ItemsPage;
import com.jingyi.clinic.core.util.ControllerHelper;
import com.jingyi.clinic.core.view.View;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Description", description = "-")
@RequestMapping(value = "/description", produces = "application/json")
public class DescriptionController {
    @ApiOperation(value = "术语详情", notes = "获取术语详情", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "descriptionId", value = "术语标识符", required = true, paramType = "path", dataType = "string"),
    })
    @GetMapping("/{descriptionId}")
    @JsonView(View.SimpleView.class)
    public Description fetchDescription(@PathVariable("descriptionId") String descriptionId){
        Description description = new Description();
        return description;
    }

    @ApiOperation(value = "查询术语简集", notes = "多条件查询术语简集", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "term", value = "术语名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "术语状态", required = false, paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(name = "termType", value = "术语类型", required = false, paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(name = "semanticTag", value = "语义标签", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "conceptStatus", value = "概念状态", required = false, paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(name = "searchMode", value = "查询模式", required = false, paramType = "query", dataType = "string"),
    })
    @GetMapping("/brief/descriptions")
    @JsonView(View.SimpleView.class)
    public Page<DescriptionSearchResult> findBriefDescriptions(
            Pageable pageable,
            @RequestParam(required = false) String term,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false,defaultValue = "") List<String> termType,
            @RequestParam(required = false) String semanticTag,
            @RequestParam(required = false) Boolean conceptStatus,
            @RequestParam(defaultValue = "STANDARD") TerminologyComponent.SearchMode searchMode) {
            List<DescriptionSearchResult> descriptionSearchResults = new ArrayList<>();
            Page<DescriptionSearchResult> descriptionPage = new PageImpl<DescriptionSearchResult>(descriptionSearchResults,pageable,descriptionSearchResults.size());
            return descriptionPage;
    }

    @ApiOperation(value = "查询术语集", notes = "多条件查询术语集", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "term", value = "术语名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "术语状态", required = false, paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(name = "termType", value = "术语类型", required = false, paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(name = "searchMode", value = "查询模式", required = false, paramType = "query", dataType = "string"),
    })
    @GetMapping("/descriptions")
    @JsonView(View.SimpleView.class)
    public ItemsPage<Description> findDetailedDescriptions(
            Pageable pageable,
            @RequestParam(required = false) String term,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false,defaultValue = "") List<String> termType,
            @RequestParam(defaultValue = "STANDARD") TerminologyComponent.SearchMode searchMode) {
        ControllerHelper.validatePageSize(pageable.getOffset(), pageable.getPageSize());
        List<Description> descriptionSearchResults = new ArrayList<>();
        ItemsPage<Description> descriptionPage = new ItemsPage<>(descriptionSearchResults);
        return descriptionPage;
    }

    @ApiOperation(value = "删除术语", notes = "删除术语数据", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "descriptionId", value = "术语标识符", required = true, paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "force", value = "是否强制删除", required = true, paramType = "query", dataType = "boolean"),
    })
    @RequestMapping(value = "/{descriptionId}", method = RequestMethod.DELETE)
    @ResponseBody
    @JsonView(value = View.SimpleView.class)
    public void deleteDescription(@PathVariable String descriptionId, @RequestParam(defaultValue = "false") boolean force) {

    }
}
