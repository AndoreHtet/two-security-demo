package com.htet.data.service.security;

import com.htet.data.dto.UserDetailDto;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface SecurityService {

    Optional<UserDetailDto> findByPhoneNo(String userName);
}
