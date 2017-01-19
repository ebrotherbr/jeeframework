package br.com.ebrother.jeeframework.exception;

import br.com.ebrother.jeeframework.exception.util.IndicadorTipoException;

/**
 * Classe abstrata de exceção.
 *
 * @author Rafael Braga.
 */
public abstract class AbstractException extends RuntimeException {

    /** Constante de serialização. */
    private static final long serialVersionUID = -8834904231920657289L;

    /** Mensagem da exceção. */
    private final String mensagem;

    /** Tipo de exceção. */
    private final IndicadorTipoException tipo;

    /**
     * Construtor da classe.
     *
     * @param mensagem Mensagem da exceção.
     * @param tipo Tipo de exceção.
     */
    public AbstractException(final String mensagem, final IndicadorTipoException tipo) {
        this.mensagem = mensagem;
        this.tipo = tipo;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return this.mensagem;
    }

    /**
     * @return the tipo
     */
    public IndicadorTipoException getTipo() {
        return this.tipo;
    }

}
