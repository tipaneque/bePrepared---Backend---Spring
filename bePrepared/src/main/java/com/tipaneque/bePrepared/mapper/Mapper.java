package com.tipaneque.bePrepared.mapper;

import com.tipaneque.bePrepared.dto.request.AlertRequest;
import com.tipaneque.bePrepared.dto.request.CitizenRequest;
import com.tipaneque.bePrepared.dto.request.UserRequest;
import com.tipaneque.bePrepared.dto.response.*;
import com.tipaneque.bePrepared.model.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Mapper {
    private final ModelMapper modelMapper;

    public ProvinceResponse mapProvinceToResponseProvince(Province province){
        return modelMapper.map(province, ProvinceResponse.class);
    }

    public List<ProvinceResponse> mapProvincesToProvinceResponseList(List<Province> provinces){
        return provinces.stream().map(this::mapProvinceToResponseProvince).collect(Collectors.toList());
    }
    public CityResponse mapCityToCityResponse(City city){
        return modelMapper.map(city, CityResponse.class);
    }

    public List<CityResponse> mapCitiesToCityResponseList(List<City> list){
        return list.stream().map(this::mapCityToCityResponse).collect(Collectors.toList());
    }

    public Alert mapAlertRequestToModel(AlertRequest alertRequest){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(alertRequest, Alert.class);
    }

    public AlertResponse mapAlertToAlertResponse(Alert alert){
        return AlertResponse.builder()
                .id(alert.getId())
                .title(alert.getTitle())
                .message(alert.getMessage())
                .severity(alert.getSeverity())
                .province(alert.getProvince().getDesignation())
                .city(alert.getCity().getDesignation())
                .isActive(alert.isActive())
                .build();
    }

    public List<AlertResponse> mapAlertToAlertResponseList(List<Alert> list){
        return list.stream().map(this::mapAlertToAlertResponse).collect(Collectors.toList());
    }

    public User mapUserRequestToUser(UserRequest request){
        return modelMapper.map(request, User.class);
    }

    public UserResponse mapUserToUserResponse(User user){
        return modelMapper.map(user, UserResponse.class);
    }

    public Citizen mapCitizenRequestToCitizen(CitizenRequest request){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(request, Citizen.class);
    }

    public CitizenResponse mapCitizenToCitizenResponse(Citizen citizen){
        return CitizenResponse.builder()
                .id(citizen.getId())
                .phone(citizen.getPhone())
                .deviceId(citizen.getDeviceId())
                .verified(citizen.isVerified())
                .city(citizen.getCity().getDesignation())
                .province(citizen.getCity().getProvince().getDesignation())
                .build();
    }

    public List<CitizenResponse> mapCitizenToCitizenResponseList(List<Citizen> list){
        return list.stream().map(this::mapCitizenToCitizenResponse).collect(Collectors.toList());
    }
}
