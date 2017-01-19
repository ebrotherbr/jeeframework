package br.com.ebrother.jeeframework.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ebrother.jeeframework.messages.MessageByLocaleService;

/**
 * Classe abstrata de serviço.
 *
 * @author Rafael Braga
 */
public abstract class AbstractService {

    /** Serviço de mensagens. */
    @Autowired
    protected MessageByLocaleService mensagens;

}
