package com.roberto.taPronto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "service")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "collaborator_id")
    private User collaborator;

    @Column(name = "service_date")
    private Instant serviceDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "budget", referencedColumnName = "id")
    private Budget budget;

    @Column(name = "finished")
    private Boolean finished;
}
