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

        if (entity == null) {
            throw new UsernameNotFoundException("유저를 찾을 수 없습니다.");
        }
        if (entity.getEditableYn() != 1) {
            throw new RuntimeException("editableYn이 활성화 되지 않았습니다.");
        }

        return MyUserDetails.builder()
                .uid(entity.getUid())
                .iuser(entity.getIuser())
                .upw(entity.getUpw())
                .roles(Collections.singletonList(entity.getRole()))
                .build();
    }
}
