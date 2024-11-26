package ru.filatov.libasis.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "reports")
public class ReportEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String report;
    @Column(name = "creation_status")
    private ReportStatus creationStatus;

    public ReportEntity(Integer id, String report, ReportStatus creationStatus) {
        this.id = id;
        this.report = report;
        this.creationStatus = creationStatus;
    }

    public ReportEntity(String report, ReportStatus creationStatus) {
        this.report = report;
        this.creationStatus = creationStatus;
    }

    public ReportEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public String getReport() {
        return report;
    }

    public ReportStatus getCreationStatus() {
        return creationStatus;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void setCreationStatus(ReportStatus creationStatus) {
        this.creationStatus = creationStatus;
    }

    @Override
    public String toString() {
        return report;
    }
}
