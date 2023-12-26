package com.green.greenjobgo1.admin.sign;

import com.green.greenjobgo1.admin.companylist.model.CompanyExcel;
import com.green.greenjobgo1.admin.sign.model.StudentExcel;
import com.green.greenjobgo1.common.utils.ExcelUtil;
import com.green.greenjobgo1.config.entity.CompanyListEntity;
import com.green.greenjobgo1.config.entity.StudentEntity;
import com.green.greenjobgo1.repository.StudentRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignService {
    private final StudentRepository stdRep;
    private final JPAQueryFactory jpaQueryFactory;
    private final ExcelUtil excelUtil;

    @Transactional
    public int addExcel(MultipartFile file) {


        List<StudentExcel> studentlist = new ArrayList<>();

        // 엑셀의 셀데이터를 객체에 담기 4번째 행부터 5번째 열까지
        List<Map<String, Object>> listMap = excelUtil.getListData(file, 3, 15);

        for (Map<String, Object> map : listMap) {
            StudentExcel student = new StudentExcel();

            // 각 셀의 데이터를 VO에 set한다.
            student.setSubjectName(map.get("0").toString());
            student.setNumber(map.get("1").toString());
            student.setStartedAt(map.get("2").toString());
            student.setEndedAt(map.get("3").toString());
            student.setTrainingTime(map.get("4").toString());
            student.setStudentName(map.get("5").toString());
            student.setBirthday(map.get("6").toString());
            student.setPhone(map.get("7").toString());
            student.setEmail(map.get("8").toString());
            student.setAddress(map.get("9").toString());
            student.setAge(map.get("11").toString());
            studentlist.add(student);
        }

        //DB에 저장
        for (StudentExcel user : studentlist) {
            StudentEntity student = new StudentEntity();
            String birthday = user.getBirthday();
            String birthdayfirst = "19"+birthday.substring(0,2);
            String birthdaysecond = "-"+birthday.substring(2,4);
            String birthdaythird = "-"+birthday.substring(4,6);
            LocalDate birth= LocalDate.parse((birthdayfirst + birthdaysecond + birthdaythird));
            if (user.getAge().equals("남")){
                student.setGender(1);
            }else {
                student.setGender(0);
            }
            String phone = user.getPhone();
            String phonesub = phone.substring(8, 12);

            student.setId(user.getEmail());
            student.setPw(birthday+phonesub);
            student.setMobileNumber(user.getPhone());
            student.setName(user.getStudentName());
            student.setBirthday(birth);
            student.setAddress(user.getAddress());
            student.setAddressDetail(user.getAddress());
            student.setEducation(user.getAddress());
            student.setRole("student");
            student.setHuntJobYn(1);
            student.setStorageYn(1);
            student.setEditableYn(1);

            StudentEntity save = stdRep.save(student);

            if (save.getId() == null){
                return 0;
            }
        }
        return 1;
    }
}
