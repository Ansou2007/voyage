/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package voyage;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.Statement;

/**
 * FXML Controller class
 *
 * @author macair
 */
public class DashboardController implements Initializable {

    private double x = 0;
    private double y = 0;

    // base de donnees
    private Connection con;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    @FXML
    private Button Btn_deconnexion;
    @FXML
    private Button Btn_close;
    @FXML
    private AnchorPane Main_formulaire;
    @FXML
    private AnchorPane Formulaire_Dashboard;

    @FXML
    private AnchorPane Formulaire_Bus;

    @FXML
    private AnchorPane Formulaire_Client;

    @FXML
    private AnchorPane Formulaire_Reservation;

    @FXML
    private AnchorPane Formulaire_Trajet;

    @FXML
    private Button Menu_Bus;

    @FXML
    private Button Menu_Client;

    @FXML
    private Button Menu_Home;

    @FXML
    private Button Menu_Reservation;

    @FXML
    private Button Menu_Trajet;
    
    // Bus
        @FXML
    private TableColumn<Bus, String> bus_col_description;

    @FXML
    private TableColumn<Bus, String> bus_col_etat;

    @FXML
    private TableColumn<Bus, String> bus_col_matricule;

    @FXML
    private TableColumn<Bus, String> bus_col_place;
    
    @FXML
    private TextArea bus_description_txt;

    @FXML
    private ComboBox<?> bus_etat_txt;

    @FXML
    private TextField bus_matricule_txt;
    @FXML
    private TextField bus_nbre_place_txt;

    @FXML
    private Spinner<?> bus_place_txt;
      @FXML
    private TableView<Bus> bus_tableView;
    // Fin BUS

    // Changement Formulaire
    public void menu(ActionEvent event) {
        if (event.getSource() == Menu_Home) {
            Formulaire_Dashboard.setVisible(true);
            Formulaire_Bus.setVisible(false);
            Formulaire_Trajet.setVisible(false);
            Formulaire_Client.setVisible(false);
            Formulaire_Reservation.setVisible(false);

            Menu_Home.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");
            Menu_Bus.setStyle("-fx-background-color:transparent");
            Menu_Trajet.setStyle("-fx-background-color:transparent");
            Menu_Client.setStyle("-fx-background-color:transparent");
            Menu_Reservation.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == Menu_Bus) {
            Formulaire_Dashboard.setVisible(false);
            Formulaire_Bus.setVisible(true);
            Formulaire_Trajet.setVisible(false);
            Formulaire_Client.setVisible(false);
            Formulaire_Reservation.setVisible(false);
            listeBusEtat();
            ListeBus();

            Menu_Bus.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");
            Menu_Home.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");
            Menu_Home.setStyle("-fx-background-color:transparent");
            Menu_Trajet.setStyle("-fx-background-color:transparent");
            Menu_Client.setStyle("-fx-background-color:transparent");
            Menu_Reservation.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == Menu_Trajet) {
            Formulaire_Dashboard.setVisible(false);
            Formulaire_Bus.setVisible(false);
            Formulaire_Trajet.setVisible(true);
            Formulaire_Client.setVisible(false);
            Formulaire_Reservation.setVisible(false);

            Menu_Trajet.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");
            Menu_Home.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");
            Menu_Home.setStyle("-fx-background-color:transparent");
            Menu_Bus.setStyle("-fx-background-color:transparent");
            Menu_Client.setStyle("-fx-background-color:transparent");
            Menu_Reservation.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == Menu_Client) {
            Formulaire_Dashboard.setVisible(false);
            Formulaire_Bus.setVisible(false);
            Formulaire_Trajet.setVisible(false);
            Formulaire_Client.setVisible(true);
            Formulaire_Reservation.setVisible(false);

            Menu_Client.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");
            Menu_Home.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");
            Menu_Home.setStyle("-fx-background-color:transparent");
            Menu_Bus.setStyle("-fx-background-color:transparent");
            Menu_Trajet.setStyle("-fx-background-color:transparent");
            Menu_Reservation.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == Menu_Reservation) {
            Formulaire_Dashboard.setVisible(false);
            Formulaire_Bus.setVisible(false);
            Formulaire_Trajet.setVisible(false);
            Formulaire_Client.setVisible(false);
            Formulaire_Reservation.setVisible(true);

            Menu_Reservation.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");
            Menu_Home.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");
            Menu_Home.setStyle("-fx-background-color:transparent");
            Menu_Bus.setStyle("-fx-background-color:transparent");
            Menu_Trajet.setStyle("-fx-background-color:transparent");
            Menu_Client.setStyle("-fx-background-color:transparent");
        }
    }

