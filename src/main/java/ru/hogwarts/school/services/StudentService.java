package ru.hogwarts.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.controllers.StudentNotFoundException;
import ru.hogwarts.school.models.Student;
import ru.hogwarts.school.models.StudentDTO;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student newStudent) {
        logger.debug("Добавить нового студента {}", newStudent.getName());
        return studentRepository.save(newStudent);
    }

    public StudentDTO getStudentByID(long id) {
        logger.debug("Писк студента по ID {}", id);
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));
        return StudentDTO.fromStudent(student);
    }

    public List<StudentDTO> getAllStudents(Integer page, Integer size) {
        if (size > 50 || size <= 0) {
            size = 50;
        }
        logger.info("Перечень всех студентов");
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Student> students = studentRepository.findAll(pageRequest);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student s : students) {
            StudentDTO studentDTO = StudentDTO.fromStudent(s);
            studentDTOS.add(studentDTO);
        }
        logger.info("Нашлось {} студентов", studentDTOS.size());
        return studentDTOS;
    }


    public Student updateStudent(Student updatedStudent) {
        logger.debug("Обновить данные студента {}", updatedStudent.getId());
        return studentRepository.save(updatedStudent);
    }

    public void deleteStudent(Student deleteStudent) {
        logger.debug("Удалить студента {}", deleteStudent.getId());
        studentRepository.delete(deleteStudent);
    }

    public List<Student> findStudentsByAge(int age) {
        logger.debug("Вывести список студентов по возрасту {}", age);
        return studentRepository.findStudentsByAge(age);
    }

    public List<Student> findByAgeBetween(int min, int max) {
        logger.debug("Вывести список студентов с {} по {} лет", min,max);
        return studentRepository.findByAgeBetween(min, max);
    }

    public int amountOfStudents() {
        logger.info("Список общего количества студентов");
        return studentRepository.getAmountOfStudents();
    }

    public double getAverageAge() {
        logger.info("Показать средний возраст обучающихся студентов");
        return studentRepository.getAverageAgeOfStudents();
    }

    public List<Student> getYoungestStudents() {
        logger.info("Показать пять наиболее молодых студентов");
        return studentRepository.getYoungestStudents();
    }
}