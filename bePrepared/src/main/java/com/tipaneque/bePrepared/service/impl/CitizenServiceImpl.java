package com.tipaneque.bePrepared.service.impl;

import com.tipaneque.bePrepared.exception.BadRequestException;
import com.tipaneque.bePrepared.exception.EntityNotFoundException;
import com.tipaneque.bePrepared.model.Citizen;
import com.tipaneque.bePrepared.model.City;
import com.tipaneque.bePrepared.repository.CitizenRepository;
import com.tipaneque.bePrepared.service.CitizenService;
import com.tipaneque.bePrepared.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CitizenServiceImpl implements CitizenService {

    private final LocationService locationService;
    private final CitizenRepository citizenRepository;

    @Override
    @Transactional
    public String createCitizen(Citizen citizen, Long cityId) {
        if(citizenRepository.existsByPhone(citizen.getPhone()))
            throw new BadRequestException("Já existe um cidadão com esse número!");
        City city = locationService.getCityByID(cityId);
        citizen.setCity(city);
        citizen.setVerified(false);
        citizen.setOtp(generateOtp(6));
        var savedCitizen = citizenRepository.save(citizen);

        return "Cidadão registrado com sucesso!\nSeu OTP é " + savedCitizen.getOtp();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Citizen> getAllCitizens() {
        return citizenRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Citizen> getAllCitizensByCityId(Long cityId) {
        return citizenRepository.findAllByCityId(cityId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Citizen> getAllCitizensByProvinceId(Long provinceId) {
        return citizenRepository.findAllByProvinceId(provinceId);
    }

    @Override
    @Transactional(readOnly = true)
    public Citizen getCitizenById(Long id) {
        return citizenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cidadão não encontrado!"));
    }

    @Override
    @Transactional
    public String verifyAccount(String otp) {
        Citizen citizen = citizenRepository.findByOtp(otp).orElseThrow(() -> new EntityNotFoundException("Cidadão não encontrado!"));
        citizen.setVerified(true);
        citizen.setOtp(null);
        citizenRepository.save(citizen);
        return "A tua conta foi verificada com sucesso!";
    }

    private static String generateOtp(int length){
        String otp = "";
        int x;
        char [] chars = new char[length];
        for(int i = 0; i < length; i++){
            Random random = new Random();
            x = random.nextInt(9);
            chars[i] = Integer.toString(x).toCharArray()[0];
        }

        otp = new String(chars);
        return otp.trim();
    }
}
