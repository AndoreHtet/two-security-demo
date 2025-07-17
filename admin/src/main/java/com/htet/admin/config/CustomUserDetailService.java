package com.htet.admin.config;

import ch.qos.logback.core.util.StringUtil;
import com.htet.data.service.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final SecurityService service;

    public CustomUserDetailService(SecurityService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNo) throws UsernameNotFoundException {
        return service.findByPhoneNo(phoneNo)
                .map(user -> User
                        .withUsername(phoneNo)
                        .username(StringUtils.arrayToCommaDelimitedString(new String[] {
                                user.getName(), user.getPhoneNo()
                        }))
                        .authorities(user.getRoleName())
                        .password(user.getPassword())
                        .accountLocked(user.isLocked())
                        .disabled(!user.isActivated())
                        .build()).orElseThrow(() -> new UsernameNotFoundException(phoneNo));
    }
}
