package ru.hogwarts.school.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class InfoController {
    @Value("${app.name}")
    private String appName;
    @Value("${app.version}")
    private String appVersion;
    @Value("${app.env}")
    private String appEnvironment;
    private List<String> response = new ArrayList<>();

    @RequestMapping(value = "/appInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public List getAppInfo() {
        response.add(appName);
        response.add(appVersion);
        response.add(appEnvironment);
        return Collections.singletonList(appName);
    }
}
