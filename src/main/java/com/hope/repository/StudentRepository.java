package com.hope.repository;

import com.hope.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // We just need to give definition, don't have to implemented


    // 1) Return list of students
    List<Student> findByfirstName(String firstName);

    // 2) The method to fetch the student whos is contains specific characters their firstName
    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    List<Student> findByFirstNameAndLastName(String firstName,
                                             String lastName);

    // JPQL
    @Query("select s from Student s where s.email = ?1")
    Student getStudentByEmailAddress(String email);

    @Query("select s.firstName from  Student s where s.email = ?1")
    // This will be String instead of Student because
    // I'm just getting the one value over here
    String getStudentFirstNameByEmailAddress(String email);


    // *** Native Queries ***
    // In here we are just gonna do same example which we have done with
    // JPQL but instead of using JPQL we are gonna use Native Queries
    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String email);


    // Query Named Params : If we have multiple paramaters that we passing over here
    // ? mark is not always the better approach, we can go with Named Params

    // First we're gonna use previous method for this approach
    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = :email",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(@Param("email") String email);



    @Modifying // we can execute not only SELECT queries, but also INSERT, UPDATE and DELETE
    @Transactional // with this annotation, we say that we want to confirm any kind of modifying operations
    @Query(
            value = "update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmail(String firstName, String email);

}
