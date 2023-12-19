/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package voyage;

/**
 *
 * @author macair
 */
public class Trajet {
    private Integer id;
   // private Integer bus_id;
    private String code_trajet;
    private String bus_name;
    private String ville_depart;
    private String ville_arrivee;
    private String matricule;
    
    
    public Trajet(Integer id, String code_trajet,String matricule, String ville_depart, String ville_arrivee){
   
        this.id = id;
       // this.bus_id = bus_id;
        this.matricule = matricule;
        this.code_trajet = code_trajet;
       // this.bus_name = bus_name;
        this.ville_depart = ville_depart;
        this.ville_arrivee = ville_arrivee;
       
    }
    
    public Integer getId(){return id;}
    //public Integer getBus_id(){return bus_id;}
    public String getCode_trajet(){return code_trajet;}
    public String getMatricule(){return matricule;}
    public String getVille_depart(){return ville_depart;}
    public String getVille_arrivee(){return ville_arrivee;}
    
}
