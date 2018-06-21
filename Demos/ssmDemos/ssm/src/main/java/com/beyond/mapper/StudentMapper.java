package com.beyond.mapper;

import com.beyond.entity.Student;

public interface StudentMapper {

	void addStudent(Student student);

	Student selectById(String id);
}
