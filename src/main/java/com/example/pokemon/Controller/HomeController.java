package com.example.pokemon.Controller;

import com.example.pokemon.Model.Pokemon;
import com.example.pokemon.Repository.PokemonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

   PokemonRepository pokemonRepository;
    public HomeController(PokemonRepository pokemonRepository){
        this.pokemonRepository=pokemonRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("pokemons", pokemonRepository.getAll());
        return "index";
    }

    //fra anchor i index (anchor = a href)
    @GetMapping("/create")
    public String showCreate(){
        return "create";
    }

    //fra form action
    @PostMapping("/create")
    public String createPokemon(@RequestParam("Pokedex-number") int newPokedex_number,
                                @RequestParam("Pokemon-name") String newName,
                                @RequestParam("Speed") int newSpeed,
                                @RequestParam("Special-defence") int newSpecial_defence,
                                @RequestParam("Special-attack") int newSpecial_attack,
                                @RequestParam("Defence") int newDefence,
                                @RequestParam("Attack") int newAttack,
                                @RequestParam("HP") int newHP,
                                @RequestParam("Primary-type") String newPrimary_type,
                                @RequestParam("Secondary-type") String newSecondary_type){

       //lave ny Pokemon
        Pokemon newPokemon = new Pokemon();
        newPokemon.setPokedex_number(newPokedex_number);
        newPokemon.setName(newName);
        newPokemon.setSpeed(newSpeed);
        newPokemon.setSpecial_defence(newSpecial_defence);
        newPokemon.setSpecial_attack(newSpecial_attack);
        newPokemon.setDefence(newDefence);
        newPokemon.setAttack(newAttack);
        newPokemon.setHp(newHP);
        newPokemon.setPrimary_type(newPrimary_type);
        newPokemon.setSecondary_type(newSecondary_type);

        //gem ny Pokemon
        pokemonRepository.addPokemon(newPokemon);

        //Tilbage til Pokemonlisten
        return "redirect:/";
    }

    //vis updateside for produkt ud fra parameter i URL
    @GetMapping("/update/{pokedex_number}")
    public String showUpdate(@PathVariable("pokedex_number") int updatePokedex_number, Model model) {

        //find Pokemon med pokedex_number lig update pokedex_number i databasen
        Pokemon updatePokemon = pokemonRepository.findPokemonById(updatePokedex_number);

        //Tilføj pokemon til viewmodel, så det kan bruges i thymeleaf
        model.addAttribute("pokemon", updatePokemon);

        //Fortæl Spring hvilken HTML-side der skal vises.
        return "update";
    }

    @PostMapping("/update")
    public String updatePokemon(@RequestParam("Name") String updateName,
                                @RequestParam("Speed") int updateSpeed,
                                @RequestParam("Special-defence") int updateSpecial_defence,
                                @RequestParam("Special-attack") int updateSpecial_attack,
                                @RequestParam("Defence") int updateDefence,
                                @RequestParam("Attack") int updateAttack,
                                @RequestParam("HP") int updateHp,
                                @RequestParam("Primary-type") String updatePrimary_type,
                                @RequestParam("Secondary-type") String updateSecondary_type,
                                @RequestParam("Pokedex-number") int updatePokedex_number){
        //Lave Pokemon ud fra parameter
        Pokemon updatePokemon = new Pokemon(updatePokedex_number, updateName, updateSpeed,
                            updateSpecial_defence, updateSpecial_attack, updateDefence, updateAttack,
                            updateHp, updatePrimary_type, updateSecondary_type);

        //Kald opdater i repository
        pokemonRepository.updatePokemon(updatePokemon);

        //Rediriger til index
        return "redirect:/";
    }



}
