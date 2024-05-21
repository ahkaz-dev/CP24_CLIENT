package ru.harmony.cp24_client.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {

    private String name;
    private String fromEmployer;
    private String wage;
    private String workExperience;
    private String headcount;

    private Spec spec;


    @Override
    public String toString() {
        return "Vacancy{" +
                "name='" + name + '\'' +
                ", fromEmployer='" + fromEmployer + '\'' +
                ", wage='" + wage + '\'' +
                ", workExperience='" + workExperience + '\'' +
                ", headcount='" + headcount + '\'' +
                ", spec=" + spec +
                '}';
    }
}
