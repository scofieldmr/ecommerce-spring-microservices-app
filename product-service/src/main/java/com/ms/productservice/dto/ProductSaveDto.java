package com.ms.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSaveDto {
    @NotBlank(message = "Name Required")
    private String name;

    @NotBlank(message = "Description Required")
    private String description;

    @NotNull(message = "Price Required")
    private BigDecimal price;
}
