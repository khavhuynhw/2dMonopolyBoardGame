package org.kh.monopolygameengineservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "map_tiles",
       uniqueConstraints = @UniqueConstraint(name = "uq_map_position", columnNames = {"map_id", "position"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MapTile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "map_id", nullable = false)
    private GameMap map;

    /** Board position 0–39 */
    @Column(nullable = false)
    private Integer position;

    @Column(name = "tile_type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private TileType tileType;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "color_group", length = 20)
    @Enumerated(EnumType.STRING)
    private ColorGroup colorGroup;

    private Integer price;

    @Column(name = "rent_base")
    private Integer rentBase;

    @Column(name = "rent_1h")
    private Integer rent1h;

    @Column(name = "rent_2h")
    private Integer rent2h;

    @Column(name = "rent_3h")
    private Integer rent3h;

    @Column(name = "rent_4h")
    private Integer rent4h;

    @Column(name = "rent_hotel")
    private Integer rentHotel;

    @Column(name = "house_cost")
    private Integer houseCost;

    @Column(name = "hotel_cost")
    private Integer hotelCost;

    private Integer mortgage;

    public enum TileType {
        GO, PROPERTY, RAILROAD, UTILITY, TAX,
        CHEST, CHANCE, JAIL, FREE_PARKING, GO_TO_JAIL, CORNER
    }

    public enum ColorGroup {
        BROWN, LIGHT_BLUE, PINK, ORANGE, RED,
        YELLOW, GREEN, DARK_BLUE
    }
}
