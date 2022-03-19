package com.geekseat.witchsaga.service.witch.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class KillingDTO {

    @NotNull(message = "Witch id can't be empty or null")
    private Long witchId;

    @NotNull(message = "Initial year can't be empty or null")
    @Positive(message = "Initial year can't be negative or zero")
    private int initialYear;

    private String killingCycle = "0 0/1 * * * ?";

}
