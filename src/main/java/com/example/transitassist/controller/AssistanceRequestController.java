package com.example.transitassist.controller;

import com.example.transitassist.Entity.AssistanceRequest;
import com.example.transitassist.service.AssistanceRequestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class AssistanceRequestController {

    private final AssistanceRequestService requestService;

    public AssistanceRequestController(
            AssistanceRequestService requestService) {
        this.requestService = requestService;
    }

    // Create assistance request
    @PostMapping("/user/{userId}")
    public ResponseEntity<AssistanceRequest> createRequest(
            @PathVariable Long userId,
            @Valid @RequestBody AssistanceRequest request) {

        return ResponseEntity.ok(
                requestService.createRequest(userId, request));
    }

    // Get all requests
    @GetMapping
    public ResponseEntity<List<AssistanceRequest>> getAllRequests() {

        return ResponseEntity.ok(
                requestService.getAllRequests());
    }

    // Get request by ID
    @GetMapping("/{id}")
    public ResponseEntity<AssistanceRequest> getRequestById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                requestService.getRequestById(id));
    }

    // Assign helper
    @PutMapping("/{requestId}/assign/{helperId}")
    public ResponseEntity<AssistanceRequest> assignHelper(
            @PathVariable Long requestId,
            @PathVariable Long helperId) {

        return ResponseEntity.ok(
                requestService.assignHelper(
                        requestId, helperId));
    }

    // Update status
    @PutMapping("/{requestId}/status")
    public ResponseEntity<AssistanceRequest> updateStatus(
            @PathVariable Long requestId,
            @RequestParam String status) {

        return ResponseEntity.ok(
                requestService.updateStatus(
                        requestId, status));
    }

    // Cancel request
    @DeleteMapping("/{id}")
    public ResponseEntity<AssistanceRequest> cancelRequest(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                requestService.cancelRequest(id));
    }
}