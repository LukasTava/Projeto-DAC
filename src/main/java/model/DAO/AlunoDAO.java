package model.DAO;

import jakarta.inject.Named;
import model.entities.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Named
public class AlunoDAO implements DAO<Aluno>, Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager manager;

    @Override
    public void inserir(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não pode ser nulo.");
        }
        manager.persist(aluno);
    }

    @Override
    public Aluno buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        return manager.find(Aluno.class, id);
    }

    @Override
    public void atualizar(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não pode ser nulo.");
        }
        manager.merge(aluno);
    }

    @Override
    public void remover(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        Aluno aluno = manager.find(Aluno.class, id);
        if (aluno != null) {
            manager.remove(aluno);
        }
    }

    @Override
    public List<Aluno> listar() {
        TypedQuery<Aluno> query = manager.createQuery("SELECT a FROM Aluno a", Aluno.class);
        return query.getResultList();
    }

}
