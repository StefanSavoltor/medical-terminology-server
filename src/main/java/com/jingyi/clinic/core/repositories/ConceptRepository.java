package com.jingyi.clinic.core.repositories;

import com.jingyi.clinic.core.domain.Concept;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ConceptRepository extends ElasticsearchRepository<Concept,String> {
}
