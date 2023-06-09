package com.zihanc.takeout.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "flav_table")
public class Flavour implements Serializable {
    @Serial
    private static final long serialVersionUID = 111L;
    @Id
    @SequenceGenerator(
            name = "id_sequence",
            sequenceName = "id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_sequence")
    private Long id;
//    @Column(nullable = false, name = "meal_id")
//    private Long mealId;

    private String name;
    //choices
    private String value;
//    private LocalDateTime createTime;
//    private LocalDateTime updateTime;
}
