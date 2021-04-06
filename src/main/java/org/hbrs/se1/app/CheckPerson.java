package org.hbrs.se1.app;

public class CheckPerson {

    /**
     * Methode zur Überprüfung der Solvenz einer Person, basierend
     * auf dem Alter einer Person. Laut Spezifikation sind negative
     * Altersangaben sowie ein Alter (age) = 0 keine gültigen Werte
     * Die Rückgabewert geben die Solvenz-Stufe an.
     *
     */
    public int checkSolvency( Person person ) {
        int age = person.getAge();
        if (age <= 0) throw new ArithmeticException("Illegal Value!");

        if ( age > 0 && age < 18 ) {
            return 0;
        }
        else if ( age >= 18 && age < 65) {
            return 1;
        }
        else {
            return 2;
        }
    }
}
