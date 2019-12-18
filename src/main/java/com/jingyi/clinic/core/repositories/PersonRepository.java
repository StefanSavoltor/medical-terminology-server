package com.jingyi.clinic.core.repositories;

import com.jingyi.clinic.core.domain.Concept;
import com.jingyi.clinic.core.domain.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonRepository extends ElasticsearchRepository<Person,String> {
}
