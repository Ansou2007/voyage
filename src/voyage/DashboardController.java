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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import voyage.ClientController;
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
    
    // Instanciez ClientController
    private ClientController clientController = new ClientController();

    @FXML
    private ClientController clientSectionController;
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

    //---------------Bus
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
    private TextField bus_search_txt;
    @FXML
    private Spinner<?> bus_place_txt;
    @FXML
    private TableView<Bus> bus_tableView;
    //-----------------Fin BUS-------------------//
    //--------------------Client-----------------//
      @FXML
    private TextField client_cin_txt;

    @FXML
    private TableColumn<Client, Integer> client_col_cin;

    @FXML
    private TableColumn<Client, Integer> client_col_id;

    @FXML
    private TableColumn<Client, String> client_col_nom;

    @FXML
    private TableColumn<Client, String> client_col_prenom;

    @FXML
    private TableColumn<Client, Integer> client_col_telephone;

    @FXML
    private TextField client_nom_txt;

    @FXML
    private TextField client_prenom_txt;

    @FXML
    private TextField client_search_txt;
     @FXML
    private TextField client_id_txt;

    @FXML
    private TableView<Client> client_tableView;

    @FXML
    private TextField client_telephone_txt;
    //---------------FIN CLIENT--------------//
    
    //---------------TRAJET--------------//
     @FXML
   // private ComboBox<?> trajet_bus_txt;
    private ComboBox<Trajet> trajet_bus_txt;
      @FXML
    private TextField bus_id_txt;
   
    //---------------FIN TRAJET--------------//

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
            rechercheBus();
            

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
            list_bus_trajet();

        } else if (event.getSource() == Menu_Client) {
            Formulaire_Dashboard.setVisible(false);
            Formulaire_Bus.setVisible(false);
            Formulaire_Trajet.setVisible(false);
            Formulaire_Client.setVisible(true);
            Formulaire_Reservation.setVisible(false);
            ListeClient();
            rechercheClient();
            rechercheClient();

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
    public void addBus() {
        String sql = "INSERT INTO bus(matricule,description,nbre_place,etat) VALUES(?,?,?,?)";
        con = database.connexionDB();
        try {
            Alert alert;
            if (bus_matricule_txt.getText().isEmpty()
                    || bus_etat_txt.getSelectionModel().getSelectedItem() == null
                    || bus_nbre_place_txt.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Remplir les champs Obligatoire SVP");
                alert.showAndWait();

            } else {
                prepare = (PreparedStatement) con.prepareStatement(sql);
                prepare.setString(1, bus_matricule_txt.getText());
                prepare.setString(2, bus_description_txt.getText());
                prepare.setString(3, bus_nbre_place_txt.getText());
                prepare.setString(4, (String) bus_etat_txt.getSelectionModel().getSelectedItem());

                prepare.executeUpdate();

                AjoutBusList();
                resetBus();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void resetBus() {
        bus_matricule_txt.setText("");
        bus_description_txt.setText("");
        bus_nbre_place_txt.setText("");
        bus_etat_txt.getSelectionModel().getSelectedItem();
    }

    // Liste des Etat du Bus (fonctionnel ou en panne)
    private String[] listEtat = {"fonctionnel", "en panne"};

    public void listeBusEtat() {
        List<String> listT = new ArrayList<>();
        for (String data : listEtat) {
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
    public void updateBus() {

        String sql = "UPDATE bus SET description = '" + bus_description_txt.getText() + "',nbre_place = '" + bus_nbre_place_txt.getText() + "',etat = '" + bus_etat_txt.getSelectionModel().getSelectedItem() + "' WHERE matricule='" + bus_matricule_txt.getText() + "'";

        try {
            con = database.connexionDB();
            Alert alert;
            if (bus_matricule_txt.getText().isEmpty()
                    || bus_etat_txt.getSelectionModel().getSelectedItem() == null
                    || bus_nbre_place_txt.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Remplir les champs Obligatoire SVP");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous modifier ces informations");
                //alert.showAndWait();  

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = con.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Modification avec succéss");
                    alert.showAndWait();
                    AjoutBusList();
                    resetBus();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Suprimer BUS    
    public void deleteBus() {

        String sql = "DELETE FROM bus WHERE matricule= '" + bus_matricule_txt.getText() + "'";

        try {
            con = database.connexionDB();
            Alert alert;
            if (bus_matricule_txt.getText().isEmpty() || bus_nbre_place_txt.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Selectionner une ligne");
                alert.showAndWait();

            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Ok");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sure de supprimer");
                // alert.showAndWait();

                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {

                    statement = con.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Ok");
                    alert.setHeaderText(null);
                    alert.setContentText("Suppression avec succéss");
                    alert.showAndWait();

                    AjoutBusList();
                    resetBus();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Selection des bus sur le tableau
    public void BusSelection() {
        Bus bus = bus_tableView.getSelectionModel().getSelectedItem();
        int num = bus_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
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
            while (result.next()) {
                bus = new Bus(result.getString("matricule"),
                         result.getString("description"),
                         result.getInt("nbre_place"),
                         result.getString("etat"));
                busList.add(bus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return busList;
    }

    // recherche Bus
    public void rechercheBus() {
        FilteredList<Bus> filter = new FilteredList<>(ajoutBusList, e -> true);
        bus_search_txt.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateBus -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                //String searchKey = newValue.toLowerCase();
                String searchKey = newValue;

                if (predicateBus.getMatricule().toString().contains(searchKey)) {
                    return true;
                }else if(predicateBus.getEtat().toString().contains(searchKey)){
                return true ;       
                }else if(predicateBus.getDescription().toString().contains(searchKey)){
                return true ;
                }else return false; 
                
            });

        });
        
        SortedList<Bus> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(bus_tableView.comparatorProperty());
        bus_tableView.setItems(sortList);

    }
    //---------------------------BUS-------------------------
    
    //------------------CLIENTS---------------------------//
     
         public void addClient() {
        String sql = "INSERT INTO client(prenom,nom,cin,telephone) VALUES(?,?,?,?)";
        con = database.connexionDB();
        try {
            Alert alert;
            if (client_prenom_txt.getText().isEmpty()
                    || client_nom_txt.getText().isEmpty()
                    || client_cin_txt.getText().isEmpty()
                    || client_telephone_txt.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Remplir les champs Obligatoire SVP");
                alert.showAndWait();

            } else {
                prepare = (PreparedStatement) con.prepareStatement(sql);
                prepare.setString(1, client_prenom_txt.getText());
                prepare.setString(2, client_nom_txt.getText());
                prepare.setString(3, client_cin_txt.getText());
                prepare.setString(4, client_telephone_txt.getText());               
                prepare.executeUpdate();
                AjoutClientList();
                resetClient();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
             // Reset Champ
    public void resetClient() {
        client_id_txt.setText("");
        client_prenom_txt.setText("");
        client_nom_txt.setText("");
        client_cin_txt.setText("");
        client_telephone_txt.setText("");
      
    }


    private ObservableList<Client> ajoutClientList;

    // Ajout liste sur le tableau
    public void AjoutClientList() {

        ajoutClientList = ListeClient();
        client_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        client_col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        client_col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        client_col_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        client_col_telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));        
        client_tableView.setItems(ajoutClientList);

    }
    // Liste des clients de la base de donnees
    public ObservableList<Client> ListeClient() {
        ObservableList<Client> clientList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM client";
        con = database.connexionDB();

        try {
            prepare = (PreparedStatement) con.prepareStatement(sql);
            result = prepare.executeQuery();
            Client client;
            while (result.next()) {
                client = new Client(result.getInt("id"),
                         result.getString("prenom"),
                         result.getString("nom"),
                         result.getString("cin"),
                         result.getString("telephone"));
                         
                clientList.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientList;
    }
      // Selection des clients sur le tableau
    public void ClientSelection() {
        Client client = client_tableView.getSelectionModel().getSelectedItem();
        int num = client_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        client_id_txt.setText(String.valueOf(client.getId()));
        client_prenom_txt.setText(client.getPrenom());
        client_nom_txt.setText(client.getNom());
        client_cin_txt.setText(String.valueOf(client.getCin()));
        client_telephone_txt.setText(String.valueOf(client.getTelephone()));   
       // client_cin_txt.setText(String.valueOf(bus.getNbre_place()));
            
    }
    // recherche Client
    public void rechercheClient() {
        FilteredList<Client> filter = new FilteredList<>(ajoutClientList, e -> true);
        client_search_txt.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateClient -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                //String searchKey = newValue.toLowerCase();
                String searchKey = newValue;

                if (predicateClient.getPrenom().toString().contains(searchKey)) {
                    return true;
                }else if(predicateClient.getNom().toString().contains(searchKey)){
                return true ;       
                }else if(predicateClient.getTelephone().toString().contains(searchKey)){
                return true ;
                }else return false; 
                
            });

        });
        
        SortedList<Client> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(client_tableView.comparatorProperty());
        client_tableView.setItems(sortList);

    }
    // Supprimer Client
     public void deleteClient() {

        String sql = "DELETE FROM client WHERE id= '" + client_id_txt.getText() + "'";

        try {
            con = database.connexionDB();
            Alert alert;
            if (client_prenom_txt.getText().isEmpty() || client_nom_txt.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Selectionner une ligne");
                alert.showAndWait();

            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ok");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sure de supprimer");
                // alert.showAndWait();

                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {

                    statement = con.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ok");
                    alert.setHeaderText(null);
                    alert.setContentText("Suppression avec succéss");
                    alert.showAndWait();

                    AjoutClientList();
                    resetClient();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     // Modifier Client
    public void updateClient() {

        String sql = "UPDATE client SET prenom = '" + client_prenom_txt.getText() + "',nom = '" + client_nom_txt.getText() + "',cin = '" + client_cin_txt.getText() + "',telephone='"+ client_telephone_txt.getText()+"' WHERE id='" + client_id_txt.getText() + "'";

        try {
            con = database.connexionDB();
            Alert alert;
            if (client_prenom_txt.getText().isEmpty()
                    || client_nom_txt.getText().isEmpty()
                    || client_cin_txt.getText().isEmpty()
                    || client_telephone_txt.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Remplir les champs Obligatoire SVP");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous modifier ces informations");
                //alert.showAndWait();  

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = con.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Modification avec succéss");
                    alert.showAndWait();
                    AjoutClientList();
                    resetClient();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     //--------------FIN CLIENTS------------
    
     //--------------TRAJET----------------------//
    
    // Liste des Bus sur  la table Trajet   
    public void list_bus_trajet(){
    
    String sql = "SELECT id,matricule FROM bus ORDER BY matricule ASC ";
    con = database.connexionDB();
    try{
        prepare = (PreparedStatement) con.prepareStatement(sql);
        result = prepare.executeQuery();
        //ObservableList listData = FXCollections.observableArrayList();
        ObservableList listData = FXCollections.observableArrayList();
        
        while(result.next()){
            //listData.add(result.getString("id"));
            listData.add(result.getString("matricule"));
        }
        trajet_bus_txt.setItems(listData);
        //bus_id_txt.setItems(listData);
        //System.out.println(listData);
    }catch(Exception e){e.printStackTrace();}
    
    }
   
    
     //--------------FIN TRAJET------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AjoutBusList();
        AjoutClientList();
        listeBusEtat();
        list_bus_trajet();
     
        
        
        
    }

}
