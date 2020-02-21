package com.udd.udd.ElasticSearchRepository;

import com.udd.udd.modelES.ReviewerES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewerESRepository extends ElasticsearchRepository <ReviewerES,Long> {
}
