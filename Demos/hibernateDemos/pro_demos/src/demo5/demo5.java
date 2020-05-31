package demo5;

import org.hibernate.Session;
import org.hibernate.Transaction;

import demo3.utils.HibernateUtils;
import demo5.entity.Student;
import demo5.entity.Teacher;

/*
 * 级联操作 一对多
 */
public class demo5 {
	public static void main(String[] args) {

		Session session = HibernateUtils.getSession();
		Transaction ts = session.beginTransaction();

		Teacher teacher = new Teacher();
		Teacher teacher1 = new Teacher();

		Student student = new Student();
		Student student1 = new Student();

		teacher.setName("jack");
		teacher1.setName("joy");

		student.setScore(22.0);
		student1.setScore(100.0);

		teacher.getStudents().add(student);
		teacher.getStudents().add(student1);
		teacher1.getStudents().add(student);
		teacher1.getStudents().add(student1);

		// student.getTeachers().add(teacher);
		// student.getTeachers().add(teacher1);
		// student1.getTeachers().add(teacher);
		// student1.getTeachers().add(teacher1);

		session.save(teacher);
		session.save(teacher1);
		session.save(student);
		session.save(student1);

		ts.commit();
	}

}
