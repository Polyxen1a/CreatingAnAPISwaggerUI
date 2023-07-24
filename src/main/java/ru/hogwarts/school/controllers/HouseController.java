package ru.hogwarts.school.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.models.Faculty;
import ru.hogwarts.school.services.HouseServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/faculty")


public class HouseController {

    private HouseServiceImpl houseService;

    @Autowired
    public HouseController(HouseServiceImpl houseService) {
        this.houseService = houseService;
    }

    @Operation(description = "Добавление нового факультета.")
    public ResponseEntity addNewHouse(@RequestBody Faculty newFaculty) {
        return ResponseEntity.ok("Новый факультет: " + houseService.addHouse(newFaculty));
    }

    @GetMapping("/{id}")
    @Operation(description = "Получение факультета по номеру.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1,2,15....")
    })
    public ResponseEntity<Faculty> getHouse(@RequestParam int id) {
        Faculty faculty = houseService.getHouseByID(id);
        if (faculty == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/getall")
    @Operation(description = "Получение всех факультетов.")
    public ResponseEntity<Map<Integer, Faculty>> getAllHouses() {
        Map<Integer, Faculty> houseMap = houseService.getAllHouses();
        if (houseMap.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(houseMap);
    }

    @PutMapping("/edit/{id}")
    @Operation(description = "Редактирование факультетов по номеру.")
    public Faculty putStudent(@RequestBody Faculty house, @PathVariable int id) {
        Faculty updateHouse = houseService.updateHouse(id, house);
        return house;
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Удаление факультета по номеру.")
    @Parameters(value = {
            @Parameter(name = "id", example = "1,2,15....")
    })
    public ResponseEntity<String> deleteHouse(@PathVariable int id) {
        houseService.deleteHouse(id);
        return ResponseEntity.ok("Факультет под номером " + id + " удалён из списка.");
    }

    @GetMapping("/findbycolor/{color}")
    @Operation(description = "Получение списка факультетов по цвету.")
    @Parameters(value = {
            @Parameter(name = "color", example = "red, green")
    })
    public ResponseEntity<List<Faculty>> getAllHousesbyColor(@RequestParam String color) {
        List<Faculty> faculties = houseService.getSpecificColorHouse(color);
        return ResponseEntity.ok(faculties);
    }
}
