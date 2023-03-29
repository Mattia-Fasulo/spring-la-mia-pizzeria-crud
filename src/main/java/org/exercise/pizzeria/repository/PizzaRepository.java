package org.exercise.pizzeria.repository;

import org.exercise.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
