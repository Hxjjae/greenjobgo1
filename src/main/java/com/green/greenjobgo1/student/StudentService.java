package com.green.greenjobgo1.student;

import com.green.greenjobgo1.common.utils.MyFileUtils;
import com.green.greenjobgo1.config.entity.FileCategoryEntity;
import com.green.greenjobgo1.config.entity.FileEntity;
import com.green.greenjobgo1.config.entity.StudentEntity;
import com.green.greenjobgo1.repository.FileCategoryRepository;
import com.green.greenjobgo1.repository.FileRepository;
import com.green.greenjobgo1.repository.StudentRepository;
import com.green.greenjobgo1.student.model.StudentInsDto;
import com.green.greenjobgo1.student.model.StudentInsRes;
import com.green.greenjobgo1.student.model.StudentSelDto;
import com.green.greenjobgo1.student.model.StudentSelRes;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository STU_REP;
    private final FileRepository FILE_REP;
    private final FileCategoryRepository FILE_CATE_REP;
    private final StudentQdsl studentQdsl;

    @Value("${file.dir}")
    private String fileDir;

    public StudentSelRes selStudent(StudentSelDto dto) {
        StudentSelRes studentSelRes = studentQdsl.studentVo(dto.getIstudent());

        if (studentSelRes != null) {
            return StudentSelRes.builder()
                    .istudent(studentSelRes.getIstudent())
                    .name(studentSelRes.getName())
                    .subjectName(studentSelRes.getSubjectName())
                    .startedAt(studentSelRes.getStartedAt())
                    .endedAt(studentSelRes.getEndedAt())
                    .address(studentSelRes.getAddress())
                    .mobileNumber(studentSelRes.getMobileNumber())
                    .id(studentSelRes.getId())
                    .education(studentSelRes.getEducation())
                    .build();
        } else {
            throw new EntityNotFoundException("찾을 수 없는 pk 입니다.");
        }
    }

    public StudentInsRes insResume(MultipartFile file, StudentInsDto dto) {
        Optional<FileCategoryEntity> fileCateId = FILE_CATE_REP.findById(dto.getIFileCategory());
        Optional<StudentEntity> stdId = STU_REP.findById(dto.getIstudent());

        FileEntity entity = new FileEntity();
        entity.setFileCategoryEntity(fileCateId.get());
        entity.setCreatedAt(LocalDate.now());
        entity.setStudentEntity(stdId.get());
        entity.setIfile(dto.getIfile());

        String centerPath = String.format("student/%d", dto.getIstudent());
        String dicPath = String.format("%s/%s", MyFileUtils.getAbsolutePath(fileDir), centerPath);
        String savedFileNm = MyFileUtils.makeRandomFileNm(file.getOriginalFilename());
        String savedFilePath = String.format("%s/%s", centerPath, savedFileNm);
        String targetPath = String.format("%s/%s", MyFileUtils.getAbsolutePath(fileDir), savedFilePath);

        entity.setFile(savedFileNm);

        File dicFile = new File(dicPath);
        if (!dicFile.exists()) {
            dicFile.mkdirs();
        }
        File target = new File(targetPath);
        try {
            file.transferTo(target);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("업로드 실패");
        }
        dto.setFile(savedFileNm);

        FileEntity result = FILE_REP.save(entity);
        try {
            StudentInsRes build = StudentInsRes.builder()
                    .file(result.getFile())
                    .ifile(result.getIfile())
                    .createdAt(result.getCreatedAt())
                    .istudent(result.getStudentEntity().getIstudent())
                    .build();
            return build;
        } catch (Exception e) {
            String FileName = dto.getFile();
            String filePath = String.format("student/%d/%s", dto.getIstudent(), FileName);
            String fullPath = String.format("%s/%s", MyFileUtils.getAbsolutePath(fileDir), filePath);
            File exceptionFile = new File(fullPath);
            if (exceptionFile.exists()) {
                exceptionFile.delete();
            }
            throw new RuntimeException("사진 업로드 실패로 인한 삭제");
        }
    }
}
