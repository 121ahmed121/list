package com.eduegy.backend.controller;

import com.eduegy.backend.model.Certificate;
import com.eduegy.backend.service.CertificateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
@CrossOrigin
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    public List<Certificate> getMyCertificates() {
        return certificateService.getMyCertificates();
    }

    @PostMapping("/{courseId}")
    public Certificate generateCertificate(@PathVariable Long courseId) {
        return certificateService.generate(courseId);
    }
}
