package controller.services;

import controller.exceptions.BusinessException;
import model.DAO.EmpresaDAO;
import model.entities.Empresa;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
public class EmpresaService implements Serializable {
	private static final long serialVersionUID = 1L;

	private final EmpresaDAO empresaDAO;

	@Inject
	public EmpresaService(EmpresaDAO empresaDAO) {
		this.empresaDAO = empresaDAO;
	}

	public Empresa buscarPorId(Long id) throws BusinessException {
		validarId(id);
		return empresaDAO.buscarPorId(id);
	}

	@Transactional
	public void salvar(Empresa empresa) throws BusinessException {
		validarEmpresa(empresa);
		empresaDAO.inserir(empresa);
	}

	@Transactional
	public void excluir(Empresa empresa) throws BusinessException {
		validarEmpresaExistente(empresa);
		empresaDAO.remover(empresa.getId());
	}

	@Transactional
	public void editar(Empresa empresa) throws BusinessException {
		validarEmpresa(empresa);
		empresaDAO.atualizar(empresa);
	}

	@Transactional
	public void excluir(List<Empresa> empresas) throws BusinessException {
		validarListaEmpresas(empresas);
		empresas.forEach(empresa -> empresaDAO.remover(empresa.getId()));
	}

	public List<Empresa> listar() {
		return empresaDAO.listar();
	}

	private void validarEmpresa(Empresa empresa) throws BusinessException {
		if (empresa == null) {
			throw new BusinessException("Empresa não pode ser nula.");
		}
	}

	private void validarEmpresaExistente(Empresa empresa) throws BusinessException {
		empresa = buscarPorId(empresa.getId());
		if (empresa == null) {
			throw new BusinessException("Empresa não encontrada para exclusão.");
		}
	}

	private void validarId(Long id) throws BusinessException {
		if (id == null) {
			throw new BusinessException("ID não pode ser nulo.");
		}
	}

	private void validarListaEmpresas(List<Empresa> empresas) throws BusinessException {
		if (empresas == null || empresas.isEmpty()) {
			throw new BusinessException("Lista de empresas está vazia.");
		}
	}
}
