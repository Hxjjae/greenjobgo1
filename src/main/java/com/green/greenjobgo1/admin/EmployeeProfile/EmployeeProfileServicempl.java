package com.green.greenjobgo1.admin.EmployeeProfile;

import com.green.greenjobgo1.admin.EmployeeProfile.model.EmployeeProfileDto;
import com.green.greenjobgo1.admin.EmployeeProfile.model.EmployeeProfileVo;
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

    public int patchPic(Long number,MultipartFile pic){
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
            return 0;
        }

        String img = savedFileName;
        EmployeeProfileEntity entity = EmployeeProfileRep.findById(number).get();

        entity.setProfilePic(img);
        EmployeeProfileRep.save(entity);

        return 1;
    }

}
