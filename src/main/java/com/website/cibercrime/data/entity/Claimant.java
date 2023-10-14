package com.website.cibercrime.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Entity(name = "claimant")
@Getter
@Setter
@EqualsAndHashCode
public class Claimant extends AbstractEntity {
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String secondName;

    @NotEmpty
    private String fatherName;

    public Claimant() {
        this.firstName = "default";
        this.secondName = "default";
        this.fatherName = "default";
    }

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "claimant_id")
    private List<Phone> phones = new ArrayList<>();

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "claimant_id")
    private List<IP> ipList = new ArrayList<>();

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "claimant_id")
    private List<AccountVK> accountVKList = new ArrayList<>();


//    @NotNull
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "claimant_id")
//    private AccountVK accountVK = new AccountVK();


    @Override
    public String toString() {
        return "Claimant{" +
                "id=" + this.getId() +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", phones=" + phones +
                ", ipList=" + ipList +
                ", accountVKList=" + accountVKList +
                '}';
    }
}

