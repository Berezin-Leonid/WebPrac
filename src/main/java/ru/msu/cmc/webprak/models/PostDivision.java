package ru.msu.cmc.webprak.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "division")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class PostDivision implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "index")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "index")
    @NonNull
    private Post post;

    @ManyToOne
    @JoinColumn(name = "division_id", referencedColumnName = "index")
    @NonNull
    private Division division;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDivision that = (PostDivision) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(post, that.post) &&
                Objects.equals(division, that.division);
    }

}