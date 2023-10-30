package com.cg.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name="deposits")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @Column(name = "amount", columnDefinition = " DECIMAL(30,0)")
    private BigDecimal amount;
    private LocalDateTime dateTime;
    private Boolean deleted;

    public Deposit() {
    }

    public Deposit(Customer customer) {
        this.customer = customer;
    }

    public Deposit(Long id, Customer customer, BigDecimal amount, LocalDateTime dateTime, Boolean deleted) {
        this.id = id;
        this.customer = customer;
        this.amount = amount;
        this.dateTime = dateTime;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
