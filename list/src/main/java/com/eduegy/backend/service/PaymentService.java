package com.eduegy.backend.service;

import com.eduegy.backend.model.Payment;
import com.eduegy.backend.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(Long courseId) {
        Payment payment = new Payment();
        payment.setCourseId(courseId);
        payment.setAmount(100.0); // dummy price
        payment.setStatus("PAID");
        payment.setCreatedAt(LocalDateTime.now());

        return paymentRepository.save(payment);
    }
}
