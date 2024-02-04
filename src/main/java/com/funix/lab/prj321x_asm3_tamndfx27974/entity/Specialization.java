package com.funix.lab.prj321x_asm3_tamndfx27974.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

// chuyen mon
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "specializations")
@Data
public class Specialization extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String image;
}
