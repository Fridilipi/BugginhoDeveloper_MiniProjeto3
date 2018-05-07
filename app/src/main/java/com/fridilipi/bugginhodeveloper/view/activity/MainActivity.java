package com.fridilipi.bugginhodeveloper.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.fridilipi.bugginhodeveloper.R;

public class MainActivity extends Activity implements View.OnClickListener {

    Button buttonCadastrarFuncionario, buttonConsultarFuncionario, buttonExibirLucroAnual,
            buttonListarFuncionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCadastrarFuncionario = findViewById(R.id.buttonCadastrarFuncionario);
        buttonConsultarFuncionario = findViewById(R.id.buttonConsultarFuncionario);
        buttonExibirLucroAnual = findViewById(R.id.buttonExibirLucroAnual);
        buttonListarFuncionario = findViewById(R.id.buttonListarFuncionario);

        buttonCadastrarFuncionario.setOnClickListener(this);
        buttonConsultarFuncionario.setOnClickListener(this);
        buttonExibirLucroAnual.setOnClickListener(this);
        buttonListarFuncionario.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == buttonCadastrarFuncionario.getId()) {
            startActivity(new Intent(this, CadastrarActivity.class));
        }

        if (view.getId() == buttonConsultarFuncionario.getId()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("CONSULTAR FUNCIONARIO");

            EditText editText = new EditText(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            editText.setLayoutParams(layoutParams);

            builder.setView(editText);

            builder.setPositiveButton("Consultar", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.show();
        }

        if (view.getId() == buttonExibirLucroAnual.getId()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("LUCRO ANUAL DA EMPRESA");

            SharedPreferences sharedPreferences = getSharedPreferences("lucroAnual", MODE_PRIVATE);
            builder.setMessage("Lucro anual da empresa: " + sharedPreferences.getFloat("lucroAnual", 0));

            builder.setPositiveButton("Atualizar", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("ATUALIZAR LUCRO ANUAL");

                    final EditText editText = new EditText(MainActivity.this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    editText.setLayoutParams(layoutParams);

                    builder.setView(editText);

                    builder.setPositiveButton("Atualizar", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences.Editor editor = getSharedPreferences("lucroAnual", MODE_PRIVATE).edit();
                            editor.putFloat("lucroAnual", Float.parseFloat(editText.getText().toString()));
                            editor.apply();
                        }
                    });

                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    builder.show();

                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.show();
        }

        if (view.getId() == buttonListarFuncionario.getId()) {
            startActivity(new Intent(this, ListarActivity.class));
        }

    }
}