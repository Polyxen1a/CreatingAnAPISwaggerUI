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

@RestController
@RequestMapping("/student")
@Tag(name = "Контроллёр студентов", description = "CRUD-операции студентов. ")

public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Добавление нового студента.")
    public ResponseEntity<String> addNewStudent(@RequestBody Student newStudent) {
        Student addedStudent = studentService.addStudent(newStudent);
        return ResponseEntity.ok("Новый студент: " + addedStudent);
    }

    @GetMapping("/{id}")
    @Operation(description = "Получение студента по номеру.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1,2,15...")
    })
    public ResponseEntity<StudentDTO> getStudent(@PathVariable long id) {
        try {
        StudentDTO studentDTO = studentService.getStudentByID(id);
            return ResponseEntity.ok(studentDTO);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    @Operation(description = "Получение списка студентов от - до.")
    public ResponseEntity<List<StudentDTO>> getAllStudents(@RequestParam ("page") Integer pageNum, @RequestParam ("size") Integer pageSize) {
        List<StudentDTO> studentMap = studentService.getAllStudents(pageNum, pageSize);
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

    @GetMapping("/findByAge/{age}")
    @Operation(description = "Получение списка студентов по возрасту.")
    @Parameters(value = {
            @Parameter(name = "age", example = "18, 21, 25")
    })
    public ResponseEntity<List<Student>> getAllStudentsByAge(@RequestParam int age) {
        List<Student> studentList = studentService.findStudentsByAge(age);
        return ResponseEntity.ok(studentList);
    }
    @GetMapping("/findByAgeBetween/{min}-{max}")
    @Operation(description = "Получение списка студентов по возрастному промежутку.")
    @Parameters(value = {
            @Parameter(name = "age", example = "18-25")
    })
    public ResponseEntity<List<Student>> getAllStudentsByAgeBetween(@RequestParam int min, int max) {
        List<Student> studentList = studentService.findByAgeBetween(min, max);
        return ResponseEntity.ok(studentList);
    }
        @GetMapping("/getSumoOfStudents")
        @Operation(description = "Получение общего числа студентов")
        public ResponseEntity<Integer> amountOfStudents(@RequestBody Student newStudent) {
            return ResponseEntity.ok(studentService.amountOfStudents());
        }
    @GetMapping("/getAverageAgeOfStudents")
    @Operation(description = "Получение среднего возраста студентов")
    public ResponseEntity<Integer> getAverageAge(@RequestBody Student newStudent) {
        return ResponseEntity.ok(studentService.getAverageAge());
    }

    @GetMapping("/getYoungestStudents")
    @Operation(description = "Получение списка самы молодых студентов")

    public ResponseEntity<List<Student>> getYoungestStudents() {
        List<Student> studentList = studentService.getYoungestStudents();
        return ResponseEntity.ok(studentList);
    }
}
