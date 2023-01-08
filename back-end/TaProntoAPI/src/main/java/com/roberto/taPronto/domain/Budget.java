package com.roberto.taPronto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "budget")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Budget implements Serializable {

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

    @Column(name = "budget_date")
    private Instant budgetDate;

    @Column(name = "approval")
    private Boolean approval;

    @Column(name = "budget_approval_date")
    private Instant budgetApprovalDate;

    public void setApproval(Boolean approval) {
        this.approval = approval;

        if (approval) {
            this.budgetApprovalDate = Instant.now();
        }
    }
}
