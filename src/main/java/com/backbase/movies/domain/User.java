package com.backbase.movies.domain;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;
}
