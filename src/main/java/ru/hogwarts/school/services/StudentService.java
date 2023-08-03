package ru.hogwarts.school.services;

import ru.hogwarts.school.models.Student;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.models.StudentDTO;
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
    public StudentDTO getStudentByID(int id) {
        Student student = studentRepository.findById(id).orElse(null);
        return StudentDTO.fromStudent(student);
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student s : students) {
            StudentDTO studentDTO = StudentDTO.fromStudent(s);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
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

    public List<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }
}