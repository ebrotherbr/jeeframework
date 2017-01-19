package br.com.ebrother.jeeframework.controller;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ebrother.jeeframework.messages.MessageByLocaleService;

/**
 * Classe abstrata de controller.
 *
 * @author Rafael Braga.
 */
public abstract class AbstractController {

    /** Serviço de mensagens. */
    @Autowired
    protected MessageByLocaleService mensagens;

}
