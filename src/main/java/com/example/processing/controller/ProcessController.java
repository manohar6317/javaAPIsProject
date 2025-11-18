package com.example.processing.controller;

import com.example.processing.dto.ProcessResponse;
import com.example.processing.service.ProcessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessController {

    private final ProcessService processService;

    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @GetMapping("/process")
    public ResponseEntity<ProcessResponse> processItems(
        @RequestParam(name = "top_n", defaultValue = "3") int topN
    ) {
        ProcessResponse response = processService.processItems(topN);
        return ResponseEntity.ok(response);
    }
}
