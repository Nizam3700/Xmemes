package com.crio.starter.controller;

import java.util.List;
import java.util.Optional;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.service.MemeService;
import com.fasterxml.jackson.core.sym.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemeController {
    @Autowired
    private MemeService memeService;

    @PostMapping("/memes")
    public ResponseEntity<?> createMeme( @RequestBody MemeEntity memeEntity) {
         try {
        if (memeEntity.getName() == null || memeEntity.getName().trim().isEmpty() ||
            memeEntity.getUrl() == null || memeEntity.getUrl().trim().isEmpty() ||
            memeEntity.getCaption() == null || memeEntity.getCaption().trim().isEmpty()) {
            throw new RuntimeException("Name, URL, and Caption are required");
        }
        
        MemeEntity savedMeme = memeService.createMeme(memeEntity);
        return new ResponseEntity<>(savedMeme, HttpStatus.CREATED);
    } catch (RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    }

    @GetMapping("/memes")
    public List<MemeEntity> getMemes() {
        return memeService.getMemes();
    }

    @GetMapping("/memes/{id}")
    public ResponseEntity<?> getMemeById(@PathVariable String id) {
        Optional<MemeEntity> meme = memeService.getMemeById(id);
        if (meme.isPresent()) {
            return new ResponseEntity<>(meme.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Meme not found", HttpStatus.NOT_FOUND);
        }
    }
}
