package com.prj.agile.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Client")
@Table(name = "client",
        schema= "onboarding",
        uniqueConstraints = {
            @UniqueConstraint(name="client_document_unique", columnNames = "document")
        }
)
public class Client {

    @Id
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName="client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "document", nullable = false, columnDefinition = "TEXT")
    private String document;

    @Column(name="name", columnDefinition = "TEXT", nullable = false)
    private String name;

    public Client(String document, String name){
        this.document = document;
        this.name = name;
    }

}