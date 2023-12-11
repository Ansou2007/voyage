/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package voyage;

/**
 *
 * @author macair
 */
public class Client {
    private Integer id;
    private String prenom;
    private String nom;
    private String cin;
    private String telephone;
    
    public  Client(Integer id,String prenom, String nom, String cin, String telephone)
    {
       this.id = id;
       this.prenom = prenom;
       this.nom = nom;
       this.cin = cin;
       this.telephone = telephone;
    }
    
    public Integer getId()
    {
        return id;
    }
    
    public String getPrenom()
    {
        return prenom;
    }
    
    public String getNom()
    {
        return nom;
    }
    
    public String getCin()
    {
        return cin;
    }
    public String getTelephone()
    {
        return telephone;
    }
}
