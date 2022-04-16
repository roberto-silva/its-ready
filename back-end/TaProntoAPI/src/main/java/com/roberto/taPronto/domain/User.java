package com.roberto.taPronto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roberto.taPronto.domain.enums.Profile;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "user_account", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cpf", "email"})
})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
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

    public Set<Profile> getProfiles() {
        return profile.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        this.profile.add(profile.getCod());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public boolean hasHole(Profile profile){
        return this.profile.stream().anyMatch(integer -> Objects.equals(integer, profile.getCod()));
    }
}
