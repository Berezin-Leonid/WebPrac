package web.models;

import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;
import java.time.LocalDate;

@Entity
@Table(name = "division")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class EmployeePostDivision implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "index")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_division_id", referencedColumnName = "index", nullable = false)
    @NonNull
    private PostDivision postDivision;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "index", nullable = false)
    @NonNull
    private Employee employee;

    @Column(name = "hire_date", nullable = false)
    @Temporal(TemporalType.DATE)
    @NonNull
    private LocalDate hireDate;

    @Column(name = "retire_date")
    @Temporal(TemporalType.DATE)
    private LocalDate retireDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePostDivision that = (EmployeePostDivision) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(postDivision, that.postDivision) &&
                Objects.equals(employee, that.employee) &&
                Objects.equals(hireDate, that.hireDate) &&
                Objects.equals(retireDate, that.retireDate);
    }


}