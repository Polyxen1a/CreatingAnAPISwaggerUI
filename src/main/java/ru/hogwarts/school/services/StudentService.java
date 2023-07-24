package ru.hogwarts.school.services;

import ru.hogwarts.school.models.Student;

import java.util.List;
import java.util.Map;


public interface StudentService {
    Student addStudent(Student newStudent);

    Student getStudentByID(int id);

    Map<Integer,Student> getAllStudents();

    Student updateStudent(int id, Student updatedStudent);

    void deleteStudent(int id);

    List<Student> getSpecificAgeStudents(int findAge);
}
