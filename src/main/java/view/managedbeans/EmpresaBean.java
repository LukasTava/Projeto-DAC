package view.managedbeans;

import controller.services.EmpresaService;
import model.entities.Empresa;
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

@Named
@ViewScoped
public class EmpresaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EmpresaService empresaService;

    private List<Empresa> empresas;
    private Empresa empresaSelecionada;
    private List<Empresa> empresasSelecionadas;

    public EmpresaBean() {
    }

    @PostConstruct
    public void init() {
        empresas = empresaService.listar();
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (empresaSelecionada.getId() == null) {
                empresaService.salvar(empresaSelecionada);
                context.addMessage(null, new FacesMessage("Empresa salva com sucesso!"));
            } else {
                empresaService.editar(empresaSelecionada);
                context.addMessage(null, new FacesMessage("Empresa editada com sucesso!"));
            }
            empresas = empresaService.listar();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        }
        PrimeFaces.current().ajax().update("formEmpresa:messages", "formEmpresa:empresasTable");
        PrimeFaces.current().executeScript("PF('manageEmpresaDialog').hide()");
    }

    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            empresaService.excluir(empresaSelecionada);
            empresas.remove(empresaSelecionada);
            context.addMessage(null, new FacesMessage("Empresa excluída com sucesso!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        }
        PrimeFaces.current().ajax().update("formEmpresa:messages", "formEmpresa:empresasTable");
    }

    public void novo() {
        empresaSelecionada = new Empresa();
    }

    public void excluirEmpresasSelecionadas() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            empresaService.excluir(empresasSelecionadas);
            empresas.removeAll(empresasSelecionadas);
            empresasSelecionadas = new ArrayList<>();
            context.addMessage(null, new FacesMessage("Empresas excluídas com sucesso!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        }
        PrimeFaces.current().ajax().update("formEmpresa:messages", "formEmpresa:empresasTable");
    }

    public boolean hasEmpresasSelecionadas() {
        return empresasSelecionadas != null && !empresasSelecionadas.isEmpty();
    }

    // Getters and setters
    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public Empresa getEmpresaSelecionada() {
        return empresaSelecionada;
    }

    public void setEmpresaSelecionada(Empresa empresaSelecionada) {
        this.empresaSelecionada = empresaSelecionada;
    }

    public List<Empresa> getEmpresasSelecionadas() {
        return empresasSelecionadas;
    }

    public void setEmpresasSelecionadas(List<Empresa> empresasSelecionadas) {
        this.empresasSelecionadas = empresasSelecionadas;
    }
}
