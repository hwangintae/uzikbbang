package com.khpl.uzikbbang.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class UzikUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Session> sessions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UzikOrder> orders = new ArrayList<>();

    private String refreshToken;

    private LocalDateTime registDt;

    @Builder
    public UzikUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.registDt = LocalDateTime.now();
    }

    public Session addSession() {
        Session session = Session.builder()
            .user(this)
        .build();

        sessions.add(session);

        return session;
    }

    public UzikOrder addOrder() {
        UzikOrder order = new UzikOrder(this);

        orders.add(order);

        return order;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
