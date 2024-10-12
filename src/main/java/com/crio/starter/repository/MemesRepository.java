package com.crio.starter.repository;
import java.util.Optional;
import com.crio.starter.data.MemeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemesRepository extends MongoRepository<MemeEntity,String> {
    Optional<MemeEntity> findByNameAndUrlAndCaption(String name, String url, String caption);
    Page<MemeEntity> findAllByOrderByIdDesc(Pageable pagable);
}