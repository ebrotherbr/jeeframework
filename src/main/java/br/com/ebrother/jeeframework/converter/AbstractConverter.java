package br.com.ebrother.jeeframework.converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.ebrother.jeeframework.dto.AbstractDTO;
import br.com.ebrother.jeeframework.model.AbstractEntity;

/**
 * Classe abstrata de converter entre entidades e DTOs.
 *
 * @author Rafael Braga.
 *
 * @param <E> a classe de Entidade.
 * @param <D> a classe de DTO.
 */
public abstract class AbstractConverter<E extends AbstractEntity<? extends Serializable>, D extends AbstractDTO> implements Converter<E, D> {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<E> convertToEntityList(final List<D> dtos) {
        final List<E> entities = new ArrayList<>();
        for (final D dto : dtos) {
            entities.add(this.convertToEntity(dto));
        }
        return entities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<D> convertToDTOList(final List<E> entities) {
        final List<D> dtos = new ArrayList<>();
        for (final E entity : entities) {
            dtos.add(this.convertToDTO(entity));
        }
        return dtos;
    }

}
