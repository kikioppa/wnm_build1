package com.example.dodgema.dao;

import com.example.dodgema.domain.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * find all the students.
     */
    List<Student> selectUser();
}