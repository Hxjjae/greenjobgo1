package com.green.greenjobgo1.admin.employeeProfile;

import com.green.greenjobgo1.admin.employeeProfile.model.EmployeeProfileDto;
import com.green.greenjobgo1.admin.employeeProfile.model.EmployeeProfileVo;
import com.green.greenjobgo1.common.entity.EmployeeProfileEntity;
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
        //List<EmployeeProfileEntity> entityList = EmployeeProfileRep.findAll();
        List<EmployeeProfileEntity> entityList = EmployeeProfileRep.findByDelYn(0);

        return entityList.stream().map(profile -> EmployeeProfileVo.builder()
                .oneWord(profile.getOneWord())
                .iemply(profile.getIemply())
                .counselingNumber (profile.getCounselingNumber())
                .name(profile.getName())
                .phoneNumber(profile.getPhoneNumber())
                .email(profile.getEmail())
                .profilePic("/img/employee/"+profile.getIemply()+"/"+profile.getProfilePic()).build()).toList();
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
        String centerPath = String.format("%s/employee/%d", MyFileUtils.getAbsolutePath(fileDir),entity.getIemply());

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
        EmployeeProfileEntity save = EmployeeProfileRep.save(entity);

        // 파일위치 붙여주기
        save.setProfilePic("/img/employee/"+save.getIemply()+"/"+save.getProfilePic());

        return entity;
    }

    public EmployeeProfileVo patchProfile(Long iemply,String name,String oneWord,String conuselingNumber,String phone,String email,MultipartFile pic){
        EmployeeProfileEntity entity = EmployeeProfileRep.findById(iemply).get();

        Optional.ofNullable(oneWord).ifPresent(entity::setOneWord);
        Optional.ofNullable(conuselingNumber).ifPresent(entity::setCounselingNumber);
        Optional.ofNullable(name).ifPresent(entity::setName);
        Optional.ofNullable(phone).ifPresent(entity::setPhoneNumber);
        Optional.ofNullable(email).ifPresent(entity::setEmail);

        if (pic != null) {
            String fileDir = MyFileUtils.getAbsolutePath(FILE_DIR);
            String centerPath = String.format("%s/employee/%d", MyFileUtils.getAbsolutePath(fileDir), iemply);

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
                .counselingNumber (entity.getCounselingNumber())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .profilePic("/img/employee/"+entity.getIemply()+"/"+entity.getProfilePic()).build();

    }


    public int delprofile(Long iemply){
        EmployeeProfileEntity entity = EmployeeProfileRep.findById(iemply).get();

        //저장된 파일 삭제
        String fileDir = MyFileUtils.getAbsolutePath(FILE_DIR);
        String centerPath = String.format("%s/employee/%d", MyFileUtils.getAbsolutePath(fileDir), iemply);
        File file = new File(centerPath);
        log.info("file :{}",file);
        file.delete();

        // delYn으로 삭제처리
        entity.setDelYn(1);
        try {
            EmployeeProfileRep.save(entity);
        }catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
