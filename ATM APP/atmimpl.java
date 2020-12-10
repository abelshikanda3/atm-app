
/**
*atmimpl is a class that impliments atm
*@auther Abel
*Version 1.0
*/


import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream; 
import java.io.FileNotFoundException;  

import java.util.*; 
import java.sql.*;

import javafx.application.Application; 
import static javafx.application.Application.launch;

import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Button; 
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;

import javafx.scene.text.Font;  
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.text.Text; 

import javafx.scene.control.TextField; 
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.PasswordField; 
import javafx.scene.control.Control;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane; 
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.FlowPane;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.Group;  
import javafx.scene.chart.*;

import javafx.scene.input.MouseEvent;

 


public class atmimpl extends Application implements atm {
	
	/**
	*
	*
	*@param
	*@exception
	*
	*/
	
	
	
	
	
	
	Connection con = null;
	String db_url = "jdbc:mysql://localhost/atm";
	String USER = "root";
	String PASS = "";
		
	public atmimpl () {}
	
	public void withdraw(){
		
			launch();
	}
	
	public void deposit() {
		
		launch();
	}
	
	public void account() {
		
			launch();
	}
	
	public void start(Stage stage) {
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(db_url, USER, PASS);
		}catch(ClassNotFoundException ex){
			System.out.println("ERROR! Unable to load Driver class");
			System.exit(1);
		}catch(SQLException se){
				se.printStackTrace();
		}
		
		//Login
		Text User = new Text("User");
		TextField user = new TextField();
		Text Password = new Text("Password");
		PasswordField Pass = new PasswordField();
		
		user.setStyle("-fx-font: normal bold 15px 'serif' ");
		Password.setStyle("-fx-font: normal bold 15px 'serif' ");
		
		Button login = new Button("Login");
		
		login.setStyle("-fx-background-color: #2F4F4F; -fx-text-fill: White;");
		
		
		
		//Home
		Text Account = new Text("Account");
		Text Name = new Text("Name");
		Text No = new Text("Acc No.");
		Text Balance = new Text("Balance");
		ComboBox<String> cbacc = new ComboBox<>();
		Label name = new Label();
		Label no = new Label();
		Label balance = new Label();
		
		name.setStyle("-fx-font: normal bold 15px 'serif' ");
		no.setStyle("-fx-font: normal bold 15px 'serif' ");
		balance.setStyle("-fx-font: normal bold 15px 'serif' ");
		
		Button with = new Button("withdraw");
		Button depo = new Button("deposit");
		
		with.setStyle("-fx-background-color: #2F4F4F; -fx-text-fill: White;");
		depo.setStyle("-fx-background-color: #2F4F4F; -fx-text-fill: White;");
		
		//withdraw
		Text Amount = new Text("Amount to withdraw");
		TextField amount = new TextField();
		
		Button ok = new Button("Withdraw");
		
		//deposited
		Text Amnt = new Text("Amount to Deposite");
		TextField amnt = new TextField();
		Button okey = new Button("Deposite");
		
		amnt.setStyle("-fx-font: normal bold 15px 'serif' ");
		okey.setStyle("-fx-background-color: #2F4F4F; -fx-text-fill: White;");
		
		//final
		Label Success = new Label("SUCCESSFUL");
		
		Button home = new Button("Home");
		home.setStyle("-fx-background-color: #2F4F4F; -fx-text-fill: White;");
		
			
		try{String sql = "SELECT accName from accounts WHERE accID = 1;";
			final PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				name.setText(rs.getString(1));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}	
		
		
		
