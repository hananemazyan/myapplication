package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class Result_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        // Récupérer la ville mentionnée dans le formulaire depuis l'intent
        String ville = getIntent().getStringExtra("villementionee");

        // Afficher le nombre d'annonces pour la ville sélectionnée
        afficherNombreAnnonces(ville);
    }

    // Méthode pour afficher le nombre d'annonces pour une ville spécifique
    private void afficherNombreAnnonces(String ville) {
        // Vérifier si la ville est non nulle
        if (ville != null) {
            // Créer une instance de DatabaseHelper (remplacer DBHelper par le nom de votre classe DBHelper)
            DBHelper db = new DBHelper(this);

            // Obtenir le nombre d'annonces pour la ville spécifiée
            int nombreAnnonces = db.compterAnnoncesPourVille(ville);

            // Trouver le TextView dans le layout
            TextView textViewAnnounceCount = findViewById(R.id.textView_announce_count);

            // Afficher le nombre d'annonces dans le TextView
            textViewAnnounceCount.setText("Nombre d'annonces pour " + ville + ": " + nombreAnnonces);
        } else {
            // Afficher un message d'erreur si la ville n'est pas spécifiée
            TextView textViewAnnounceCount = findViewById(R.id.textView_announce_count);
            textViewAnnounceCount.setText("Erreur: Aucune ville spécifiée");
        }
    }
}