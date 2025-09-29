package cohort_65.java.personservice.person.service;

import cohort_65.java.personservice.person.dao.PersonRepository;
import cohort_65.java.personservice.person.dto.AddressDto;
import cohort_65.java.personservice.person.dto.CityPopulationDto;
import cohort_65.java.personservice.person.dto.PersonDto;
import cohort_65.java.personservice.person.dto.exeptions.PersonNotFoundException;
import cohort_65.java.personservice.person.model.Address;
import cohort_65.java.personservice.person.model.Person;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    final PersonRepository personRepository;
    final ModelMapper modelMapper;


    @Override
    public PersonDto addPerson(PersonDto personDto) {
        Person person = modelMapper.map(personDto, Person.class);
        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class) ;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        personRepository.findById(id);
        return modelMapper.map(personRepository.findById(id), PersonDto.class);
    }

    @Override
    @Transactional
    public List<PersonDto> findPersonByCity(String city) {
        return personRepository.findAllByAddressCity(city)
                .map(person -> modelMapper.map(person, PersonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<PersonDto> getAllPersons() {
        return personRepository.findAll().stream().map(person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<PersonDto> findPersonsByAge(Integer minAge, Integer maxAge) {
        LocalDate startAge = LocalDate.now().minusYears(maxAge);
        LocalDate endAge = LocalDate.now().minusYears(minAge);
        return personRepository.findAllByBirthDateBetween(startAge, endAge)
                .map(person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());
    }

    @Override
    public PersonDto updatePersonName(Integer id, String name) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        if(person.getName() !=null){
            person.setName(name);
        }
        person =personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto updatePersonAddress(Integer id, AddressDto addressDto) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setAddress(modelMapper.map(addressDto, Address.class));
        person =personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto deletePersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        personRepository.delete(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional
    public List<CityPopulationDto> getCityPopulation() {
        return personRepository.getCityPopulationByCity().map(city -> modelMapper.map(city, CityPopulationDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<PersonDto> findPersonByName(String name) {
       return personRepository.findAllByName(name).map(person -> modelMapper.map(person, PersonDto.class))
                .collect(Collectors.toList());
    }
}
