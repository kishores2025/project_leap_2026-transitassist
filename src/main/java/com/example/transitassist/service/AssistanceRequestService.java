package com.example.transitassist.service;

import com.example.transitassist.Entity.AssistanceRequest;
import com.example.transitassist.Entity.Helper;
import com.example.transitassist.Entity.User;
import com.example.transitassist.repository.AssistanceRequestRepository;
import com.example.transitassist.repository.HelperRepository;
import com.example.transitassist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistanceRequestService {

    private final AssistanceRequestRepository requestRepository;
    private final UserRepository userRepository;
    private final HelperRepository helperRepository;

    public AssistanceRequestService(
            AssistanceRequestRepository requestRepository,
            UserRepository userRepository,
            HelperRepository helperRepository) {

        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.helperRepository = helperRepository;
    }

    // Create assistance request
    public AssistanceRequest createRequest(
            Long userId,
            AssistanceRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getEndTime().isBefore(request.getStartTime())
                || request.getEndTime().isEqual(request.getStartTime())) {
            throw new RuntimeException("End time must be after start time");
        }

        request.setUser(user);
        request.setStatus("REQUESTED");

        return requestRepository.save(request);
    }

    // Get all requests
    public List<AssistanceRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    // Get request by ID
    public AssistanceRequest getRequestById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Assistance request not found"));
    }

    // Assign helper
    public AssistanceRequest assignHelper(
            Long requestId,
            Long helperId) {

        AssistanceRequest request = getRequestById(requestId);

        Helper helper = helperRepository.findById(helperId)
                .orElseThrow(() ->
                        new RuntimeException("Helper not found"));

        if ("COMPLETED".equals(request.getStatus())) {
            throw new RuntimeException(
                    "Completed request cannot be reassigned");
        }

        if ("CANCELLED".equals(request.getStatus())) {
            throw new RuntimeException(
                    "Cancelled request cannot be reassigned");
        }

        List<AssistanceRequest> overlappingRequests =
                requestRepository
                        .findByHelperIdAndStartTimeLessThanAndEndTimeGreaterThan(
                                helperId,
                                request.getEndTime(),
                                request.getStartTime()
                        );

        if (!overlappingRequests.isEmpty()) {
            throw new RuntimeException(
                    "Helper already has another request at this time");
        }

        request.setHelper(helper);
        request.setStatus("ASSIGNED");

        return requestRepository.save(request);
    }

    // Change request status
    public AssistanceRequest updateStatus(
            Long requestId,
            String status) {

        AssistanceRequest request = getRequestById(requestId);

        if ("COMPLETED".equals(request.getStatus())) {
            throw new RuntimeException(
                    "Completed request status cannot be changed");
        }

        if ("CANCELLED".equals(request.getStatus())) {
            throw new RuntimeException(
                    "Cancelled request status cannot be changed");
        }

        if (!status.equals("REQUESTED")
                && !status.equals("ASSIGNED")
                && !status.equals("COMPLETED")
                && !status.equals("CANCELLED")) {

            throw new RuntimeException("Invalid status");
        }

        request.setStatus(status);

        return requestRepository.save(request);
    }

    // Cancel request
    public AssistanceRequest cancelRequest(Long requestId) {

        AssistanceRequest request = getRequestById(requestId);

        if ("COMPLETED".equals(request.getStatus())) {
            throw new RuntimeException(
                    "Completed request cannot be cancelled");
        }

        request.setStatus("CANCELLED");

        return requestRepository.save(request);
    }

    // Get helper workload
    public List<AssistanceRequest> getHelperWorkload(Long helperId) {

        helperRepository.findById(helperId)
                .orElseThrow(() ->
                        new RuntimeException("Helper not found"));

        return requestRepository.findByHelperId(helperId);
    }
}