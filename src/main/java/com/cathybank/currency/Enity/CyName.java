package com.cathybank.currency.Enity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CYNAME")
@Getter
@Setter
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class CyName {
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "CRUCHTNAME")
    private String cyChtName;
    @Column(name = "CURRENCY")
    private String cyName;

    @Override
    public String toString() {
        return "CyName{" +
                "id=" + id +
                ", cyChtName='" + cyChtName + '\'' +
                ", cyName='" + cyName + '\'' +
                '}';
    }
}
