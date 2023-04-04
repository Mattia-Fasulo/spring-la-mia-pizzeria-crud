package org.exercise.pizzeria.controller;

import jakarta.validation.Valid;
import org.exercise.pizzeria.exceptions.PizzaNotFoundException;
import org.exercise.pizzeria.model.Pizza;
import org.exercise.pizzeria.repository.PizzaRepository;
import org.exercise.pizzeria.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;


    @GetMapping
    public String index(Model model,
                        @RequestParam(name = "q") Optional<String> keyword
                        ) {
        List<Pizza> pizzas;
        if(keyword.isEmpty()){
            pizzas = pizzaService.getAll();
        } else {
            pizzas = pizzaService.getByName(keyword.get());
            model.addAttribute("keyword", keyword.get());
        }

        model.addAttribute("list", pizzas);
        return "/pizzas/index";
    }

    @GetMapping("/{pizzaId}")
    public String show(Model model, @PathVariable("pizzaId") Integer id ){
        Pizza p = pizzaService.getById(id);
        model.addAttribute("pizza", p);
        return "/pizzas/show";
    }

    @GetMapping("/search")
    public String search(){
        return "/pizzas/search";
    }

    @GetMapping("/search/result")
    public String searchResult(Model model,
                               @RequestParam(name = "i") Optional<String> ingredients,
                               @RequestParam(name = "p") Optional<BigDecimal> price){
        List<Pizza> pizzas = pizzaService.getByDescriptionAndPrice(ingredients, price);
        model.addAttribute("list", pizzas);
        return "/pizzas/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "/pizzas/create";
    }
    @PostMapping("/create")
    public String doCreate(Model model,
                           @Valid @ModelAttribute("pizza") Pizza formPizza,
                           BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "/pizzas/create";
        }

        pizzaService.createPizza(formPizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable Integer id,
            Model model
    ){
        try {
           Pizza pizza = pizzaService.getById(id);
           model.addAttribute("pizza", pizza);
           return "/pizzas/create";
        } catch(PizzaNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id " + id + " not found");
        }

    }

    @PostMapping("/edit/{id}")
    public String doEdit(
            @PathVariable Integer id,
            @Valid @ModelAttribute("book") Pizza formPizza,
            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/pizzas/create";
        }
        try{
            Pizza updatePizza = pizzaService.updatePizza(formPizza, id);
            return "redirect:/pizzas/" + Integer.toString(updatePizza.getId());
        } catch(PizzaNotFoundException e) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id " + id + " not found");
        }
    }

}
