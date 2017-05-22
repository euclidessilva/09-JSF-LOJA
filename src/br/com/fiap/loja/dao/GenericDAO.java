package br.com.fiap.loja.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import br.com.fiap.loja.exception.DataBaseException;



public interface GenericDAO<T extends Serializable,K> {
	public T adicionar(T entidade) throws DataBaseException;
	public void adicionar(Collection<T> entidades)throws DataBaseException;
	public T atualizar(T entidade) throws DataBaseException;
	public void atualizar(Collection<T> entidades)throws DataBaseException;
	public void remover(K codigo) throws DataBaseException,EntityNotFoundException;
	public void remover(Collection<K> codigos) throws DataBaseException;
	public T buscarPorId(K codigo) throws EntityNotFoundException;
	public List<T> buscarTodos() throws EntityNotFoundException;
	public List<T> buscarTodosPaginado(int inicio, int quantidade)throws EntityNotFoundException;
	public Long contarTodos(T entidade)throws EntityNotFoundException;

}