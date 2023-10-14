package com.website.cibercrime.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class CrimeCase extends AbstractEntity {
    private Integer caseNumber;
    private LocalDate caseNumberDate;

    public CrimeCase() {
        this.caseNumber = 20;
        this.caseNumberDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "CrimeCase{" +
                "id=" + this.getId() +
                ", caseNumber=" + caseNumber +
                ", caseNumberDate=" + caseNumberDate +
                '}';
    }
}
