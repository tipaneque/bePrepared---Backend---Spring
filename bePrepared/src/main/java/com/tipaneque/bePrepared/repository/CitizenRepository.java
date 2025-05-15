package com.tipaneque.bePrepared.repository;

import com.tipaneque.bePrepared.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    boolean existsByPhone(String phone);
    Optional<Citizen> findByOtp(String otp);
    List<Citizen> findAllByCityId(Long cityId);
    List<Citizen> findAllByProvinceId(Long provinceId);
}
