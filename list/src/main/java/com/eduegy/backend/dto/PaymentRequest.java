package com.eduegy.backend.dto;

public class PaymentRequest {

    private Long userId;
    private Long courseId;
    private Double amount;

    public PaymentRequest() {
    }

    public PaymentRequest(Long userId, Long courseId, Double amount) {
        this.userId = userId;
        this.courseId = courseId;
        this.amount = amount;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
