package ru.hogwarts.school.services;

import ru.hogwarts.school.controllers.StudentNotFoundException;
import ru.hogwarts.school.models.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.models.StudentDTO;
import java.util.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;



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
    public StudentDTO getStudentByID(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));
        return StudentDTO.fromStudent(student);
    }

    public List<StudentDTO> getAllStudents(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Student> students = studentRepository.findAllStudents(pageRequest);
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
    public int amountOfStudents() {
        return studentRepository.getAmountOfStudents();
    }

    public int getAverageAge() {
        return studentRepository.getAverageAgeOfStudents();
    }

    public List<Student> getYoungestStudents() {
        return studentRepository.getYoungestStudents();
    }
}