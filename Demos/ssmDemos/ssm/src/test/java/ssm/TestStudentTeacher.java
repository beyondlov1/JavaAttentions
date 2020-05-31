package ssm;

import java.util.Iterator;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beyond.entity.Student;
import com.beyond.entity.Teacher;
import com.beyond.mapper.StudentMapper;
import com.beyond.mapper.TeacherMapper;

public class TestStudentTeacher {

	@Test
	public void testSelectStudent() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentMapper studentMapper = (StudentMapper) context.getBean("studentMapper");
		TeacherMapper teacherMapper = (TeacherMapper) context.getBean("teacherMapper");

		Student foundStudent = studentMapper.selectById("7b3a129e708d11e89452107b447deebe");
		System.out.println(foundStudent.getTeachers().size());

		Iterator<Teacher> it = foundStudent.getTeachers().iterator();
		while (it.hasNext()) {
			Teacher teacher = it.next();
			String id = teacher.getId();
			System.out.println(id);
		}

		System.out.println("yes");
	}

	@Test
	public void testSelectTeacher() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentMapper studentMapper = (StudentMapper) context.getBean("studentMapper");
		TeacherMapper teacherMapper = (TeacherMapper) context.getBean("teacherMapper");

		Teacher foundTeacher = teacherMapper.selectById("a89b709a708d11e89452107b447deebe");
		System.out.println(foundTeacher.getStudents().size());

		Iterator<Student> it = foundTeacher.getStudents().iterator();
		while (it.hasNext()) {
			Student student = it.next();
			String id = student.getId();
			System.out.println(id);
		}

		System.out.println("yes");
	}

	@Test
	public void testAddTeacher() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentMapper studentMapper = (StudentMapper) context.getBean("studentMapper");
		TeacherMapper teacherMapper = (TeacherMapper) context.getBean("teacherMapper");

		Teacher teacher = new Teacher();
		teacher.setName("t600");

		teacherMapper.addTeacher(teacher);
		System.out.println("yes");
	}

	@Test
	public void testAddStudent() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentMapper studentMapper = (StudentMapper) context.getBean("studentMapper");
		TeacherMapper teacherMapper = (TeacherMapper) context.getBean("teacherMapper");

		Student student = new Student();
		student.setName("wangwu");
		Teacher teacher = new Teacher();
		teacher.setId("2");
		teacher.setName("t600");

		student.getTeachers().add(teacher);
		studentMapper.addStudent(student);

		System.out.println("yes");
	}
}
