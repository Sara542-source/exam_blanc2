package org.example.examblanc_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.management.remote.JMXConnectorFactory.connect;

public class MembreDaoImpl implements MembreDao{
    private DatabaseManager databaseManager = null;


    protected MembreDaoImpl() {
        this.databaseManager = databaseManager;
    }

    public boolean insere(Membre membre) {
        String insertSQL = "INSERT INTO membre (nom, prenom, email, phone) VALUES (?, ?, ?, ?)";
        try (Connection connection = databaseManager.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, membre.getNom());
            preparedStatement.setString(2, membre.getPrenom());
            preparedStatement.setString(3, membre.getEmail());
            preparedStatement.setString(4, membre.getPhone());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public int chargerListeMembre(String nomFichier) {
        Set<Membre> membres = new HashSet<>();
        int membresInseres = 0;

        // Lecture du fichier CSV
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;

            while ((ligne = br.readLine()) != null) {
                String[] details = ligne.split(",");

                // Vérifiez qu'il y a exactement 4 colonnes dans la ligne
                if (details.length == 4) {
                    String nom = details[0].trim();
                    String prenom = details[1].trim();
                    String email = details[2].trim();
                    String phone = details[3].trim();

                    // Créer un objet Membre
                    Membre membre = new Membre(nom, prenom, email, phone);

                    // Ajoutez le membre dans le Set (filtrage des doublons basé sur equals/hashCode)
                    membres.add(membre);
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            return 0;
        }

        // Insérer les membres dans la base de données
        for (Membre membre : membres) {
            if (insere(membre)) {
                membresInseres++;
            }
        }

        return membresInseres;
    }
    @Override
    public List<Incident> chargerListIncidents(int membreId) {
        List<Incident> incidents = new ArrayList<>();
        String querySQL = "SELECT id, reference, time, status FROM incidentdb WHERE membre_id = ?";

        try (Connection connection = databaseManager.connect(); // Assurez-vous que DatabaseManager est bien accessible
             PreparedStatement preparedStatement = connection.prepareStatement(querySQL)) {

            preparedStatement.setInt(1, membreId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Incident incident = new Incident();
                    incident.SetRference(resultSet.getString("reference"));
                    incident.SetTime(resultSet.getDate("time").toLocalDate());
                    incident.SetStatus(resultSet.getString("status"));
                    incident.setMembreId(membreId);

                    incidents.add(incident);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return incidents;
    }
    //public static void main(String[] args) {
    // DatabaseManager dbManager = new DatabaseManager(); // Votre classe pour gérer les connexions DB
      //  MembreDaoImpl membreDao = new MembreDaoImpl(dbManager);

       // int membresInseres = membreDao.chargerListeMembre("membres.csv");
    // System.out.println("Nombre de membres insérés : " + membresInseres);
   // }
    }


