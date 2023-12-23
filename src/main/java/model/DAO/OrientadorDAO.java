package model.DAO;

import jakarta.inject.Named;
import model.entities.Orientador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Named
public class OrientadorDAO implements DAO<Orientador>, Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager manager;

    @Override
    public void inserir(Orientador orientador) {
        if (orientador == null) {
            throw new IllegalArgumentException("Orientador não pode ser nulo.");
        }
        manager.persist(orientador);
    }

    @Override
    public Orientador buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        return manager.find(Orientador.class, id);
    }

    @Override
    public void atualizar(Orientador orientador) {
        if (orientador == null) {
            throw new IllegalArgumentException("Orientador não pode ser nulo.");
        }
        manager.merge(orientador);
    }

    @Override
    public void remover(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        Orientador orientador = manager.find(Orientador.class, id);
        if (orientador != null) {
            manager.remove(orientador);
        }
    }

    @Override
    public List<Orientador> listar() {
        TypedQuery<Orientador> query = manager.createQuery("SELECT o FROM Orientador o", Orientador.class);
        return query.getResultList();
    }
}
