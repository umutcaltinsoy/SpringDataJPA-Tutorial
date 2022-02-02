package com.hope.repository;

import com.hope.entity.Guardian;
import com.hope.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .email("umutcaltinsoy@gmail.com")
                .firstName("Umut")
                .lastName("Altinsoy")
                // .guardianName("Ata")
                // .guardianEmail("ata@gmail.com")
                // .guardianMobile("+905505505085")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {

        Guardian guardian = Guardian.builder()
                .name("Ata")
                .email("ata@gmail.com")
                .mobile("+0905555555555")
                .build();

        Student student = Student.builder()
                .firstName("John")
                .lastName("Wick")
                .email("wick@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }


    @Test
    public void printAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("Students : " + studentList );
    }

    @Test
    public void listOfStudentByFirstName() {
        List<Student> studentList =
                studentRepository.findByfirstName("umut");

        System.out.println("Student = " + studentList);
    }

    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> studentList =
                studentRepository.findByFirstNameContaining("t");
        System.out.println("Student : " + studentList);
    }

    @Test
    public void printStudentBasedOnGuardianName() {
        List<Student> studentList = studentRepository.findByGuardianName("Ata");
        System.out.println("Student : " + studentList);
    }

    @Test
    public void printgetgetStudentByEmailAddress() {
        Student student = studentRepository.getStudentByEmailAddress("umutcaltinsoy@gmail.com");
        System.out.println("Student :" + student);
    }

    @Test
    public void printgetStudentFirstNameByEmailAddress() {
        String firstName = studentRepository.getStudentFirstNameByEmailAddress("umutcaltinsoy@gmail.com");
        System.out.println("Student : " + firstName);
    }

    @Test
    public void printgetStudentByEmailAddressNative() {
        Student student = studentRepository.getStudentByEmailAddressNative("umutcaltinsoy@gmail.com");
        System.out.println(student);
    }

    @Test
    public void printgetStudentByEmailAddressNativeNamedParam() {
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("wick@gmail.com");
        System.out.println("Student : " + student);
    }

    @Test
    public void updateStudentNameByEmailTest() {
        studentRepository.updateStudentNameByEmail(
                "Umut Cagri",
                "umutcaltinsoy@gmail.com"
        );
    }

}