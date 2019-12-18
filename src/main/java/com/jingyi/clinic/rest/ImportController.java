package com.jingyi.clinic.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.jingyi.clinic.core.domain.Person;
import com.jingyi.clinic.core.domain.Relationship;
import com.jingyi.clinic.core.repositories.PersonRepository;
import com.jingyi.clinic.core.service.ImportService;
import com.jingyi.clinic.core.view.View;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "Import", description = "-")
@RequestMapping(value = "/import", produces = "application/json")
public class ImportController {
    @Autowired
    private ImportService importService;

    @ApiOperation(value = "术语导入页面", notes = "术语导入页面")
    @GetMapping("/")
    @JsonView(View.SimpleView.class)
    public String fetchImportPage(){
        return "/import/index";
    }

    @ApiOperation(value = "导入术语信息", notes = "导入术语集", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tag", value = "导入的是概念，术语还是关系等", required = true, paramType = "body", dataType = "string"),
            @ApiImplicitParam(name = "importFile", value = "文本文件", required = true, paramType = "query", dataType = "MultipartFile"),
    })
    @PostMapping("/loadFromLocal")
    @ResponseBody
    @JsonView(View.SimpleView.class)
    public Map<String,String> importTerminologyFromFile(@RequestParam("tag") String tag, @RequestParam("file") MultipartFile file) throws IOException {
        Map<String,String> map = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line="";
        String[] arrs=null;
        List<Person> personList = new ArrayList<>();
        try {
            while ((line = br.readLine()) != null) {
                arrs = line.split("\t");
                if (arrs.length == 3) {
                    Person p = new Person(Long.valueOf(arrs[0]), arrs[1], Integer.valueOf(arrs[2]));
                    personList.add(p);
                    importService.savePerson(p);
                }

                System.out.println(arrs[0] + " : " + arrs[1] + " : " + arrs[2]);
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
