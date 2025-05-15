package com.tipaneque.bePrepared.controller;

import com.tipaneque.bePrepared.dto.response.CityResponse;
import com.tipaneque.bePrepared.dto.response.ProvinceResponse;
import com.tipaneque.bePrepared.mapper.Mapper;
import com.tipaneque.bePrepared.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/locations")
public class LocationController {

    private final Mapper mapper;
    private final LocationService locationService;

    @GetMapping("/provinces")
    public ResponseEntity<List<ProvinceResponse>> getAllProvinces(){
        return ResponseEntity.ok(mapper.mapProvincesToProvinceResponseList(locationService.getAllProvinces()));
    }

    @GetMapping("/province")
    public ResponseEntity<ProvinceResponse> getProvinceByID(@RequestParam Long id){
        return ResponseEntity.ok(mapper.mapProvinceToResponseProvince(locationService.getProvinceByID(id)));
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityResponse>> getAllCities(){
        return ResponseEntity.ok(mapper.mapCitiesToCityResponseList(locationService.getAllCities()));
    }

    @GetMapping("/cities/{provinceId}")
    public ResponseEntity<List<CityResponse>> getCitiesByProvinceID(@PathVariable Long provinceId){
        return ResponseEntity.ok(mapper.mapCitiesToCityResponseList(locationService.getAllCitiesProvinceByID(provinceId)));
    }

    @GetMapping("/city")
    public ResponseEntity<CityResponse> getCityByID(@RequestParam Long id){
        return ResponseEntity.ok(mapper.mapCityToCityResponse(locationService.getCityByID(id)));
    }
}
