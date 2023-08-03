package ru.hogwarts.school.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

import java.util.Objects;

@Data
@NoArgsConstructor
@Entity

public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String name;
    private String color;

    @OneToMany(mappedBy = "faculty")
    private Set<Student> students;

    public Faculty(long id, String name, String color, Set<Student> students) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.students = students;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id == faculty.id && name.equals(faculty.name) && color.equals(faculty.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }
}
