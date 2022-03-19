package com.geekseat.witchsaga.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "village_infos")
@Data
public class VillageInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "date")
    private Date date;

}
