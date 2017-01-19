package br.com.ebrother.jeeframework.messages.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import br.com.ebrother.jeeframework.messages.MessageByLocaleService;

/**
 * Implemetação do serviço de mensagens.
 *
 * @author Rafael Braga.
 */
@Component
public class MessageByLocaleServiceImpl implements MessageByLocaleService {

    /** A fonte de mensagens. */
    @Autowired
    private MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    public String getMessage(final String id) {
        final Locale locale = LocaleContextHolder.getLocale();
        return this.messageSource.getMessage(id, null, locale);
    }

}
