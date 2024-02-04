package com.funix.lab.prj321x_asm3_tamndfx27974.repository;

import com.funix.lab.prj321x_asm3_tamndfx27974.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
