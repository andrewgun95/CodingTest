package com.geekseat.witchsaga.service.person.dto;

import com.geekseat.witchsaga.entity.PersonType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PersonDTO {

    private Long id;

    @NotBlank(message = "Name can't be null or empty")
    private String name;

    @NotNull(message = "Gender can't be null or empty")
    private PersonType gender;

    private BigDecimal ageOfDeath = BigDecimal.ZERO;

    private BigDecimal yearOfDeath = BigDecimal.ZERO;

}
