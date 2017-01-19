package br.com.ebrother.jeeframework.exception.util;

/**
 * Enumerador de tipos de exceção.
 *
 * @author Rafael Braga.
 */
public enum IndicadorTipoException {

    /** Indica uma exceção de erro. */
    ERRO(0, "Erro");

    /** Código da exceção. */
    private Integer codigo;

    /** Descrição da exceção. */
    private String descricao;

    /**
     * Construtor da classe.
     * 
     * @param codigo Código da exceção.
     * @param descricao Descrição da exceção.
     */
    private IndicadorTipoException(final Integer codigo, final String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return this.codigo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return this.descricao;
    }

}