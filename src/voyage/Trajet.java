/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package voyage;

import java.util.Date;

/**
 *
 * @author macair
 */
public class Trajet {
       private Integer id;
    private Integer bus_id;
    private String bus_name;
    private String ville_depart;
    private String ville_arrivee;
    private Date date_depart;     
    private String heure_depart;
    
    public Trajet(Integer id, Integer bus_id,String bus_name,String ville_depart,String ville_arrivee,Date date_depart,String heure_depart){
   
        this.id = id;
        this.bus_id = bus_id;
        this.bus_name = bus_name;
        this.ville_depart = ville_depart;
        this.ville_arrivee = ville_arrivee;
        this.date_depart = date_depart;
        this.heure_depart = heure_depart;
    }
    
    public Integer getId(){return id;}
    public Integer getBus_id(){return bus_id;}
    public String getBus_name(){return bus_name;}
    public String getVille_depart(){return ville_depart;}
    public String getVille_arrivee(){return ville_arrivee;}
    public Date getDate_depart(){return date_depart;}
    public String getHeure_depart(){return heure_depart;}
}
