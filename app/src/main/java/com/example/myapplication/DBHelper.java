package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myapp.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DBHelper"; // Tag for logging

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the "users" table
        String createUserTableQuery = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "email TEXT, "
                + "password TEXT)";
        db.execSQL(createUserTableQuery);
        Log.d(TAG, "Users table created"); // Log creation message

        // Create the "Annonces" table
        String createAnnoncesTableQuery = "CREATE TABLE IF NOT EXISTS Annonces ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "titre TEXT, "
                + "categorie TEXT, "
                + "secteur TEXT, "
                + "type_contrat TEXT, "
                + "description TEXT, "
                + "ville TEXT)";
        db.execSQL(createAnnoncesTableQuery);
        Log.d(TAG, "Annonces table created"); // Log creation message
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade if necessary
        // You might add ALTER TABLE or other upgrade logic here
    }

    // Methods to manage users
    public void ajouterUtilisateur(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        long newRowId = db.insert("users", null, values);
        db.close();
        Log.d(TAG, "New user inserted with ID: " + newRowId); // Log insertion message
    }

    public boolean verifierConnexion(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", new String[]{"username", "password"},
                "username = ? AND password = ?", new String[]{username, password},
                null, null, null);
        int count = cursor.getCount();
        cursor.close();
        Log.d(TAG, "Verification result count: " + count); // Log verification result
        return count > 0;
    }

    // Method to insert sample user data
    public void populateUsersTable() {
        ajouterUtilisateur("user1@example.com", "password1");
        ajouterUtilisateur("user2@example.com", "password2");

    }

    // Method to add an annonce
    public void ajouterAnnonce(String titre, String categorie, String secteur, String typeContrat, String description, String ville) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titre", titre);
        values.put("categorie", categorie);
        values.put("secteur", secteur);
        values.put("typeContrat", typeContrat);
        values.put("description", description);
        values.put("ville", ville);
        long newRowId = db.insert("Annonces", null, values);
        db.close();
        Log.d(TAG, "New annonce inserted with ID: " + newRowId); // Log insertion message
    }

    // Method to insert sample Annonces data

    public int compterAnnoncesPourVille(String ville) {
        int nombreAnnonces = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM Annonces WHERE ville=?", new String[]{ville})) {
            if (cursor != null && cursor.moveToFirst()) {
                nombreAnnonces = cursor.getInt(0);
            }
        }
        return nombreAnnonces;
    }
}
