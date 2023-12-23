package model.DAO;

import jakarta.inject.Named;
import model.entities.AvaliacaoOrientador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Named
public class AvaliacaoOrientadorDAO implements DAO<AvaliacaoOrientador> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager manager;

    @Override
    public void inserir(AvaliacaoOrientador avaliacaoOrientador) {
        if (avaliacaoOrientador == null) {
            throw new IllegalArgumentException("Avaliação do Orientador não pode ser nula.");
        }
        manager.persist(avaliacaoOrientador);
    }

    @Override
    public AvaliacaoOrientador buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        return manager.find(AvaliacaoOrientador.class, id);
    }

    @Override
    public void atualizar(AvaliacaoOrientador avaliacaoOrientador) {
        if (avaliacaoOrientador == null) {
            throw new IllegalArgumentException("Avaliação do Orientador não pode ser nula.");
        }
        manager.merge(avaliacaoOrientador);
    }

    @Override
    public void remover(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        AvaliacaoOrientador avaliacao = manager.find(AvaliacaoOrientador.class, id);
        if (avaliacao != null) {
            manager.remove(avaliacao);
        }
    }

    @Override
    public List<AvaliacaoOrientador> listar() {
        TypedQuery<AvaliacaoOrientador> query = manager.createQuery("SELECT a FROM AvaliacaoOrientador a", AvaliacaoOrientador.class);
        return query.getResultList();
    }
}
