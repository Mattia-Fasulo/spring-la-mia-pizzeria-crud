package org.exercise.pizzeria.repository;

import org.exercise.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    public List<Pizza> findByNameContainingIgnoreCase(String title);
}
