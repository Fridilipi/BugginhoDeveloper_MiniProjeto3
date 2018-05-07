package com.fridilipi.bugginhodeveloper.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fridilipi.bugginhodeveloper.R;
import com.fridilipi.bugginhodeveloper.dao.DAOFuncionario;
import com.fridilipi.bugginhodeveloper.enums.Cargo;
import com.fridilipi.bugginhodeveloper.modelo.Funcionario;

public class CadastrarActivity extends Activity implements View.OnClickListener {

    private DAOFuncionario daoFuncionario;

    Button buttonCadastrar;
    EditText editTextCPFFuncionario, editTextNomeFuncionario, editTextSalarioFuncionario;
    Spinner spinnerCargoFuncionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        daoFuncionario = new DAOFuncionario(getApplicationContext());

        buttonCadastrar = findViewById(R.id.buttonCadastrar);

        editTextCPFFuncionario = findViewById(R.id.editTextCPFFuncionario);
        editTextNomeFuncionario = findViewById(R.id.editTextNomeFuncionario);
        editTextSalarioFuncionario = findViewById(R.id.editTextSalarioFuncionario);

        spinnerCargoFuncionario = findViewById(R.id.spinnerCargoFuncionario);

        buttonCadastrar.setOnClickListener(this);

        spinnerCargoFuncionario.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Cargo.values()));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == buttonCadastrar.getId()) {

            if (daoFuncionario.inserirFuncionario(new Funcionario(null, editTextNomeFuncionario.getText().toString(), editTextCPFFuncionario.getText().toString(), spinnerCargoFuncionario.getSelectedItem().toString(), Double.parseDouble(editTextSalarioFuncionario.getText().toString())))) {
                Toast.makeText(this, "FUNFOU", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "XAMPSON", Toast.LENGTH_LONG).show();
            }

        }
    }
}
