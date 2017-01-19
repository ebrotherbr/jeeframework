package br.com.ebrother.jeeframework.exception.util;

/**
 * Classe que representa o DTO de uma exceção.
 *
 * @author Rafael Braga.
 */
public class ResponseExceptionDTO {

    /** Mensagem da exceção. */
    private String mensagem;

    /** Tipo de exceção. */
    private String tipo;

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return this.mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(final String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

}
