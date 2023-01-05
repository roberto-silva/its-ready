package com.roberto.taPronto.dto;

import com.roberto.taPronto.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Integer id;

    @NotEmpty
    private String cep;

    @NotEmpty
    private String city;

    @NotEmpty
    private String district;

    @NotEmpty
    private String street;

    @NotEmpty
    private String referencePoint;


    public AddressDTO(Address address) {
        this.id = address.getId();
        this.cep = address.getCep();
        this.city = address.getCity();
        this.district = address.getDistrict();
        this.street = address.getStreet();
        this.referencePoint = address.getReferencePoint();
    }
}
