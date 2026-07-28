package com.eduegy.backend.repository;

import com.eduegy.backend.model.LiveClassFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LiveClassFileRepository extends JpaRepository<LiveClassFile, Long> {
    List<LiveClassFile> findByLiveClass_IdOrderByUploadedAtDesc(Long liveClassId);
}
