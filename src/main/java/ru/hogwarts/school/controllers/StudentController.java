package ru.hogwarts.school.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.models.Student;
import ru.hogwarts.school.services.StudentService;
import ru.hogwarts.school.models.StudentDTO;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
@Tag(name = "Контроллёр студентов", description = "CRUD-операции студентов. ")

public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Добавление нового студента.")
    public ResponseEntity addNewStudent(@RequestBody Student newStudent) {
        return ResponseEntity.ok("Новый студент: " + studentService.addStudent(newStudent));
    }

    @GetMapping("/{id}")
    @Operation(description = "Получение студента по номеру.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1,2,15....")
    })
    public ResponseEntity<StudentDTO> getStudent(@RequestParam int id) {
        StudentDTO studentDTO = studentService.getStudentByID(id);
        if (studentDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(studentDTO);
    }

    @GetMapping("/getall")
    @Operation(description = "Получение всех студентов.")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> studentMap = studentService.getAllStudents();
        if (studentMap.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(studentMap);
    }

    @PutMapping("/edit/{id}")
    @Operation(description = "Редактирование студента по номеру.")
    public Student putStudent(@RequestBody Student student, @PathVariable int id) {
        Student updateStudent = studentService.updateStudent(student);
        return student;
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Удаление студента по номеру.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1,2,15....")
    })
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(studentService.getStudentByID(id).toStudent());
        return ResponseEntity.ok("Студент под номером " + id + " удалён из списка.");
    }

    @GetMapping("/findbyage/{age}")
    @Operation(description = "Получение списка студентов по возрасту.")
    @Parameters(value = {
            @Parameter(name = "age", example = "18, 21, 25")
    })
    public ResponseEntity<List<Student>> getAllStudentsByAge(@RequestParam int age) {
        List<Student> studentList = studentService.findStudentsByAge(age);
        return ResponseEntity.ok(studentList);
    }
    @GetMapping("/findbyageBetween/{min}-{max}")
    @Operation(description = "Получение списка студентов по возрастному промежутку.")
    @Parameters(value = {
            @Parameter(name = "age", example = "18-25")
    })
    public ResponseEntity<List<Student>> getAllStudentsByAgeBetween(@RequestParam int min,int max) {
        List<Student> studentList = studentService.findByAgeBetween(min,max);
        return ResponseEntity.ok(studentList);
    }
}
