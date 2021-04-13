package org.hbrs.se2.project.hellocar.control;

import org.hbrs.se2.project.hellocar.dtos.RolleDTO;
import org.hbrs.se2.project.hellocar.dtos.UserDTO;
import org.hbrs.se2.project.hellocar.entities.Rolle;
import org.hbrs.se2.project.hellocar.util.Globals;

import java.util.Arrays;
import java.util.List;

// @Component
public class AuthorizationControl {

    /**
     * Methode zur Überprüfung, ob ein Benutzer eine gegebene Rolle besitzt
     * 
     */
    public boolean isUserInRole(UserDTO user , String role  ) {
        List<RolleDTO> rolleList = user.getRoles();
        // A bit lazy but hey it works ;-)
        for (  RolleDTO rolle : rolleList ) {
            if ( rolle.getBezeichhnung().equals(role) ) return true;
        }
        return false;
    }

    /**
     * Erweiterte Methode zur Bestimmung, ob ein User mit einer bestimmten Rolle ein
     * Feature (hier: ein Web-Seite bzw. eine View) zu einem bestimmten Kontext (Bsp: ein Tageszeit, mit
     * einem bestimmten Device etc.) angezeigt bekommt
     */
    public boolean isUserisAllowedToAccessThisFeature(UserDTO user , String role , String feature , String[] context  ) {
        List<RolleDTO> rolleList = user.getRoles();
        // Check, ob ein Benutzer eine Rolle besitzt:
        for (  RolleDTO rolle : rolleList ) {
            if ( rolle.getBezeichhnung().equals(role) )
                // Einfache (!) Kontrolle,  ob die Rolle auf ein Feature zugreifen kann
                if (checkRolleWithFeature(rolle, feature)) {
                    // Check, ob context erfüllt ist, bleibt hier noch aus, kann man nachziehen
                    return true;
                }
        }

        return false;
    }

    private boolean checkRolleWithFeature(RolleDTO rolle, String feature) {
        String[] rolles = getRollesForFeature(feature);
        if  ( rolles.length == 0 || rolles == null ) return false;
        return Arrays.asList(rolles).contains(rolle);


    }

    /**
     * Methode zur Bestimmung, welche Rollen ein Feature (hier: View) verwenden dürfen
     * Diese Zuordnung sollte man natürlich in Praxis in einer Datenbank hinterlegen, so dass man
     * die Zuordnungen flexibel anpassen kann.
     * @param feature
     * @return
     */
    private String[] getRollesForFeature(String feature) {
        // Da im Framework nur zwei Views unterstützt werden, werden auch diese nur unterschieden
        if ( feature.equals( Globals.Pages.ENTER_CAR ) ) {
            return new String[]{ Globals.Roles.ADMIN };
        } else if (  feature.equals( Globals.Pages.SHOW_CARS ) ) {
            return (  new String[] { Globals.Roles.USER , Globals.Roles.ADMIN }  );
        }
        return new String[] {};
    }


}
