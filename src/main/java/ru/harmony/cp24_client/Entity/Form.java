package ru.harmony.cp24_client.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Form {
    private String formHeader;
    private String aspSpec;
    private String name;
    private String surName;
    private String lastName;
    private String fio = surName + ' ' + name + ' ' + lastName;
    private String aspSkills;
    private String workExperience;
    private String education;
    private String workBefore;

    private String aspBirthDate;

    private Vacancy vacancy;
}
