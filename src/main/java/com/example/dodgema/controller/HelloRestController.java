package com.example.dodgema.controller;

import java.util.List;

import com.example.dodgema.dao.StudentMapper;
import com.example.dodgema.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dodgema.model.Hello;
import com.example.dodgema.repository.HelloDao;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloRestController {

    @Autowired
    private HelloDao helloDao;
    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping("/add")
    public Hello add(Hello hello) {

        Hello helloData = helloDao.save(hello);

        return helloData;
    }

    @RequestMapping("/list")
    public List<Hello> list(Model model) {

        List<Hello> helloList = helloDao.findAll();

        return helloList;
    }

    @RequestMapping("/student")
    public List<Student> list(ModelAndView model) {

        List<Student> studentList = studentMapper.selectUser();
        return studentList;
    }


}
