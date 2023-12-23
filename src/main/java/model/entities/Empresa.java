package model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

/**
 * Entidade que representa uma empresa.
 */
@Entity(name = "Empresa")
public class Empresa implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;

    @OneToMany(mappedBy = "empresa")
    private Set<AvaliacaoEmpresa> avaliacoes = new HashSet<>();

    public Empresa() {
    }

    public Empresa(String nome, String cnpj) {
        setNome(nome);
        setCnpj(cnpj);
    }

    public Empresa(Long id, String nome, String cnpj) {
        this.id = id;
        setNome(nome);
        setCnpj(cnpj);
    }

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
            throw new IllegalArgumentException("Nome da empresa não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            throw new IllegalArgumentException("CNPJ não pode ser nulo ou vazio.");
        }
        this.cnpj = cnpj;
    }

    public Set<AvaliacaoEmpresa> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(Set<AvaliacaoEmpresa> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empresa)) return false;
        Empresa empresa = (Empresa) o;
        return id != null && id.equals(empresa.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
