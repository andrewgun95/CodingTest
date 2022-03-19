package com.geekseat.witchsaga.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "witchs")
@Data
public class Witch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "intro")
    private String intro;

    @Column(name = "death_sentence")
    private String deathSentence;

    @Column(name = "leave")
    private Boolean leave = false;

}
