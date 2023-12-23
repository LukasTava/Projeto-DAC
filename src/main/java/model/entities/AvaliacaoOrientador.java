package model.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class AvaliacaoOrientador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Orientador orientador;

    @OneToOne
    private Aluno aluno;

    private Long assiduidade;
    private Long disciplina;
    private Long sociabilidade;
    private Long responsabilidade;
    private Long iniciativa;

    public AvaliacaoOrientador() {
    }

    public AvaliacaoOrientador(Long id, Orientador orientador, Aluno aluno, Long assiduidade, Long disciplina, Long sociabilidade, Long responsabilidade, Long iniciativa) {
        this.id = id;
        this.orientador = orientador;
        this.aluno = aluno;
        this.assiduidade = assiduidade;
        this.disciplina = disciplina;
        this.sociabilidade = sociabilidade;
        this.responsabilidade = responsabilidade;
        this.iniciativa = iniciativa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orientador getOrientador() {
        return orientador;
    }

    public void setOrientador(Orientador orientador) {
        this.orientador = orientador;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Long getAssiduidade() {
        return assiduidade;
    }

    public void setAssiduidade(Long assiduidade) {
        this.assiduidade = assiduidade;
    }

    public Long getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Long disciplina) {
        this.disciplina = disciplina;
    }

    public Long getSociabilidade() {
        return sociabilidade;
    }

    public void setSociabilidade(Long sociabilidade) {
        this.sociabilidade = sociabilidade;
    }

    public Long getResponsabilidade() {
        return responsabilidade;
    }

    public void setResponsabilidade(Long responsabilidade) {
        this.responsabilidade = responsabilidade;
    }

    public Long getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(Long iniciativa) {
        this.iniciativa = iniciativa;
    }
}
