package fontys.s3.Domain;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDogRequest {
    @Setter
    private long dogId;
    private String name;
    private String breed;
    private int age;
    private int years;

    public void setId(long id) {
    }
}