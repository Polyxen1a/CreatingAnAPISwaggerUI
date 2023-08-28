package ru.hogwarts.school.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import java.util.Objects;

@Getter
@Entity
@Data
@NoArgsConstructor


public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToOne()
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;
    public Student(long id, String name, int age, Faculty faculty, Avatar avatar) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.faculty = faculty;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && age == student.age && name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
