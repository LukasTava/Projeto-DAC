package view.managedbeans;

import controller.services.OrientadorService;
import model.DAO.OrientadorDAO;
import model.entities.Orientador;
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
public class OrientadorBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private final OrientadorService orientadorService;
    private final OrientadorDAO orientadorDAO;

    private List<Orientador> orientadores = new ArrayList<>();

    private Orientador orientadorSelecionado;
    private List<Orientador> orientadoresSelecionados;

    @Inject
    public OrientadorBean(OrientadorService orientadorService, OrientadorDAO orientadorDAO) {
        this.orientadorService = orientadorService;
        this.orientadorDAO = orientadorDAO;
    }

    @PostConstruct
    public void init() {
        this.orientadores = this.orientadorDAO.listar();
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (orientadorSelecionado.getId() == null) {
                this.orientadorService.salvarOrientador(orientadorSelecionado);
                context.addMessage(null, new FacesMessage("Orientador salvo com sucesso!"));
            } else {
                this.orientadorService.editarOrientador(orientadorSelecionado);
                context.addMessage(null, new FacesMessage("Orientador editado com sucesso!"));
            }
            this.orientadores = this.orientadorDAO.listar();
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
        PrimeFaces.current().executeScript("PF('manageOrientadorDialog').hide()");
        PrimeFaces.current().ajax().update("formOrientador:messages", "formOrientador:orientadoresTable");
    }

    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.orientadorService.excluirOrientador(orientadorSelecionado);
            this.orientadores.remove(orientadorSelecionado);
            context.addMessage(null, new FacesMessage("Orientador excluído com sucesso!"));
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
        PrimeFaces.current().ajax().update("formOrientador:messages", "formOrientador:orientadoresTable");
    }

    public void novo() {
        this.orientadorSelecionado = new Orientador();
    }

    public void excluirOrientadoresSelecionados() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.orientadorService.excluirOrientadores(orientadoresSelecionados);
            this.orientadores.removeAll(orientadoresSelecionados);
            this.orientadoresSelecionados = null;
            context.addMessage(null, new FacesMessage("Orientadores excluídos com sucesso!"));
        } catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
        PrimeFaces.current().ajax().update("formOrientador:messages", "formOrientador:orientadoresTable");
    }

    public boolean hasOrientadoresSelecionados() {
        return this.orientadoresSelecionados != null && !this.orientadoresSelecionados.isEmpty();
    }

    public List<Orientador> getOrientadores() {
        return orientadores;
    }

    public void setOrientadores(List<Orientador> orientadores) {
        this.orientadores = orientadores;
    }

    public List<Orientador> getOrientadoresSelecionados() {
        return orientadoresSelecionados;
    }

    public void setOrientadoresSelecionados(List<Orientador> orientadoresSelecionados) {
        this.orientadoresSelecionados = orientadoresSelecionados;
    }

    public Orientador getOrientadorSelecionado() {
        return orientadorSelecionado;
    }

    public void setOrientadorSelecionado(Orientador orientadorSelecionado) {
        this.orientadorSelecionado = orientadorSelecionado;
    }
}
