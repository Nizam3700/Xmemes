package com.crio.starter.service;

import java.util.List;
import java.util.Optional;
import com.crio.starter.data.MemeEntity;

public interface MemeService {
    MemeEntity createMeme(MemeEntity memeEntity);
    List<MemeEntity> getMemes();
    Optional<MemeEntity> getMemeById(String id);
}