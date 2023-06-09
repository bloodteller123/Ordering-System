package com.zihanc.takeout.service;

import com.zihanc.takeout.model.Flavour;

import java.util.List;

public interface FlavourService {
    void saveAll(List<Flavour> f);
    List<Flavour> getFlavoursByMealId(Long id);
    void deleteByMealId(Long id);
}
