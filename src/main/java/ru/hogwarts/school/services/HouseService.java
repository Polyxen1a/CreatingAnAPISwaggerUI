package ru.hogwarts.school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.models.Faculty;
import ru.hogwarts.school.models.FacultyDTO;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.List;

@Service

public class HouseService {

    @Autowired
    private final FacultyRepository facultyRepository;
    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    Logger logger = LoggerFactory.getLogger(HouseService.class);

    public Faculty addHouse(Faculty newFaculty) {
        logger.debug("Добавление нового факультета {}", newFaculty.getName());
        return facultyRepository.save(newFaculty);
    }

    public FacultyDTO getHouseByID(int id) {
        logger.debug("Поиск факультета по ID {}", id);
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        assert faculty != null;
        return FacultyDTO.fromFaculty(faculty);
    }

    public List<FacultyDTO> getAllHouses() {
        logger.info("Показать перечень всех факультетов");
        List<Faculty> houses = facultyRepository.findAll();
        List<FacultyDTO> facultyDTOS = new ArrayList<>();
        for (Faculty f : houses) {
            FacultyDTO facultyDTO = FacultyDTO.fromFaculty(f);
            facultyDTOS.add(facultyDTO);
        }
        logger.info("Нашлось {} факультетов", facultyDTOS.size());
        return facultyDTOS;
    }

    public Faculty updateHouse(Faculty updatedFaculty) {
        logger.debug("Обновить данные факультета {}", updatedFaculty.getId());
        return facultyRepository.save(updatedFaculty);
    }

    public void deleteHouse(Faculty updatedFaculty) {
        logger.debug("Удалить факультет {}", updatedFaculty.getId());
        facultyRepository.delete(updatedFaculty);
    }

    public List<Faculty> findByColor(String findColor) {
        logger.debug("Вывести перечень факультетов определённого цвета {}", findColor);
        return facultyRepository.findByColor(findColor);
    }

    public List<Faculty> findByName(String name) {
        logger.debug("Поиск факультета по названию {}", name);
        return facultyRepository.findByNameIgnoreCase(name);
    }
}
