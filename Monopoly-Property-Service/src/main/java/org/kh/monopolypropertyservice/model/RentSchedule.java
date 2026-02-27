package org.kh.monopolypropertyservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rent_schedule",
       uniqueConstraints = @UniqueConstraint(
               name = "uq_rent_catalog_houses",
               columnNames = {"catalog_id", "houses"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RentSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "catalog_id", nullable = false)
    private PropertyCatalog catalog;

    /**
     * Số nhà trên ô:
     *  0 = không có nhà (hoặc chưa monopoly)
     *  1–4 = số nhà
     *  5 = khách sạn
     *  Với Railroad: 1–4 = số railroad đang sở hữu
     *  Với Utility: 1 = sở hữu 1 utility (x4 dice), 2 = cả 2 (x10 dice)
     */
    @Column(nullable = false)
    private Integer houses;

    @Column(name = "rent_amount", nullable = false)
    private Integer rentAmount;
}
