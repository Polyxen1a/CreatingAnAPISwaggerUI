package ru.hogwarts.school.services;


import ru.hogwarts.school.models.Faculty;

import java.util.List;
import java.util.Map;


public interface HouseService {
    Faculty addHouse(Faculty newFaculty);

    Faculty getHouseByID(int id);

    Map<Integer, Faculty> getAllHouses();

    Faculty updateHouse(int id, Faculty updatedFaculty);

    void deleteHouse(int id);

    List<Faculty> getSpecificColorHouse(String findColor);
}
