package model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dateSerialization.LocalDateDeserializer;
import dateSerialization.LocalDateSerializer;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Account implements Comparable<Account> {

    private String iban;
    private String name;
    private String currency;
    private double balance;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate closingDate;

    @Override
    public int compareTo(Account o) {
        return String.CASE_INSENSITIVE_ORDER.compare(this.name, o.name);
    }
}

