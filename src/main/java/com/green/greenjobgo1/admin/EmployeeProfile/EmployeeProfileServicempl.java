package com.green.greenjobgo1.admin.EmployeeProfile;

import com.green.greenjobgo1.admin.EmployeeProfile.model.EmployeeProfileDto;
import com.green.greenjobgo1.admin.EmployeeProfile.model.EmployeeProfileVo;
import com.green.greenjobgo1.admin.EmployeeProfile.model.EmployeePutProfileDto;
import com.green.greenjobgo1.config.entity.EmployeeProfileEntity;
import com.green.greenjobgo1.repository.EmployeeProfileRepository;
import com.green.greenjobgo1.common.utils.MyFileUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeProfileServicempl {
    @Value("${file.dir}")
    private String FILE_DIR;

    private final JPAQueryFactory jpaQueryFactory;
    private final EmployeeProfileRepository EmployeeProfileRep;


    public List<EmployeeProfileVo> getProfile(){
        List<EmployeeProfileEntity> all = EmployeeProfileRep.findAll();

        return all.stream().map(profile -> EmployeeProfileVo.builder()
                .iemply(profile.getIemply())
                .name(profile.getName())
                .phone(profile.getPhone())
                .email(profile.getEmail())
                .profilePic(profile.getProfilePic())
                .kakaoId(profile.getKakaoid()).build()).toList();

    }

    public EmployeeProfileEntity insProfile(EmployeeProfileDto dto){


        EmployeeProfileEntity entity = new EmployeeProfileEntity();
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setKakaoid(dto.getKakaoid());

        EmployeeProfileRep.save(entity);

        return entity;
    }

    public EmployeeProfileVo putProfile(EmployeePutProfileDto dto){
        EmployeeProfileEntity entity = EmployeeProfileRep.findById(dto.getIemply()).get();

        if (dto.getName()!=null){
            entity.setName(dto.getName());
        }
        if (dto.getPhone()!=null){
            entity.setPhone(dto.getPhone());
        }
        if (dto.getEmail()!=null){
            entity.setEmail(dto.getEmail());
        }
        if (dto.getKakaoId()!=null){
            entity.setKakaoid(dto.getKakaoId());
        }

        EmployeeProfileRep.save(entity);

        return EmployeeProfileVo.builder().iemply(entity.getIemply())
                .name(entity.getName())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .profilePic(entity.getProfilePic())
                .kakaoId(entity.getKakaoid()).build();

    }

    public EmployeeProfileVo patchPic(Long number,MultipartFile pic){
        EmployeeProfileEntity entity = EmployeeProfileRep.findById(number).get();
        String fileDir = MyFileUtils.getAbsolutePath(FILE_DIR);
        String centerPath = String.format("%s/Employee/%d", MyFileUtils.getAbsolutePath(fileDir),number);

        File dic = new File(centerPath);
        if(!dic.exists()){
            dic.mkdirs();
        }

        String originFileName = pic.getOriginalFilename();
        String savedFileName = MyFileUtils.makeRandomFileNm(originFileName);
        String savedFilePath = String.format("%s/%s",centerPath, savedFileName);

        File target = new File(savedFilePath);
        try {
            pic.transferTo(target);
        }catch (Exception e) {
            return null;
        }

        String img = savedFileName;


        entity.setProfilePic(img);
        EmployeeProfileRep.save(entity);

        return EmployeeProfileVo.builder().iemply(entity.getIemply())
                .name(entity.getName())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .profilePic(entity.getProfilePic())
                .kakaoId(entity.getKakaoid()).build();
    }

    public EmployeeProfileVo patchName(Long iemply,String name){
        EmployeeProfileEntity entity = EmployeeProfileRep.findById(iemply).get();
        entity.setName(name);
        EmployeeProfileRep.save(entity);

        return EmployeeProfileVo.builder().iemply(entity.getIemply())
                .name(entity.getName())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .profilePic(entity.getProfilePic())
                .kakaoId(entity.getKakaoid()).build();
    }

    public EmployeeProfileVo patchPhone(Long iemply,String phone){
        EmployeeProfileEntity entity = EmployeeProfileRep.findById(iemply).get();
        entity.setPhone(phone);
        EmployeeProfileRep.save(entity);

        return EmployeeProfileVo.builder().iemply(entity.getIemply())
                .name(entity.getName())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .profilePic(entity.getProfilePic())
                .kakaoId(entity.getKakaoid()).build();
    }

    public EmployeeProfileVo patchemail(Long iemply,String email){
        EmployeeProfileEntity entity = EmployeeProfileRep.findById(iemply).get();
        entity.setEmail(email);
        EmployeeProfileRep.save(entity);

        return EmployeeProfileVo.builder().iemply(entity.getIemply())
                .name(entity.getName())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .profilePic(entity.getProfilePic())
                .kakaoId(entity.getKakaoid()).build();
    }

    public EmployeeProfileVo patchKakaoid(Long iemply,String kakaoid){
        EmployeeProfileEntity entity = EmployeeProfileRep.findById(iemply).get();
        entity.setKakaoid(kakaoid);
        EmployeeProfileRep.save(entity);

        return EmployeeProfileVo.builder().iemply(entity.getIemply())
                .name(entity.getName())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .profilePic(entity.getProfilePic())
                .kakaoId(entity.getKakaoid()).build();
    }
    public int delprofile(Long iemply){
        EmployeeProfileEntity entity = EmployeeProfileRep.findById(iemply).get();
        try {
            EmployeeProfileRep.delete(entity);
        }catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
