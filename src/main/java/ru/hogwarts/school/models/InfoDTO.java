package ru.hogwarts.school.models;

import lombok.Data;

@Data
public class InfoDTO {
    private String appName = "SchoolLife";
    private String appVersion = "1.0.6";
    private String appEnvironment = "development";

    public InfoDTO(String appName, String appVersion, String appEnvironment) {
        this.appName = appName;
        this.appVersion = appVersion;
        this.appEnvironment = appEnvironment;
    }
}
