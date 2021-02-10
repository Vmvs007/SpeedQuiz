package pt.ipp.estg.speedquiz;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import pt.ipp.estg.speedquiz.Date.DateConverter;

@Entity
public class Utilizador {

    @PrimaryKey(autoGenerate = true)
    private int id_utilizador;
    private String nome;
    private String morada;
    @TypeConverters(DateConverter.class)
    private Date data_nascimento;
    private int contacto;

    public Utilizador() {
    }

    @Ignore
    public Utilizador(String nome, String morada, Date data_nascimento, int contacto) {
        this.nome = nome;
        this.morada = morada;
        this.data_nascimento = data_nascimento;
        this.contacto = contacto;
    }

    public int getId_utilizador() {
        return id_utilizador;
    }

    public void setId_utilizador(int id_utilizador) {
        this.id_utilizador = id_utilizador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public int getContacto() {
        return contacto;
    }

    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "id_utilizador=" + id_utilizador +
                ", nome='" + nome + '\'' +
                ", morada='" + morada + '\'' +
                ", data_nascimento=" + data_nascimento +
                ", contacto=" + contacto +
                '}';
    }
}
