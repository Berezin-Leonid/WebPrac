package com.berezin.WebPrak.models;

import lombok.*;
import javax.persistence.*;


@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Post implements CommonEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "name", length = 100)
    @NonNull
    private String name;

    @Column(nullable = false, name = "respons", columnDefinition = "TEXT")
    @NonNull
    private String respons;


}