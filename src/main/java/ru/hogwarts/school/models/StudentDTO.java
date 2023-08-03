package ru.hogwarts.school.models;

import lombok.Data;

@Data

public class StudentDTO {
    private long id;
    private String name;
    private int age;
    private long faculty;

    public static StudentDTO fromStudent(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setFaculty(student.getFaculty().getId());
        return dto;
    }
    public Student toStudent(){
        Student student = new Student();
        student.setId(this.getId());
        student.setName(this.getName());
        student.setAge(this.getAge());
        student.setFaculty(this.toStudent().getFaculty());
        return student;
    }
}
