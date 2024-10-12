package com.crio.starter.service;

import java.util.List;
import java.util.Optional;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.repository.MemesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemeServiceImpl implements MemeService{
    @Autowired
    private MemesRepository memesRepository;

    @Override
    public MemeEntity createMeme(MemeEntity memeEntity) {
        Optional<MemeEntity> existingMeme = memesRepository.findByNameAndUrlAndCaption(memeEntity.getName(), memeEntity.getUrl(), memeEntity.getCaption());
        if (existingMeme.isPresent()) {
            throw new RuntimeException("Duplicate meme");
        }
        return memesRepository.save(memeEntity);
    }

    @Override
    public List<MemeEntity> getMemes() {
        Pageable pageable=PageRequest.of(0,100);
        return memesRepository.findAllByOrderByIdDesc(pageable).getContent();
    }

    @Override
    public Optional<MemeEntity> getMemeById(String id) {
        return memesRepository.findById(id);
    }
}