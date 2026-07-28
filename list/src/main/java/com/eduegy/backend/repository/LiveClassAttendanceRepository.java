package com.eduegy.backend.repository;

import com.eduegy.backend.model.LiveClassAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LiveClassAttendanceRepository extends JpaRepository<LiveClassAttendance, Long> {
    Optional<LiveClassAttendance> findByLiveClass_IdAndUser_Id(Long liveClassId, Long userId);
}
