package view.managedbeans;

import controller.services.AlunoService;
import controller.services.AvaliacaoOrientadorService;
import controller.services.OrientadorService;
import model.entities.Aluno;
import model.entities.AvaliacaoOrientador;
import model.entities.Orientador;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AvaliacaoOrientadorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private AvaliacaoOrientador avaliacaoOrientadorSelecionado;
    private List<AvaliacaoOrientador> avaliacoes;
    private List<Orientador> orientadores;
    private List<Aluno> alunos;

    @Inject
    private AlunoService alunoService;
    @Inject
    private OrientadorService orientadorService;
    @Inject
    private AvaliacaoOrientadorService avaliacaoOrientadorService;

    private final Integer INSUFICIENTE = 1;
    private final Integer REGULAR = 2;
    private final Integer BOM = 3;
    private final Integer MUITO_BOM = 4;

    @PostConstruct
    public void init() {
        avaliacoes = avaliacaoOrientadorService.listar();
        alunos = alunoService.listarAlunos();
        orientadores = orientadorService.listarOrientadores();
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (avaliacaoOrientadorSelecionado.getId() == null) {
                avaliacaoOrientadorService.salvar(avaliacaoOrientadorSelecionado);
                context.addMessage(null, new FacesMessage("Avaliação salva com sucesso!"));
            } else {
                avaliacaoOrientadorService.editar(avaliacaoOrientadorSelecionado);
                context.addMessage(null, new FacesMessage("Avaliação atualizada com sucesso!"));
            }
            atualizarListaAvaliacoes();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar a avaliação", e.getMessage()));
        }
        PrimeFaces.current().ajax().update("formAvaliacaoOrientador:messages", "formAvaliacaoOrientador:avaliacoesTable");
        PrimeFaces.current().executeScript("PF('manageAvaliacaoOrientadorDialog').hide()");
    }

    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            avaliacaoOrientadorService.excluir(avaliacaoOrientadorSelecionado);
            avaliacoes.remove(avaliacaoOrientadorSelecionado);
            context.addMessage(null, new FacesMessage("Avaliação excluída com sucesso!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir a avaliação", e.getMessage()));
        }
        atualizarListaAvaliacoes();
    }

    public void novo() {
        avaliacaoOrientadorSelecionado = new AvaliacaoOrientador();
        avaliacaoOrientadorSelecionado.setAluno(new Aluno());
        avaliacaoOrientadorSelecionado.setOrientador(new Orientador());
        // Inicialize outros campos da nova avaliação, se necessário
    }

    private void atualizarListaAvaliacoes() {
        avaliacoes = avaliacaoOrientadorService.listar();
        PrimeFaces.current().ajax().update("formAvaliacaoOrientador:avaliacoesTable");
    }

    // Getters and Setters

    public AvaliacaoOrientador getAvaliacaoOrientadorSelecionado() {
        return avaliacaoOrientadorSelecionado;
    }

    public void setAvaliacaoOrientadorSelecionado(AvaliacaoOrientador avaliacaoOrientadorSelecionado) {
        this.avaliacaoOrientadorSelecionado = avaliacaoOrientadorSelecionado;
    }

    public List<AvaliacaoOrientador> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<AvaliacaoOrientador> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Orientador> getOrientadores() {
        return orientadores;
    }

    public void setOrientadores(List<Orientador> orientadores) {
        this.orientadores = orientadores;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Integer getInsuficiente() {
        return INSUFICIENTE;
    }

    public Integer getRegular() {
        return REGULAR;
    }

    public Integer getBom() {
        return BOM;
    }

    public Integer getMuitoBom() {
        return MUITO_BOM;
    }
}
