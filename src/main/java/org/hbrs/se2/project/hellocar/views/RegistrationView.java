package org.hbrs.se2.project.hellocar.views;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.select.Select;
import org.hbrs.se2.project.hellocar.control.ManageCarControl;
import org.hbrs.se2.project.hellocar.dtos.impl.CarDTOImpl;
import org.hbrs.se2.project.hellocar.dtos.UserDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.project.hellocar.util.Globals;

@Route(value = "registration" )
@PageTitle("Registration")
@CssImport("./styles/views/entercar/enter-car-view.css")
public class RegistrationView extends Div {

    // ToDo: Variablennamen umbenennen, weitere Interaktionsfelder hinzufügen
    // c / o by Sascha Alda and Team Gibralda
    private TextField brand = new TextField("Vorname");
    private TextField model = new TextField("Nachname");
    private TextField description = new TextField("Ihre MatrikelNummer");
    private TextField price = new TextField("Password");

    private Button register = new Button("Register");

    private Binder<CarDTOImpl> binder = new Binder(CarDTOImpl.class);

    public RegistrationView( ManageCarControl carService ) {
        addClassName("enter-car-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        // Default Mapping of Cars attributes and the names of this View based on names
        // Source: https://vaadin.com/docs/flow/binding-data/tutorial-flow-components-binder-beans.html
        binder.bindInstanceFields(this);
        clearForm();

        register.addClickListener(e -> {
            // Speicherung der Daten über das zuhörige Control-Object.
            // Daten des Autos werden aus Formular erfasst und als DTO übergeben.
            // Zusätzlich wird das aktuelle UserDTO übergeben.
            // UserDTO userDTO = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
            // carService.createCar( binder.getBean() ,  userDTO );

            // ToDo: Implementierung von: RegistrationControl; UserDTO (für Registrierung);  Binder;

            // Alternative für Binder: this.description.getValue()  --> Einsetzen in das DTO

            Notification.show("Registrierung erfolgreich");
            clearForm();

            // Navigation laut Page Flow
            UI.getCurrent().navigate(Globals.Pages.LOGIN_VIEW);
        });
    }

    private void clearForm() {
        binder.setBean(new CarDTOImpl());
    }

    private Component createTitle() {
        return new H3("Student Registration");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(brand, model, description, price);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        register.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(register);
        return buttonLayout;
    }

}
