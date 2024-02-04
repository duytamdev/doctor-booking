package com.funix.lab.prj321x_asm3_tamndfx27974.repository;

import com.funix.lab.prj321x_asm3_tamndfx27974.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    PasswordResetToken findByToken(String token);

    @Query("SELECT prt FROM PasswordResetToken prt WHERE prt.user.email = ?1")
    Optional<PasswordResetToken> findByUserEmail(String email);

    void deleteById(int id);
}
