package org.example.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.objects.Product;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Game implements UiInterface {
    @Override
    public void setupGame(List<Product> result) {
        // Maak een ObjectMapper instantie voor Jackson
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Zet de lijst van producten om naar JSON en sla deze op in een bestand
            objectMapper.writeValue(new File("gameSetup.json"), result);

            // Log het resultaat in de console (optioneel)
            System.out.println("Setup Game:");
            for (Product p : result) {
                System.out.println("    Product: " + p.getNaam());
                List<Product> ingredienten = p.getIngredienten();
                System.out.println("    Ingrediënten: ");
                for (Product ingredient : ingredienten) {
                    System.out.println("        " + ingredient.getNaam());
                }
            }

            System.out.println("Game setup saved to 'gameSetup.json'.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void joinGame() {
        System.out.println("Join Game");
    }
}
