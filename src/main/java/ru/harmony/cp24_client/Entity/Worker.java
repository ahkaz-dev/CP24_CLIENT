package ru.harmony.cp24_client.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Worker {
    private Long id;
    private String name;
    private String surName;
    private String lastName;

    private String position;

    private Access access;
}
