package view.managedbeans;

import controller.services.AlunoService;
import model.entities.Aluno;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Managed Bean para operações relacionadas a alunos no Jakarta Faces.
 */
@Named
@ViewScoped
public class AlunoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private AlunoService alunoService;

    private List<Aluno> alunos;
    private Aluno alunoSelecionado;
    private List<Aluno> alunosSelecionados;

    public AlunoBean() {
    }

    /**
     * Método inicializador chamado após a construção do bean.
     */
    @PostConstruct
    public void init() {
        alunos = alunoService.listarAlunos();
    }

    /**
     * Salva ou atualiza o aluno selecionado.
     */
    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (alunoSelecionado.getId() == null) {
                alunoService.salvarAluno(alunoSelecionado);
                context.addMessage(null, new FacesMessage("Aluno salvo com sucesso!"));
            } else {
                alunoService.editarAluno(alunoSelecionado);
                context.addMessage(null, new FacesMessage("Aluno editado com sucesso!"));
            }
            alunos = alunoService.listarAlunos();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        }
        PrimeFaces.current().ajax().update("formAluno:messages", "formAluno:alunosTable");
        PrimeFaces.current().executeScript("PF('manageAlunoDialog').hide()");
    }

    /**
     * Exclui o aluno selecionado.
     */
    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            alunoService.excluir(alunoSelecionado);
            alunos.remove(alunoSelecionado);
            context.addMessage(null, new FacesMessage("Aluno excluído com sucesso!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        }
        PrimeFaces.current().ajax().update("formAluno:messages", "formAluno:alunosTable");
    }

    /**
     * Prepara um novo aluno para ser cadastrado.
     */
    public void novo() {
        alunoSelecionado = new Aluno();
    }

    /**
     * Exclui todos os alunos selecionados.
     */
    public void excluirAlunosSelecionados() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            alunoService.excluirAlunos(alunosSelecionados);
            alunos.removeAll(alunosSelecionados);
            alunosSelecionados = null;
            context.addMessage(null, new FacesMessage("Alunos excluídos com sucesso!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        }
        PrimeFaces.current().ajax().update("formAluno:messages", "formAluno:alunosTable");
    }

    /**
     * Verifica se há alunos selecionados.
     */
    public boolean hasAlunosSelecionados() {
        return alunosSelecionados != null && !alunosSelecionados.isEmpty();
    }

    // Getters e Setters
    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }

    public List<Aluno> getAlunosSelecionados() {
        return alunosSelecionados;
    }

    public void setAlunosSelecionados(List<Aluno> alunosSelecionados) {
        this.alunosSelecionados = alunosSelecionados;
    }
}