    // Deconnexion
    public void deconnexion() {

        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous vous deconnectez");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                Btn_deconnexion.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                // rendre le formulaire transparent
                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fermer 
    public void Btn_close() {
        System.exit(0);
    }

    // Reduire 
    public void minimize() {
        Stage stage = (Stage) Main_formulaire.getScene().getWindow();
        stage.setIconified(true);
    }

    //---------------------------BUS-------------------------
    
    
    public void addBus()
    {
       String sql = "INSERT INTO bus(matricule,description,nbre_place,etat) VALUES(?,?,?,?)";
       con = database.connexionDB();
       try{
           Alert alert;
           if(bus_matricule_txt.getText().isEmpty()
                   || bus_etat_txt.getSelectionModel().getSelectedItem() == null 
                   || bus_nbre_place_txt.getText().isEmpty()){
           alert = new Alert(AlertType.ERROR);
           alert.setTitle("Erreur");
           alert.setHeaderText(null);
           alert.setContentText("Remplir les champs Obligatoire SVP");
           alert.showAndWait();
           
           }else{
           prepare = (PreparedStatement) con.prepareStatement(sql);
           prepare.setString(1,bus_matricule_txt.getText());
           prepare.setString(2,bus_description_txt.getText());
           prepare.setString(3,bus_nbre_place_txt.getText());
           prepare.setString(4,(String)bus_etat_txt.getSelectionModel().getSelectedItem());
           
           prepare.executeUpdate();
           
           AjoutBusList();
           resetBus();
           }
           
           
       }catch(Exception e){e.printStackTrace();}

    }
    
    public void resetBus()
    {
        bus_matricule_txt.setText("");
        bus_description_txt.setText("");
        bus_nbre_place_txt.setText("");
        bus_etat_txt.getSelectionModel().getSelectedItem();
    }
    
    // Liste des Etat du Bus (fonctionnel ou en panne)
    private String[] listEtat = {"fonctionnel","en panne"};
    
    public void listeBusEtat(){
        List<String> listT = new ArrayList<>();
        for(String data: listEtat){
            listT.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listT);
        bus_etat_txt.setItems(listData);
    }
    private ObservableList<Bus> ajoutBusList;
    
    // Ajout liste sur le tableau
    public void AjoutBusList() {
        
        ajoutBusList = ListeBus();
        bus_col_matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        bus_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        bus_col_place.setCellValueFactory(new PropertyValueFactory<>("nbre_place"));
        bus_col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));        
        bus_tableView.setItems(ajoutBusList);        
        
    }
    // Modifier Bus
    public void updateBus(){
    
        String sql = "UPDATE bus SET description = '"+bus_description_txt.getText() +"',nbre_place = '"+bus_nbre_place_txt.getText()+"',etat = '"+bus_etat_txt.getSelectionModel().getSelectedItem()+"' WHERE matricule='"+bus_matricule_txt.getText()+"'";
             
       try{
          con = database.connexionDB();
            Alert alert;
           if(bus_matricule_txt.getText().isEmpty()
                   || bus_etat_txt.getSelectionModel().getSelectedItem() == null 
                   || bus_nbre_place_txt.getText().isEmpty()){
           alert = new Alert(AlertType.ERROR);
           alert.setTitle("Erreur");
           alert.setHeaderText(null);
           alert.setContentText("Remplir les champs Obligatoire SVP");
           alert.showAndWait();
           
           }else{
               alert = new Alert(AlertType.CONFIRMATION);
           alert.setTitle("Ok");
           alert.setHeaderText(null);
           alert.setContentText("Etes-vous sure de modifier");
           alert.showAndWait();
           
           Optional<ButtonType> option = alert.showAndWait();
           if(option.get().equals(ButtonType.OK)){
           statement = con.createStatement();
           statement.executeUpdate(sql);
            alert = new Alert(AlertType.CONFIRMATION);
           alert.setTitle("Ok");
           alert.setHeaderText(null);
           alert.setContentText("Modification avec succéss");
           alert.showAndWait();
           
           AjoutBusList();
           resetBus();
           }
           
           }
          
       }catch(Exception e){e.printStackTrace();}
    }
    
    // Suprimer BUS    
    public void deleteBus(){
    
       String sql = "DELETE FROM bus WHERE matricule= '"+bus_matricule_txt.getText() +"'";
             
       try{
          con = database.connexionDB();
            Alert alert;
           if(bus_matricule_txt.getText().isEmpty() || bus_nbre_place_txt.getText().isEmpty()){
           alert = new Alert(AlertType.ERROR);
           alert.setTitle("Erreur");
           alert.setHeaderText(null);
           alert.setContentText("Selectionner une ligne");
           alert.showAndWait();
           
           }else{
               alert = new Alert(AlertType.CONFIRMATION);
           alert.setTitle("Ok");
           alert.setHeaderText(null);
           alert.setContentText("Etes-vous sure de supprimer");
           alert.showAndWait();
           
           Optional<ButtonType> option = alert.showAndWait();
           if(option.get().equals(ButtonType.OK)){
           statement = con.createStatement();
           statement.executeUpdate(sql);
            alert = new Alert(AlertType.CONFIRMATION);
           alert.setTitle("Ok");
           alert.setHeaderText(null);
           alert.setContentText("Suppression avec succéss");
           alert.showAndWait();
           
           AjoutBusList();
           resetBus();
           }
           
           }
          
       }catch(Exception e){e.printStackTrace();}
    }
    
    // Selection des bus sur le tableau
    public void BusSelection(){
    Bus bus = bus_tableView.getSelectionModel().getSelectedItem();
    int num = bus_tableView.getSelectionModel().getSelectedIndex();
    
    if((num - 1) < -1){return;}
    bus_matricule_txt.setText(bus.getMatricule());
    bus_description_txt.setText(bus.getDescription());
    bus_nbre_place_txt.setText(String.valueOf(bus.getNbre_place()));
   // bus_etat_txt.setText(bus.getEtat());
    
    
    
    }
    // Liste des Bus de la base de donnees
    public ObservableList<Bus> ListeBus() {
        ObservableList<Bus> busList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM bus";
        con = database.connexionDB();

        try {
            prepare = (PreparedStatement) con.prepareStatement(sql);
            result = prepare.executeQuery();
            Bus bus;
            while(result.next()){
            bus = new Bus(result.getString("matricule")
                    ,result.getString("description")
                    ,result.getInt("nbre_place")
                    ,result.getString("etat"));
            busList.add(bus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return busList;
    }
    //---------------------------BUS-------------------------

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AjoutBusList();
        listeBusEtat();
    }

}
