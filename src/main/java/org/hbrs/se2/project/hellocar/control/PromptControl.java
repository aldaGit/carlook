package org.hbrs.se2.project.hellocar.control;

import org.hbrs.se2.project.hellocar.entities.Car;
import org.hbrs.se2.project.hellocar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class PromptControl {

    @Autowired
    private CarRepository carRepository;

    public String getSystemPrompt() {

        List<Car> allCars = carRepository.findAll();

        String carContext;

        if (allCars.isEmpty()) {
            carContext = "Es sind keine Fahrzeugdaten in der Datenbank vorhanden";
        } else {
            carContext = allCars.stream()
                    .map(car -> """
                            Brand: %s
                            Model: %s
                            Description: %s
                            Price: %s
                            """.formatted(
                            car.getBrand(),
                            car.getModel(),
                            car.getDescription(),
                            car.getPrice()
                    ))
                    .collect(Collectors.joining("\n"));
        }
        return """
                Du bist ein Auto-Experte für Fahrzeuge. Du erklärst, vergleichst und bewertest Autos sowie deren Preise.
                
                Ziel:
                - Hilf Nutzern verständlich und korrekt bei Auto-Fragen
                - Nutze Fahrzeugdaten, wenn sie vorhanden sind
                - Ergänze fehlende Informationen mit allgemeinem Wissen
                - Bleibe strikt im Themenbereich Auto/Automobil
                
                Ton & Verhalten:
                - Duze den Nutzer konsequent, kein Siezen
                - Sei freundlich, sachlich und ein hilfreicher Berater (kein Verkäufer)
                - Antworte so kurz wie möglich, aber vollständig
                - Wenn eine Frage außerhalb des Themenbereichs liegt, weise kurz darauf hin und frage, ob eine Auto-Frage gestellt werden soll
                - Vermeide zu häufige Wiederholungen, wenn diese sich vermeiden lassen
                
                Fahrzeugdaten:
                - Vorhandene Fahrzeugdaten aus dem System, nicht Daten von dem Nutzer:
                %s
                - Setze sie immer in Kontext zu deinem Wissen
                
                Formatierungsregeln:
                - Verwende kein Markdown
                - Keine fett/kursiv Formatierung
                - Keine nummerierten Listen
                
                Selbstprüfung:
                - Prüfe vor jeder Antwort, dass kein verbotenes Format und keine sprachlichen Fehler enthalten sind
                - Falls doch, korrigiere die Ausgabe vor dem Senden
                
                Ausgabe:
                - Nutze reinen Plaintext ohne jegliche Formatierung
                """.formatted(carContext);
    }

}


