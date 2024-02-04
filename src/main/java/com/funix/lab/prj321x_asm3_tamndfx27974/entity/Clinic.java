package com.funix.lab.prj321x_asm3_tamndfx27974.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

// co so y te
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clinics")
@Data
public class Clinic extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String phone;
    private String introductionHTML;
    private String introductionMarkdown;
    private String image;
    private String description;
}
