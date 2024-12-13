package org.example.examblanc_3;

import java.util.List;

public interface MembreDao {
    public boolean insere(Membre m) ;
    public List<Incident> chargerListIncidents(int idMember) ;
}
