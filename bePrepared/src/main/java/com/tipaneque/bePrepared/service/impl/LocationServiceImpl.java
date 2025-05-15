package com.tipaneque.bePrepared.service.impl;

import com.tipaneque.bePrepared.exception.EntityNotFoundException;
import com.tipaneque.bePrepared.model.City;
import com.tipaneque.bePrepared.model.Province;
import com.tipaneque.bePrepared.repository.CityRepository;
import com.tipaneque.bePrepared.repository.ProvinceRepository;
import com.tipaneque.bePrepared.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final CityRepository cityRepository;
    private final ProvinceRepository provinceRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> getAllCitiesProvinceByID(Long provinceId) {
        return cityRepository.findAllByProvinceId(provinceId);
    }

    @Override
    @Transactional(readOnly = true)
    public Province getProvinceByID(Long provinceId) {
        return provinceRepository.findById(provinceId).orElseThrow(() ->
                new EntityNotFoundException("A provincia nao foi encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public City getCityByID(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(() ->
                new EntityNotFoundException("O distrito nao foi encontrado"));
    }
}
