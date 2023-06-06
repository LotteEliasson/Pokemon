package com.example.pokemon.Repository;

import com.example.pokemon.Model.Login;
import com.example.pokemon.Model.Pokemon;
import com.example.pokemon.Model.UserPokemon;
import com.example.pokemon.utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class LoginRepository {
    @Value("${spring.datasource.url}")
    private String DB_URL;

    @Value("${spring.datasource.username}")
    private String UID;

    @Value("${spring.datasource.password}")
    private String PWD;


        public void addUser(Login login){
            try {
            //Connect to DB
                Connection connection = ConnectionManager.getConnection(DB_URL, UID, PWD);

                final String CREATE_USER = "INSERT INTO pokedex.pokemon_user(user_name, user_passw) VALUES(?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER);

                preparedStatement.setString(1, (login.getUserName()));
                preparedStatement.setString(2, login.getUserPassw());

                preparedStatement.executeUpdate();

                //Mangler at kontrollere om brugernavn er unikt

            } catch (SQLException e) {
                System.out.println("Could not query database");
                e.printStackTrace();
            }
        }

        public void getUserById(int userId){
            Login user = new Login();
            try {
                //Connect to DB
                Connection connection = ConnectionManager.getConnection(DB_URL, UID, PWD);
                Statement statement = connection.createStatement();
                final String GET_USER = "SELECT * pokedex.pokemon_user WHERE user_id=" + userId;
                ResultSet resultSet = statement.executeQuery(GET_USER);




            } catch (SQLException e) {
                System.out.println("Could not query database");
                e.printStackTrace();
            }
        }
}