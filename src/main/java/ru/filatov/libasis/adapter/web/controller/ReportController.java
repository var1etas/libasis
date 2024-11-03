package ru.filatov.libasis.adapter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.filatov.libasis.entity.ReportEntity;
import ru.filatov.libasis.service.ReportService;

import javax.swing.text.html.HTML;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/reports")
public class ReportController {
    ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/generate")
    public Integer generateReport() {
        return reportService.startCreating();
    }

    @GetMapping("{report_id}")
    public String getReport(@PathVariable("report_id") Integer report_id) {
        return reportService.getReport(report_id).toString();
    }
}
