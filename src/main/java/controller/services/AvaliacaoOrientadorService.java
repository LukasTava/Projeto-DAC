package controller.services;

import model.DAO.AvaliacaoOrientadorDAO;
import model.entities.AvaliacaoOrientador;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

@Named
public class AvaliacaoOrientadorService implements Serializable {

    private final AvaliacaoOrientadorDAO avalicaoOrientadorDAO;

    @Inject
    public AvaliacaoOrientadorService(AvaliacaoOrientadorDAO avalicaoOrientadorDAO) {
        this.avalicaoOrientadorDAO = avalicaoOrientadorDAO;
    }

    @Transactional
    public void salvar(AvaliacaoOrientador avaliacaoOrientador) {
        validarAvaliacao(avaliacaoOrientador);
        avalicaoOrientadorDAO.inserir(avaliacaoOrientador);
    }

    @Transactional
    public void excluir(AvaliacaoOrientador avaliacaoOrientador) {
        validarId(avaliacaoOrientador);
        avalicaoOrientadorDAO.remover(avaliacaoOrientador.getId());
    }

    @Transactional
    public void editar(AvaliacaoOrientador avaliacaoOrientador) {
        validarAvaliacao(avaliacaoOrientador);
        avalicaoOrientadorDAO.atualizar(avaliacaoOrientador);
    }

    public AvaliacaoOrientador buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        return avalicaoOrientadorDAO.buscarPorId(id);
    }

    public List<AvaliacaoOrientador> listar() {
        return avalicaoOrientadorDAO.listar();
    }

    private void validarAvaliacao(AvaliacaoOrientador avaliacaoOrientador) {
        if (avaliacaoOrientador == null) {
            throw new IllegalArgumentException("Avaliação não pode ser nula.");
        }
    }

    private void validarId(AvaliacaoOrientador avaliacaoOrientador) {
        if (avaliacaoOrientador == null || avaliacaoOrientador.getId() == null) {
            throw new IllegalArgumentException("Avaliação inválida para operação.");
        }
    }
}
