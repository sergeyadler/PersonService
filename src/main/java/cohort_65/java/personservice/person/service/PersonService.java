package cohort_65.java.personservice.person.service;

import cohort_65.java.personservice.person.dto.AddressDto;
import cohort_65.java.personservice.person.dto.CityPopulationDto;
import cohort_65.java.personservice.person.dto.PersonDto;

import java.util.List;

public interface PersonService {
    PersonDto addPerson(PersonDto personDto);

    PersonDto findPersonById(Integer id);

    List<PersonDto> findPersonByCity(String city);

    List<PersonDto> getAllPersons();

    List<PersonDto> findPersonsByAge(Integer age1, Integer age2);

    PersonDto updatePersonName(Integer id, String name);

    PersonDto updatePersonAddress(Integer id, AddressDto addressDto);

    PersonDto deletePersonById(Integer id);

    List<CityPopulationDto>  getCityPopulation();
}
