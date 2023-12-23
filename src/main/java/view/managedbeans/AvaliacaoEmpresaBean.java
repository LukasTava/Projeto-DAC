package view.managedbeans;

import controller.services.AlunoService;
import controller.services.AvaliacaoEmpresaService;
import controller.services.EmpresaService;
import model.entities.Aluno;
import model.entities.AvaliacaoEmpresa;
import model.entities.Empresa;
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
public class AvaliacaoEmpresaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private AvaliacaoEmpresa avaliacaoEmpresaSelecionado;
    private List<AvaliacaoEmpresa> avaliacoes;
    private List<Empresa> empresas;
    private List<Aluno> alunos;

    @Inject
    private AlunoService alunoService;
    @Inject
    private EmpresaService empresaService;
    @Inject
    private AvaliacaoEmpresaService avaliacaoEmpresaService;

    private final Integer INSUFICIENTE = 1;
    private final Integer REGULAR = 2;
    private final Integer BOM = 3;
    private final Integer MUITO_BOM = 4;

    @PostConstruct
    public void init() {
        avaliacoes = avaliacaoEmpresaService.listar();
        alunos = alunoService.listarAlunos();
        empresas = empresaService.listar();
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (avaliacaoEmpresaSelecionado.getId() == null) {
                avaliacaoEmpresaService.salvar(avaliacaoEmpresaSelecionado);
                context.addMessage(null, new FacesMessage("Avaliação salva com sucesso!"));
            } else {
                avaliacaoEmpresaService.editar(avaliacaoEmpresaSelecionado);
                context.addMessage(null, new FacesMessage("Avaliação atualizada com sucesso!"));
            }
            atualizarListaAvaliacoes();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar a avaliação", e.getMessage()));
        }
        PrimeFaces.current().ajax().update("formAvaliacaoEmpresa:messages", "formAvaliacaoEmpresa:avaliacoesTable");
        PrimeFaces.current().executeScript("PF('manageAvaliacaoEmpresaDialog').hide()");
    }

    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            avaliacaoEmpresaService.excluir(avaliacaoEmpresaSelecionado);
            avaliacoes.remove(avaliacaoEmpresaSelecionado);
            context.addMessage(null, new FacesMessage("Avaliação excluída com sucesso!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir a avaliação", e.getMessage()));
        }
        PrimeFaces.current().ajax().update("formAvaliacaoEmpresa:messages", "formAvaliacaoEmpresa:avaliacoesTable");
    }

    public void novo() {
        avaliacaoEmpresaSelecionado = new AvaliacaoEmpresa();
    }

    private void atualizarListaAvaliacoes() {
        avaliacoes = avaliacaoEmpresaService.listar();
    }

    // Getters and setters

    public AvaliacaoEmpresa getAvaliacaoEmpresaSelecionado() {
        return avaliacaoEmpresaSelecionado;
    }

    public void setAvaliacaoEmpresaSelecionado(AvaliacaoEmpresa avaliacaoEmpresaSelecionado) {
        this.avaliacaoEmpresaSelecionado = avaliacaoEmpresaSelecionado;
    }

    public List<AvaliacaoEmpresa> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<AvaliacaoEmpresa> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
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