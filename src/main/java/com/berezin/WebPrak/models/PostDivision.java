package com.berezin.WebPrak.models;


import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_division")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class PostDivision implements CommonEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // связь с Post
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @NonNull
    private Post post;

    // связь с Division
    @ManyToOne
    @JoinColumn(name = "division_id", nullable = false)
    @NonNull
    private Division division;
}
