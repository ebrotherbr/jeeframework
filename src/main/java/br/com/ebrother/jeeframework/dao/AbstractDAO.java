package br.com.ebrother.jeeframework.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.ebrother.jeeframework.messages.MessageByLocaleService;
import br.com.ebrother.jeeframework.model.AbstractEntity;

/**
 * Classe abstrata de acesso ao banco de dados.
 *
 * @author Rafael Braga
 *
 * @param <E> Entidade
 * @param <PK> Pk
 */
public abstract class AbstractDAO<E extends AbstractEntity<P>, P extends Serializable> {

    /** O {@link EntityManager} para acesso ao banco de dados. */
    @PersistenceContext
    private EntityManager entityManager;

    /** Serviço de mensagens. */
    @Autowired
    private MessageByLocaleService mensagens;

    /**
     * Recupera um objeto pelo seu identificador.
     *
     * @param id o identificador do objeto.
     * @return o objeto recuperado.
     */
    public E findOne(final P id) {
        return this.getEntityManager().find(this.getEntityClass(), id);
    }

    /**
     * Recupera todos os registros.
     *
     * @return os registros.
     */
    public List<E> listAll() {
        final Criteria criteria = this.createCriteria();
        return this.executeCriteria(criteria);
    }

    /**
     * Salva um objeto.
     *
     * @param objeto o objeto a ser salvo.
     * @return o objeto salvo.
     */
    public E save(final E objeto) {
        if (objeto.getId() == null) {
            this.getEntityManager().persist(objeto);
            return objeto;
        }
        return this.getEntityManager().merge(objeto);
    }

    /**
     * Exclui um registro.
     *
     * @param objeto o objeto a ser excluído.
     */
    public void delete(final E objeto) {
        this.getEntityManager().remove(objeto);
    }

    /**
     * Exclui um registro.
     *
     * @param id o identificador do registro a ser excluído.
     */
    public void delete(final P id) {
        this.getEntityManager().remove(this.getEntityManager().find(this.getEntityClass(), id));
    }

    /**
     * Retorna a classe da entidade.
     *
     * @return a classe da entidade.
     */
    @SuppressWarnings("unchecked")
    protected Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Cria uma subquery.
     *
     * @return a {@link DetachedCriteria}.
     */
    protected DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(this.getEntityClass());
    }

    /**
     * Cria a query.
     *
     * @return a {@link Criteria}.
     */
    protected Criteria createCriteria() {
        final DetachedCriteria detachedCriteria = this.createDetachedCriteria();
        return detachedCriteria.getExecutableCriteria((Session) this.entityManager.getDelegate());
    }

    /**
     * Executa a {@link Criteria} com paginação e ordenação.
     *
     * @param criteria a {@link Criteria}.
     * @param firstResult o índice do primeiro registro.
     * @param maxResults o número de registros.
     * @param mapOrdenacao os campos para ordenação.
     * @return a lista com os registros encontrados.
     */
    @SuppressWarnings({ "unchecked" })
    protected <T> List<T> executeCriteria(final Criteria criteria, final int firstResult, final int maxResults, final Map<String, Boolean> mapOrdenacao) {
        criteria.setFirstResult(firstResult);
        if (maxResults > 0) {
            criteria.setMaxResults(maxResults);
        }
        for (final Map.Entry<String, Boolean> entry : mapOrdenacao.entrySet()) {
            this.addOrder(criteria, entry.getKey(), entry.getValue());
        }
        return criteria.list();
    }

    /**
     * Realiza uma consulta paginada com ordenação em uma propriedade.
     *
     * @param criteria a {@link Criteria}.
     * @param firstResult o índice do primeiro registro.
     * @param maxResults o número de registros.
     * @param orderProperty a propriedade usada para ordenação.
     * @param asc true para ascendente ou false para descendente.
     * @return os registros encontrados.
     */
    protected <T> List<T> executeCriteria(final Criteria criteria, final int firstResult, final int maxResults, final String orderProperty, final boolean asc) {
        final Map<String, Boolean> mapOrdenacao = new HashMap<>();
        if (orderProperty != null && !orderProperty.trim().isEmpty()) {
            mapOrdenacao.put(orderProperty, Boolean.valueOf(asc));
        }
        return this.executeCriteria(criteria, firstResult, maxResults, mapOrdenacao);
    }

    /**
     * Realiza uma consulta paginada com ordenação em uma propriedade.
     *
     * @param criteria a {@link Criteria}.
     * @param firstResult o índice do primeiro registro.
     * @param maxResults o número de registros.
     * @return os registros encontrados.
     */
    protected <T> List<T> executeCriteria(final Criteria criteria, final int firstResult, final int maxResults) {
        return this.executeCriteria(criteria, firstResult, maxResults, null, false);
    }

    /**
     * Executa a query.
     *
     * @param criteria a {@link Criteria}.
     * @return a lista com os registros encontrados.
     */
    protected <T> List<T> executeCriteria(final Criteria criteria) {
        return this.executeCriteria(criteria, 0, 0, null, false);
    }

    /**
     * Adiciona ordenação na query.
     *
     * @param criteria a {@link Criteria}.
     * @param orderProperty a propriedade para ordenação.
     * @param asc ascendente ou descendente.
     */
    private void addOrder(final Criteria criteria, final String orderProperty, final boolean asc) {
        if (orderProperty != null && !orderProperty.trim().isEmpty()) {
            criteria.addOrder(asc ? Order.asc(orderProperty).ignoreCase() : Order.desc(orderProperty));
        }
    }

    /**
     * Retorna o número total de registros encontrados na query.
     *
     * @param criteria a {@link Criteria}.
     * @return o número total de registros.
     */
    protected Long executeCriteriaCount(final Criteria criteria) {
        criteria.setProjection(Projections.rowCount());
        final Long result = (Long) criteria.uniqueResult();
        if (result == null) {
            return 0L;
        }
        return result;
    }

    /**
     * Retorna o número total de registros encontrados na query.
     *
     * @param criteria a {@link Criteria}.
     * @param countProperty propriedade para o distinct.
     * @return o número total de registros.
     */
    protected Long executeCriteriaCountDistinct(final Criteria criteria, final String countProperty) {
        criteria.setProjection(Projections.countDistinct(countProperty));
        final Long result = (Long) criteria.uniqueResult();
        if (result == null) {
            return 0L;
        }
        return result;
    }

    /**
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    /**
     * @return the mensagens
     */
    public MessageByLocaleService getMensagens() {
        return this.mensagens;
    }

}
