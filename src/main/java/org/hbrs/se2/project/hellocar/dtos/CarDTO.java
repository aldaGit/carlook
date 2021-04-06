package org.hbrs.se2.project.hellocar.dtos;

import java.time.LocalDate;

public interface CarDTO {

    public String getBrand();

    public String getModel();

    public String getDescription();

    public LocalDate getDate();

    public String getPhone();

    public String getPrice();

}