package com.funix.lab.prj321x_asm3_tamndfx27974.repository;

import com.funix.lab.prj321x_asm3_tamndfx27974.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
    @Query("SELECT c FROM Clinic c WHERE c.name LIKE %:keyword% OR c.address LIKE %:keyword% OR c.phone LIKE %:keyword%")
    List<Clinic> searchClinic(@Param("keyword") String keyword);

}
