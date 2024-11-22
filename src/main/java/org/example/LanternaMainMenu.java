package org.example;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import org.example.game.Game;
import org.example.game.UiInterface;
import org.example.objects.Product;

import java.util.ArrayList;
import java.util.List;

public class LanternaMainMenu {
    public void renderMenu() {

        UiInterface game = new Game();

        try {
            // Create a terminal screen
            Screen screen = new DefaultTerminalFactory().createScreen();
            screen.startScreen();

            // Create a GUI
            WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
            BasicWindow window = new BasicWindow("Industria");

            // Create a main panel
            Panel panel = new Panel();
//            panel.setLayoutManager(new GridLayout(1)); // 1-column layout
            panel.setPreferredSize(new TerminalSize(80, 30));

            panel.setTheme(new SimpleTheme(new TextColor.RGB(156, 164, 173), new TextColor.RGB(34, 53, 74)));

            Button orderPlaatsenButton = new Button("Plaats order", () -> {

            });

            Button beherenBedrijf = new Button("Beheer bedrijf", () -> {

            });

            Button beherenLening = new Button("Beheer lening", () -> {

            });


            Panel gamePanel = new Panel();
            gamePanel.setLayoutManager(new GridLayout(1));
            gamePanel.setPreferredSize(new TerminalSize(40, 10));
            gamePanel.addComponent(orderPlaatsenButton);
            gamePanel.addComponent(beherenBedrijf);
            gamePanel.addComponent(beherenLening);

            Button gamePanelButton = new Button("Spelacties", () -> {
                window.setComponent(gamePanel);
            });

            Button exitButton = new Button("Quit", () -> {
                try {
                    screen.stopScreen();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.exit(0);
            });

            TextInputDialogBuilder textInputDialogBuilder = new TextInputDialogBuilder();

            // Setup the game panel
            Panel setupPanel = new Panel();
            setupPanel.setLayoutManager(new GridLayout(1));
            setupPanel.setPreferredSize(new TerminalSize(40, 10)); // Set the panel to be larger (width x height)
            setupPanel.addComponent(new Label("Setup your game!"));
            setupPanel.addComponent(new EmptySpace(new TerminalSize(0, 1))); // Add empty space

            List<Product> products = new ArrayList<>(); // Store created products

            // Create the button to add a product
            Button buttonProduct = new Button("Product aanmaken", () -> {
                TextInputDialog productInputDialog = textInputDialogBuilder.build();
                productInputDialog.setTitle("Productnaam:");

                String productNaam = productInputDialog.showDialog(textGUI);


                if (productNaam != null && !productNaam.trim().isEmpty()) {
                    List<Product> ingredienten = new ArrayList<>(); // Store ingredients for this product

                    // Add ingredients to the product
                    while (true) {
                        TextInputDialog ingredientInputDialog = textInputDialogBuilder.build();
                        ingredientInputDialog.setTitle("Ingrediënt naam:");
                        String ingredientNaam = ingredientInputDialog.showDialog(textGUI);

                        // Stop if the user cancels or provides no input
                        if (ingredientNaam == null || ingredientNaam.trim().isEmpty()) {
                            break; // Stop adding ingredients if input is empty or user cancels
                        }

                        // Add the ingredient to the list
                        ingredienten.add(new Product(ingredientNaam));

                    }

                    // Add the product with its ingredients to the list of products
                    products.add(new Product(productNaam, ingredienten)); // Add the product with its ingredients
                }
            });


            setupPanel.addComponent(buttonProduct);

            // Button to confirm setup and start the game
            Button buttonConfirm = new Button("Confirm Setup", () -> {
                if (products.isEmpty()) {
                    // If no products have been added, display a message
                    new MessageDialogBuilder()
                            .setTitle("No Products")
                            .setText("You must create at least one product!")
                            .build()
                            .showDialog(textGUI);
                } else {
                    // Pass the created products to the game setup
                    game.setupGame(products);

                    setupPanel.addComponent(new Button("Game Started! Press to exit.", () -> {
                        window.setComponent(panel);
                    }));
                }
            });

            setupPanel.addComponent(buttonConfirm);

            // Add the exit button to the panel
            setupPanel.addComponent(exitButton);

            // Add buttons for the main menu
            Button button1 = new Button("Setup game", () -> {
                window.setComponent(setupPanel);
            });
            Button button2 = new Button("Join game", () -> game.joinGame());

            panel.addComponent(new Label("Welkom bij Industria!"));
            panel.addComponent(gamePanelButton);
            panel.addComponent(button1);
            panel.addComponent(button2);
            panel.addComponent(new EmptySpace(new TerminalSize(0, 1))); // Add empty space
            panel.addComponent(exitButton);

            // Set the main menu as the window content
            window.setComponent(panel);

            // Show the window
            textGUI.addWindowAndWait(window);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
