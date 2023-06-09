package com.zihanc.takeout.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "category_table")
@EqualsAndHashCode(exclude = "meals")
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = 888L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true)
    //cuisine name -> canadian meal, chinese meal, american meal etc
    private String name;
//    private LocalDateTime createTime;
//    private LocalDateTime updateTime;
    // employee
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
    // mappedBy = '?', ? is the corresponding variable name in Meal.
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    //https://stackoverflow.com/questions/67353793/what-does-jsonignorepropertieshibernatelazyinitializer-handler-do
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "category"})
    Set<Meal> meals = new HashSet<>();

    public void addMeal(Meal meal){
        meals.add(meal);
    }
}
