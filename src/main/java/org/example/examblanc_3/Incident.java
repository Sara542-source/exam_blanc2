package org.example.examblanc_3;

import java.time.LocalDate;
import java.util.Date;

public class Incident {

    private String reference ;
    private Date time  ;
    private String status ;
    private LocalDate date;
    private int membreId ;

    public Incident( String reference , Date time , String status , int membreId  ) {
        this.reference = reference ;
        this.time = time ;
        this.status = status ;
        this.membreId=membreId ;
    }

    public Incident() {

    }

    public String getReference(){
        return reference ;
  }
  public java.sql.Date getTime(){
        return (java.sql.Date) time;
  }
  public String getStatus(){
        return status ;
  }

  public void SetRference( String reference){
        this.reference=reference ;
  }
  public void SetTime(LocalDate date){
        this.date=date ;
    }
    public void SetStatus( String status){
        this.status=status ;
    }
    public void setMembreId(int membreId) {
        this.membreId = membreId;
    }

    public int getMemberId() {
        return membreId ;
    }
}
