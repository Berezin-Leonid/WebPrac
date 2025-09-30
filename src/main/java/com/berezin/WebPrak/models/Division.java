package com.berezin.WebPrak.models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "division")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Division implements CommonEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    // Родительское подразделение
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Division parent;

    // Дочерние подразделения (обратная связь)
    @OneToMany(mappedBy = "parent")
    private List<Division> children;
}