package com.hope.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence")
    private Long courseId;
    private String title;
    private Integer credit;

    // When we adding @OneToOne and CourseMaterial here
    // We are gonna see both Course and CourseMaterial
    // properties at the same time when we run the
    // CourseRepository.java, before this adding operation
    // we just saw the Course properties. Those are called
    // as Uni&Bi directional relationship. We've implemented
    // this kind of relationship for OneToOne we can also
    // implement those relationships for others
    // (OneToMany, ManyToMany..)
    @OneToOne(
            mappedBy = "course"
    )
    private CourseMaterial courseMaterial;

    // ManyToOne
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    // ManyToMany
    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "student_course_map",
            joinColumns = @JoinColumn(
                    name = "course_id", // db
                    referencedColumnName = "courseId" // class property
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            )
    )
    private List<Student> students;

    public void addStudent(Student student) {
        if (students == null) students = new ArrayList<>();
        students.add(student);
    }
}
