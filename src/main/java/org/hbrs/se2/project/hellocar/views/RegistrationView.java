package org.hbrs.se2.project.hellocar.views;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import org.hbrs.se2.project.hellocar.control.ManageCarControl;
import org.hbrs.se2.project.hellocar.dtos.impl.CarDTOImpl;
import org.hbrs.se2.project.hellocar.dtos.UserDTO;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
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
@PageTitle("User Registration")
@CssImport("./styles/views/entercar/enter-car-view.css")
public class RegistrationView extends Div {  // 3. Form (Spezialisierung / Vererbung)

    // ToDo: Validierung; weitere Felder / Varibalen; Variablen umbenennen.
    // c / 0 Sascha Alda in Kooperation mit dem Team NoCode
    private TextField brand = new TextField("UserName");
    private TextField model = new TextField("My E-Mail");
    private TextField description = new TextField("Passwort");
    private TextField price = new TextField("Passwort (Wdh)");

    private Button register = new Button("Register");

    private Binder<CarDTOImpl> binder = new Binder(CarDTOImpl.class);

    public RegistrationView( ManageCarControl carService) {
        //ToDo: tba

        addClassName("enter-car-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        // Default Mapping of Cars attributes and the names of this View based on names
        // Source: https://vaadin.com/docs/flow/binding-data/tutorial-flow-components-binder-beans.html
        binder.bindInstanceFields(this); // Nr. 1 API-Methode
        clearForm();

        register.addClickListener(e -> {
            // Speicherung der Daten über das zuhörige Control-Object.
            // Daten des Autos werden aus Formular erfasst und als DTO übergeben.
            // Zusätzlich wird das aktuelle UserDTO übergeben.
            // UserDTO userDTO = (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
            // carService.createCar(binder.getBean() ,  userDTO );

            Notification.show("User wurde registriert.");
            clearForm();

            // Navigation
            UI.getCurrent().navigate( Globals.Pages.LOGIN_VIEW );
        });
    }

    private void clearForm() {
        binder.setBean(new CarDTOImpl());
    }

    private Component createTitle() {
        return new H3("User Registration");
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
