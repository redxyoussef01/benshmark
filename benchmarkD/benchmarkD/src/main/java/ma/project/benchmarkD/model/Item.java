package ma.project.benchmarkD.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 64)
    @Column(length = 64, unique = true, nullable = false)
    private String sku;

    @NotNull
    @Size(max = 128)
    @Column(length = 128, nullable = false)
    private String name;

    @NotNull
    @PositiveOrZero
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Integer stock;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    // Relation N-1 vers Category (Lazy par défaut pour @ManyToOne)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
