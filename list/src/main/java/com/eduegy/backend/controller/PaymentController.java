package com.eduegy.backend.controller;

import com.eduegy.backend.model.Payment;
import com.eduegy.backend.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{courseId}")
    public Payment createPayment(@PathVariable Long courseId) {
        return paymentService.createPayment(courseId);
    }
}
