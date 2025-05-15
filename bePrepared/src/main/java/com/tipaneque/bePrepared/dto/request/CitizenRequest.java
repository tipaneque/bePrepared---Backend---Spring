package com.tipaneque.bePrepared.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CitizenRequest {

    @NotBlank
    @Size(min = 9, max = 9)
    private String phone;

    private String deiceId;

    @NotNull
    private Long cityId;
}
