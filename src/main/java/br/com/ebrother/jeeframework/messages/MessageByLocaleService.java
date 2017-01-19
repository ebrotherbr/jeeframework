package br.com.ebrother.jeeframework.messages;

/**
 * Interface do serviço de mensagens.
 *
 * @author Rafael Braga
 */
public interface MessageByLocaleService {

    /**
     * Busca uma mensagem.
     *
     * @param id identificador da mensagem.
     * @return a mensagem.
     */
    String getMessage(String id);

}
