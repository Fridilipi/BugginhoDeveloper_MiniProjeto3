package com.fridilipi.bugginhodeveloper.modelo;

import java.io.Serializable;

public class Funcionario implements Serializable {

    private Integer codigoFuncionario;
    private String nomeFuncionario;
    private String cpfFuncionario;
    private String cargoFuncionario;
    private Double salarioFuncionario;

    public Funcionario(Integer codigoFuncionario, String nomeFuncionario, String cpfFuncionario, String cargoFuncionario, Double salarioFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.cpfFuncionario = cpfFuncionario;
        this.cargoFuncionario = cargoFuncionario;
        this.salarioFuncionario = salarioFuncionario;
    }

    public Integer getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(Integer codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public String getCargoFuncionario() {
        return cargoFuncionario;
    }

    public void setCargoFuncionario(String cargoFuncionario) {
        this.cargoFuncionario = cargoFuncionario;
    }

    public Double getSalarioFuncionario() {
        return salarioFuncionario;
    }

    public void setSalarioFuncionario(Double salarioFuncionario) {
        this.salarioFuncionario = salarioFuncionario;
    }
}
