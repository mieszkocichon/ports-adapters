package org.example.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Setter
@Getter
@ToString(of = "uuid")
@MappedSuperclass
public class BaseEntity {

    public static final String SEQ_GEN = "bigserial";
    @Id
    @GeneratedValue(
            generator = SEQ_GEN,
            strategy = GenerationType.SEQUENCE
    )
    @GenericGenerator(
            name = SEQ_GEN,
            strategy = "org.example.common.BigSerialSequenceGenerator",
            parameters = {
                    @Parameter(name = SequenceStyleGenerator.CONFIG_PREFER_SEQUENCE_PER_ENTITY, value = "true"),
                    @Parameter(name = SequenceStyleGenerator.CONFIG_SEQUENCE_PER_ENTITY_SUFFIX, value = "_id_seq"),
                    @Parameter(name = SequenceStyleGenerator.INITIAL_PARAM, value = "1"),
                    @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
            }
    )
    private Long id;

    private UUID uuid;

    @Version
    private int version;

    @Column(updatable = false)
    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}