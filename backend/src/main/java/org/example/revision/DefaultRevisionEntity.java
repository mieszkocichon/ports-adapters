package org.example.revision;

import jakarta.persistence.*;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;


@Entity
@RevisionEntity
@Table(name = "revinfo")
public class DefaultRevisionEntity {
    @Id
    @SequenceGenerator(name = "revinfo_seq_gen", sequenceName = "revinfo_rev_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "revinfo_seq_gen")
    @RevisionNumber
    private Long rev;
    @RevisionTimestamp
    private Long revtstmp;
}
