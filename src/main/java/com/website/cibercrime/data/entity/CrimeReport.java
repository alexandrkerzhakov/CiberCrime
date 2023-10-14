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
public class CrimeReport extends AbstractEntity {
    private Integer messageNumber;
    private LocalDate messageDate;
    @NotEmpty
    private String message;
    public CrimeReport() {
        this.messageNumber = 30;
        this.messageDate = LocalDate.now();
        this.message = "Сотрудники ЦБ";
    }
    @Override
    public String toString() {
        return "CrimeReport{" +
                "id=" + this.getId() +
                ", messageNumber=" + messageNumber +
                ", messageDate=" + messageDate +
                ", message='" + message + '\'' +
                '}';
    }
}
