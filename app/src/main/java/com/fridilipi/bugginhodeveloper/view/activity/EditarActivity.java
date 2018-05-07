package com.fridilipi.bugginhodeveloper.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fridilipi.bugginhodeveloper.R;
import com.fridilipi.bugginhodeveloper.dao.DAOFuncionario;
import com.fridilipi.bugginhodeveloper.enums.Cargo;
import com.fridilipi.bugginhodeveloper.modelo.Funcionario;

public class EditarActivity extends Activity implements View.OnClickListener {

    private DAOFuncionario daoFuncionario;

    Button buttonExcluirFuncionario, buttonSalvarAlteracoes;
    TextView textViewBonusFuncionario, textViewCargoFuncionario, textViewCPFFuncionario,
            textViewNomeFuncionario, textViewSalarioFuncionario;

    Funcionario funcionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        daoFuncionario = new DAOFuncionario(this);

        funcionario = (Funcionario) getIntent().getSerializableExtra("funcionario");

        buttonExcluirFuncionario = findViewById(R.id.buttonExcluirFuncionario);
        buttonSalvarAlteracoes = findViewById(R.id.buttonSalvarAlteracoes);

        textViewBonusFuncionario = findViewById(R.id.textViewBonusFuncionarioEditar);
        textViewCargoFuncionario = findViewById(R.id.textViewCargoFuncionarioEditar);
        textViewCPFFuncionario = findViewById(R.id.textViewCPFFuncionarioEditar);
        textViewNomeFuncionario = findViewById(R.id.textViewNomeFuncionarioEditar);
        textViewSalarioFuncionario = findViewById(R.id.textViewSalarioFuncionarioEditar);

        buttonExcluirFuncionario.setOnClickListener(this);
        buttonSalvarAlteracoes.setOnClickListener(this);

        textViewCargoFuncionario.setOnClickListener(this);
        textViewCPFFuncionario.setOnClickListener(this);
        textViewNomeFuncionario.setOnClickListener(this);
        textViewSalarioFuncionario.setOnClickListener(this);

        textViewBonusFuncionario.setText(String.valueOf(calcularBonusFuncionario(funcionario)));
        textViewCargoFuncionario.setText(funcionario.getCargoFuncionario());
        textViewCPFFuncionario.setText(funcionario.getCpfFuncionario());
        textViewNomeFuncionario.setText(funcionario.getNomeFuncionario());
        textViewSalarioFuncionario.setText(funcionario.getSalarioFuncionario().toString());
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == buttonExcluirFuncionario.getId()) {

            if (daoFuncionario.deletarFuncionario(funcionario)) {
                Toast.makeText(this, "FUNFOU", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "XAMPSON", Toast.LENGTH_LONG).show();
            }

        }

        if (view.getId() == buttonSalvarAlteracoes.getId()) {

            if (daoFuncionario.alterarFuncionario(new Funcionario(funcionario.getCodigoFuncionario(), textViewNomeFuncionario.getText().toString(), textViewCPFFuncionario.getText().toString(), textViewCargoFuncionario.getText().toString(), Double.parseDouble(textViewSalarioFuncionario.getText().toString())))) {
                Toast.makeText(this, "FUNFOU", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "XAMPSON", Toast.LENGTH_LONG).show();
            }

        }

        if (view.getId() == textViewCargoFuncionario.getId()) {
            exibirDialogEditar(textViewCargoFuncionario);
        }

        if (view.getId() == textViewCPFFuncionario.getId()) {
            exibirDialogEditar(textViewCPFFuncionario);
        }

        if (view.getId() == textViewNomeFuncionario.getId()) {
            exibirDialogEditar(textViewNomeFuncionario);
        }

        if (view.getId() == textViewSalarioFuncionario.getId()) {
            exibirDialogEditar(textViewSalarioFuncionario);
        }

    }

    public void exibirDialogEditar(final TextView textView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alterar campo");

        if (textView != textViewCargoFuncionario) {
            final EditText input = new EditText(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            builder.setView(input);

            builder.setPositiveButton("Alterar", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    textView.setText(input.getText());
                }

            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }

            });

        } else {
            final Spinner spinner = new Spinner(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            spinner.setLayoutParams(layoutParams);

            spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Cargo.values()));

            builder.setView(spinner);

            builder.setPositiveButton("Alterar", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    textView.setText(spinner.getSelectedItem().toString());
                }

            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }

            });
        }

        builder.show();
    }

    public double calcularBonusFuncionario(Funcionario funcionario) {

        SharedPreferences sharedPreferences = getSharedPreferences("lucroAnual", MODE_PRIVATE);

        if (funcionario.getCargoFuncionario().equals(Cargo.Atendimento.toString())) {
            return (sharedPreferences.getFloat("lucroAnual", 0) * (1 / daoFuncionario.contarFuncionarioCargo(Cargo.Atendimento.toString()))) / 100;
        }

        if (funcionario.getCargoFuncionario().equals(Cargo.Designer.toString())) {
            return (sharedPreferences.getFloat("lucroAnual", 0) * (1.5 / daoFuncionario.contarFuncionarioCargo(Cargo.Designer.toString()))) / 100;
        }

        if (funcionario.getCargoFuncionario().equals(Cargo.Gerente.toString())) {
            return (sharedPreferences.getFloat("lucroAnual", 0) * (3 / daoFuncionario.contarFuncionarioCargo(Cargo.Gerente.toString()))) / 100;
        }

        if (funcionario.getCargoFuncionario().equals(Cargo.Programador.toString())) {
            return (sharedPreferences.getFloat("lucroAnual", 0) * (1.5 / daoFuncionario.contarFuncionarioCargo(Cargo.Programador.toString()))) / 100;
        }

        return 0;
    }

}
