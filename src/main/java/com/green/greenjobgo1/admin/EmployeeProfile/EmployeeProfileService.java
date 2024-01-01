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
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeProfileService {
    @Value("${file.dir}")
    private String FILE_DIR;

    private final JPAQueryFactory jpaQueryFactory;
    private final EmployeeProfileRepository EmployeeProfileRep;


    public List<EmployeeProfileVo> getProfile(){
        List<EmployeeProfileEntity> entityList = EmployeeProfileRep.findAll();

        return entityList.stream().map(profile -> EmployeeProfileVo.builder()
                .oneWord(profile.getOneWord())
                .iemply(profile.getIemply())
                .conuselingNumber(profile.getCounselingNumber())
                .name(profile.getName())
                .phoneNumber(profile.getPhoneNumber())
                .email(profile.getEmail())
                .profilePic(profile.getProfilePic()).build()).toList();
    }

    public EmployeeProfileEntity insProfile(EmployeeProfileDto dto,MultipartFile pic){

        EmployeeProfileEntity entity = EmployeeProfileEntity.builder()
                .oneWord(dto.getOneWord())
                .name(dto.getName())
                .counselingNumber(dto.getCounselingNumber())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail()).build();

        EmployeeProfileRep.save(entity);

        String fileDir = MyFileUtils.getAbsolutePath(FILE_DIR);
        String centerPath = String.format("%s/Employee/%d", MyFileUtils.getAbsolutePath(fileDir),entity.getIemply());

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


        return entity;
    }

    public EmployeeProfileVo patchProfile(Long iemply,String oneWord,String name,String conuselingNumber,String phone,String email,MultipartFile pic){
        EmployeeProfileEntity entity = EmployeeProfileRep.findById(iemply).get();

        entity.setIemply(iemply);

        Optional.ofNullable(oneWord).ifPresent(entity::setOneWord);
        Optional.ofNullable(conuselingNumber).ifPresent(entity::setCounselingNumber);
        Optional.ofNullable(name).ifPresent(entity::setName);
        Optional.ofNullable(phone).ifPresent(entity::setPhoneNumber);
        Optional.ofNullable(email).ifPresent(entity::setEmail);

        if (pic != null) {
            String fileDir = MyFileUtils.getAbsolutePath(FILE_DIR);
            String centerPath = String.format("%s/Employee/%d", MyFileUtils.getAbsolutePath(fileDir), iemply);

            File file = new File(centerPath+"/"+entity.getProfilePic());
            log.info("file :{}",file);
            file.delete();

            File dic = new File(centerPath);
            if (!dic.exists()) {
                dic.mkdirs();
            }

            String originFileName = pic.getOriginalFilename();
            String savedFileName = MyFileUtils.makeRandomFileNm(originFileName);
            String savedFilePath = String.format("%s/%s", centerPath, savedFileName);

            File target = new File(savedFilePath);
            try {
                pic.transferTo(target);
            } catch (Exception e) {
                return null;
            }

            String img = savedFileName;
            entity.setProfilePic(img);
        }
        EmployeeProfileRep.save(entity);
        return EmployeeProfileVo.builder().iemply(entity.getIemply())
                .oneWord(entity.getOneWord())
                .name(entity.getName())
                .conuselingNumber(entity.getCounselingNumber())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .profilePic(entity.getProfilePic()).build();

    }


    public int delprofile(Long iemply){
        EmployeeProfileEntity entity = EmployeeProfileRep.findById(iemply).get();

        //저장된 파일 삭제
        String fileDir = MyFileUtils.getAbsolutePath(FILE_DIR);
        String centerPath = String.format("%s/Employee/%d", MyFileUtils.getAbsolutePath(fileDir), iemply);
        File file = new File(centerPath);
        log.info("file :{}",file);
        file.delete();

        try {
            EmployeeProfileRep.delete(entity);
        }catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
