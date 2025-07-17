package com.htet.data.service.security;

import com.htet.data.dto.UserDetailDto;
import com.htet.data.repo.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SecurityServiceImpl implements SecurityService{

    private final UserRepo userRepo;


    @Override
    public Optional<UserDetailDto> findByPhoneNo(String phoneNo) {
        return userRepo.findByPhoneNo(phoneNo).map(UserDetailDto::new);
    }
}
