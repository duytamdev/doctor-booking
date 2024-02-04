package com.funix.lab.prj321x_asm3_tamndfx27974.repository;

import com.funix.lab.prj321x_asm3_tamndfx27974.entity.AppointmentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentScheduleRepository extends JpaRepository<AppointmentSchedule, Integer> {

    @Query("SELECT a FROM AppointmentSchedule a WHERE a.doctor.id = ?1")
    List<AppointmentSchedule> findByDoctorId(int doctorId);

    @Query("SELECT a FROM AppointmentSchedule a WHERE a.user.id = ?1")
    List<AppointmentSchedule> findByUserId(int userId);
}
