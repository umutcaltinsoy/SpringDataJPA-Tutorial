package com.hope.repository;

import com.hope.entity.Course;
import com.hope.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;


    @Test
    public void saveTeacher() {

        Course courseMath = Course.builder()
                .title("Math")
                .credit(5)
                .build();

        Course courseWave = Course.builder()
                .title("Wave Theory")
                .credit(7)
                .build();

        Teacher teacher =
                Teacher.builder()
                        .firstName("Ata")
                        .lastName("Sevinc")
//                        .courses(List.of(courseMath, courseWave))
                        .build();

        teacherRepository.save(teacher);
    }

}