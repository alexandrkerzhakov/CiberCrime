package com.website.cibercrime.data.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@Entity(name = "accountVK")
@Getter
@Setter

public class AccountVK extends AbstractEntity {
    private String identifier;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "accountvk_id")
    private List<IP> ipList = new ArrayList<>();

    public AccountVK() {
//        this.identifier = "id\\";
    }

    @Override
    public String toString() {
        return "AccountVK{" +
                "id=" + this.getId() +
                ", identifier='" + identifier + '\'' +
                ", ipList=" + ipList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountVK accountVK = (AccountVK) o;
        return Objects.equals(identifier, accountVK.identifier) && Objects.equals(ipList, accountVK.ipList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, ipList);
    }
}
