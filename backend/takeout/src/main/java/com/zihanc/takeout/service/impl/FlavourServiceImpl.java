package com.zihanc.takeout.service.impl;

import com.zihanc.takeout.model.Flavour;
import com.zihanc.takeout.repository.FlavourRepo;
import com.zihanc.takeout.service.FlavourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlavourServiceImpl implements FlavourService {
    private final FlavourRepo flavourRepo;
    @Autowired
    public FlavourServiceImpl(FlavourRepo flavourRepo) {
        this.flavourRepo = flavourRepo;
    }

    @Override
    public void saveAll(List<Flavour> f) {
        flavourRepo.saveAll(f);
    }

    @Override
    public List<Flavour> getFlavoursByMealId(Long id) {
        if(id==null) return new ArrayList<>();
        return flavourRepo.getFlavoursByMealId(id).orElse(new ArrayList<>());
    }

    @Override
    public void deleteByMealId(Long id) {
        flavourRepo.deleteByMealId(id);
    }
}
