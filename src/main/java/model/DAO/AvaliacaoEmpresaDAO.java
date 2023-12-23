package model.DAO;

import jakarta.inject.Named;
import model.entities.AvaliacaoEmpresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Named
public class AvaliacaoEmpresaDAO implements DAO<AvaliacaoEmpresa> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager manager;

    @Override
    public void inserir(AvaliacaoEmpresa avaliacaoEmpresa) {
        if (avaliacaoEmpresa == null) {
            throw new IllegalArgumentException("Avaliação da empresa não pode ser nula.");
        }
        manager.persist(avaliacaoEmpresa);
    }

    @Override
    public AvaliacaoEmpresa buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        return manager.find(AvaliacaoEmpresa.class, id);
    }

    @Override
    public void atualizar(AvaliacaoEmpresa avaliacaoEmpresa) {
        if (avaliacaoEmpresa == null) {
            throw new IllegalArgumentException("Avaliação da empresa não pode ser nula.");
        }
        manager.merge(avaliacaoEmpresa);
    }

    @Override
    public void remover(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        AvaliacaoEmpresa avaliacao = manager.find(AvaliacaoEmpresa.class, id);
        if (avaliacao != null) {
            manager.remove(avaliacao);
        }
    }

    @Override
    public List<AvaliacaoEmpresa> listar() {
        TypedQuery<AvaliacaoEmpresa> query = manager.createQuery("SELECT a FROM AvaliacaoEmpresa a", AvaliacaoEmpresa.class);
        return query.getResultList();
    }
}
