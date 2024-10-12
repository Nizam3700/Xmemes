package com.crio.starter.repository;

import java.util.List;
import com.crio.starter.data.MemeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

public class MemesRepositoryImpl {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<MemeEntity> getLatestMemes(int limit) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "createdAt"));
        return mongoTemplate.find(query.limit(limit), MemeEntity.class);
    }
}
