package com.beyond.mapper;

import com.beyond.entity.Teacher;

public interface TeacherMapper {

	void addTeacher(Teacher teacher);

	Teacher selectById(String id);
}
