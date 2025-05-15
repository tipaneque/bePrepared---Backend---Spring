package com.tipaneque.bePrepared.service.impl;

import com.tipaneque.bePrepared.exception.EntityNotFoundException;
import com.tipaneque.bePrepared.model.Alert;
import com.tipaneque.bePrepared.model.City;
import com.tipaneque.bePrepared.model.Province;
import com.tipaneque.bePrepared.repository.AlertRepository;
import com.tipaneque.bePrepared.service.AlertService;
import com.tipaneque.bePrepared.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;
    private final LocationService locationService;

    @Override
    @Transactional
    public String createAlert(Alert alert, Long cityId, Long provinceId) {
        City city = locationService.getCityByID(cityId);
        Province province = locationService.getProvinceByID(provinceId);
        alert.setActive(false);
        alert.setCity(city);
        alert.setProvince(province);
        alertRepository.save(alert);

        return "Alerta criado com sucesso!";
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alert> getAllAlerts() {

        return alertRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alert> getAllActiveAlerts() {
        return alertRepository.findAllByActive(true);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alert> getAllAlertsByCityId(Long cityId) {
        return alertRepository.findAllByActiveAndCityId(true, cityId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alert> getAllAlertsByProvinceId(Long provinceId) {
        return alertRepository.findAllByActiveAndProvinceId(true, provinceId);
    }

    @Override
    @Transactional(readOnly = true)
    public Alert getAlertById(Long alertId) {
        return alertRepository.findById(alertId).orElseThrow(() -> new EntityNotFoundException("Alerta nao encontrado!"));
    }

    @Override
    @Transactional
    public String activeAlert(Long alertId) {
        Alert alert = getAlertById(alertId);
        alert.setActive(true);
        alertRepository.save(alert);
        return "Perigo, proteja-se!";
    }
}
