package com.hope.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "tbl_student",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email_address"
        )

)
public class Student {

    // Attention : Some of the dbms doesnt support sequence. For instance MySQL
    // and MySQL has auto increment structure instead of sequence structure.
    // What if we want to use sequence structure instead of auto increment struct.?
    // For this we'll use @SequenceGenerator annotation in below.
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long studentId;

    private String firstName;
    private String lastName;
    @Column(
            name = "email_address",
            nullable = false

    )
    private String email;

    // This propertys should be another class
    // We will allocate this variables in another class but
    // we will see them in the database along with the others
//    private String guardianName;
//    private String guardianEmail;
//    private String guardianMobile;

    @Embedded
    private Guardian guardian;

}
