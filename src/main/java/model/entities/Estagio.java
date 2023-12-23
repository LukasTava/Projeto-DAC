package model.entities;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;

/**
 * Classe que representa um estágio.
 */
@Entity
public class Estagio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String inicio;
    private String fim;
    private int cargaHoraria;
    private int totalHoras;
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    private Aluno aluno;

    @OneToOne(fetch = FetchType.LAZY)
    private Empresa empresa;

    @OneToOne(fetch = FetchType.LAZY)
    private Orientador orientador;

    public Estagio() {
    }

    public Estagio(String inicio, String fim, int cargaHoraria, int totalHoras, String status, Aluno aluno, Empresa empresa, Orientador orientador) {
        setInicio(inicio);
        setFim(fim);
        setCargaHoraria(cargaHoraria);
        setTotalHoras(totalHoras);
        setStatus(status);
        this.aluno = aluno;
        this.empresa = empresa;
        this.orientador = orientador;
    }

    public Estagio(Long id, String inicio, String fim, int cargaHoraria, int totalHoras, String status, Aluno aluno, Empresa empresa, Orientador orientador) {
        this.id = id;
        setInicio(inicio);
        setFim(fim);
        setCargaHoraria(cargaHoraria);
        setTotalHoras(totalHoras);
        setStatus(status);
        this.aluno = aluno;
        this.empresa = empresa;
        this.orientador = orientador;
    }

    // Getters e setters com validações

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        if (inicio == null || inicio.trim().isEmpty()) {
            throw new IllegalArgumentException("Data de início não pode ser nula ou vazia.");
        }
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        if (fim == null || fim.trim().isEmpty()) {
            throw new IllegalArgumentException("Data de fim não pode ser nula ou vazia.");
        }
        this.fim = fim;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        if (cargaHoraria < 0) {
            throw new IllegalArgumentException("Carga horária deve ser positiva.");
        }
        this.cargaHoraria = cargaHoraria;
    }

    public int getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(int totalHoras) {
        if (totalHoras < 0) {
            throw new IllegalArgumentException("Total de horas deve ser positivo.");
        }
        this.totalHoras = totalHoras;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status não pode ser nulo ou vazio.");
        }
        this.status = status;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Orientador getOrientador() {
        return orientador;
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estagio)) return false;
        Estagio estagio = (Estagio) o;
        return Objects.equals(id, estagio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
