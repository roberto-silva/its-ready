package com.roberto.taPronto.model;

import com.roberto.taPronto.dto.AddressDTO;
import com.roberto.taPronto.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="cep")
    private String cep;

    @Column(name="city")
    private String city;

    @Column(name="district")
    private String district;

    @Column(name="street")
    private String street;

    @Column(name="reference_point")
    private String referencePoint;

    public Address(AddressDTO address) {
        this.id = address.getId();
        this.cep = address.getCep();
        this.city = address.getCity();
        this.district = address.getDistrict();
        this.street = address.getStreet();
        this.referencePoint = address.getReferencePoint();
    }
}
