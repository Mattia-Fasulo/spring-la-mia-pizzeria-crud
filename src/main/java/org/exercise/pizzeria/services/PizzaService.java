package org.exercise.pizzeria.services;

import org.exercise.pizzeria.model.Pizza;
import org.exercise.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> getAll(){
        return pizzaRepository.findAll();
    }

    public List<Pizza> getByName(String keyword){
        return pizzaRepository.findByNameContainingIgnoreCase(keyword);
    }

    public Pizza getById(Integer id){

        //pizzaRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND))

        Optional<Pizza> result = pizzaRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public List<Pizza> getByDescriptionAndPrice(Optional<String> ingredients, Optional<BigDecimal> price){
        return pizzaRepository.findByDescriptionContainingAndPriceIsLessThanEqual(ingredients.orElse(null), price.orElse(BigDecimal.valueOf(1000)));
    }
}