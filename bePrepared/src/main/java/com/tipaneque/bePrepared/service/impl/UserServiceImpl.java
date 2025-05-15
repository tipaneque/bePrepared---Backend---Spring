package com.tipaneque.bePrepared.service.impl;

import com.tipaneque.bePrepared.dto.response.StatsResponse;
import com.tipaneque.bePrepared.exception.BadRequestException;
import com.tipaneque.bePrepared.exception.EntityNotFoundException;
import com.tipaneque.bePrepared.model.User;
import com.tipaneque.bePrepared.repository.AlertRepository;
import com.tipaneque.bePrepared.repository.CitizenRepository;
import com.tipaneque.bePrepared.repository.UserRepository;
import com.tipaneque.bePrepared.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AlertRepository alertRepository;
    private final CitizenRepository citizenRepository;

    @Override
    @Transactional
    public String createUser(User user) {
        if(userRepository.existsByEmail(user.getEmail()))
            throw new BadRequestException("Já existe um usuário com este e-mail!");
        userRepository.save(user);
        return "Usuário criado com sucesso!";
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));
    }

    @Override
    @Transactional(readOnly = true)
    public StatsResponse getAllStats() {
        return StatsResponse.builder()
                .citizens(citizenRepository.count())
                .totalAlerts(alertRepository.count())
                .activeAlerts(alertRepository.countByActive(true))
                .build();
    }
}
