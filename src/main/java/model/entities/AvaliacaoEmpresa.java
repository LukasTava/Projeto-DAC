package model.entities;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * Entidade que representa uma avaliação de empresa.
 */
@Entity
public class AvaliacaoEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Empresa empresa;

    @OneToOne(fetch = FetchType.LAZY)
    private Aluno aluno;

    private Long rendimento;
    private Long conhecimento;
    private Long cumprimentoTarefas;
    private Long aprendizagem;
    private Long desempenho;

    public AvaliacaoEmpresa() {
    }

    public AvaliacaoEmpresa(Long id, Empresa empresa, Aluno aluno, Long rendimento, Long conhecimento, Long cumprimentoTarefas, Long aprendizagem, Long desempenho) {
        this.id = id;
        this.empresa = empresa;
        this.aluno = aluno;
        setRendimento(rendimento);
        setConhecimento(conhecimento);
        setCumprimentoTarefas(cumprimentoTarefas);
        setAprendizagem(aprendizagem);
        setDesempenho(desempenho);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Long getRendimento() {
        return rendimento;
    }

    public void setRendimento(Long rendimento) {
        validarAtributoNumerico(rendimento, "Rendimento");
        this.rendimento = rendimento;
    }

    public Long getConhecimento() {
        return conhecimento;
    }

    public void setConhecimento(Long conhecimento) {
        validarAtributoNumerico(conhecimento, "Conhecimento");
        this.conhecimento = conhecimento;
    }

    public Long getCumprimentoTarefas() {
        return cumprimentoTarefas;
    }

    public void setCumprimentoTarefas(Long cumprimentoTarefas) {
        validarAtributoNumerico(cumprimentoTarefas, "Cumprimento de Tarefas");
        this.cumprimentoTarefas = cumprimentoTarefas;
    }

    public Long getAprendizagem() {
        return aprendizagem;
    }

    public void setAprendizagem(Long aprendizagem) {
        validarAtributoNumerico(aprendizagem, "Aprendizagem");
        this.aprendizagem = aprendizagem;
    }

    public Long getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(Long desempenho) {
        validarAtributoNumerico(desempenho, "Desempenho");
        this.desempenho = desempenho;
    }

    private void validarAtributoNumerico(Long valor, String atributo) {
        if (valor == null || valor < 0) {
            throw new IllegalArgumentException(atributo + " deve ser positivo.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AvaliacaoEmpresa)) return false;
        AvaliacaoEmpresa that = (AvaliacaoEmpresa) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
