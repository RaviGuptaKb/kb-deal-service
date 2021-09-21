package com.kb.deal.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "supplier")
public class Supplier {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "gst_or_udyog_number")
    private String gstOrUdyogNumber;

    @Column(name = "supplier_address")
    private String supplierAddress;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "supplier_address_2")
    private String supplierAddress2;

    @Column(name = "supplier_address_type")
    private String supplierAddressType;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "supplier_email")
    private String supplierEmail;

    @Column(name = "supplier_phone")
    private String supplierPhone;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "state")
    private String state;

    @Column(name = "created_on")
    private java.sql.Timestamp createdOn;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "updated_on")
    private java.sql.Timestamp updatedOn;

    @Column(name = "is_direct_shippment")
    private Byte isDirectShippment;

    @Column(name = "avg_handling_time_per_prod")
    private String avgHandlingTimePerProd;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
