package controller.services;

import controller.exceptions.BusinessException;
import model.DAO.OrientadorDAO;
import model.entities.Orientador;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
public class OrientadorService implements Serializable {
	private static final long serialVersionUID = 1L;

	private final OrientadorDAO orientadorDAO;

	@Inject
	public OrientadorService(OrientadorDAO orientadorDAO) {
		this.orientadorDAO = orientadorDAO;
	}

	public Orientador buscarOrientadorPorId(Long id) throws BusinessException {
		validarId(id);
		return orientadorDAO.buscarPorId(id);
	}

	@Transactional
	public void salvarOrientador(Orientador orientador) throws BusinessException {
		validarOrientador(orientador);
		orientadorDAO.inserir(orientador);
	}

	@Transactional
	public void excluirOrientador(Orientador orientador) throws BusinessException {
		validarExistenciaOrientador(orientador);
		orientadorDAO.remover(orientador.getId());
	}

	@Transactional
	public void editarOrientador(Orientador orientador) throws BusinessException {
		validarOrientador(orientador);
		orientadorDAO.atualizar(orientador);
	}

	@Transactional
	public void excluirOrientadores(List<Orientador> orientadores) throws BusinessException {
		validarListaOrientadores(orientadores);
		orientadores.forEach(orientador -> orientadorDAO.remover(orientador.getId()));
	}

	public List<Orientador> listarOrientadores() {
		return orientadorDAO.listar();
	}

	private void validarOrientador(Orientador orientador) throws BusinessException {
		if (orientador == null) {
			throw new BusinessException("Orientador não pode ser nulo.");
		}
	}

	private void validarExistenciaOrientador(Orientador orientador) throws BusinessException {
		orientador = buscarOrientadorPorId(orientador.getId());
		if (orientador == null) {
			throw new BusinessException("Orientador não encontrado para exclusão.");
		}
	}

	private void validarId(Long id) throws BusinessException {
		if (id == null) {
			throw new BusinessException("ID não pode ser nulo.");
		}
	}

	private void validarListaOrientadores(List<Orientador> orientadores) throws BusinessException {
		if (orientadores == null || orientadores.isEmpty()) {
			throw new BusinessException("Lista de orientadores está vazia.");
		}
	}
}
