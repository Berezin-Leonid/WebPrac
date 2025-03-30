package web.models;

import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "division")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Division implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "index")
    private Long id;

    @Column(nullable = false, name = "name", length = 100)
    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "index")
    private Division parent;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Division division = (Division) o;
        return Objects.equals(id, division.id)
                //&& name.equals(o.getName())
                && Objects.equals(parent, division.parent);
    }


}