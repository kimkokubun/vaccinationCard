package com.example.vaccinationcard.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class DocumentDTO {
    //unique identification id
    @Size(max = 11)
    private String cpf;

    @NotNull
    private Date birthDate;

    @NotBlank
    private String motherName;
}
