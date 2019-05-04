package ro.ubb.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class ClientDTO extends BaseDTO{
    private String name;
    private String dateOfBirth;
}
