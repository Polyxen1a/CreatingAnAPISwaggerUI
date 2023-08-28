package ru.hogwarts.school.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.models.Avatar;
import ru.hogwarts.school.models.StudentDTO;
import ru.hogwarts.school.repository.AvatarRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Service
@Transactional

public class AvatarService {
    @Value("${application.avatars.folder}")
    private String avatarsDir;
    private final AvatarRepository avatarRepository;
    private final StudentService studentService;

    public AvatarService(String avatarsDir, AvatarRepository avatarRepository, StudentService studentService) {
        this.avatarsDir = avatarsDir;
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }

    public Avatar findAvatar(Long avatarID) {
        return avatarRepository.findById(avatarID).orElse(null);
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    public void uploadAvatar(Long studentID, MultipartFile file) throws IOException {
        StudentDTO student = studentService.getStudentByID(Math.toIntExact(studentID));
        Path filePath = Path.of(avatarsDir, studentID + "." + getExtension(Objects.requireNonNull(file.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }

        Avatar avatar = findAvatar(studentID);
        avatar.setStudent(student.toStudent());
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatarRepository.save(avatar);

    }
}
