package com.fridilipi.bugginhodeveloper.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fridilipi.bugginhodeveloper.R;
import com.fridilipi.bugginhodeveloper.modelo.Funcionario;

import java.util.List;

public class FuncionarioAdapter extends ArrayAdapter<Funcionario> {

    LayoutInflater inflater;
    int resourceId;

    public FuncionarioAdapter(Context context, int resource, List<Funcionario> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        resourceId = resource;
    }

    @SuppressLint("ViewHolder")
    public View getView(int position, View convertView, ViewGroup parent) {
        Funcionario funcionario = getItem(position);

        convertView = inflater.inflate(resourceId, parent, false);

        TextView textViewNomeFuncionario = convertView.findViewById(R.id.textViewNomeFuncionario);
        textViewNomeFuncionario.setText(funcionario.getNomeFuncionario());

        TextView textViewCPFFuncionario = convertView.findViewById(R.id.textViewCPFFuncionario);
        textViewCPFFuncionario.setText(funcionario.getCpfFuncionario());

        TextView textViewCargoFuncionario = convertView.findViewById(R.id.textViewCargoFuncionario);
        textViewCargoFuncionario.setText(funcionario.getCargoFuncionario());

        TextView textViewSalarioFuncionario = convertView.findViewById(R.id.textViewSalarioFuncionario);
        textViewSalarioFuncionario.setText(funcionario.getSalarioFuncionario().toString());

        return convertView;
    }

}