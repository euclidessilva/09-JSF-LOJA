package br.com.fiap.loja.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.fiap.loja.dao.GenericDAO;
import br.com.fiap.loja.exception.DataBaseException;
import br.com.fiap.loja.singleton.EntityManagerFactorySingleton;

public abstract class GenericDAOImpl<T extends Serializable, K> implements GenericDAO<T, K> {

	private EntityManager em;
	private final Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	@Override
	public T adicionar(T entidade) throws DataBaseException {
		try {
			em = EntityManagerFactorySingleton.getInstance().createEntityManager();
			em.getTransaction().begin();
			em.persist(entidade);
			em.getTransaction().commit();
			return entidade;
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			throw new DataBaseException("Cadastro não realizado");
		} finally {
			em.close();
		}
	}
	@Override
	public void adicionar(Collection<T> entidades) throws DataBaseException {
		try {
			em = EntityManagerFactorySingleton.getInstance().createEntityManager();
			em.getTransaction().begin();
			entidades.forEach(em::persist);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			throw new DataBaseException("Cadastro em lote não realizado");
		} finally {
			em.close();
		}

	}
	@Override
	public T atualizar(T entidade) throws DataBaseException {
		try {
			em = EntityManagerFactorySingleton.getInstance().createEntityManager();
			em.getTransaction().begin();
			em.merge(entidade);
			em.getTransaction().commit();
			return entidade;
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			throw new DataBaseException("Atualização não realizada");
		} finally {
			em.close();
		}
	}

	@Override
	public void atualizar(Collection<T> entidades) throws DataBaseException {
		try {
			em = EntityManagerFactorySingleton.getInstance().createEntityManager();
			em.getTransaction().begin();
			entidades.forEach(em::merge);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			throw new DataBaseException("Atualização em lote não realizada");
		} finally {
			em.close();
		}
	}

	@Override
	public void remover(K codigo) throws DataBaseException, EntityNotFoundException {
		try {
			em = EntityManagerFactorySingleton.getInstance().createEntityManager();
			em.getTransaction().begin();
			em.remove(em.find(entityClass, codigo));
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			throw new DataBaseException("Exclusão não realizada");
		} finally {
			em.close();
		}
	}

	@Override
	public void remover(Collection<K> codigos) throws DataBaseException {
		try {
			em = EntityManagerFactorySingleton.getInstance().createEntityManager();
			em.getTransaction().begin();
			codigos.forEach(codigo -> em.remove(em.find(entityClass, codigo)));
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			throw new DataBaseException("Exclusão em lote não realizada");
		} finally {
			em.close();
		}
	}
	
	
	@Override
	public T buscarPorId(K codigo) throws EntityNotFoundException {
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		T entidade = em.find(entityClass, codigo);
		if (entidade == null) {
			throw new EntityNotFoundException();
		}
		em.close();
		return entidade;
	}

	@Override
	public List<T> buscarTodos() throws EntityNotFoundException {
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(entityClass);
		query.select(query.from(entityClass));

		List<T> lista = em.createQuery(query).getResultList();
		if (lista == null) {
			throw new EntityNotFoundException();
		}
		em.close();
		return lista;
	}

	@Override
	public List<T> buscarTodosPaginado(int inicio, int quantidade) throws EntityNotFoundException {
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(entityClass);
		query.select(query.from(entityClass));

		List<T> lista = em.createQuery(query).setFirstResult(inicio).setMaxResults(quantidade).getResultList();
		if (lista == null) {
			throw new EntityNotFoundException();
		}
		em.close();
		return lista;
	}

	@Override
	public Long contarTodos(T entidade) throws EntityNotFoundException {
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(entityClass)));
		Long count = em.createQuery(cq).getSingleResult();

		if (count == null) {
			throw new EntityNotFoundException();
		}
		em.close();
		return count;
	}
	
}
