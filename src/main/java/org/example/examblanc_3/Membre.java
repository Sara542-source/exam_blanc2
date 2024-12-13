package org.example.examblanc_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Membre {
    private  int identifient ;
    private String nom ;
    private String prenom ;
    private String email ;
    private String phone ;
    private List<Incident> incidents ;
    // constructeurs
    public Membre(String nom , String prenom , String email , String phone ){

        this.identifient = identifient ;
        this.nom =  nom ;
        this.prenom = prenom ;
        this.email = email ;
        this.phone  = phone ;
        this.incidents=new ArrayList<>() ;
    }
    public int getIdentifient(){
        return identifient ;
    }
    public String getNom(){
        return nom ;
    }
    public String getPrenom(){
        return prenom ;
    }
    public String getEmail(){
        return email ;
    }
    public String getPhone(){
        return phone ;
    }
    public List<Incident> getIncidents(){
        return incidents ;
    }

    public void ajouterIncident(Incident incident){
        this.incidents.add(incident) ;
    }
    @Override
    public boolean equals(Object o){
        if(this == o ){
            return true ;
        }
        if(o == null || getClass() != o.getClass()){
            return false ;
        }
        Membre membre = (Membre) o ;
        return identifient ==membre.identifient ;
    }
    @Override
    public int hashCode(){
        return Objects.hash(identifient) ;

    }
    @Override
    public String toString() {
        return "Membre{id='" + identifient + "', nom='" + nom + "', prenom='" + prenom + "', email='" + email + "', phone='" + phone + "'}";
    }

}
