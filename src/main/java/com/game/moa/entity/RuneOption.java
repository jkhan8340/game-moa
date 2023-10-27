package com.game.moa.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "rune_option")
public class RuneOption {

    @Id
    @Column(name = "rune_option_seq", nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long runeOptionSeq;

    @Column(name = "ability", nullable = false)
    private String ability;

    @Column(name = "item_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rune_seq")
    private Rune rune;

    @Getter
    @JsonSerialize(using = ItemType.Serializer.class)
    public enum ItemType {
        WEAPON("무기"),
        ARMOR("갑옷"),
        HELM("투구"),
        SHIELD("방패");

        private final String title;
        ItemType(String title) {
            this.title = title;
        }

        public static class Serializer extends JsonSerializer<ItemType> {
            @Override
            public void serialize(ItemType value, JsonGenerator gen, SerializerProvider provider) throws IOException {
                gen.writeString(value.getTitle());
            }
        }
    }
}
