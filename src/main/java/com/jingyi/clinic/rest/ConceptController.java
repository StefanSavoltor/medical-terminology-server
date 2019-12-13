package com.jingyi.clinic.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.jingyi.clinic.core.domain.Concept;
import com.jingyi.clinic.core.domain.ConceptMini;
import com.jingyi.clinic.core.domain.Description;
import com.jingyi.clinic.core.domain.Mapping;
import com.jingyi.clinic.core.domain.Relationship;
import com.jingyi.clinic.core.util.ControllerHelper;
import com.jingyi.clinic.core.view.View;
import com.jingyi.clinic.core.pojo.ItemsPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.common.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api(tags = "Concept", description = "-")
@RequestMapping(value = "/concept", produces = "application/json")
public class ConceptController {

    @ApiOperation(value = "查询概念简集", notes = "多条件查询概念简集", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(name = "term", value = "术语", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "termStatus", value = "术语状态", required = false, paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(name = "conceptIds", value = "概念标识符集", required = false, paramType = "query"),
            @ApiImplicitParam(name = "offset", value = "位偏移", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "条目数量限制", required = false, paramType = "query", dataType = "int"),
    })
    @GetMapping(value = "/brief/concepts", produces = {"application/json", "text/json"})
    @JsonView(View.SimpleView.class)
    public ItemsPage<ConceptMini> findBriefConcepts(
        @RequestParam(required = false) Boolean status,
        @RequestParam(required = false) String term,
        @RequestParam(required = false) Boolean termStatus,
        @RequestParam(required = false) Set<String> conceptIds,
        @RequestParam(required = false, defaultValue = "0") long offset,
        @RequestParam(required = false, defaultValue = "50") int limit,
        @RequestParam(required = false) String searchAfter) {
        ControllerHelper.validatePageSize(offset, limit);
        Set<ConceptMini> conceptMinis = new HashSet<>();
        ItemsPage<ConceptMini> conceptMiniItemsPage = new ItemsPage<>(conceptMinis);
        return conceptMiniItemsPage;
    }

    @ApiOperation(value = "概念简介", notes = "获取概念简介", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "conceptId", value = "概念标识符", required = true, paramType = "path", dataType = "long"),
    })
    @GetMapping("/brief/{conceptId}")
    @JsonView(View.SimpleView.class)
    public ConceptMini findBriefConcept(@PathVariable("conceptId") Long conceptId){
        ConceptMini concept = new ConceptMini();
        return concept;
    }

    @ApiOperation(value = "查询概念集", notes = "多条件查询概念集", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "conceptIds", value = "概念标识符", required = true, paramType = "path", dataType = "long"),
            @ApiImplicitParam(name = "offset", value = "位偏移", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "条目数量限制", required = false, paramType = "query", dataType = "int"),
    })
    @GetMapping(value = "/concepts")
    @JsonView(value = View.SimpleView.class)
    public ItemsPage<Concept> getDetailedConcepts(@RequestParam(required = false) List<Long> conceptIds,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "50") int limit,
            @RequestParam(required = false) String searchAfter) {
        ControllerHelper.validatePageSize(offset, limit);
        Set<Concept> concepts = new HashSet<>();
        return new ItemsPage<>(concepts);
    }

    @ApiOperation(value = "概念详情", notes = "获取概念详情", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "conceptId", value = "概念标识符", required = true, paramType = "path", dataType = "long"),
    })
    @GetMapping("/{conceptId}")
    @JsonView(View.SimpleView.class)
    public Concept findDetailedConcept(@PathVariable("conceptId") Long conceptId){
        Concept concept = new Concept();
        return concept;
    }

    @ApiOperation(value = "概念描述集", notes = "获取概念描述集", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "conceptId", value = "概念标识符", required = true, paramType = "path", dataType = "long"),
    })
    @GetMapping("/{conceptId}/descriptions")
    @JsonView(View.SimpleView.class)
    public Collection<Description> findConceptDescriptions(@PathVariable("conceptId") Long conceptId){
        Set<Description> descriptions = new HashSet<>();
        return descriptions;
    }

    @ApiOperation(value = "删除概念", notes = "删除概念数据", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "conceptId", value = "概念标识符", required = true, paramType = "path", dataType = "long"),
    })
    @RequestMapping(value = "/{conceptId}", method = RequestMethod.DELETE)
    public void deleteConcept(@PathVariable String conceptId) {

    }

    @ApiOperation(value = "概念关系集", notes = "获取概念关系集", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "conceptId", value = "概念标识符", required = true, paramType = "path", dataType = "long"),
    })
    @GetMapping("/{conceptId}/relationships")
    @JsonView(View.SimpleView.class)
    public Collection<Relationship> findConceptRelationships(@PathVariable("conceptId") Long conceptId){
        Set<Relationship> relationships = new HashSet<>();
        return relationships;
    }

    @ApiOperation(value = "概念映射集", notes = "获取概念映射集", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "conceptId", value = "概念标识符", required = true, paramType = "path", dataType = "long"),
    })
    @GetMapping("/{conceptId}/mappings")
    @JsonView(View.SimpleView.class)
    public Collection<Mapping> findConceptMappings(@PathVariable("conceptId") Long conceptId){
        Set<Mapping> mappings = new HashSet<>();
        return mappings;
    }

    @ApiOperation(value = "概念子集", notes = "获取概念子集", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "conceptId", value = "概念标识符", required = true, paramType = "path", dataType = "long"),

    })
    @GetMapping("/{conceptId}/children")
    @JsonView(View.SimpleView.class)
    public Collection<ConceptMini> findConceptChildren(@PathVariable("conceptId") Long conceptId){
        Set<ConceptMini> list = new HashSet<>();
        return list;
    }

    @ApiOperation(value = "概念超类集", notes = "获取概念超类集", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "conceptId", value = "概念标识符", required = true, paramType = "path", dataType = "long"),

    })
    @GetMapping("/{conceptId}/parents")
    @JsonView(View.SimpleView.class)
    public Collection<ConceptMini> findConceptParents(@PathVariable("conceptId") Long conceptId){
        Set<ConceptMini> list = new HashSet<>();
        return list;
    }

    @ApiOperation(value = "语义标签及概念数量", notes = "获取全部语义标签及对应的概念数量", produces = "application/json")
    @GetMapping(value = "/semantictags")
    @JsonView(value = View.SimpleView.class)
    public Map<String, Long> countSemanticTags() {
        Map<String, Long> map = new HashMap<>();
        return map;
    }
}
