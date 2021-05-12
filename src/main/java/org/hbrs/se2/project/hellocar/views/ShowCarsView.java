package org.hbrs.se2.project.hellocar.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.hbrs.se2.project.hellocar.control.ManageCarControl;
import org.hbrs.se2.project.hellocar.dtos.CarDTO;
import org.hbrs.se2.project.hellocar.dtos.UserDTO;
import org.hbrs.se2.project.hellocar.repository.CarRepository;
import org.hbrs.se2.project.hellocar.util.Globals;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Darstellung einer Tabelle (bei Vaadin: ein Grid) zur Anzeige von Autos.
 * Hier wurden nur grundlegende Elemente verarbeitet. Weitere Optionen (z.B.
 * weitere Filter-Möglichkeiten) kann man hier entnehmen:
 * https://vaadin.com/components/vaadin-grid/java-examples/header-and-footer
 *
 */
@Route(value = Globals.Pages.SHOW_CARS, layout = AppView.class)
@PageTitle("Show Cars")
@CssImport("./styles/views/showcars/show-cars-view.css")
public class ShowCarsView extends Div  {

    private List<CarDTO> personList;

    public ShowCarsView( ManageCarControl carControl ) {
            addClassName("show-cars-view");

            // Auslesen alle abgespeicherten Autos aus der DB (über das Control)
            personList = carControl.readAllCars();

            // Titel überhalb der Tabelle
            add(this.createTitle());

            // Hinzufügen der Tabelle (bei Vaadin: ein Grid)
            add(this.createGridTable());
    }

    private Component createGridTable() {
        Grid<CarDTO> grid = new Grid<>();

        // Befüllen der Tabelle mit den zuvor ausgelesenen Autos
        ListDataProvider<CarDTO> dataProvider = new ListDataProvider<>(
                personList);
        grid.setDataProvider(dataProvider);

        Grid.Column<CarDTO> brandColumn = grid
                .addColumn(CarDTO::getBrand).setHeader("Brand");
        Grid.Column<CarDTO> modelColumn = grid.addColumn(CarDTO::getModel)
                .setHeader("Model");
        Grid.Column<CarDTO> descriptionColumn = grid
                .addColumn(CarDTO::getDescription)
                .setHeader("Description");
        Grid.Column<CarDTO> priceColumn = grid
                .addColumn(CarDTO::getPrice)
                .setHeader("Price");

        HeaderRow filterRow = grid.appendHeaderRow();

        // First filter
        TextField modelField = new TextField();
        modelField.addValueChangeListener(event -> dataProvider.addFilter(
                car -> StringUtils.containsIgnoreCase(car.getModel(),
                        modelField.getValue())));

        modelField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(modelColumn).setComponent(modelField);
        modelField.setSizeFull();
        modelField.setPlaceholder("Filter");

        // Second filter
        TextField brandField = new TextField();
        brandField.addValueChangeListener(event -> dataProvider
                .addFilter(car -> StringUtils.containsIgnoreCase(
                        String.valueOf(car.getBrand()), brandField.getValue())));

        brandField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(brandColumn).setComponent(brandField);
        brandField.setSizeFull();
        brandField.setPlaceholder("Filter");

        return grid;
    }

    private Component createTitle() {
        return new H3("Search for Cars");
    }


};
