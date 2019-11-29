package com.baizhi.controller;

import com.baizhi.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/poems")
public class PoemController {
    @Autowired
    PoemService poemService;

    @RequestMapping("/poem")
    public List<Map> search(String query) {
        List<Map> search = poemService.search(query);
        return search;
    }

    @RequestMapping("highlight")
    public List<Map> highlight(String query) {
        return null;
    }
}
