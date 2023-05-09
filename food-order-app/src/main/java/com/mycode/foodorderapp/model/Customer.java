package com.mycode.foodorderapp.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="customer")
@SequenceGenerator(name="customer_id_seq", initialValue=1000)
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "customer_id_seq")
    private long customerId;

    @Column
    private String firstName;

    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;

}
