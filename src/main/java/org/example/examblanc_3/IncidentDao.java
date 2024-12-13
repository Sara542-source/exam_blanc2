package org.example.examblanc_3;

import java.util.Set;

public interface IncidentDao {
    public boolean inserer(Incident i) ;
    public void inser(Set is) ;

    boolean inserer(Set<Incident> incidents);
}
