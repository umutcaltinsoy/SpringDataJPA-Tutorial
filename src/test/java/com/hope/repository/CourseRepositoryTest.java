package com.hope.repository;

import com.hope.entity.Course;
import com.hope.entity.Student;
import com.hope.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {


    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println("Courses : " + courses);
    }


    @Test
    public void saveCourseWithTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("Hamit")
                .lastName("Mizrak")
                .build();


        Course course = Course.builder()
                .title("Java")
                .credit(6)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {
        Pageable firsPagewithThreeRecords =
                PageRequest.of(0, 3);

        Pageable secondPagewithTwoRecords =
                PageRequest.of(1, 2);

        List<Course> courses =
                courseRepository.findAll(secondPagewithTwoRecords).getContent();

        long totalElements =
                courseRepository.findAll(secondPagewithTwoRecords)
                                .getTotalElements();

        long totalPages =
                courseRepository.findAll(secondPagewithTwoRecords)
                                .getTotalPages();

        System.out.println("Total Pages : " + totalPages);

        System.out.println("Total Elements : " + totalElements);


        System.out.println(courses);


    }

    @Test
    public void findAllSorting() {
        Pageable sortByTitle =
                PageRequest.of(0, 2, Sort.by("title"));

        Pageable sortByCredirDesc =
                PageRequest.of(0, 2, Sort.by("credit").descending());

        Pageable sortByCreditAndDesc =
                PageRequest.of(0, 2, Sort.by("title").descending().and(Sort.by("credit")));

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();

        System.out.println("Courses" + courses);
    }

    @Test
    public void printfindByTitleContaining() {
        Pageable firstPageTenRecords =
                PageRequest.of(0, 10);

        List<Course> courses =
                courseRepository.findByTitleContaining("D", firstPageTenRecords).getContent();

        System.out.println("Courses" + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("Lizze")
                .lastName("Morgan")
                .build();

        Student student = Student.builder()
                .firstName("Umut")
                .lastName("Altinsoy")
                .email("umut@hotmail.com")
                .build();


        Course course = Course.builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudent(student);

        courseRepository.save(course);
    }

}