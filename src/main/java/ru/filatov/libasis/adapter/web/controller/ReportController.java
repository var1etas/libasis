package ru.filatov.libasis.adapter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.filatov.libasis.entity.ReportEntity;
import ru.filatov.libasis.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {
    ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/generate")
    public ResponseEntity<Object> generateReport() {
        return new ResponseEntity<>(reportService.startCreating(), HttpStatus.CREATED);
    }

    @GetMapping("{report_id}")
    public ResponseEntity<String> getReport(@PathVariable("report_id") Integer reportId) {
        ReportEntity reportEntity = reportService.getReport(reportId);
        return new ResponseEntity<>(reportEntity.toString(), HttpStatus.FOUND);
    }
}
