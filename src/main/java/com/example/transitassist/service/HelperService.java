package com.example.transitassist.service;

import com.example.transitassist.Entity.Helper;
import com.example.transitassist.repository.HelperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelperService {

    private final HelperRepository helperRepository;

    public HelperService(HelperRepository helperRepository) {
        this.helperRepository = helperRepository;
    }

    // Create helper
    public Helper createHelper(Helper helper) {
        return helperRepository.save(helper);
    }

    // Get all helpers
    public List<Helper> getAllHelpers() {
        return helperRepository.findAll();
    }

    // Get helper by ID
    public Helper getHelperById(Long id) {
        return helperRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Helper not found"));
    }
}