package com.funix.lab.prj321x_asm3_tamndfx27974.repository;


import com.funix.lab.prj321x_asm3_tamndfx27974.entity.Role;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    User findByRole(Role role);

    @Modifying
    @Query("UPDATE User u SET u.isActive = false WHERE u.email = ?1")
    void lockByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.isActive = true WHERE u.email = ?1")
    void unlockByEmail(String email);

}
