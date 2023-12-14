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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
      
       @FXML
    private DatePicker rsv_date;
       
        @FXML
    private TextField rsv_siege;
        @FXML
    private TextField rsv_montant;
        @FXML
    private TextField rsv_id;
        @FXML
    private TextField rsv_search;
        
        
           @FXML
    private TableView<Reservation> reservation_tableView;

    @FXML
    private TableColumn<Reservation, Integer> rsv_col_id;

    @FXML
    private TableColumn<Reservation, String> rsv_col_bus;

    @FXML
    private TableColumn<Reservation, String> rsv_col_client;

    @FXML
    private TableColumn<Reservation, String> rsv_col_code;

    @FXML
    private TableColumn<Reservation, Integer> rsv_col_montant;

    @FXML
    private TableColumn<Reservation, String> rsv_col_paiement;

    @FXML
    private TableColumn<Reservation, Integer> rsv_col_siege;

    @FXML
    private TableColumn<Reservation, String> rsv_col_trajet;
    @FXML
    private TableColumn<Reservation, Date> rsv_col_date;

    

    

    

   
  

    
    
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
        
        ObservableList listData = FXCollections.observableArrayList();
        
        while(result.next()){
          
            listData.add(result.getString("code_trajet"));
        }
        rsv_trajet.setItems(listData);
        //bus_id_txt.setItems(listData);
        //System.out.println(listData);
    }catch(Exception e){e.printStackTrace();}
    
    }
    
    // Reset Champ
    public void resetRservation() {
        rsv_client.getSelectionModel().getSelectedItem();
        rsv_trajet.getSelectionModel().getSelectedItem();
        rsv_date.getValue();
        rsv_heure.getSelectionModel().getSelectedItem();
        rsv_mode_paie.getSelectionModel().getSelectedItem();
        rsv_siege.setText("");
        rsv_montant.setText("");
      
    }
    
     // Ajout liste sur le tableau
     private ObservableList<Reservation> ajoutReservationList;
     
    public void AjoutReservationList() {

        ajoutReservationList = ListeReservation();
        rsv_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        rsv_col_client.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
        rsv_col_bus.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
        rsv_col_trajet.setCellValueFactory(new PropertyValueFactory<>("nom_trajet"));
        rsv_col_paiement.setCellValueFactory(new PropertyValueFactory<>("mode_paiement"));
        rsv_col_code.setCellValueFactory(new PropertyValueFactory<>("code_paiement"));
        rsv_col_siege.setCellValueFactory(new PropertyValueFactory<>("numero_siege"));
        rsv_col_montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        rsv_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
             
        reservation_tableView.setItems(ajoutReservationList);

    }
    
      // Liste des reservation de la base de donnees
    public ObservableList<Reservation> ListeReservation() {
        ObservableList<Reservation> reservationList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM reservation";
        con = database.connexionDB();

        try {
            prepare = (PreparedStatement) con.prepareStatement(sql);
            result = prepare.executeQuery();
            Reservation reservation;
            while (result.next()) {
                reservation = new Reservation(result.getInt("id"),
                         result.getString("nom_client"),
                         //result.getString("nom_client"),
                         result.getString("nom_trajet"),
                         result.getString("mode_paiement"),
                         result.getString("code_paiement"),
                         result.getInt("numero_siege"),
                         result.getInt("montant"),
                        result.getDate("date_depart"),
                         result.getString("heure_depart"));
                         
                reservationList.add(reservation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservationList;
    }
    
     public void addReservation() {
        String sql = "INSERT INTO reservation(nom_client,nom_trajet,date_depart,numero_siege,mode_paiement,code_paiement,montant) VALUES(?,?,?,?,?,?,?)";
        con = database.connexionDB();
        try {
            Alert alert;
            if (rsv_client.getSelectionModel().getSelectedItem() == null
                    || rsv_trajet.getSelectionModel().getSelectedItem() == null
                    || rsv_date.getValue() == null
                    || rsv_heure.getSelectionModel().getSelectedItem() == null
                    || rsv_mode_paie.getSelectionModel().getSelectedItem() == null
                    || rsv_siege.getText().isEmpty()
                    || rsv_montant.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Remplir les champs Obligatoire SVP");
                alert.showAndWait();

            } else {
                prepare = (PreparedStatement) con.prepareStatement(sql);
                prepare.setString(1, (String)rsv_client.getSelectionModel().getSelectedItem());
                prepare.setString(2, (String)rsv_trajet.getSelectionModel().getSelectedItem());
                prepare.setDate(3, java.sql.Date.valueOf(rsv_date.getValue()));
                prepare.setString(4, (String)rsv_heure.getSelectionModel().getSelectedItem());               
                prepare.setString(5, (String)rsv_mode_paie.getSelectionModel().getSelectedItem());               
                prepare.setString(6, rsv_siege.getText());               
                prepare.setString(7, rsv_montant.getText());               
                prepare.executeUpdate();
                AjoutReservationList();
                resetRservation();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     
     // Selection des reservation sur le tableau
    public void ReservationSelection() {
        Reservation reservation = reservation_tableView.getSelectionModel().getSelectedItem();
        int num = reservation_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        rsv_id.setText(String.valueOf(reservation.getId()));
        rsv_siege.setText(String.valueOf(reservation.getNumero_siege()));
        rsv_montant.setText(String.valueOf(reservation.getMontant()));
       // client_cin_txt.setText(String.valueOf(client.getCin()));
        //client_telephone_txt.setText(String.valueOf(client.getTelephone()));   
       //// client_cin_txt.setText(String.valueOf(bus.getNbre_place()));
            

    }
    
    // recherche Reservation
    public void rechercheReservation() {
        FilteredList<Reservation> filter = new FilteredList<>(ajoutReservationList, e -> true);
        rsv_search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateReservation -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                //String searchKey = newValue.toLowerCase();
                String searchKey = newValue;

                if (predicateReservation.getNom_client().toString().contains(searchKey)) {
                    return true;
                }else if(predicateReservation.getDate_depart().toString().contains(searchKey)){
                return true ;       
                }else if(predicateReservation.getNom_trajet().toString().contains(searchKey)){
                return true ;
                }else return false; 
                
            });

        });
        
        SortedList<Reservation> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(reservation_tableView.comparatorProperty());
        reservation_tableView.setItems(sortList);

    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
}
