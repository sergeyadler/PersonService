package cohort_65.java.personservice.person.dao;

import cohort_65.java.personservice.person.dto.CityPopulationDto;
import cohort_65.java.personservice.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.stream.Stream;
public interface PersonRepository extends JpaRepository<Person,Integer> {
    Stream<Person> findAllByAddressCity(String city);
    Stream<Person> findAllByBirthDateBetween(LocalDate startAge, LocalDate endAge);



    /***
     * JPQL c constructor expression
     FROM Person p — работаем с Entity Person, алиас p.

     p.address.city — берём поле из @Embedded Address.

     count(p) — агрегируем по количеству записей в каждой группе.

     GROUP BY p.address.city — группировка по городу.

     ORDER BY count(p) DESC — сортируем по убыванию количества.

     Фишка: SELECT new ... — это constructor expression. JPA соберёт не Object[], а сразу DTO, вызвав его конструктор.

     Важно: для constructor expression требуется полное имя класса (FQN) и публичный конструктор с подходящими типами.
     count(...) возвращает Long, значит конструктор должен принимать String, Long (или long).
     * */
    @Query(value = "SELECT new cohort_65.java.personservice.person.dto.CityPopulationDto(p.address.city, count(p)) " +
            "FROM Person  p  " +
            "GROUP BY p.address.city " +
            "ORDER BY  count(p) desc")
    Stream<CityPopulationDto> getCityPopulationByCity();


}
