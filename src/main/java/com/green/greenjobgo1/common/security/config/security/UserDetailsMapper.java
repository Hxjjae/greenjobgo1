package com.green.greenjobgo1.common.security.config.security;

import com.green.greenjobgo1.common.security.config.security.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDetailsMapper {
    int save(UserEntity p);
    UserEntity getByUid(String uid);

}
