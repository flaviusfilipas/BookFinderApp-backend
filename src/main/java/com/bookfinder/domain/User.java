package com.bookfinder.domain;

import lombok.Data;

import javax.persistence.*;

@Table(name = "user")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
}
