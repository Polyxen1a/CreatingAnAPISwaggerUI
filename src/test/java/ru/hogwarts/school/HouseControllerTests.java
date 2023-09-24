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
import ru.hogwarts.school.models.Faculty;
import ru.hogwarts.school.models.Student;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)

public class HouseControllerTests {
    @Autowired
    MockMvc mvc;

    @Test
    void getFacultysByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/faculty/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void createFacultyTest() throws Exception {
        Faculty Faculty = new Faculty();
        mvc.perform(MockMvcRequestBuilders.post("/faculty")
                        .content(String.valueOf(Faculty))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateFaculty() throws Exception {
        Set<Student> students = new HashSet<>();
        mvc.perform(MockMvcRequestBuilders.put("/faculty/edit/{id}", 2)
                        .content(String.valueOf(new Faculty(1, "houseName", "RED", students
                        )))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("houseName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value("RED"));
    }

    @Test
    void deleteFacultyTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/faculty/delete/{id}", 1))
                .andExpect(status().isAccepted());
    }
}
