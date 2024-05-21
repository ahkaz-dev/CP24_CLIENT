package ru.harmony.cp24_client.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.harmony.cp24_client.controller.FormController;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Form {
    private Long id;
    private String formHeader;
    private String aspSpec;
    private String name;
    private String surName;
    private String lastName;
    private String aspSkills;
    private String workExperience;
    private String education;
    private String workBefore;

    private String aspBirthDate;

    private Vacancy vacancy;

}
