package com.htet.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -4601122482532544948L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "phone_no",nullable = false,unique = true)
    private String phoneNo;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "login_name",unique = true)
    private String userId;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "status")
    private Integer status;
    @Column(name = "locked")
    private boolean locked;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private User updatedBy;
    @Column(name = "created_time", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_time")
    private LocalDateTime updatedAt;

    @JoinColumn(name = "user_role_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserRole role;


}
