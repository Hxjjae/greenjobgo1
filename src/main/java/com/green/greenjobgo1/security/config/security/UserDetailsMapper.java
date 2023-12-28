package com.green.greenjobgo1.security.config.security;

import com.green.greenjobgo1.security.config.security.model.MyUserDetails;
import com.green.greenjobgo1.security.config.security.model.UserEntity;
import com.green.greenjobgo1.security.config.security.model.UserTokenEntity;
import com.green.greenjobgo1.security.sign.model.UserUpdDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDetailsMapper {
    int save(UserEntity p);
    UserEntity getByUid(String uid);
    int updSecretKey(UserUpdDto dto);

    int updUserToken(UserTokenEntity p);
    UserTokenEntity selUserToken(UserTokenEntity p);
}
