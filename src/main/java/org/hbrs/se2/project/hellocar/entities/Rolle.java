package org.hbrs.se2.project.hellocar.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table( name ="rolle" , schema = "carlook" )
public class Rolle {
    private String bezeichhnung;
    private List<User> users;

    @Id
    @Column(name = "bezeichhnung")
    public String getBezeichhnung() {
        return bezeichhnung;
    }

    public void setBezeichhnung(String bezeichhnung) {
        this.bezeichhnung = bezeichhnung;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rolle rolle = (Rolle) o;
        return Objects.equals(bezeichhnung, rolle.bezeichhnung);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bezeichhnung);
    }

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER )
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
