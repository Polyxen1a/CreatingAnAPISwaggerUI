package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.models.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar,Long> {

}
