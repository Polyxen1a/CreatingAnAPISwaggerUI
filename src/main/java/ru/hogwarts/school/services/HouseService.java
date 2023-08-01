package ru.hogwarts.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repository.FacultyRepository;


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

    public Faculty getHouseByID(int id) {
        return facultyRepository.findById(id).get();
    }

    public List<Faculty> getAllHouses() {
        return facultyRepository.findAll();
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
}
