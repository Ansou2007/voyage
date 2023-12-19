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
public class Reservation {
    private Integer id;
    private String nom_client;
    private String matricule;
    private String code_trajet;
    private String mode_paiement;
    private String code_paiement;
    private Integer numero_siege;
    private Integer montant;
    private Date date_depart;
    
   
    
    private String heure_depart;
    
   
    
  
    
    public Reservation(Integer id,String nom_client, String matricule, String code_trajet, String mode_paiement, String code_paiement, Integer numero_siege, Integer montant, Date date_depart ){
    
        this.id = id;
        this.nom_client = nom_client;
        this.matricule = matricule;
        this.code_trajet = code_trajet;
        this.mode_paiement = mode_paiement;
        this.code_paiement = code_paiement;
        this.numero_siege = numero_siege;
        this.montant = montant;
        this.date_depart = date_depart;
      
    
    };
    
    public Integer getId(){return id;}
    public String getNom_client(){return nom_client;}
    public String getMatricule(){return matricule;}
    public String getCode_trajet(){return code_trajet;}
    public String getMode_paiement(){return mode_paiement;}
    public String getCode_paiement(){return code_paiement;}
    public Integer getNumero_siege(){return numero_siege;}
    public Integer getMontant(){return montant;}
    
    public Date getDate_depart(){return date_depart;}
    //public String getHeure_depart(){return heure_depart;}
    //
    //
    //
   // public Integer getMontant(){return montant;}
}
