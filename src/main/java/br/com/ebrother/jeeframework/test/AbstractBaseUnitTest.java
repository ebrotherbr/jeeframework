package br.com.ebrother.jeeframework.test;

import java.lang.reflect.ParameterizedType;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Classe auxiliar para testes unitários.
 *
 * @author Luis Moura - CTIS.
 *
 * @param <C> a classe que será testada.
 */
public abstract class AbstractBaseUnitTest<C> {

    /**
     * Classe de teste. É um {@link Mockito#spy(Class)} para poder "sobrescrever" métodos internos.
     */
    @InjectMocks
    protected C classeEmTeste = Mockito.spy(this.getClazz());

    /**
     * Inicializa os mocks antes de cada teste.
     */
    @Before
    public void setUpBefore() {
        MockitoAnnotations.initMocks(this);
        this.beforeTest();
    }

    /**
     * Caso necessário algum comportamento padrão no @Before
     */
    protected void beforeTest() {
        // PREENCHER CASO NECESSÁRIO
    }

    /**
     * Busca a classe que está executando.
     *
     * @return {@link Class} em execução.
     */
    @SuppressWarnings("unchecked")
    private Class<C> getClazz() {
        final ParameterizedType paramType = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<C>) paramType.getActualTypeArguments()[0];
    }

}
