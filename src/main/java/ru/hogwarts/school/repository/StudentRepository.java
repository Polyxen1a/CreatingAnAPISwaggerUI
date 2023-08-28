package ru.hogwarts.school.repository;

import org.springframework.data.domain.Page;
import ru.hogwarts.school.models.Student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    List<Student> findStudentsByAge(int age);
    Page<Student> findAllStudents(Pageable pageable);
    List<Student> findByAgeBetween(int min, int max);

    @Query(value = "select COUNT (*) from students", nativeQuery = true)
    int getAmountOfStudents();
    @Query(value = "select AVG(age) from students", nativeQuery = true)
    int getAverageAgeOfStudents();
    @Query(value = "select * from students order by age desc limit 5", nativeQuery = true)
    List<Student> getYoungestStudents();
}
