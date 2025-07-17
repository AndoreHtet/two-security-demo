package com.htet.data.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
@Entity
@NoArgsConstructor
@Table(name = "role_access")
public class RoleAccess extends BaseField implements Serializable {

    @Serial
    private static final long serialVersionUID = -1662824006817526462L;


    @Column(name = "name")
    private String name;
    @Column(name = "url",nullable = false)
    private String url;
    @Column(nullable = false, name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id",nullable = false)
    private UserRole role;




}
