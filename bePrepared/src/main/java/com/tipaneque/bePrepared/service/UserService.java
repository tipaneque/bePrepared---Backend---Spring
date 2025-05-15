package com.tipaneque.bePrepared.service;

import com.tipaneque.bePrepared.dto.response.StatsResponse;
import com.tipaneque.bePrepared.model.User;

public interface UserService {
    String createUser(User user);
    User getUserById(Long id);
    StatsResponse getAllStats();

}
