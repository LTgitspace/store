package com.LT.store.model.storage;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "storages")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

}
