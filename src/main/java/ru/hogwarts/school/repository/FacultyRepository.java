package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.models.Faculty;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class FacultyRepository implements JpaRepository<Faculty, Integer> {

    public abstract List<Faculty> findByColor(String findColor);
    public abstract List<Faculty> findByNameIgnoreCase(String name);
}
