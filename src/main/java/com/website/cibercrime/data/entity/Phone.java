package com.website.cibercrime.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "phone")
@Getter
@Setter

public class Phone extends AbstractEntity {
    private String number;
    public Phone(String number) {
        this.number = number;
    }

    public Phone() {

    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + this.getId() +
                ", number='" + number + '\'' +
                '}';
    }
}
