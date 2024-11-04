package com.example.SpringJWT.service;

import com.example.SpringJWT.dto.JoinDTO;
import com.example.SpringJWT.entity.UserEntity;
import com.example.SpringJWT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) {
        String userId = joinDTO.getUserId();
        String password = joinDTO.getPassword();
        System.out.println("userId = " + userId);
        System.out.println("password = " + password);
//        boolean isExist = userRepository.existsByUsername(userId);
        boolean isExist = userRepository.existsByUserId(userId);

        if (isExist) {
            return;
        }
        UserEntity data = new UserEntity();
        data.setUserId(userId);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_ADMIN");


        userRepository.save(data);
    }

}
