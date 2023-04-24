package com.example.pokemon.Repository;

import com.example.pokemon.Model.Pokemon;
import com.example.pokemon.utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PokemonRepository {
    //private final static String DB_URL ="jdbc:mysql://localhost:3306/pokedex";
    //private final static String UID = "root";
    //private final String PWD = "Skolekode1";

    @Value("${spring.datasource.url}")
    private String DB_URL;

    @Value("${spring.datasource.username}")
    private String UID;

    @Value("${spring.datasource.password}")
    private String PWD;
    public List<Pokemon> getAll(){
        List<Pokemon> pokemonList = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection(DB_URL, UID, PWD);
            Statement statement = connection.createStatement();
            final String SQL_QUERY = "SELECT * FROM pokemon";
            ResultSet resultSet = statement.executeQuery(SQL_QUERY);
            while (resultSet.next()){
                int pokedex_number = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int speed = resultSet.getInt(3);
                int special_defence = resultSet.getInt(4);
                int special_attack = resultSet.getInt(5);
                int defence = resultSet.getInt(6);
                int attack = resultSet.getInt(7);
                int hp = resultSet.getInt(8);
                String primary_type = resultSet.getString(9);
                String secondary_type = resultSet.getString(10);
                Pokemon pokemon = new Pokemon(pokedex_number, name, speed, special_defence, special_attack, defence, attack, hp, primary_type, secondary_type);
                pokemonList.add(pokemon);
                System.out.println(pokemon);
            }

        }
        catch(SQLException e)
        {
            System.out.println("Could not query database");
            e.printStackTrace();
        }
        return pokemonList;
    }

    public void addPokemon(Pokemon pokemon){
        try {
            //connect to db
            Connection connection = ConnectionManager.getConnection(DB_URL, UID,PWD);
            final String CREATE_QUERY = "INSERT INTO pokemon(pokedex_number, name, speed, special_defence, special_attack, defence, attack, hp, primary_type, secondary_type) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);

            //set attributer i prepared statement
            preparedStatement.setInt(1, pokemon.getPokedex_number());
            preparedStatement.setString(2, pokemon.getName());
            preparedStatement.setInt(3, pokemon.getSpeed());
            preparedStatement.setInt(4, pokemon.getSpecial_defence());
            preparedStatement.setInt(5, pokemon.getSpecial_attack());
            preparedStatement.setInt(6, pokemon.getDefence());
            preparedStatement.setInt(7, pokemon.getAttack());
            preparedStatement.setInt(8, pokemon.getHp());
            preparedStatement.setString(9, pokemon.getPrimary_type());
            preparedStatement.setString(10, pokemon.getSecondary_type());

            //execute statement
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Could not query database");
            e.printStackTrace();
        }
    }

    public void updatePokemon(Pokemon pokemon){
        //SQL statement
        final String UPDATE_QUERY = "UPDATE pokemon SET name = ?, speed = ?, special_defence = ?, special_attack = ?, defence = ?, attack = ? hp = ?, primary_type = ?, secondary_type = ? WHERE pokedex_number = ?";

        try {
            //Connect db
            Connection connection = ConnectionManager.getConnection(DB_URL, UID, PWD);
            //Prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            //Set parameter
            String name = pokemon.getName();
            int speed = pokemon.getSpeed();
            int special_defence = pokemon.getSpecial_defence();
            int special_attack = pokemon.getSpecial_attack();
            int defence = pokemon.getDefence();
            int attack = pokemon.getAttack();
            int hp = pokemon.getHp();
            String primary_type = pokemon.getPrimary_type();
            String secondary_type = pokemon.getSecondary_type();
            int pokedex_number = pokemon.getPokedex_number();

            //set attributer i prepared statement
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, speed);
            preparedStatement.setInt(3, special_defence);
            preparedStatement.setInt(4, special_attack);
            preparedStatement.setInt(5, defence);
            preparedStatement.setInt(6, attack);
            preparedStatement.setInt(7, hp);
            preparedStatement.setString(8, primary_type);
            preparedStatement.setString(9, secondary_type);
            preparedStatement.setInt(10, pokedex_number);

            //Execute statement
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println("Could not update Pokemon");
            e.printStackTrace();
        }
    }

    public Pokemon findPokemonById(int pokedex_number){
        //SQL statement
        final String FIND_QUERY = "SELECT * FROM pokemon WHERE id = ?";
        Pokemon pokemon = new Pokemon();
        pokemon.setPokedex_number(pokedex_number);

        try {
            //DB connection
            Connection connection = ConnectionManager.getConnection(DB_URL, UID, PWD);

            //Prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);

            //Set parameters
            preparedStatement.setInt(1, pokedex_number);

            //Execute statement
            ResultSet resultSet = preparedStatement.executeQuery();

            //Få Pokemon ud af resultatset
            resultSet.next();
            String name = resultSet.getString(2);
            int speed = resultSet.getInt(3);
            int special_defence = resultSet.getInt(4);
            int special_attack = resultSet.getInt(5);
            int defence = resultSet.getInt(6);
            int attack = resultSet.getInt(7);
            int hp = resultSet.getInt(8);
            String primary_type = resultSet.getString(9);
            String secondary_type = resultSet.getString(10);

            pokemon.setName(name);
            pokemon.setSpeed(speed);
            pokemon.setSpecial_defence(special_defence);
            pokemon.setSpecial_attack(special_attack);
            pokemon.setDefence(defence);
            pokemon.setAttack(attack);
            pokemon.setHp(hp);
            pokemon.setPrimary_type(primary_type);
            pokemon.setSecondary_type(secondary_type);




        }catch (SQLException e){
            System.out.println("Could not find Pokemon");
        }
        //return Pokemon
        return pokemon;
    }

    public void deleteById(int pokedex_number){
        //SQL-query
        final String DELETE_QUERY = "DELETE FROM pokemon WHERE pokedex_number=?";

        try{
            //Connect til DB
            Connection connection = ConnectionManager.getConnection(DB_URL, UID, PWD);

            //Create statement
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);

            //Set parameter
            preparedStatement.setInt(1, pokedex_number);

            //Execute statement
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println("Could not delete Pokemon");
        }
    }

}


