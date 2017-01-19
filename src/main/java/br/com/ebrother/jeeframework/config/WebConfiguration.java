package br.com.ebrother.jeeframework.config;

import java.util.Locale;

import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Classe responsável por realizar as configurações básicas da aplicação.
 *
 * @author Rafael Braga
 */
@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {

    /** Classpath dos arquivos de mensagem. */
    private static final String CLASSPATH_I18N_MESSAGES = "classpath:i18n/messages";

    /** Linguagem padrão da aplicação. */
    private static final Locale LOCALE_PT_BR = new Locale("pt", "BR");

    /** Parâmetro de configuração da linguagem da aplicação. */
    private static final String PARAM_LOCALE = "lang";

    /**
     * Configura a linguagem padrão da aplicação para pt-BR.
     *
     * @return {@link LocaleResolver}.
     */
    @Bean
    public LocaleResolver localeResolver() {
        final SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(LOCALE_PT_BR);
        return localeResolver;
    }

    /**
     * Permite o acesso a conteúdos estáticos.
     *
     * @param configurer {@link DefaultServletHandlerConfigurer}.
     */
    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Adiciona o interceptor responsável por trocar a linguagem da aplicação.
     *
     * @param registry {@link InterceptorRegistry}.
     */
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(this.localeChangeInterceptor());
    }

    /**
     * Configura o mecanismo de troca de linguagem.
     *
     * @return {@link LocaleChangeInterceptor}.
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName(PARAM_LOCALE);
        return localeChangeInterceptor;
    }

    /**
     * Configura a aplicação para usar os arquivos de mensagem.
     *
     * @return o {@link MessageSource}.
     */
    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource ret = new ReloadableResourceBundleMessageSource();
        ret.setBasename(CLASSPATH_I18N_MESSAGES);
        return ret;
    }

    /**
     * Instancia uma {@link DispatcherServlet}.
     *
     * @return a {@link DispatcherServlet}.
     */
    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    /**
     * Configura o servlet de requisições aos serviços REST.
     *
     * @return {@link ServletRegistrationBean}.
     */
    @Bean
    public ServletRegistrationBean dispatchServletRegistration() {
        final ServletRegistrationBean registration = new ServletRegistrationBean(this.dispatcherServlet(), "/rest/*");
        registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
        return registration;
    }

    /**
     * Habilita o CORS.
     *
     * @param registry {@link CorsRegistry}.
     */
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**");
    }

    /**
     * Configura a aplicação para tratar adequadamente os arquivos Multipart.
     *
     * @return {@link CommonsMultipartResolver}.
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

}
