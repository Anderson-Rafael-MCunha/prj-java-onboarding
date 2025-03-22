package com.prj.agile.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "Client")
@Table(name = "client",
        schema= "onboarding",
        uniqueConstraints = {
            @UniqueConstraint(name="client_document_unique", columnNames = "document"),
            @UniqueConstraint(name="client_email_unique", columnNames = "email")
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

    @Column(name="birth_date", columnDefinition = "DATE", nullable = false)
    private Date birthDate;

    @Column(name="created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private Date createdAt;

    @Column(name="pep", columnDefinition = "BOOLEAN", nullable = false)
    private Boolean pep;

    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR")
    private String status;

    @OneToOne
    @JoinColumn(name = "client_type_id")
    private ClientType clientType;


    public Client(String document, String name, Date birthDate, Date createdAt, Boolean pep, String email, String status) {
        this.document = document;
        this.name = name;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.pep = pep;
        this.email = email;
        this.status = status;
    }
}