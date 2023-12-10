/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package voyage;

/**
 *
 * @author macair
 */
public class Bus {
    
    private String matricule;
    private String description;
    private Integer nbre_place;
    private String etat;
    
    public  Bus(String matricule, String description, Integer nbre_place, String etat)
    {
       this.matricule = matricule;
       this.description = description;
       this.nbre_place = nbre_place;
       this.etat = etat;
    }
    
    public String getMatricule()
    {
        return matricule;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public Integer getNbre_place()
    {
        return nbre_place;
    }
    
    public String getEtat()
    {
        return etat;
    }
    
}
