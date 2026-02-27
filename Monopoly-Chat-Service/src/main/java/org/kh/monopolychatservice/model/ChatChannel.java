package org.kh.monopolychatservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chat_channels")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ChatChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private ChannelType type;

    /** room_id for ROOM channels, null for GLOBAL */
    @Column(name = "ref_id")
    private Long refId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdAt ASC")
    @Builder.Default
    private List<Message> messages = new ArrayList<>();

    @OneToOne(mappedBy = "channel", cascade = CascadeType.ALL, orphanRemoval = true)
    private PrivateChannel privateChannel;

    public enum ChannelType {
        GLOBAL, ROOM, PRIVATE
    }
}

