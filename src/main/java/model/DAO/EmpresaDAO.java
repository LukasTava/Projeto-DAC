package model.DAO;

import jakarta.inject.Named;
import model.entities.Empresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Named
public class EmpresaDAO implements DAO<Empresa>, Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager manager;

    @Override
    public void inserir(Empresa empresa) {
        if (empresa == null) {
            throw new IllegalArgumentException("Empresa não pode ser nula.");
        }
        manager.persist(empresa);
    }

    @Override
    public Empresa buscarPorId(Long id){
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        return manager.find(Empresa.class, id);
    }

    @Override
    public void atualizar(Empresa empresa) {
        if (empresa == null) {
            throw new IllegalArgumentException("Empresa não pode ser nula.");
        }
        manager.merge(empresa);
    }

    @Override
    public void remover(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        Empresa empresa = manager.find(Empresa.class, id);
        if (empresa != null) {
            manager.remove(empresa);
        }
    }

    @Override
    public List<Empresa> listar() {
        TypedQuery<Empresa> query = manager.createQuery("SELECT e FROM Empresa e", Empresa.class);
        return query.getResultList();
    }
}
