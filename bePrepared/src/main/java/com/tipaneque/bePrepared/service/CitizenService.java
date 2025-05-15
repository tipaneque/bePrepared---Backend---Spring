package com.tipaneque.bePrepared.service;

import com.tipaneque.bePrepared.model.Citizen;

import java.util.List;

public interface CitizenService {
    String createCitizen(Citizen citizen, Long cityId);
    List<Citizen> getAllCitizens();
    List<Citizen> getAllCitizensByCityId(Long cityId);
    List<Citizen> getAllCitizensByProvinceId(Long provinceId);
    Citizen getCitizenById(Long id);
    String verifyAccount(String otp);
}
