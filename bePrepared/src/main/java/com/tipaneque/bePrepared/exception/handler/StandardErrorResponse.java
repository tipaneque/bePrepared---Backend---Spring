package com.tipaneque.bePrepared.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.OffsetTime;
import java.util.List;

@Data
@NoArgsConstructor
public class StandardErrorResponse {
    private int errorCode;
    private HttpStatus status;
    private OffsetTime timeStamp;
    private String errorTitle;
    private String errorPath;
    private List<ValidationError> fields;

    @Data
    @AllArgsConstructor
    public static class ValidationError{
        private String field;
        private String message;
    }
}
