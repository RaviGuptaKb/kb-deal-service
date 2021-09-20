package com.kb.deal.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
/**
 * @author Ravi Gupta
 */
@Entity
@Getter
@Setter
@ToString
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
}
