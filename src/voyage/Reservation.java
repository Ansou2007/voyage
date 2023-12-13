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
    private String nom_trajet;
    private Date date_depart;
    private String heure_depart;
    private Integer numero_siege;
    private String mode_paiement;
    private String code_paiement;
    private Integer montant;
    
    public Reservation(Integer id,String nom_client,String nom_trajet,Date date_depart,String heure_depart,Integer numero_siege,String mode_paiement,String code_paiement,Integer montant ){
    
        this.id = id;
        this.nom_client = nom_client;
        this.nom_trajet = nom_trajet;
        this.date_depart = date_depart;
        this.numero_siege = numero_siege;
        this.mode_paiement = mode_paiement;
        this.code_paiement = code_paiement;
        this.montant = montant;
    
    };
    
    public Integer getId(){return id;}
    public String getNom_client(){return nom_client;}
    public String getNom_trajet(){return nom_trajet;}
    public Date getDate_depart(){return date_depart;}
    public String getHeure_depart(){return heure_depart;}
    public Integer getNumero_siege(){return numero_siege;}
    public String getMode_paiement(){return mode_paiement;}
    public String getCode_paiement(){return code_paiement;}
    public Integer getMontant(){return montant;}
}
