package com.zihanc.takeout.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "employee_table")
public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 888L;
    @Id
    @SequenceGenerator(
            name = "id_sequence",
            sequenceName = "id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_sequence")
    private Long id;
    @Column(name = "username")
    // json key 'username' from frontend must equal to variable name of this field
    private String username;
    @Column(name = "name")
    private String name;
    private String password;
    private String phone;
    private String gender;
    private Integer status;
//    @Column(name = "create_time")
//    private LocalDateTime createTime;
//    @Column(name = "update_time")
//    private LocalDateTime updateTime;
    private String salt;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnoreProperties("employee")
    Set<Meal> meals = new HashSet<>();

//    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
//    Set<Order> orders = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    Set<Category> categories = new HashSet<>();
}
