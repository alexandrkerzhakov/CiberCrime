package com.website.cibercrime.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity(name="IP")
@Getter
@Setter

public class IP extends AbstractEntity {
    private String ip;
    private String ipDate;
    private String time;

    public IP() {

    }

    public IP(String ip, String ipDate, String time) {
        this.ip = ip;
        this.ipDate = ipDate;
        this.time = time;
    }

    @Override
    public String toString() {
        return "IP{" +
                "id=" + this.getId() +
                ", ip='" + ip + '\'' +
                ", ipDate='" + ipDate + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IP ip1 = (IP) o;
        return Objects.equals(ip, ip1.ip) && Objects.equals(ipDate, ip1.ipDate) && Objects.equals(time, ip1.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, ipDate, time);
    }
}
