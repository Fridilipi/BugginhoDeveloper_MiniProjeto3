package com.fridilipi.bugginhodeveloper.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fridilipi.bugginhodeveloper.R;
import com.fridilipi.bugginhodeveloper.dao.DAOFuncionario;
import com.fridilipi.bugginhodeveloper.modelo.Funcionario;
import com.fridilipi.bugginhodeveloper.view.adapter.FuncionarioAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListarActivity extends Activity implements AdapterView.OnItemClickListener {

    private DAOFuncionario daoFuncionario;

    ListView listViewFuncionario;

    List<Funcionario> funcionarioList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        daoFuncionario = new DAOFuncionario(this);

        listarFuncionario();
    }

    @Override
    protected void onResume() {
        super.onResume();

        listarFuncionario();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Funcionario funcionario = (Funcionario) listViewFuncionario.getAdapter().getItem(position);

        startActivity(new Intent(this, EditarActivity.class).putExtra("funcionario", (Serializable) funcionario));
    }

    private void listarFuncionario() {
        funcionarioList = daoFuncionario.listarFuncionario();

        FuncionarioAdapter adapter = new FuncionarioAdapter(this, R.layout.adapter_funcionario, funcionarioList);

        listViewFuncionario = findViewById(R.id.listViewFuncionario);
        listViewFuncionario.setAdapter(adapter);
        listViewFuncionario.setOnItemClickListener(this);
    }

}