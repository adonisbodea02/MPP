package ro.ubb.converter;

import domain.BaseEntity;
import ro.ubb.dto.BaseDTO;

public interface Converter<Model extends BaseEntity<Long>, DTO extends BaseDTO> {

    Model convertDTOToModel(DTO dto);

    DTO convertModelToDTO(Model model);

}
