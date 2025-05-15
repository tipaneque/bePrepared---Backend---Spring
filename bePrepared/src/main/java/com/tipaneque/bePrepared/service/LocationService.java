package com.tipaneque.bePrepared.service;

import com.tipaneque.bePrepared.model.City;
import com.tipaneque.bePrepared.model.Province;

import java.util.List;

public interface LocationService {
    List<Province> getAllProvinces();
    List<City> getAllCities();
    List<City> getAllCitiesProvinceByID(Long provinceId);
    Province getProvinceByID(Long provinceId);
    City getCityByID(Long cityId);
}
