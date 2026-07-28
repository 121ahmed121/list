package com.eduegy.backend.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "live_class_attendance", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"liveClass_id", "user_id"})
})
public class LiveClassAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private LiveClass liveClass;

    @ManyToOne(optional = false)
    private User user;

    private Instant joinedAt = Instant.now();

    public LiveClassAttendance() {}

    public LiveClassAttendance(LiveClass liveClass, User user) {
        this.liveClass = liveClass;
        this.user = user;
        this.joinedAt = Instant.now();
    }

    public Long getId() { return id; }
    public LiveClass getLiveClass() { return liveClass; }
    public User getUser() { return user; }
    public Instant getJoinedAt() { return joinedAt; }
    public void setJoinedAt(Instant joinedAt) { this.joinedAt = joinedAt; }
}
