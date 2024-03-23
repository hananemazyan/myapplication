package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Database name and version
    private static final String DATABASE_NAME = "myapp.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_ANNONCES = "annonces";

    // Common column names
    private static final String COLUMN_ID = "_id";

    // Users table column names
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    // Annonces table column names
    private static final String COLUMN_TITRE = "titre";
    private static final String COLUMN_CATEGORIE = "categorie";
    private static final String COLUMN_SECTEUR = "secteur";
    private static final String COLUMN_TYPE_CONTRAT = "type_contrat";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_VILLE = "ville";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create users table
        String createUserTableSQL = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USERNAME + " TEXT," +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createUserTableSQL);

        // Create annonces table
        String createAnnoncesTableSQL = "CREATE TABLE " + TABLE_ANNONCES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TITRE + " TEXT," +
                COLUMN_CATEGORIE + " TEXT," +
                COLUMN_SECTEUR + " TEXT," +
                COLUMN_TYPE_CONTRAT + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_VILLE + " TEXT)";
        db.execSQL(createAnnoncesTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed and recreate them
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANNONCES);
        onCreate(db);
    }

    // User Methods

    public long addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs,
                null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    // Annonces Methods

    public long insertAnnonce(String titre, String categorie, String secteur, String typeContrat, String description, String ville) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITRE, titre);
        values.put(COLUMN_CATEGORIE, categorie);
        values.put(COLUMN_SECTEUR, secteur);
        values.put(COLUMN_TYPE_CONTRAT, typeContrat);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_VILLE, ville);
        long result = db.insert(TABLE_ANNONCES, null, values);
        db.close();
        return result;
    }

    public Cursor getAllAnnonces() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_ANNONCES, null, null, null, null, null, null);
    }
}
