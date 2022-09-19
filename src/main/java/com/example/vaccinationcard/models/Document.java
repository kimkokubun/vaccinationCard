package com.example.vaccinationcard.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "document",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "cpf")
        })
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //unique identification id
    @Size(max = 11)
    private String cpf;

    @NotNull
    private Date birthDate;

    @NotBlank
    private String motherName;

    @ManyToOne(fetch = FetchType.EAGER, cascade =  CascadeType.REFRESH)
    @JoinColumn(name="user_id")
    private User user;


    public Integer getCurrentAge(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.birthDate);
        return Calendar.getInstance().get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
    }
}
