package ru.hogwarts.school.models;

import lombok.Data;

import java.util.Set;

@Data

public class FacultyDTO {
    private long id;
    private String name;
    private String color;
    private Set<Student> students;

    public static FacultyDTO fromFaculty(Faculty faculty) {

        FacultyDTO dto = new FacultyDTO();
        dto.setId(faculty.getId());
        dto.setName(faculty.getName());
        dto.setColor(faculty.getColor());
        dto.setStudents(faculty.getStudents());
        return dto;
    }

    public Faculty toFaculty() {

        Faculty faculty = new Faculty();
        faculty.setId(this.getId());
        faculty.setName(this.name);
        faculty.setColor(this.getColor());
        faculty.setStudents(this.students);
        return faculty;
    }
}
