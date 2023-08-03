package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.models.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findStudentsByAge(int age);
    List<Student> findByAgeBetween(int min, int max);
}
