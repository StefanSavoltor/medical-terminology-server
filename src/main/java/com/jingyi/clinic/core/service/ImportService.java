package com.jingyi.clinic.core.service;

import com.jingyi.clinic.core.domain.Concept;
import com.jingyi.clinic.core.domain.Person;
import com.jingyi.clinic.core.domain.TerminologyComponent;
import com.jingyi.clinic.core.repositories.ConceptRepository;
import com.jingyi.clinic.core.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImportService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ConceptRepository conceptRepository;

    public void savePerson(Person person){
        personRepository.save(person);
    }

    private void batchInsertPerson(List<Person> persons){
        personRepository.saveAll(persons);
    }

    private void batchInsertConcept(List<Concept> concepts){
        conceptRepository.saveAll(concepts);
    }

    public void batchInsert(String tag,List terminologyComponents){
        if(tag.equals("person")){
           this.batchInsertPerson((List<Person>)terminologyComponents);
        }else if(tag.equals("concept")){
            this.batchInsertConcept((List<Concept>)terminologyComponents);
        }
//        personRepository.saveAll(terminologyComponents);
    }

    public void importTerminologyFromFile(String tag, MultipartFile file) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line="";
        String[] arr;
        List terminologyComponents = new ArrayList<>();
        try {
            while ((line = br.readLine()) != null) {
                arr = line.split("\t");
                if (tag.equals("person") && arr.length == 3) {
                    Person p = new Person(Long.valueOf(arr[0]), arr[1], Integer.valueOf(arr[2]));
                    terminologyComponents.add(p);

                }else if(tag.equals("concept") && arr.length == 4){
                    Concept concept = new Concept(Long.valueOf(arr[0]),new Date(arr[1]),Boolean.valueOf(arr[2]),arr[3]);
                    terminologyComponents.add(concept);
                }
                if(terminologyComponents.size() == 2){
                    logger.info("import  batch insert.");
                    this.batchInsert(tag,terminologyComponents);
                    terminologyComponents.clear();
                }
                System.out.println(arr[0] + " : " + arr[1] + " : " + arr[2]);
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
