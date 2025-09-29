package cohort_65.java.personservice.person.controller;

import cohort_65.java.personservice.person.dto.AddressDto;
import cohort_65.java.personservice.person.dto.CityPopulationDto;
import cohort_65.java.personservice.person.dto.PersonDto;
import cohort_65.java.personservice.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    final PersonService personService;
    @PostMapping
    public PersonDto  addPerson(@RequestBody PersonDto personDto){
        return personService.addPerson(personDto);

    }
    @GetMapping("/all")
    public   List<PersonDto>  getAllPersons(){
        return personService.getAllPersons();

    }

    @GetMapping("/{id}")
    public PersonDto  findPersonById(@PathVariable Integer id){
        return personService.findPersonById(id);

    }

    @PutMapping("/{id}/name/{name}")
    public PersonDto updatePersonName(@PathVariable Integer id, @PathVariable String name){
        return personService.updatePersonName(id,name);
    }
    @GetMapping("/name/{name}")
    public List<PersonDto>  findPersonByName(@PathVariable String name){
        return personService.findPersonByName(name);
    }


    @PutMapping("/{id}/address")
    public PersonDto updatePersonAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto){
        return personService.updatePersonAddress(id,addressDto);
    }

    @DeleteMapping("/{id}")
    public PersonDto  deletePersonById(@PathVariable Integer id){
        return personService.deletePersonById(id);

    }

    @GetMapping("/city/{city}")
    List<PersonDto> findPersonByCity(@PathVariable String city) {
        return personService.findPersonByCity(city);
    }


    @GetMapping("/ages/{age1}/{age2}")
    List<PersonDto> findPersonsByAge(@PathVariable Integer age1, @PathVariable Integer age2) {
        return personService.findPersonsByAge(age1,age2);
    }


    @GetMapping("/population/city")
    List<CityPopulationDto> findPersonsByAge() {
        return personService.getCityPopulation();
    }







}
