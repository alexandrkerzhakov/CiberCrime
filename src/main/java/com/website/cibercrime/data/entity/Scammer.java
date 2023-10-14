package com.website.cibercrime.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "scammer")
@Getter
@Setter
public class Scammer extends AbstractEntity {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String secondName;
    @NotEmpty
    private String fatherName;
    @Email
    private String email;
    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "scammer_id")
    private List<Phone> phoneList = new ArrayList<>();

    public Scammer() {
    }

    public Scammer(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Scammer{" +
                "id=" + this.getId() +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", email='" + email + '\'' +
                ", phoneList=" + phoneList +
                '}';
    }
}


