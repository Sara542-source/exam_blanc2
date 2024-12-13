package org.example.examblanc_3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class HelloController {
    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button inserer;

    private DatabaseManager databaseManager = new DatabaseManager();

    private final MembreDaoImpl membreDao = new MembreDaoImpl() {
        // Implémentation nécessaire pour les méthodes abstraites (si présentes)
    };

    @FXML
    public void initialize() {
        inserer.setOnAction(event -> enregistrerMembre());
    }

    private void enregistrerMembre() {
        // Récupérer les valeurs des champs
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        // Validation des champs
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            System.out.println("Tous les champs doivent être remplis !");
            return;
        }

        // Créer un objet Membre
        Membre membre = new Membre(nom, prenom, email, phone);

        // Appeler la méthode insere pour ajouter dans la base de données
        boolean success = membreDao.insere(membre);

        if (success) {
            System.out.println("Membre ajouté avec succès !");
            // Réinitialiser les champs
            nomField.clear();
            prenomField.clear();
            emailField.clear();
            phoneField.clear();
        } else {
            System.out.println("Erreur lors de l'ajout du membre.");
        }
    }
}