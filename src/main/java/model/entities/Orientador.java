package model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import jakarta.persistence.*;

/**
 * Classe que representa um orientador.
 */
@Entity
public class Orientador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String disciplina;

    @OneToMany(mappedBy = "orientador")
    private Set<Estagio> estagios = new HashSet<>();

    public Orientador() {
    }

    public Orientador(String nome, String disciplina) {
        setNome(nome);
        setDisciplina(disciplina);
    }

    public Orientador(Long id, String nome, String disciplina) {
        this.id = id;
        setNome(nome);
        setDisciplina(disciplina);
    }

    // Getters e setters com validações

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        if (disciplina == null || disciplina.trim().isEmpty()) {
            throw new IllegalArgumentException("Disciplina não pode ser nula ou vazia.");
        }
        this.disciplina = disciplina;
    }

    public Set<Estagio> getEstagios() {
        return estagios;
    }

    public void setEstagios(Set<Estagio> estagios) {
        this.estagios = estagios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orientador)) return false;
        Orientador that = (Orientador) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
