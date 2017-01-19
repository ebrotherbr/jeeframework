package br.com.ebrother.jeeframework.exception;

import br.com.ebrother.jeeframework.exception.util.IndicadorTipoException;

/**
 * Exceção genérica.
 *
 * @author Rafael Braga.
 */
public class GenericException extends AbstractException {

    /** Constante de serialização. */
    private static final long serialVersionUID = 7788449426572887941L;

    /**
     * Construtor da classe.
     *
     * @param mensagem Mensagem da exceção.
     * @param tipo Tipo de exceção.
     */
    public GenericException(final String mensagem, final IndicadorTipoException tipo) {
        super(mensagem, tipo);
    }

}
