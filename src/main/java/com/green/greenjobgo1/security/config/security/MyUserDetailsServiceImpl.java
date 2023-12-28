package com.green.greenjobgo1.security.config.security;

import com.green.greenjobgo1.security.config.security.model.MyUserDetails;
import com.green.greenjobgo1.security.config.security.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsMapper MAPPER;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = MAPPER.getByUid(username);
        return MyUserDetails.builder()
                .uid(entity.getUid())
                .iuser(entity.getIuser())
                .upw(entity.getUpw())
                .roles(Collections.singletonList(entity.getRole()))
                .build();
    }
}
