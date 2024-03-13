package com.funix.lab.prj321x_asm3_tamndfx27974;

import com.funix.lab.prj321x_asm3_tamndfx27974.entity.Role;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.User;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.RoleRepository;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Prj321xAsm3TamndFx27974Application implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(Prj321xAsm3TamndFx27974Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = roleRepository.findByName("ADMIN");
        User admin = userRepository.findByRole(adminRole);
        if (admin == null) {
            User admin1 = new User();
            admin1.setEmail("admin@gmail.com");
            admin1.setName("Admin");
            admin1.setPhone("0123456789");
            admin1.setAddress("Ha Noi");
            admin1.setActive(true);
            admin1.setRole(adminRole);
            admin1.setPassword(new BCryptPasswordEncoder().encode("1234"));
            userRepository.save(admin1);
        }
    }
}
