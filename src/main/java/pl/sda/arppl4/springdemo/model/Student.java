package pl.sda.arppl4.springdemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@ApiModel(value = "Student", description = "Encja reprezentująca instancję studenta w bazie danych.")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Identyfikator studenta", example = "1", notes = "identyfikator studentów  bazie danych.")
    private Long id;

    @ApiModelProperty(value = "Imię", example = "Jan", notes = "Imię studenta")
    private String name;
    @ApiModelProperty(value = "Nazwisko", example = "Kowalski", notes = "Nazwisko studenta")
    private String surname;
    @ApiModelProperty(value = "Numer Indeksu", example = "030495", notes = "Numer indeksu studenta. Uwaga! nie mylić z identyfikatorem")
    private String indexNumber;
    @ApiModelProperty(value = "Data Urodzenia", example = "2002-02-02", notes = "Data urodzenia studenta w formacje yyyy-MM-dd")
    LocalDate birthDate;
}
