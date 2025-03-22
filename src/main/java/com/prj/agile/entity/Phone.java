package com.prj.agile.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Phone")
@Table(name = "phone",
        schema= "onboarding",
        uniqueConstraints = {
                @UniqueConstraint(name="phone_number_unique", columnNames = "number")
        }
)
public class Phone {
    @Id
    @SequenceGenerator(
            name = "phone_sequence",
            sequenceName="phone_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "area", nullable = false)
    private String area;

    @Column(name = "ddd", nullable = false)
    private String ddd;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "phone_type", nullable = false)
    private String phoneType;

    @Column(name = "phone_status", nullable = false)
    private String phoneStatus;

    @ManyToOne
    @JoinColumn(name = "phone_client_id")
    private Client client;


}