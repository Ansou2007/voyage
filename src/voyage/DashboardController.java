/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package voyage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author macair
 */
public class DashboardController implements Initializable {

    private double x = 0;
    private double y = 0;
    
     @FXML
    private Button Btn_deconnexion;
    @FXML
    private Button Btn_close;
    @FXML
    private AnchorPane Main_formulaire;
        @FXML
    private AnchorPane Formulaire_Bus;

    @FXML
    private AnchorPane Formulaire_Client;

    @FXML
    private AnchorPane Formulaire_Dashboard;

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

    
    
    
    
    
    

    // Changement Formulaire
    public void menu(ActionEvent event)
    {
        if(event.getSource() == Menu_Home)
        {
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
            
        }else if(event.getSource() == Menu_Bus )
        {
            Formulaire_Dashboard.setVisible(false);
            Formulaire_Bus.setVisible(true);
            Formulaire_Trajet.setVisible(false);
            Formulaire_Client.setVisible(false);
            Formulaire_Reservation.setVisible(false); 
            
            Menu_Bus.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");            Menu_Home.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");
            Menu_Home.setStyle("-fx-background-color:transparent");
            Menu_Trajet.setStyle("-fx-background-color:transparent");
            Menu_Client.setStyle("-fx-background-color:transparent");
            Menu_Reservation.setStyle("-fx-background-color:transparent");
        
        }else if(event.getSource() == Menu_Trajet )
        {
            Formulaire_Dashboard.setVisible(false);
            Formulaire_Bus.setVisible(false);
            Formulaire_Trajet.setVisible(true);
            Formulaire_Client.setVisible(false);
            Formulaire_Reservation.setVisible(false); 
            
            Menu_Trajet.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");            Menu_Home.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");
            Menu_Home.setStyle("-fx-background-color:transparent");
            Menu_Bus.setStyle("-fx-background-color:transparent");
            Menu_Client.setStyle("-fx-background-color:transparent");
            Menu_Reservation.setStyle("-fx-background-color:transparent");
        
        }else if(event.getSource() == Menu_Client )
        {
            Formulaire_Dashboard.setVisible(false);
            Formulaire_Bus.setVisible(false);
            Formulaire_Trajet.setVisible(false);
            Formulaire_Client.setVisible(true);
            Formulaire_Reservation.setVisible(false); 
            
            Menu_Client.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");            Menu_Home.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");
            Menu_Home.setStyle("-fx-background-color:transparent");
            Menu_Bus.setStyle("-fx-background-color:transparent");
            Menu_Trajet.setStyle("-fx-background-color:transparent");
            Menu_Reservation.setStyle("-fx-background-color:transparent");
        
        }else if(event.getSource() == Menu_Reservation )
        {
            Formulaire_Dashboard.setVisible(false);
            Formulaire_Bus.setVisible(false);
            Formulaire_Trajet.setVisible(false);
            Formulaire_Client.setVisible(false);
            Formulaire_Reservation.setVisible(true); 
            
            Menu_Reservation.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");            Menu_Home.setStyle("-fx-background-color:linear-gradient(to bottom right, #25a473, #89892b)");
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
    public void AjoutBus()
    {
        String sql = "INSERT INTO bus(description,matricule,nbre_place,etat)";
    }
    
    public ObservableList<busData> ListeBus()
    {
        return null;
    }
    //---------------------------BUS-------------------------

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
