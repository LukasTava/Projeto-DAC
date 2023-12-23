package model.DAO;

import java.util.List;

/**
 * Interface genérica para operações de acesso a dados.
 * @param <T> Tipo da entidade.
 */
public interface DAO<T> {

    /**
     * Insere uma entidade no banco de dados.
     * @param entidade Entidade a ser inserida.
     */
    void inserir(T entidade);

    /**
     * Busca uma entidade pelo seu ID.
     * @param id O ID da entidade.
     * @return A entidade encontrada, ou null se não for encontrada.
     */
    T buscarPorId(Long id);

    /**
     * Atualiza uma entidade no banco de dados.
     * @param entidade Entidade a ser atualizada.
     */
    void atualizar(T entidade);

    /**
     * Remove uma entidade do banco de dados pelo seu ID.
     * @param id O ID da entidade.
     */
    void remover(Long id);

    /**
     * Lista todas as entidades de um tipo.
     * @return Uma lista de entidades.
     */
    List<T> listar();
}
