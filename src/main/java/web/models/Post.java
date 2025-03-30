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
public class Post implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "index")
    private Long id;

    @Column(nullable = false, name = "name", length = 100)
    @NonNull
    private String name;

    @Column(nullable = false, name = "respons", columnDefinition = "TEXT")
    @NonNull
    private String respons;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                name.equals(post.name) &&
                respons.equals(post.respons);
    }

}