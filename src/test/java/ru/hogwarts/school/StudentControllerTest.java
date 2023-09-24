package ru.hogwarts.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.school.models.Avatar;
import ru.hogwarts.school.models.Faculty;
import ru.hogwarts.school.models.Student;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)

public class StudentControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void getStudentsByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/student/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void createStudentTest() throws Exception {
        Student student = new Student();
        mvc.perform(MockMvcRequestBuilders.post("/student")
                        .content(String.valueOf(student))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateStudent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/student/edit/{id}", 2)
                        .content(String.valueOf(new Student(1, "Billy", 25, new Faculty(), new Avatar())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Billy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(25));
    }

    @Test
    void deleteStudentTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/student/delete/{id}", 1))
                .andExpect(status().isAccepted());
    }
}
