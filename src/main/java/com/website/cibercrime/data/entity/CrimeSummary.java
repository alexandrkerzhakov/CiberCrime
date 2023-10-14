package com.website.cibercrime.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Entity(name = "crimeSummary")
@Getter
@Setter
public class CrimeSummary extends AbstractEntity {
    @NotEmpty
    private String areaComboBox;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CrimeReport crimeReport = new CrimeReport();
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CrimeCase crimeCase = new CrimeCase();
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Claimant claimant = new Claimant();
    public CrimeSummary() {

    }

    @Override
    public String toString() {
        return "CrimeSummary{" +
                "id=" + this.getId() +
                ", areaComboBox='" + areaComboBox + '\'' +
                ", crimeReport=" + crimeReport +
                ", crimeCase=" + crimeCase +
                ", claimant=" + claimant +
                '}';
    }
}
