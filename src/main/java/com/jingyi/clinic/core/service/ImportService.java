package com.jingyi.clinic.core.service;

import com.jingyi.clinic.core.domain.Person;
import com.jingyi.clinic.core.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

@Service
public class ImportService {
    @Autowired
    private PersonRepository personRepository;

    public void savePerson(Person person){
        personRepository.save(person);
    }

}
