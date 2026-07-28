package com.eduegy.backend.dto;

public class ProgressRequest {

    private Long userId;
    private Long courseId;
    private Integer progress; // percentage 0–100

    public ProgressRequest() {
    }

    public ProgressRequest(Long userId, Long courseId, Integer progress) {
        this.userId = userId;
        this.courseId = courseId;
        this.progress = progress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}
