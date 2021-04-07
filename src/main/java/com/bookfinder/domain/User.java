package com.bookfinder.domain;

import lombok.Data;

import javax.persistence.*;

@Table(name = "user")
@Entity
@Data
public class User {
    @Id
    private String id;
    private String email;
}
