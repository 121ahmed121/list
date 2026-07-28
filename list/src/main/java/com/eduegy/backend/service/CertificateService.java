package com.eduegy.backend.service;

import com.eduegy.backend.model.Certificate;
import com.eduegy.backend.repository.CertificateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CertificateService {

    private final CertificateRepository certificateRepository;

    public CertificateService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    public List<Certificate> getMyCertificates() {
        return certificateRepository.findAll();
    }

    public Certificate generate(Long courseId) {
        Certificate certificate = new Certificate();
        certificate.setCourseId(courseId);
        certificate.setIssuedDate(LocalDate.now());
        return certificateRepository.save(certificate);
    }
}
