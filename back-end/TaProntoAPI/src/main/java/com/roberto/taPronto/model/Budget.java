package com.roberto.taPronto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="budget")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Budget {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="client")
    private User client;

    @Column(name="technician")
    private User technician;

    @Column(name="budget_date")
    private Instant budgetDate;

    @Column(name="approval")
    private Boolean approval;

    @Column(name="budget_approval_date")
    private Instant budgetApprovalDate;
}
