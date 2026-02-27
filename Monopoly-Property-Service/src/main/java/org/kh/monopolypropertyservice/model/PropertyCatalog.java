package org.kh.monopolypropertyservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "property_catalog",
       uniqueConstraints = @UniqueConstraint(
               name = "uq_catalog_map_position",
               columnNames = {"map_id", "tile_position"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PropertyCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** References maps.id in GameEngine Service */
    @Column(name = "map_id", nullable = false)
    private Integer mapId;

    @Column(name = "tile_position", nullable = false)
    private Integer tilePosition;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "property_type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PropertyType propertyType = PropertyType.PROPERTY;

    @Column(name = "color_group", length = 10)
    @Enumerated(EnumType.STRING)
    private ColorGroup colorGroup;

    @Column(name = "purchase_price", nullable = false)
    private Integer purchasePrice;

    @Column(name = "mortgage_value", nullable = false)
    private Integer mortgageValue;

    @Column(name = "unmortgage_cost", nullable = false)
    private Integer unmortgageCost;

    @Column(name = "house_cost")
    private Integer houseCost;

    @Column(name = "hotel_cost")
    private Integer hotelCost;

    @Column(name = "max_houses", nullable = false)
    @Builder.Default
    private Integer maxHouses = 4;

    @OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("houses ASC")
    @Builder.Default
    private List<RentSchedule> rentSchedules = new ArrayList<>();

    public enum PropertyType {
        PROPERTY, RAILROAD, UTILITY
    }

    public enum ColorGroup {
        BROWN, LIGHT_BLUE, PINK, ORANGE,
        RED, YELLOW, GREEN, DARK_BLUE
    }
}
