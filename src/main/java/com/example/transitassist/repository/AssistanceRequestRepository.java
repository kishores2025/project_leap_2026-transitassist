package com.example.transitassist.repository;

import com.example.transitassist.Entity.AssistanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AssistanceRequestRepository
        extends JpaRepository<AssistanceRequest, Long> {

    List<AssistanceRequest> findByHelperId(Long helperId);

    List<AssistanceRequest> findByHelperIdAndStartTimeLessThanAndEndTimeGreaterThan(
            Long helperId,
            LocalDateTime endTime,
            LocalDateTime startTime
    );
}