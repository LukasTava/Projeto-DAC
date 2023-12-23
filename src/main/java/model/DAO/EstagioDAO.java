package model.DAO;

import jakarta.inject.Named;
import jakarta.persistence.NoResultException;
import model.entities.Estagio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Named
public class EstagioDAO implements DAO<Estagio> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager manager;

    @Override
    public void inserir(Estagio estagio) {
        if (estagio == null) {
            throw new IllegalArgumentException("Estágio não pode ser nulo.");
        }
        manager.persist(estagio);
    }

    @Override
    public Estagio buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        return manager.find(Estagio.class, id);
    }

    @Override
    public void atualizar(Estagio estagio) {
        if (estagio == null) {
            throw new IllegalArgumentException("Estágio não pode ser nulo.");
        }
        manager.merge(estagio);
    }

    @Override
    public void remover(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        Estagio estagio = manager.find(Estagio.class, id);
        if (estagio != null) {
            manager.remove(estagio);
        }
    }

    @Override
    public List<Estagio> listar() {
        TypedQuery<Estagio> query = manager.createQuery("SELECT e FROM Estagio e", Estagio.class);
        return query.getResultList();
    }

    public Estagio filtrarEstagioPorMatricula(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Matrícula não pode ser nula ou vazia.");
        }

        TypedQuery<Estagio> query = manager.createQuery(
                "SELECT e FROM Estagio e WHERE e.aluno.matricula = :matricula",
                Estagio.class
        );
        query.setParameter("matricula", matricula);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
