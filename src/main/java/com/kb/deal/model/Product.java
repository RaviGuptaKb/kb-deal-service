package com.kb.deal.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "preview_image")
    private String previewImage;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_string")
    private String productString;

    @Column(name = "updated_on")
    private java.sql.Timestamp updatedOn;

    @Column(name = "brand_model_category_id")
    private Long brandModelCategoryId;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "legal_disclaimer_description")
    private String legalDisclaimerDescription;

    @Column(name = "manufacturer_contact_number")
    private String manufacturerContactNumber;

    @Column(name = "on_offer")
    private Boolean onOffer;
}
