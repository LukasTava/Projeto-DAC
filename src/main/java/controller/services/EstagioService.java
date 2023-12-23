package controller.services;

import model.DAO.EstagioDAO;
import model.entities.Estagio;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

@Named
public class EstagioService implements Serializable {
    private static final long serialVersionUID = 1L;
    private final EstagioDAO estagioDAO;

    @Inject
    public EstagioService(EstagioDAO estagioDAO) {
        this.estagioDAO = estagioDAO;
    }

    public List<Estagio> listarEstagios() {
        return estagioDAO.listar();
    }

    public Estagio buscarEstagioPorId(Long id) {
        validarId(id);
        return estagioDAO.buscarPorId(id);
    }

    @Transactional
    public void salvarEstagio(Estagio estagio) {
        validarEstagio(estagio);
        estagioDAO.inserir(estagio);
    }

    @Transactional
    public void editarEstagio(Estagio estagio) {
        validarEstagio(estagio);
        estagioDAO.atualizar(estagio);
    }

    @Transactional
    public void excluirEstagio(Estagio estagio) {
        validarId(estagio.getId());
        estagioDAO.remover(estagio.getId());
    }

    @Transactional
    public void excluirEstagios(List<Estagio> estagios) {
        validarListaEstagios(estagios);
        estagios.forEach(estagio -> estagioDAO.remover(estagio.getId()));
    }

    private void validarEstagio(Estagio estagio) {
        if (estagio == null) {
            throw new IllegalArgumentException("Estágio não pode ser nulo.");
        }
    }

    private void validarId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
    }

    private void validarListaEstagios(List<Estagio> estagios) {
        if (estagios == null || estagios.isEmpty()) {
            throw new IllegalArgumentException("Lista de estágios está vazia.");
        }
    }
}
