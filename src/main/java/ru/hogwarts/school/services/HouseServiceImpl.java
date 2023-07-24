package ru.hogwarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.models.Faculty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class HouseServiceImpl implements HouseService {

    private int generateId = 0;
    private static Map<Integer, Faculty> houseMap = new HashMap();

    @Override
    public Faculty addHouse(Faculty newFaculty) {
        houseMap.put(generateId, newFaculty);
        generateId++;
        return newFaculty;
    }

    @Override
    public Faculty getHouseByID(int id) {
        return houseMap.get(id);
    }

    @Override
    public Map<Integer, Faculty> getAllHouses() {
        return houseMap;
    }


    @Override
    public Faculty updateHouse(int id, Faculty updatedFaculty) {
        houseMap.put(id, updatedFaculty);
        return updatedFaculty;
    }

    @Override
    public void deleteHouse(int id) {
        houseMap.remove(id);
    }

    @Override
    public List<Faculty> getSpecificColorHouse(String findColor) {
        List<Faculty> findHouse = new ArrayList<>();
        for (int i = 0; i < houseMap.size() - 1; i++) {
            if (houseMap.get(i).getColor().equals(findColor)) findHouse.add(houseMap.get(i));
        }
        return findHouse;
    }
}
