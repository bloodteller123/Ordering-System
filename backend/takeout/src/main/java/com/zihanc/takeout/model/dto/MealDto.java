package com.zihanc.takeout.model.dto;

import com.zihanc.takeout.model.Flavour;
import com.zihanc.takeout.model.Meal;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MealDto extends Meal {
//    private List<Flavour> flavours = new ArrayList<>();
    private String categoryName;
    private Integer copies;
}
