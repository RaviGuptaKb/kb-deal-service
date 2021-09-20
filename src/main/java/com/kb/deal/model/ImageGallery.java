package com.kb.deal.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "image_gallery")
public class ImageGallery {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "large_image")
    private String largeImage;

    @Column(name = "medium_image")
    private String mediumImage;

    @Column(name = "small_image")
    private String smallImage;

    @Column(name = "image_1")
    private String image1;

    @Column(name = "image_2")
    private String image2;

    @Column(name = "image_3")
    private String image3;

    @Column(name = "image_4")
    private String image4;

    @Column(name = "image_5")
    private String image5;

    @Column(name = "image_6")
    private String image6;
}
