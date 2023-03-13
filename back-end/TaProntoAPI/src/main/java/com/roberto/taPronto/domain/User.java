package com.roberto.taPronto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roberto.taPronto.domain.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "user_account")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "profiles")
    private Set<Integer> profile = new HashSet<>();

    @Column(name = "activated")
    private boolean activated;

    public Set<Role> getProfiles() {
        return profile.stream().map(Role::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(Role role) {
        this.profile.add(role.getCod());
    }

    public boolean hasHole(Role role) {
        return this.profile.stream().anyMatch(integer -> Objects.equals(integer, role.getCod()));
    }
}
