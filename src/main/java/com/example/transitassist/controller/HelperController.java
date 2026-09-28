package com.example.transitassist.controller;

import com.example.transitassist.Entity.Helper;
import com.example.transitassist.Entity.AssistanceRequest;
import com.example.transitassist.service.HelperService;
import com.example.transitassist.service.AssistanceRequestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/helpers")
public class HelperController {

    private final HelperService helperService;
    private final AssistanceRequestService requestService;

    public HelperController(
            HelperService helperService,
            AssistanceRequestService requestService) {

        this.helperService = helperService;
        this.requestService = requestService;
    }

    // Create helper
    @PostMapping
    public ResponseEntity<Helper> createHelper(
            @Valid @RequestBody Helper helper) {

        return ResponseEntity.ok(
                helperService.createHelper(helper));
    }

    // Get all helpers
    @GetMapping
    public ResponseEntity<List<Helper>> getAllHelpers() {

        return ResponseEntity.ok(
                helperService.getAllHelpers());
    }

    // Get helper by ID
    @GetMapping("/{id}")
    public ResponseEntity<Helper> getHelperById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                helperService.getHelperById(id));
    }

    // View helper workload
    @GetMapping("/{id}/requests")
    public ResponseEntity<List<AssistanceRequest>> getHelperWorkload(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                requestService.getHelperWorkload(id));
    }
}