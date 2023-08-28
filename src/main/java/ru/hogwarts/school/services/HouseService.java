package ru.hogwarts.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repository.FacultyRepository;

import ru.hogwarts.school.models.FacultyDTO;

import java.util.ArrayList;

import ru.hogwarts.school.models.Faculty;

import java.util.List;
@Service

public class HouseService {

    @Autowired
    private final FacultyRepository facultyRepository;
    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public Faculty addHouse(Faculty newFaculty) {
        return facultyRepository.save(newFaculty);
    }

    public FacultyDTO getHouseByID(int id) {
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        assert faculty != null;
        return FacultyDTO.fromFaculty(faculty);
    }

    public List<FacultyDTO> getAllHouses() {
        List<Faculty> houses = facultyRepository.findAll();
        List<FacultyDTO> facultyDTOS = new ArrayList<>();
        for (Faculty f : houses) {
            FacultyDTO facultyDTO = FacultyDTO.fromFaculty(f);
            facultyDTOS.add(facultyDTO);
        }
        return facultyDTOS;
    }

    public Faculty updateHouse(Faculty updatedFaculty) {
        return facultyRepository.save(updatedFaculty);
    }

    public void deleteHouse(Faculty updatedFaculty) {
        facultyRepository.delete(updatedFaculty);
    }

    public List<Faculty> findByColor(String findColor) {
        return facultyRepository.findByColor(findColor);
    }

    public List<Faculty> findByName(String name) {
        return facultyRepository.findByNameIgnoreCase(name);
    }
}
