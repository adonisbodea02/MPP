package ro.ubb.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class BookDTO extends BaseDTO{
    private String title;
    private String genre;
    private int year;
}
