package org.hbrs.se2.project.hellocar.repository;

import org.hbrs.se2.project.hellocar.dtos.CarDTO;
import org.hbrs.se2.project.hellocar.entities.Car;
import org.hbrs.se2.project.hellocar.entities.Rolle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
/**
 * JPA-Repository für die Verwaltung von Autos (cars). Die Bezeichnung einer Methode
 * bestimmt dabei die Selektionsbedingung (den WHERE-Teil). Der Rückgabewert einer
 * Methode bestimmt den Projectionsbedingung (den SELECT-Teil).
 * Mehr Information über die Entwicklung von Queries in JPA:
 * https://www.baeldung.com/spring-data-jpa-projections
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
 *
 */
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query("  SELECT c.brand, c.model, c.price, u.firstName, u.lastName" +
            " FROM Car c, User u " +
            " WHERE  c.userid = u.id ")
    List<Object[]> findAllCarsAndTheirUsers();

    @Query("  SELECT c.brand, c.model, c.description, c.date, c.phone, c.price " +
            " FROM Car c ")
    List<CarDTO> findAllCarsWithMostImportantValues();

    List<CarDTO> findCarsByDateIsNotNull();

 }