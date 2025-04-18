package web.models;

import lombok.*;
import jakarta.persistence.*;
import java.util.Objects;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Employee implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "index")
    private Long index;

    @Column(nullable = false, name = "name", length = 100)
    @NonNull
    private String name;

    @Column(nullable = false, name = "home_address", length = 200)
    @NonNull
    private String home_address;

    @Column(name = "education", length = 100)
    private String education;

    @Column(name = "start_year")
    @Temporal(TemporalType.DATE)
    private LocalDate startYear;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee other = (Employee) o;
        return Objects.equals(index, other.index)
                && name.equals(other.name)
                && home_address.equals(other.home_address)
                && Objects.equals(education, other.education)
                && Objects.equals(startYear, other.startYear);
    }

    @Override
    public Long getId() {
        return index;
    }

    @Override
    public void setId(Long id) {
        this.index = id;
    }
}