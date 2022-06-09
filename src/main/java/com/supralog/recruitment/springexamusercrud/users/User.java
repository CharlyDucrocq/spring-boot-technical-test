package com.supralog.recruitment.springexamusercrud.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.supralog.recruitment.springexamusercrud.common.DefaultDto;
import com.supralog.recruitment.springexamusercrud.common.error.BadAttributeException;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
public class User implements DefaultDto {
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
    private String country;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @ApiModelProperty(example = "04-04-1983")
    private LocalDate birthDate;

    @Pattern(regexp = "\\d{10}", message = "Phone number invalid")
    @ApiModelProperty(example = "0708090102")
    private String phoneNumber;

    Integer getAge(){
        if (birthDate == null) return null;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public void validate() throws BadAttributeException {
        if(this.birthDate != null && this.getAge() < MINIMAL_AGE_REQUIRED){
            throw new BadAttributeException("Too young to be registered");
        }
    }
}