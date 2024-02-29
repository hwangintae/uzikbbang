package com.khpl.uzikbbang.domain.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.khpl.uzikbbang.api.controller.auth.Session;
import com.khpl.uzikbbang.domain.Level;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Session> sessions = new ArrayList<>();

    private String refreshToken;

    private LocalDateTime registDt;

    private boolean useAt;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Builder
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.registDt = LocalDateTime.now();
        this.useAt = true;
        this.level = Level.BEGINNER;
    }

    public Session addSession() {
        Session session = Session.builder()
            .user(this)
        .build();

        sessions.add(session);

        return session;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateUseAt(boolean useAt) {
        this.useAt = useAt;
    }
}
