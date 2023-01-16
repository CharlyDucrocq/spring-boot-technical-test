package com.supralog.recruitment.springexamusercrud.users.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "user_entity")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public static final int MINIMAL_AGE_REQUIRED = 18;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull
    @Size( min = 2, max = 20)
    @NotBlank(message = "Name is mandatory")
    @ApiModelProperty(example = "Mario Bros")
    private String name;

    @ApiModelProperty(example = "MALE")
    private Gender gender;

    @NotNull
    @NotBlank(message = "Country of residence is mandatory")
    @ApiModelProperty(example = "France")
    @Pattern(regexp = "France", message = "Only user from \"France\" can be registered.")
    private String country;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @ApiModelProperty(example = "04-04-1983")
    private LocalDate birthDate;

    @Pattern(regexp = "\\d{10}", message = "Phone number invalid")
    @ApiModelProperty(example = "0708090102")
    private String phoneNumber;
    @JsonIgnore
    @Min(value = MINIMAL_AGE_REQUIRED, message = "Too young to be registered")
    Integer getAge(){
        if (birthDate == null) return null;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public User(Long id) {
        this.id = id;
    }
}