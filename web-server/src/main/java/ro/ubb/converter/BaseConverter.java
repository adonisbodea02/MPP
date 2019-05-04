package ro.ubb.converter;

import domain.BaseEntity;
import ro.ubb.dto.BaseDTO;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseConverter<Model extends BaseEntity<Long>, DTO extends BaseDTO> implements Converter<Model, DTO>{

    public Set<Long> convertModelsToIDs(Set<Model> models) {
        return models.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toSet());
    }

    public Set<Long> convertDTOsToIDs(Set<DTO> dtos) {
        return dtos.stream()
                .map(BaseDTO::getId)
                .collect(Collectors.toSet());
    }

    public Set<DTO> convertModelsToDTOs(Collection<Model> models) {
        return models.stream()
                .map(this::convertModelToDTO)
                .collect(Collectors.toSet());
    }
}
