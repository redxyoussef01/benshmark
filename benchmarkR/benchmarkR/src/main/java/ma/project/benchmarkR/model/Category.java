package ma.project.benchmarkR.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 32)
    @Column(length = 32, unique = true, nullable = false)
    private String code;

    @NotNull
    @Size(max = 128)
    @Column(length = 128, nullable = false)
    private String name;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    // Relation 1-N vers Item
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Item> items;
}
