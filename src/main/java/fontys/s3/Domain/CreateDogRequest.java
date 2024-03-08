package fontys.s3.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDogRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String breed;

    @NotNull
    private Integer age;

    @NotNull
    private Integer years;
}