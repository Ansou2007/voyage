/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package voyage;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author macair
 */
public class LoginController implements Initializable {

     // Connexion Base de donnees
    
    private Connection con;
    private PreparedStatement prepare;
    private ResultSet result;
    private double x= 0; 
    private double y= 0; 
    
       @FXML
    private Button btnConnexion;

    @FXML
    private Button close;

    @FXML
    private TextField login;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;
    
   public void login(){
       String sql = "SELECT * FROM utilisateur WHERE login = ? AND mot_de_pass = ?";
       con = database.connexionDB();
       
       try{ 
           prepare = (PreparedStatement) con.prepareStatement(sql);
           prepare.setString(1,login.getText()) ;
           prepare.setString(2,password.getText()) ;
           result = prepare.executeQuery();
           Alert alert;
           if(login.getText().isEmpty() || password.getText().isEmpty()){
               alert = new Alert(AlertType.ERROR);
               alert.setTitle("Erreur");
               alert.setHeaderText(null);
               alert.setContentText("Remplir les champs svp");
               alert.showAndWait();
           }else{
               if(result.next()){
               alert = new Alert(AlertType.INFORMATION);
               alert.setTitle("Information");
               alert.setHeaderText(null);
               alert.setContentText("Bienvenue !!");
               alert.showAndWait();
               // Cahcher le formulaire Login
               btnConnexion.getScene().getWindow().hide();
               // Affichage du Dashboard
               Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
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
        
            root.setOnMouseReleased((MouseEvent event) ->{
            stage.setOpacity(1);
             });
        
            stage.initStyle(StageStyle.TRANSPARENT);
               
               stage.setScene(scene);
               stage.show();
               
               }else{
               alert = new Alert(AlertType.ERROR);
               alert.setTitle("Erreur");
               alert.setHeaderText(null);
               alert.setContentText("Login ou Mot de passe incorrecte");
               alert.showAndWait();
               }
           }
       }catch(Exception e){e.printStackTrace();}
       
   }
   
    public void close(){
        System.exit(0);
    }
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        //con = database.connexionDB();
    }    
    
}
