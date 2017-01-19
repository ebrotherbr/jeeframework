package br.com.ebrother.jeeframework.controller.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ebrother.jeeframework.controller.AbstractController;
import br.com.ebrother.jeeframework.exception.GenericException;
import br.com.ebrother.jeeframework.exception.NenhumRegistroEncontradoException;
import br.com.ebrother.jeeframework.exception.util.ResponseExceptionDTO;

@ControllerAdvice
public abstract class AbstractExceptionControllerAdvice extends AbstractController {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ResponseExceptionDTO> lancarExcecaoNegocio(final HttpServletRequest req, final GenericException e) {
        final ResponseExceptionDTO response = new ResponseExceptionDTO();
        response.setMensagem(e.getMensagem());
        response.setTipo(e.getTipo().getDescricao());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NenhumRegistroEncontradoException.class)
    public ResponseEntity<String> lancarExcecaoNenhumRegistroEncontrado(final HttpServletRequest req, final NenhumRegistroEncontradoException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
