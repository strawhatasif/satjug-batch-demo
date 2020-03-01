package com.fun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class SettledAccount extends Account {
    private final static String DELIMITER = ",";
    private String status;

    @Override
    public String toString() {
        return new StringBuilder()
                .append(super.getId()).append(DELIMITER)
                .append(super.getName()).append(DELIMITER)
                .append(super.getProduct()).append(DELIMITER)
                .append(super.getNotes()).append(DELIMITER)
                .append(this.getStatus())
                .toString();
    }
}
