package com.roberto.taPronto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDTO implements Serializable {

    private String username;

    private String password;
}