		try{String sql1 = "SELECT accNo from accounts WHERE accID = 1;";
			final PreparedStatement stmt1 = con.prepareStatement(sql1);
			ResultSet rs1 = stmt1.executeQuery(sql1);
			
			while (rs1.next()) {
				no.setText(rs1.getString(1));
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}	
		
		
		
		try{String sql3 = "SELECT (SUM(Deposited) - SUM(withdrawn)) AS Balance FROM transactions;";
			final PreparedStatement stmt3 = con.prepareStatement(sql3);
			ResultSet rs3 = stmt3.executeQuery(sql3);
			
			while (rs3.next()) {
				balance.setText(rs3.getString(1));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		
		
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(10, 10, 10, 10));
		gp.setVgap(10);
		gp.setHgap(10);
		gp.setPrefSize(500, 300);
		gp.setStyle("-fx-background-color: #5F9EA0;");
		gp.setAlignment(Pos.CENTER);
		
		
		with.setOnAction( e -> {
			gp.getChildren().clear();
			gp.add(Amount, 1, 1);
			gp.add(amount, 1, 2);
			gp.add(ok, 1, 3);
		});
		
		
		
		depo.setOnAction( e -> {
			gp.getChildren().clear();
			gp.add(Amnt, 1, 1);
			gp.add(amnt, 1, 2);
			gp.add(okey, 1, 3);
		});
		
		ok.setOnAction( e -> {
			System.out.println("withdrawing");
			try{
				String sql = "INSERT INTO transactions (date, withdrawn, deposited) VALUES (sysdate(), '"+ amount.getText() +"', .12);";
				final PreparedStatement stmt = con.prepareStatement(sql);
				stmt.executeUpdate(sql); 
				
				}catch(SQLException se){
				se.printStackTrace();
			}
			gp.getChildren().clear();
			gp.add(Success, 1, 1);
			gp.add(home, 1, 2);
			
		});
		
		okey.setOnAction( e -> {
			System.out.println("withdrawing");
			try{
				String sql = "INSERT INTO transactions (date, withdrawn, deposited) VALUES (sysdate(), .12 '"+ amount.getText() +"');";
				final PreparedStatement stmt = con.prepareStatement(sql);
				stmt.executeUpdate(sql); 
				
				}catch(SQLException se){
				se.printStackTrace();
			}
			gp.getChildren().clear();
			gp.add(Success, 1, 1);
			gp.add(home, 1, 2);
			
		});
		
		home.setOnAction( e -> {
			gp.getChildren().clear();
			gp.add(Account, 0, 0);	gp.add(cbacc, 1, 0);
			gp.add(Name, 0, 1);		gp.add(name, 1, 1);
			gp.add(No, 0, 2);		gp.add(no, 1, 2);
			gp.add(Balance, 0, 3);	gp.add(balance, 1, 3);
			gp.add(with, 1, 4);
			gp.add(depo, 2, 4);
		});
		
		
		gp.add(Account, 0, 0);	gp.add(cbacc, 1, 0);
		gp.add(Name, 0, 1);		gp.add(name, 1, 1);
		gp.add(No, 0, 2);		gp.add(no, 1, 2);
		gp.add(Balance, 0, 3);	gp.add(balance, 1, 3);
		gp.add(with, 1, 4);
		gp.add(depo, 2, 4);
		
		GridPane g = new GridPane();
		g.setPadding(new Insets(10, 10, 10, 10));
		g.setVgap(10);
		g.setHgap(10);
		g.setPrefSize(500, 300);
		g.setStyle("-fx-background-color: #5F9EA0;");
		g.setAlignment(Pos.CENTER);
		
		login.setOnAction( e -> {
			System.out.println("Chekking details");
			try{
					String sql = "SELECT * FROM users WHERE email = '"+ user.getText()+"';";
					String sqlq = "SELECT * FROM users WHERE Password = '"+ Pass.getText()+"';";
					final PreparedStatement stmt = con.prepareStatement(sql);
					final PreparedStatement stmtq = con.prepareStatement(sqlq);
					
					ResultSet rs = stmt.executeQuery();
					ResultSet rsq = stmtq.executeQuery();
					
					if(rs.next()&&rsq.next()) {
						g.getChildren().clear();
					
						Scene s = new Scene(gp);
						stage.setTitle("Home");
						stage.setScene(s);
						stage.show();
					}else {
						System.out.println("Please Try Again!");
					}
				}catch(SQLException se){
					//return false;
					se.printStackTrace();
					}
		});
		
		
		g.add(User, 0, 0);		g.add(user, 1, 0);
		g.add(Password, 0, 1);	g.add(Pass, 1, 1);	
		g.add(login, 1, 2);
			
		Scene s = new Scene(g);
		stage.setTitle("Login");
		stage.setScene(s);
		stage.show();
		
		
		
	}
}