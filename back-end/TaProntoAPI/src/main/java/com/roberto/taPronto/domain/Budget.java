package com.roberto.taPronto.domain;

import com.roberto.taPronto.domain.User;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "collaborator_id")
    private User collaborator;

    @Column(name="budget_date")
    private Instant budgetDate;

    @Column(name="approval")
    private Boolean approval;

    @Column(name="budget_approval_date")
    private Instant budgetApprovalDate;
}
