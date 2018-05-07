package com.fridilipi.bugginhodeveloper.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DataHelper {

    protected Context context;
    protected SQLiteDatabase database;
    protected SQLiteStatement statement;

    public DataHelper(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        database = openHelper.getWritableDatabase();
    }

    public class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context) {
            super(context, "DB_Bugginho", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL("CREATE TABLE funcionario (codigoFuncionario INTEGER PRIMARY KEY AUTOINCREMENT, nomeFuncionario TEXT, cpfFuncionario TEXT, cargoFuncionario TEXT, salarioFuncionario REAL);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            database.execSQL("DROP TABLE IF EXISTS funcionario;");

            onCreate(database);
        }
    }

}