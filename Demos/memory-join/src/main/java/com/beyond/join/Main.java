package com.beyond.join;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<School> schools = new ArrayList<>();
        School school1 = new School();
        school1.setSchoolId("1");
        school1.setSchoolName("SchoolName1");
        School school2 = new School();
        school2.setSchoolId("2");
        school2.setSchoolName("SchoolName2");
        School school3 = new School();
        school3.setSchoolId("3");
        school3.setSchoolName("SchoolName3");
        schools.add(school1);
        schools.add(school2);
        schools.add(school3);


        List<Student> students = new ArrayList<>();
        Student student1 = new Student();
        student1.setSchoolId("1");
        student1.setStudentId("1");
        student1.setStudentName("studentName1");
        Student student2 = new Student();
        student2.setSchoolId("1");
        student2.setStudentId("2");
        student2.setStudentName("studentName2");
        Student student3 = new Student();
        student3.setSchoolId("2");
        student3.setStudentId("3");
        student3.setStudentName("studentName3");
        students.add(student1);
        students.add(student2);
        students.add(student3);

        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setBookId("1");
        book1.setStudentId("1");
        book1.setBookName("bookName1");
        Book book2 = new Book();
        book2.setBookId("2");
        book2.setStudentId("1");
        book2.setBookName("bookName2");
        Book book3 = new Book();
        book3.setBookId("3");
        book3.setStudentId("3");
        book3.setBookName("bookName3");
        books.add(book1);
        books.add(book2);
        books.add(book3);

        List<BookExt> bookExts = new ArrayList<>();
        BookExt bookExt1 = new BookExt();
        bookExt1.setBookId("1");
        bookExt1.setPrice(BigDecimal.valueOf(234.3));
        BookExt bookExt2 = new BookExt();
        bookExt2.setBookId("2");
        bookExt2.setPrice(BigDecimal.valueOf(234.3));
        bookExts.add(bookExt1);
        bookExts.add(bookExt2);


        JoinUtils.JoinResultChain joinResultChain = JoinUtils.start(schools, School.class)
                .one2ManyJoin(students, Student.class, School::getSchoolId, Student::getSchoolId)
                .one2ManyJoin(books, Book.class, Student::getStudentId, Book::getStudentId)
                .one2OneJoin(bookExts, BookExt.class, Book::getBookId, BookExt::getBookId)
                .end();

        Map<Book, BookExt> student2BooksMap = joinResultChain.getOne2OneMap(Book.class, BookExt.class);
        System.out.println(student2BooksMap);


        JoinUtils.JoinResultChain joinResultChain2 = JoinUtils.start(books, Book.class)
                .one2OneJoin(students, Student.class, Book::getStudentId, Student::getStudentId)
                .end();

        Map<Book, Student> student2BooksMap2 = joinResultChain2.getOne2OneMap(Book.class, Student.class);
        System.out.println(student2BooksMap2);
    }


}
