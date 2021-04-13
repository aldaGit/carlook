package org.hbrs.se2.project.hellocar.dtos.impl;

import org.hbrs.se2.project.hellocar.dtos.RolleDTO;

public class RolleDTOImpl implements RolleDTO {

    private String bezeichnung;

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public String getBezeichhnung() {
        return this.bezeichnung;
    }

    @Override
    public String toString() {
        return "RolleDTOImpl{" +
                "bezeichnung='" + bezeichnung + '\'' +
                '}';
    }
}
