package com.website.cibercrime.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter

public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

//    @Version
//    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public int getVersion() {
//        return version;
//    }

//    @Override
//    public int hashCode() {
//        if (getId() != null) {
//            return getId().hashCode();
//        }
//        return super.hashCode();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (!(obj instanceof AbstractEntity that)) {
//            return false; // null or not an AbstractEntity class
//        }
//        if (getId() != null) {
//            return getId().equals(that.getId());
//        }
//        return super.equals(that);
//    }
}
