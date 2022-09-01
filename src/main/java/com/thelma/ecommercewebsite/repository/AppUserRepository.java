package com.thelma.ecommercewebsite.repository;

import com.thelma.ecommercewebsite.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findAppUserByEmail(String email);
    Optional<AppUser> findAppUserByEmailAndPassword(String email, String password);
}
