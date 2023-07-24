package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.models.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class StudentServiceImpl implements StudentService {
    private int generateId = 0;
    private static Map<Integer, Student> studentMap = new HashMap();

    @Override
    public Student addStudent(Student newStudent) {
        studentMap.put(generateId, newStudent);
        generateId++;
        return newStudent;
    }

    @Override
    public Student getStudentByID(int id) {
        return studentMap.get(id);
    }

    @Override
    public Map<Integer, Student> getAllStudents() {
        return studentMap;
    }

    @Override
    public Student updateStudent(int id, Student updatedStudent) {
        studentMap.put(id, updatedStudent);
        return updatedStudent;
    }

    @Override
    public void deleteStudent(int id) {
        studentMap.remove(id);
    }

    @Override
    public List<Student> getSpecificAgeStudents(int findAge) {
        List<Student> findStudent = new ArrayList<>();
        for (int i = 0; i < studentMap.size()-1; i++) {
            if (studentMap.get(i).getAge() == findAge) findStudent.add(studentMap.get(i));
        }
        return findStudent;
    }
}
