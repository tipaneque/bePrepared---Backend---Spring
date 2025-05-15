package com.tipaneque.bePrepared.controller;

import com.tipaneque.bePrepared.dto.request.UserRequest;
import com.tipaneque.bePrepared.dto.response.StatsResponse;
import com.tipaneque.bePrepared.dto.response.UserResponse;
import com.tipaneque.bePrepared.mapper.Mapper;
import com.tipaneque.bePrepared.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.el.lang.FunctionMapperImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final Mapper mapper;
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequest request){
        return new ResponseEntity<>(userService.createUser(
                mapper.mapUserRequestToUser(request)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(mapper.mapUserToUserResponse(userService.getUserById(id)));
    }

    @GetMapping("/metrics")
    public ResponseEntity<StatsResponse> getMetrics(){
        return ResponseEntity.ok(userService.getAllStats());
    }

}
