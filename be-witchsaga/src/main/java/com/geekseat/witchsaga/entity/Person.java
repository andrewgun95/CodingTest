package com.geekseat.witchsaga.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "persons")
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private PersonType gender;

    @Column(name = "age_of_death")
    private BigDecimal ageOfDeath = BigDecimal.ZERO;

    @Column(name = "year_of_death")
    private BigDecimal yearOfDeath = BigDecimal.ZERO;

}
