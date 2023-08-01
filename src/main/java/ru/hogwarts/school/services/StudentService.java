package ru.hogwarts.school.services;

import ru.hogwarts.school.models.Student;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student addStudent(Student newStudent) {
        return studentRepository.save(newStudent);
    }
    public Student getStudentByID(int id) {
        return studentRepository.findById(id).get();
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Student updatedStudent) {
        return studentRepository.save(updatedStudent);
    }

    public void deleteStudent(Student deleteStudent) {
        studentRepository.delete(deleteStudent);
    }

    public List<Student> findStudentsByAge(int age) {
        return studentRepository.findStudentsByAge(age);
    }
}