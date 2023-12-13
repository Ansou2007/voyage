/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package voyage;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 *
 * @author macair
 */
public class ReservationController implements Initializable {

    @FXML
    private ComboBox<?> rsv_mode_paie;
      @FXML
    private ComboBox<?> rsv_client;
      @FXML
    private ComboBox<?> rsv_trajet;
    
      @FXML
    private ComboBox<?> rsv_heure;
    
    // base de donnees
    private Connection con;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    
          // Liste des mode de paiement
    private String[] listMode_paiement = {"espece", "wallet"};
    public void listMode_paiement() {
        List<String> listT = new ArrayList<>();
        for (String data : listMode_paiement) {
            listT.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listT);
        rsv_mode_paie.setItems(listData);
    }
          // Liste des heure de depart
    private String[] listHeure_depart = {"7h30", "8h30", "9h30","10h30","11h30"};
    public void listHeure_depart() {
        List<String> listT = new ArrayList<>();
        for (String data : listHeure_depart) {
            listT.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listT);
        rsv_heure.setItems(listData);
    }
    
    
        // Liste des CLIENT sur  la reservation 
    public void list_client_reservation(){
    
    String sql = "SELECT prenom,nom FROM client ORDER BY prenom ASC ";
    con = database.connexionDB();
    try{
        prepare = (PreparedStatement) con.prepareStatement(sql);
        result = prepare.executeQuery();
        //ObservableList listData = FXCollections.observableArrayList();
        ObservableList listData = FXCollections.observableArrayList();
        
        while(result.next()){
            //listData.add(result.getString("id"));
            listData.add(result.getString("prenom"));
        }
        rsv_client.setItems(listData);
        //bus_id_txt.setItems(listData);
        //System.out.println(listData);
    }catch(Exception e){e.printStackTrace();}
    
    }
        // Liste des trajets sur  la reservation 
    public void list_trajet_reservation(){
    
    String sql = "SELECT code_trajet FROM trajet ORDER BY code_trajet ASC ";
    con = database.connexionDB();
    try{
        prepare = (PreparedStatement) con.prepareStatement(sql);
        result = prepare.executeQuery();
        //ObservableList listData = FXCollections.observableArrayList();
        ObservableList listData = FXCollections.observableArrayList();
        
        while(result.next()){
            //listData.add(result.getString("id"));
            listData.add(result.getString("code_trajet"));
        }
        rsv_trajet.setItems(listData);
        //bus_id_txt.setItems(listData);
        //System.out.println(listData);
    }catch(Exception e){e.printStackTrace();}
    
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
}
