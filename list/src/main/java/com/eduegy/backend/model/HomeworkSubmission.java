package com.eduegy.backend.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "homework_submissions")
public class HomeworkSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Homework homework;

    @ManyToOne(optional = false)
    private User student;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String storedName;

    private Instant submittedAt = Instant.now();

    private Integer grade; // optional
    @Column(length = 2000)
    private String feedback;

    public HomeworkSubmission() {}

    public HomeworkSubmission(Homework homework, User student, String originalName, String storedName) {
        this.homework = homework;
        this.student = student;
        this.originalName = originalName;
        this.storedName = storedName;
        this.submittedAt = Instant.now();
    }

    public Long getId() { return id; }
    public Homework getHomework() { return homework; }
    public User getStudent() { return student; }
    public String getOriginalName() { return originalName; }
    public String getStoredName() { return storedName; }
    public Instant getSubmittedAt() { return submittedAt; }
    public Integer getGrade() { return grade; }
    public void setGrade(Integer grade) { this.grade = grade; }
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}
