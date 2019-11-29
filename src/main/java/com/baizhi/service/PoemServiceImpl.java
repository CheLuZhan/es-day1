package com.baizhi.service;

import com.baizhi.esrepository.PoemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Repository
@Service
public class PoemServiceImpl implements PoemService {
    @Autowired
    PoemRepository poemRepository;

    @Override
    public List<Map> search(String query) {
        List<Map> search = poemRepository.search(query);
        return search;
    }
}
