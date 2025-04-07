package com.ui.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
@Table("items")
public class Item {

    @Id
    private Long itemId;
    private String name;
    private String description;
    private BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return Objects.equals(getItemId(), item.getItemId()) && Objects.equals(getName(), item.getName()) && Objects.equals(getName(), item.getName()) && Objects.equals(getDescription(), item.getDescription()) && Objects.equals(getPrice(), item.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId(), getName(), getDescription(), getPrice());
    }

}
