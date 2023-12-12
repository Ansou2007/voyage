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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author macair
 */
public class ClientController implements Initializable {
    
    // Client
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
    
    
     // base de donnees
    private Connection con;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    //---------------------------CLIENT-------------------------
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


   

    // Ajout liste sur le tableau
     private ObservableList<Client> ajoutClientList;
    public void AjoutClientList() {

        ajoutClientList = ListeClient();
        client_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        client_col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        client_col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        client_col_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        client_col_telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));        
        client_tableView.setItems(ajoutClientList);

    }

    // Modifier Client
    public void updateClient() {

        String sql = "UPDATE bus SET prenom = '" + client_prenom_txt.getText() + "',nom = '" + client_nom_txt.getText() + "',cin = '" + client_cin_txt.getText() + "',telephone='"+ client_telephone_txt.getText()+"' WHERE id='" + client_id_txt.getText() + "'";

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

    // Suprimer Client    
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

    // Selection des bus sur le tableau
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
    //---------------------------FIN CLIENT-------------------------
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      AjoutClientList();
        
    }
    
    
    
}
