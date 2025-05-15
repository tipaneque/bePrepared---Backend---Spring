package com.tipaneque.bePrepared.service;

import com.tipaneque.bePrepared.model.Alert;

import java.util.List;

public interface AlertService {

    String createAlert(Alert alert, Long cityId, Long provinceId);
    List<Alert> getAllAlerts();
    List<Alert> getAllActiveAlerts();
    List<Alert> getAllAlertsByCityId(Long cityId);
    List<Alert> getAllAlertsByProvinceId(Long provinceId);
    Alert getAlertById(Long alertId);
    String activeAlert(Long alertId);

}
