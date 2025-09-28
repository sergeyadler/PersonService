package cohort_65.java.personservice.person.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    int id ;
    String name;
    LocalDate birthDate;
    AddressDto address;

}

//
//   {
//           "id": 1000,
//           "name": "Peter",
//           "birthDate": "1996-11-12",
//           "address": {
//           "city": "Beer-Sheva",
//           "street": "Herzl",
//           "building": 21
//           }
//           },