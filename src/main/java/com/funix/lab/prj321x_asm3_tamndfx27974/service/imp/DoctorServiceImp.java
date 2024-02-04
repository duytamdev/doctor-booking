package com.funix.lab.prj321x_asm3_tamndfx27974.service.imp;

import com.funix.lab.prj321x_asm3_tamndfx27974.dto.DoctorCreateRequest;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.Doctor;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.Specialization;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.User;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.DoctorRepository;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.RoleRepository;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.SpecializationRepository;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.UserRepository;
import com.funix.lab.prj321x_asm3_tamndfx27974.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImp implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SpecializationRepository specializationRepository;

    @Override
    public Doctor createDoctor(DoctorCreateRequest req) {
        if (emailExists(req.getEmail())) {
            throw new RuntimeException("Email already exists in the system");
        }
        User userInfo = new User();
        userInfo.setName(req.getName());
        userInfo.setEmail(req.getEmail());
        userInfo.setPhone(req.getPhone());
        userInfo.setAddress(req.getAddress());
        userInfo.setRole(roleRepository.findByName("DOCTOR"));
        userInfo.setPassword(passwordEncoder.encode(req.getPassword()));
        userInfo.setActive(true);
        Doctor doctor = new Doctor();
        doctor.setUserInfo(userInfo);
        doctor.setTrainingProcess(req.getTrainingProcess());
        doctor.setIntroduction(req.getIntroduction());
        List<Integer> specializationIds = req.getSpecializationsId().stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Specialization> specializations =
                specializationRepository.findAllById(specializationIds);


        doctor.setSpecializations(specializations);
        doctorRepository.save(doctor);
        return doctor;
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
