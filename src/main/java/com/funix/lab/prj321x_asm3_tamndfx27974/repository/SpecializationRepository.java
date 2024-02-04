package com.funix.lab.prj321x_asm3_tamndfx27974.repository;

import com.funix.lab.prj321x_asm3_tamndfx27974.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
    @Query("SELECT s FROM Specialization s WHERE s.name LIKE %:keyword% OR s.description LIKE %:keyword%")
    List<Specialization> searchSpecialization(@Param("keyword") String keyword);

    Optional<Specialization> searchSpecializationsByName(String name);

}
