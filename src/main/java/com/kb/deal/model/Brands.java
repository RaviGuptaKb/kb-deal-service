package com.kb.deal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
/**
 * @author Ravi Gupta
 */
@Entity
@Getter
@Setter
@Table(name = "brands")
public class Brands {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "thumbnails")
    private String thumbnails;

    @Column(name = "trending")
    private Boolean trending;

    @Override
    public String toString() {
        return "Brands{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", thumbnails='" + thumbnails + '\'' +
                ", trending=" + trending +
                '}';
    }
}
