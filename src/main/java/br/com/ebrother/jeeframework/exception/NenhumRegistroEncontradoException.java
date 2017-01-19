package br.com.ebrother.jeeframework.exception;

import br.com.ebrother.jeeframework.exception.util.IndicadorTipoException;

/**
 * Exceção lançada em caso de nenhum registro ter sido encontrado na busca.
 *
 * @author Rafael Braga.
 */
public class NenhumRegistroEncontradoException extends AbstractException {

    /** Constante de serialização. */
    private static final long serialVersionUID = -8536199773502445584L;

    /** Mensagem padrão. */
    private final static String mensagem = "Nenhum registro encontrado!";

    /**
     * Construtor da classe.
     */
    public NenhumRegistroEncontradoException() {
        super(mensagem, IndicadorTipoException.ERRO);
    }

}
