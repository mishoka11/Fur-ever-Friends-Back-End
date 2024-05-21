package fontys.s3.Persistence.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "dog_size")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogSizeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private Size size;

    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<DogEntity> dog;
}
