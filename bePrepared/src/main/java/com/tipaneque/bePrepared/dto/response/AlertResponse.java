package com.tipaneque.bePrepared.dto.response;

import com.tipaneque.bePrepared.model.enums.Severity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlertResponse {
    private Long id;
    private String title;
    private String message;
    private Severity severity;
    private String province;
    private String city;
    private boolean isActive;
}
