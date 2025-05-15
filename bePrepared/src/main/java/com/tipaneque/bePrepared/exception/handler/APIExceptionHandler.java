package com.tipaneque.bePrepared.exception.handler;

import com.tipaneque.bePrepared.exception.BadRequestException;
import com.tipaneque.bePrepared.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<StandardErrorResponse.ValidationError> validationErrorList = new ArrayList<>();
        for(ObjectError objectError : ex.getBindingResult().getAllErrors()){
            String name = ((FieldError) objectError).getField();
            String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
            validationErrorList.add(new StandardErrorResponse.ValidationError(name, message));
        }

        StandardErrorResponse response = new StandardErrorResponse();
        response.setErrorCode(httpStatus.value());
        response.setStatus(httpStatus);
        response.setTimeStamp(OffsetTime.now());
        response.setErrorTitle("ERRO DE VALIDAÇÃO DE DADOS");
        response.setErrorPath(request.getContextPath());
        response.setFields(validationErrorList);
        return super.handleExceptionInternal(ex,response, headers, httpStatus, request);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardErrorResponse response = new StandardErrorResponse();
        response.setErrorCode(status.value());
        response.setStatus(status);
        response.setTimeStamp(OffsetTime.now());
        response.setErrorTitle(ex.getMessage());
        response.setErrorPath(request.getContextPath());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(BadRequestException ex, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardErrorResponse response = new StandardErrorResponse();
        response.setErrorCode(status.value());
        response.setStatus(status);
        response.setTimeStamp(OffsetTime.now());
        response.setErrorTitle(ex.getMessage());
        response.setErrorPath(request.getContextPath());
        return new ResponseEntity<>(response, status);
    }
}
