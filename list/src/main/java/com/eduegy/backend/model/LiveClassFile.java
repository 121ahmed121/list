package com.eduegy.backend.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "live_class_files")
public class LiveClassFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private LiveClass liveClass;

    @ManyToOne(optional = false)
    private User uploader;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String storedName;

    private Instant uploadedAt = Instant.now();

    public LiveClassFile() {}

    public LiveClassFile(LiveClass liveClass, User uploader, String originalName, String storedName) {
        this.liveClass = liveClass;
        this.uploader = uploader;
        this.originalName = originalName;
        this.storedName = storedName;
        this.uploadedAt = Instant.now();
    }

    public Long getId() { return id; }
    public LiveClass getLiveClass() { return liveClass; }
    public User getUploader() { return uploader; }
    public String getOriginalName() { return originalName; }
    public String getStoredName() { return storedName; }
    public Instant getUploadedAt() { return uploadedAt; }
}
