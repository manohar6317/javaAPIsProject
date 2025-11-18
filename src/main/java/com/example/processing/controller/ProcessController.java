package com.example.processing.controller;

import com.example.processing.dto.ProcessResponse;
import com.example.processing.service.ProcessService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Min;

@RestController
@Validated
public class ProcessController {

    private final ProcessService processService;

    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @GetMapping("/process")
    public ResponseEntity<ProcessResponse> processItems(
        @RequestParam(name = "top_n", defaultValue = "3") @Min(value = 1, message = "top_n must be a positive integer") int topN
    ) {
        ProcessResponse response = processService.processItems(topN);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the Item Processing Service! The primary endpoint is at /process.";
    }
}
