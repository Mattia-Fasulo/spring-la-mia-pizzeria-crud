package org.exercise.pizzeria.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "must not be empty")
    @Column(name = "name", columnDefinition = "varchar(40)")
    private String name;
    @Lob
    @NotEmpty(message = "must not be empty")
    private String description;
    @NotNull(message = "the price must be greater than zero")
    @Positive
    private BigDecimal price;

    @NotEmpty(message = "must not be empty")
    private String imgPath;

    private LocalDateTime createdAt;

    public Pizza() {
        super();
    }

    public Pizza(String name, String description, BigDecimal price, String imgPath, LocalDateTime createdAt) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgPath = imgPath;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
