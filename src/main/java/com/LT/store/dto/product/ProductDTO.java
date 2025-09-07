package com.LT.store.dto.product;


import lombok.*;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String barcode;
    private String name;
    private UUID categoryId;
}
