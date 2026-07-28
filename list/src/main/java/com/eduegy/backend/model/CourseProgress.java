package com.eduegy.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "course_progress")
public class CourseProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private int completedVideos;
    private int totalVideos;

    private double progressPercentage;

    private boolean completed;

    /* ================= Getters & Setters ================= */

    public Long getId() { return id; }

    public User getUser() { return user; }

    public Course getCourse() { return course; }

    public int getCompletedVideos() { return completedVideos; }

    public int getTotalVideos() { return totalVideos; }

    public double getProgressPercentage() { return progressPercentage; }

    public boolean isCompleted() { return completed; }

    public void setUser(User user) { this.user = user; }

    public void setCourse(Course course) { this.course = course; }

    public void setCompletedVideos(int completedVideos) {
        this.completedVideos = completedVideos;
    }

    public void setTotalVideos(int totalVideos) {
        this.totalVideos = totalVideos;
    }

    public void setProgressPercentage(double progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
