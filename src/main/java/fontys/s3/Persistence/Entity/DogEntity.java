package fontys.s3.Persistence.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "dog")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "breed")
    private String breed;

    @NotNull
    @Column(name = "age")
    private int age;

    @NotNull
    @Column(name = "dog_years")
    private int dogYears;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private DogSizeEntity size;
}
