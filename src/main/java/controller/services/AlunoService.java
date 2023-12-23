package controller.services;

import controller.exceptions.BusinessException;
import model.DAO.AlunoDAO;
import model.entities.Aluno;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
public class AlunoService implements Serializable {
	private static final long serialVersionUID = 1L;

	private final AlunoDAO alunoDAO;

	@Inject
	public AlunoService(AlunoDAO alunoDAO) {
		this.alunoDAO = alunoDAO;
	}

	// Busca um aluno por ID
	public Aluno buscarAluno(Long id) {
		return this.alunoDAO.buscarPorId(id);
	}

	// Salva um novo aluno
	@Transactional
	public void salvarAluno(Aluno aluno) throws BusinessException {
		validarAluno(aluno);
		this.alunoDAO.inserir(aluno);
	}

	// Exclui um aluno
	@Transactional
	public void excluirAluno(Aluno aluno) throws BusinessException {
		validarExistenciaAluno(aluno);
		this.alunoDAO.remover(aluno.getId());
	}

	// Edita um aluno existente
	@Transactional
	public void editarAluno(Aluno aluno) throws BusinessException {
		validarAluno(aluno);
		this.alunoDAO.atualizar(aluno);
	}

	// Exclui uma lista de alunos
	@Transactional
	public void excluirAlunos(List<Aluno> alunos) throws BusinessException {
		validarListaAlunos(alunos);
		alunos.forEach(aluno -> this.alunoDAO.remover(aluno.getId()));
	}

	// Lista todos os alunos
	public List<Aluno> listarAlunos() {
		return this.alunoDAO.listar();
	}

	private void validarAluno(Aluno aluno) throws BusinessException {
		if (aluno == null) {
			throw new BusinessException("Aluno não pode ser nulo.");
		}
	}

	private void validarExistenciaAluno(Aluno aluno) throws BusinessException {
		aluno = buscarAluno(aluno.getId());
		if (aluno == null) {
			throw new BusinessException("Aluno não encontrado para exclusão.");
		}
	}

	private void validarListaAlunos(List<Aluno> alunos) throws BusinessException {
		if (alunos == null || alunos.isEmpty()) {
			throw new BusinessException("Lista de alunos está vazia.");
		}
	}

	public void excluir(Aluno alunoSelecionado) {
	}
}
