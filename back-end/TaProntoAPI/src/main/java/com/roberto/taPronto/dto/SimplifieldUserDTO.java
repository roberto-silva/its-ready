package com.roberto.taPronto.dto;

import com.roberto.taPronto.repository.projection.SimplifieldUserProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimplifieldUserDTO {
    private Long id;

    @NotEmpty(message = "The field is mandatory.")
    @Size(min = 2, max = 100, message = "User name must be between 2 and 100 characters long.")
    private String name;

}
