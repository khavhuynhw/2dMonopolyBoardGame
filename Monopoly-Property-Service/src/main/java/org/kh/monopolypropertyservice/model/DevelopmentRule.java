package org.kh.monopolypropertyservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "development_rules",
       uniqueConstraints = @UniqueConstraint(name = "uq_dev_rules_map", columnNames = "map_id"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DevelopmentRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** References maps.id in GameEngine Service */
    @Column(name = "map_id", nullable = false, unique = true)
    private Integer mapId;

    /**
     * Bắt buộc xây đều trong nhóm màu:
     * không được xây nhà thứ 2 cho 1 ô khi ô khác cùng màu chưa có nhà.
     */
    @Column(name = "even_build_required", nullable = false)
    @Builder.Default
    private Boolean evenBuildRequired = true;

    /**
     * Tỷ lệ hoàn tiền khi bán lại nhà / khách sạn.
     * Mặc định = 0.50 (50% giá mua).
     */
    @Column(name = "sell_back_ratio", nullable = false, precision = 4, scale = 2)
    @Builder.Default
    private BigDecimal sellBackRatio = new BigDecimal("0.50");

    /** Số nhà tối đa trong toàn bộ game (nguồn cung giới hạn). */
    @Column(name = "max_houses_supply", nullable = false)
    @Builder.Default
    private Integer maxHousesSupply = 32;

    /** Số khách sạn tối đa trong toàn bộ game. */
    @Column(name = "max_hotels_supply", nullable = false)
    @Builder.Default
    private Integer maxHotelsSupply = 12;
}
