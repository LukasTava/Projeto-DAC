package controller.services;

import model.DAO.AvaliacaoEmpresaDAO;
import model.entities.AvaliacaoEmpresa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

@Named
public class AvaliacaoEmpresaService implements Serializable {

    private final AvaliacaoEmpresaDAO avaliacaoEmpresaDAO;

    @Inject
    public AvaliacaoEmpresaService(AvaliacaoEmpresaDAO avaliacaoEmpresaDAO) {
        this.avaliacaoEmpresaDAO = avaliacaoEmpresaDAO;
    }

    // Salva uma nova avaliação de empresa
    @Transactional
    public void salvar(AvaliacaoEmpresa avaliacaoEmpresa) {
        if (avaliacaoEmpresa == null) {
            throw new IllegalArgumentException("Avaliação da empresa não pode ser nula.");
        }
        avaliacaoEmpresaDAO.inserir(avaliacaoEmpresa);
    }

    // Exclui uma avaliação de empresa
    @Transactional
    public void excluir(AvaliacaoEmpresa avaliacaoEmpresa) {
        if (avaliacaoEmpresa == null || avaliacaoEmpresa.getId() == null) {
            throw new IllegalArgumentException("Avaliação da empresa inválida para exclusão.");
        }
        avaliacaoEmpresaDAO.remover(avaliacaoEmpresa.getId());
    }

    // Edita uma avaliação de empresa existente
    @Transactional
    public void editar(AvaliacaoEmpresa avaliacaoEmpresa) {
        if (avaliacaoEmpresa == null) {
            throw new IllegalArgumentException("Avaliação da empresa não pode ser nula.");
        }
        avaliacaoEmpresaDAO.atualizar(avaliacaoEmpresa);
    }

    // Busca uma avaliação de empresa por ID
    public AvaliacaoEmpresa buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID da avaliação da empresa não pode ser nulo.");
        }
        return avaliacaoEmpresaDAO.buscarPorId(id);
    }

    // Lista todas as avaliações de empresas
    public List<AvaliacaoEmpresa> listar() {
        return avaliacaoEmpresaDAO.listar();
    }
}
