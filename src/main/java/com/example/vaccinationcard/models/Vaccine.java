package com.example.vaccinationcard.models;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@TypeDefs({
        @TypeDef(
                name = "int-array",
                typeClass = IntArrayType.class
        )
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vaccine",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    @Column(name = "age_in_days")
    private Integer ageInDays;
    private boolean mandatory;

    @Type(type="int-array")
    @Column(name="booster_in_days", columnDefinition = "int[]")
    private int[] boosterInDays;
    private Integer repeatBoosterInDays;

}
