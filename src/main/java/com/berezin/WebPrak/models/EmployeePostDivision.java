package com.berezin.WebPrak.models;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee_post_division")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePostDivision implements CommonEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // связь с Employee
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    // связь с PostDivision
    @ManyToOne
    @JoinColumn(name = "post_division_id", nullable = false)
    private PostDivision postDivision;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "retire_date")
    private LocalDate retireDate;
}
