package com.fridilipi.bugginhodeveloper.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.fridilipi.bugginhodeveloper.modelo.Funcionario;
import com.fridilipi.bugginhodeveloper.util.DataHelper;

import java.util.ArrayList;
import java.util.List;

public class DAOFuncionario extends DataHelper {

    public DAOFuncionario(Context context) {
        super(context);
    }

    public boolean inserirFuncionario(Funcionario funcionario) {
        try {
            statement = database.compileStatement("INSERT INTO funcionario (nomeFuncionario, cpfFuncionario, cargoFuncionario, salarioFuncionario) VALUES (?, ?, ?, ?);");

            statement.bindString(1, funcionario.getNomeFuncionario());
            statement.bindString(2, funcionario.getCpfFuncionario());
            statement.bindString(3, funcionario.getCargoFuncionario());
            statement.bindDouble(4, funcionario.getSalarioFuncionario());

            statement.execute();

            return true;
        } catch (Exception exception) {
            Log.e("XAMPSON", "inserirFuncionario: " + exception.getMessage());

            return false;
        }

    }

    public boolean alterarFuncionario(Funcionario funcionario) {
        try {
            statement = database.compileStatement("UPDATE funcionario SET nomeFuncionario = ?, cpfFuncionario = ?, cargoFuncionario = ?, salarioFuncionario = ? WHERE codigoFuncionario = ?;");

            statement.bindString(1, funcionario.getNomeFuncionario());
            statement.bindString(2, funcionario.getCpfFuncionario());
            statement.bindString(3, funcionario.getCargoFuncionario());
            statement.bindDouble(4, funcionario.getSalarioFuncionario());
            statement.bindLong(5, funcionario.getCodigoFuncionario());

            statement.execute();

            return true;
        } catch (Exception exception) {
            Log.e("XAMPSON", "alterarFuncionario: " + exception.getMessage());

            return false;
        }
    }

    public boolean deletarFuncionario(Funcionario funcionario) {
        try {
            statement = database.compileStatement("DELETE FROM funcionario WHERE codigoFuncionario = ?");

            statement.bindLong(1, funcionario.getCodigoFuncionario());

            statement.execute();

            return true;
        } catch (Exception exception) {
            Log.e("XAMPSON", "deletarFuncionario: " + exception.getMessage());

            return false;
        }
    }

    public List<Funcionario> listarFuncionario() {
        Cursor cursor;
        List<Funcionario> funcionarioList = new ArrayList<>();

        try {
            cursor = database.rawQuery("SELECT * FROM funcionario", null);

            if (cursor.moveToFirst()) {

                do {
                    funcionarioList.add(new Funcionario(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4)));
                } while (cursor.moveToNext());

            }

            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }

        } catch (Exception exception) {
            Log.e("XAMPSON", "listarFuncionario: " + exception.getMessage());
        }

        return funcionarioList;
    }

    public long contarFuncionarioCargo(String cargoFuncionario) {
        try {
            statement = database.compileStatement("SELECT COUNT(*) FROM funcionario WHERE cargoFuncionario = '" + cargoFuncionario + "';");

            return statement.simpleQueryForLong();

        } catch (Exception exception) {
            Log.e("XAMPSON", "contarFuncionarioCargo: " + exception.getMessage());

            return 0;
        }

    }

}