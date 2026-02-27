package org.kh.monopolypropertyservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "color_group_config",
       uniqueConstraints = @UniqueConstraint(
               name = "uq_color_group_map",
               columnNames = {"map_id", "color_group"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ColorGroupConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** References maps.id in GameEngine Service */
    @Column(name = "map_id", nullable = false)
    private Integer mapId;

    @Column(name = "color_group", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private PropertyCatalog.ColorGroup colorGroup;

    /** Tổng số ô trong nhóm màu này */
    @Column(name = "total_tiles", nullable = false)
    private Integer totalTiles;

    /**
     * Hệ số nhân tiền thuê khi sở hữu toàn bộ nhóm màu (monopoly).
     * Mặc định = 2.00 (gấp đôi tiền thuê cơ bản)
     */
    @Column(name = "monopoly_rent_multiplier", nullable = false,
            precision = 4, scale = 2)
    @Builder.Default
    private BigDecimal monopolyRentMultiplier = new BigDecimal("2.00");
}
