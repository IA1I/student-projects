package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.DCONST;
import com.sun.prism.paint.Color;

import java.sql.PreparedStatement;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Main extends Application {
	//переменная для создания подключения
	public static Connection con;
	//переменная для вывода информации
	public static TextArea ta = new TextArea();
	public static String result = "";
	@Override
	public void start(Stage primaryStage) {
		try {
			//функция подключения к бд
			connectToBD();
			ta.setEditable(false);
			Group root = new Group();
			//кнопка для приема
			Button btShowApp = new Button("Show appoinment");
			btShowApp.setOnAction(e -> appointment());
			btShowApp.setLayoutX(0);
			btShowApp.setLayoutY(0);
			//кнопки для вывода таблиц
			
				Button btShowPatient = new Button("Show patients");
				btShowPatient.setOnAction(e -> printTable("patient"));
				btShowPatient.setLayoutX(0);
				btShowPatient.setLayoutY(25);
				Button btShowDoctor = new Button("Show doctors");
				btShowDoctor.setOnAction(e -> printTable("doctor"));
				btShowDoctor.setLayoutX(90);
				btShowDoctor.setLayoutY(25);
				Button btShowWriteCard = new Button("Show write to card");
				btShowWriteCard.setOnAction(e -> printTable("write_to_card"));
				btShowWriteCard.setLayoutX(177);
				btShowWriteCard.setLayoutY(25);
				Button btShowWriteLab = new Button("Show write lab");
				btShowWriteLab.setOnAction(e -> printTable("write_to_laboratory"));
				btShowWriteLab.setLayoutX(292);
				btShowWriteLab.setLayoutY(25);
				
				Button btShowDiagRec = new Button("Show diagnostic record");
				btShowDiagRec.setOnAction(e -> printTable("diagnostic_record"));
				btShowDiagRec.setLayoutX(0);
				btShowDiagRec.setLayoutY(50);
				Button btShowVacRec = new Button("Show vaccination record");
				btShowVacRec.setOnAction(e -> printTable("vaccination_record"));
				btShowVacRec.setLayoutX(140);
				btShowVacRec.setLayoutY(50);
				Button btShowFluRec = new Button("Show fluorography record");
				btShowFluRec.setOnAction(e -> printTable("fluorography_record"));
				btShowFluRec.setLayoutX(285);
				btShowFluRec.setLayoutY(50);
				Button btShowPatCard = new Button("Show patient card");
				btShowPatCard.setOnAction(e -> printTable("patient_card"));
				btShowPatCard.setLayoutX(440);
				btShowPatCard.setLayoutY(50);
			
				Button btShowLab = new Button("Show laboratory");
				btShowLab.setOnAction(e -> printTable("laboratory"));
				btShowLab.setLayoutX(0);
				btShowLab.setLayoutY(75);
				Button btShowVac = new Button("Show vaccinations");
				btShowVac.setOnAction(e -> printTable("vaccinations"));
				btShowVac.setLayoutX(103);
				btShowVac.setLayoutY(75);
				Button btShowAppoi = new Button("Show appointment");
				btShowAppoi.setOnAction(e -> printTable("appointment"));
				btShowAppoi.setLayoutX(216);
				btShowAppoi.setLayoutY(75);
				Button btShowDiagTyp = new Button("Show diagnostic types");
				btShowDiagTyp.setOnAction(e -> printTable("diagnostic_types"));
				btShowDiagTyp.setLayoutX(333);
				btShowDiagTyp.setLayoutY(75);
				
				Button btShowAnTyp = new Button("Show analysis types");
				btShowAnTyp.setOnAction(e -> printTable("analysis_types"));
				btShowAnTyp.setLayoutX(0);
				btShowAnTyp.setLayoutY(100);
				Button btShowVacTyp = new Button("Show vaccination types");
				btShowVacTyp.setOnAction(e -> printTable("vaccination_types"));
				btShowVacTyp.setLayoutX(121);
				btShowVacTyp.setLayoutY(100);
				Button btShowFlu = new Button("Show fluorography");
				btShowFlu.setOnAction(e -> printTable("fluorography"));
				btShowFlu.setLayoutX(261);
				btShowFlu.setLayoutY(100);
				Button btShowFuncDiag = new Button("Show functional diagnostics");
				btShowFuncDiag.setOnAction(e -> printTable("functional_diagnostics"));
				btShowFuncDiag.setLayoutX(378);
				btShowFuncDiag.setLayoutY(100);
				
			Button btAddPatient = new Button("Add patient");
			btAddPatient.setOnAction(e -> addPatient());
			btAddPatient.setLayoutX(0);
			btAddPatient.setLayoutY(125);

			Button btAddToAppointment = new Button("Add To Appointment");
			btAddToAppointment.setOnAction(e -> addToAppointment());
			btAddToAppointment.setLayoutX(0);
			btAddToAppointment.setLayoutY(90);
			//кнопка для информации о пациентах
			Button btShowInfo = new Button("Show information");
			btShowInfo.setOnAction(e -> showInfo());
			btShowInfo.setLayoutX(150);
			btShowInfo.setLayoutY(0);
			ta.setLayoutX(0);
			ta.setLayoutY(150);
			ta.setPrefSize(600, 400);
			//root.getChildren().addAll(btShowPatient,btShowDoctor,btShowWriteCard,btShowWriteLab);
			//root.getChildren().addAll(btShowDiagRec,btShowVacRec,btShowFluRec,btShowPatCard);
			//root.getChildren().addAll(btShowLab,btShowVac,btShowAppoi,btShowDiagTyp);
			//root.getChildren().addAll(btShowAnTyp,btShowVacTyp,btShowFlu,btShowFuncDiag,btAddPatient);
			root.getChildren().add(btAddPatient);
			root.getChildren().add(btAddToAppointment);
			root.getChildren().addAll(btShowApp,btShowInfo,ta);
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public static void connectToBD() {//метод для подлючения к бд
		//содержит имя класса, который реализует код import java.sql.DriverManager;
		String driver = "com.mysql.cj.jdbc.Driver";
		//информация о подключении к базе данных
		String url = "jdbc:mysql://localhost:3306/hospital?&serverTimezone=UTC";
		//имя пользователя
		String user = "root";
		//пароль
		String password = "MYSQLqeadws21";
		try {
			//подлючение
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);	
		}catch (SQLException e) {
			System.out.println(e);
		}catch (ClassNotFoundException e) {
			System.out.println(e);
		}//

	}
	public static void printTable(String tableName) {//метод для вывода таблиц
		try {
			ta.clear();
			result ="";
			//запрос
			String query = "SELECT * FROM " + tableName+";";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);{
			int colNum = getColumnNames(rs);
			result+="\n";
			if(colNum>0)
				while(rs.next()) {//цикл пока не дойдем до конца
					 for(int i =0; i<colNum; i++) {//выводим значения из таблицы через запяту
						 if(i+1 == colNum)
							 result+=(rs.getString(i+1)+"\n");
						 else
							 result+=(rs.getString(i+1)+ ", ");
					 }
				}
			}
			ta.setText(result);
		}
		catch (SQLException e) {
			System.out.println(e);
		}

	}
	public static void printTable(String tableName, String query) {//метод для вывода таблиц
		try {
			//query = "SELECT * FROM " + tableName+";";
			ta.clear();
			result = "";
			//запрос 
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);{
			int colNum = getColumnNames(rs);
			result+="\n";
			if(colNum>0)
				while(rs.next()) {//цикл пока не дойдем до конца
					 for(int i =0; i<colNum; i++) {//выводим значения из таблицы через запяту
						 if(i+1 == colNum)
							 result+=(rs.getString(i+1)+"\n");
						 else
							 result+=(rs.getString(i+1)+ ", ");
					 }
				}
			}
			ta.setText(result);
		}
		catch (SQLException e) {
			System.out.println(e);
		}

	}
	public static void insertTable(String tableName, String query) {//метод для добавления в таблицу
		try {
			//query = "SELECT * FROM " + tableName+";";
			Statement st = con.createStatement();
			st.executeUpdate(query);
		}
		catch (SQLException e) {
			displayError("Error", "This entry already exists.");
		}

	}
	public static int getColumnNames(ResultSet rs) throws SQLException {//метод для получения кол-ва столбиков в таблице
		 int numberOfColumns = 0;
		 if (rs != null) {
		//создаем объект на основе метаданных
			 ResultSetMetaData rsMetaData = rs.getMetaData();
		//используем метод, чтобы получить количество возвращаемых столбцов
			 numberOfColumns = rsMetaData.getColumnCount();
		//получаем и печатаем имена столбцов, индексы столбцов начинаются с 1
			 for (int i = 1; i < numberOfColumns + 1; i++) {
				 String columnName = rsMetaData.getColumnName(i);
				 result+=(columnName + ", ");
			 }
		 }
		 System.out.println();
		 return numberOfColumns;
	}
	public static void appointment() {//окно приема
		String patienID;
		Label lbl = new Label("Doctor ID");
		lbl.setLayoutX(0);
		lbl.setLayoutY(0);
		Group newRoot = new Group();
		TextField tfild = new TextField();
		tfild.setLayoutX(55);
		tfild.setLayoutY(0);
		Button btGetPatient = new Button("Сheck timetable");
		Button btAddAn = new Button("Assign tests");
		Button btAddDiag = new Button("Assign diagnostics");
		Button btAddVac = new Button("Assign vaccinations");
		Button btAddFlu = new Button("Assign fluorography");
		btAddAn.setVisible(false);
		btAddDiag.setVisible(false);
		btAddVac.setVisible(false);
		btAddFlu.setVisible(false);
		btGetPatient.setOnAction(e -> {
			if(tfild.getText().isEmpty()) {
				displayError("ID Error", "No value entered for Doctor ID");
			}
			else if (!checkID("doctor", "id_doctor", tfild))
				displayError("ID Error", "This ID does not exist");
			else {
				getPatien(tfild);
				btAddAn.setVisible(true);
				btAddDiag.setVisible(true);
				btAddVac.setVisible(true);
				btAddFlu.setVisible(true);
			}
			
		});
		btGetPatient.setLayoutX(205);
		btGetPatient.setLayoutY(0);
		
		btAddAn.setOnAction(e -> assignTests(tfild));
		btAddAn.setLayoutX(0);
		btAddAn.setLayoutY(25);
		
		btAddDiag.setOnAction(e -> assignDiagnostics(tfild));
		btAddDiag.setLayoutX(78);
		btAddDiag.setLayoutY(25);
		
		btAddVac.setOnAction(e -> assignVaccination(tfild));
		btAddVac.setLayoutX(192);
		btAddVac.setLayoutY(25);
		
		btAddFlu.setOnAction(e -> assignFluorography(tfild));
		btAddFlu.setLayoutX(310);
		btAddFlu.setLayoutY(25);
		newRoot.getChildren().addAll(btAddAn,btAddDiag,btAddVac,btAddFlu,btGetPatient,tfild,lbl);
		Scene newScene = new Scene(newRoot,500,100);
		Stage newWindow = new Stage();
		newWindow.setTitle("Appointment");
		newWindow.setScene(newScene);
		newWindow.show();
	}
	public static void getPatien(TextField tf) {//меод для добавления пациента
		String docID = tf.getText();
		PreparedStatement pstmt;
		Statement st;
		ResultSet rs;
		
		try {
			
			String query = "SELECT id_patient,first_name, last_name,appointment_time FROM appointment JOIN patient USING (id_patient) WHERE id_doctor = '" + docID + "' AND appointment_date >= CURDATE();";
			//pstmt = con.prepareStatement(query);
			st = con.createStatement();
			rs = st.executeQuery(query);
			printTable("appointment",query);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch(NumberFormatException e) {
			displayError("ID Error","No value entered for Doctor ID");
		}


	}
	public static void displayError(String title, String message) {//метод для вывода ошибки неправильного формата ввода
		//создаем alert без заголовка
		//задаем название, сообщение
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	public static void addPatient() {//меод для добавления пациента
		Group newRoot = new Group();
		TextField tfild1 = new TextField();
		tfild1.setLayoutX(0);
		tfild1.setLayoutY(0);
		TextField tfild2 = new TextField();
		tfild2.setLayoutX(0);
		tfild2.setLayoutY(25);
		TextField tfild3 = new TextField();
		tfild3.setLayoutX(0);
		tfild3.setLayoutY(50);
		TextField tfild4 = new TextField();
		tfild4.setLayoutX(0);
		tfild4.setLayoutY(75);
		TextField tfild5 = new TextField();
		tfild5.setLayoutX(0);
		tfild5.setLayoutY(100);
		TextField tfild6 = new TextField();
		tfild6.setLayoutX(0);
		tfild6.setLayoutY(125);
		Button btEntry = new Button("Entry");
		btEntry.setOnAction(e -> addToTable("patient", tfild1, tfild2, tfild3, tfild4, tfild5, tfild6));
		btEntry.setLayoutX(150);
		btEntry.setLayoutY(0);
		newRoot.getChildren().addAll(tfild1,tfild2,tfild3,tfild4,tfild5,tfild6,btEntry);
		Scene newScene = new Scene(newRoot,400,200);
		Stage newWindow = new Stage();
		newWindow.setTitle("Add Patient");
		newWindow.setScene(newScene);
		newWindow.show();
	}
	public static void addToAppointment() {//меод для добавления пациента
		Group newRoot = new Group();
		TextField tfild1 = new TextField();
		tfild1.setLayoutX(0);
		tfild1.setLayoutY(0);
		TextField tfild2 = new TextField();
		tfild2.setLayoutX(0);
		tfild2.setLayoutY(25);
		TextField tfild3 = new TextField();
		tfild3.setLayoutX(0);
		tfild3.setLayoutY(50);
		TextField tfild4 = new TextField();
		tfild4.setLayoutX(0);
		tfild4.setLayoutY(75);
		Button btEntry = new Button("Entry");
		btEntry.setOnAction(e -> addToTable("appointment", tfild1, tfild2, tfild3, tfild4));
		btEntry.setLayoutX(150);
		btEntry.setLayoutY(0);
		newRoot.getChildren().addAll(tfild1,tfild2,tfild3,tfild4,btEntry);
		Scene newScene = new Scene(newRoot,400,200);
		Stage newWindow = new Stage();
		newWindow.setTitle("Add Patient");
		newWindow.setScene(newScene);
		newWindow.show();
	}public static void addToTable(String tableName,TextField tfild1,TextField tfild2,TextField tfild3,TextField tfild4) {
		String query="";
		try {
			query = "INSERT INTO " + tableName +" (id_doctor, id_patient, appointment_date, appointment_time) Values ('"+tfild1.getText()+
					"','"+tfild2.getText()+"','"+tfild3.getText()+"', '"+tfild4.getText()+"')"+";";
			System.out.println(query);
			Statement st = con.createStatement();

			st.executeUpdate(query);
		}
		catch(SQLIntegrityConstraintViolationException e) {
			if (!checkID("doctor", "id_doctor", tfild1))
				displayError("ID Error", "This doctor ID does not exist");
			if (!checkID("patient", "id_patient", tfild2))
				displayError("ID Error", "This patient ID does not exist");

		}
		catch (SQLException e) {
			System.out.println(e);
		}

	}
	public static void addToTable(String tableName,TextField tfild1,TextField tfild2,TextField tfild3,TextField tfild4,TextField tfild5,TextField tfild6) {
		String query="";
		try {
			query = "INSERT INTO " + tableName +" (id_patient, first_name, last_name, policy_number, snils, id_card) Values ('"+tfild1.getText()+
					"','"+tfild2.getText()+"','"+tfild3.getText()+"',"+tfild4.getText()+","+tfild5.getText()+",'"+tfild6.getText()+"')"+";";
			System.out.println(query);
			Statement st = con.createStatement();

			st.executeUpdate(query);
		}
		catch(SQLIntegrityConstraintViolationException e) {
			try {
				Statement st = con.createStatement();
				String add ="INSERT INTO patient_card"  +" ( id_card, id_patient) Values ('"+tfild6.getText()+"','"+tfild1.getText()+"')"+";";
				st.executeUpdate(add);
				st.executeUpdate(query);
			}catch (SQLException ep) {
				System.out.println(ep);
			}
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	public static void assignTests(TextField docField) {//метод для назначения тестов
		Group newRoot = new Group();
		Label lbl = new Label("Patient ID");
		lbl.setLayoutX(10);
		lbl.setLayoutY(0);
		TextField tfild1 = new TextField();
		tfild1.setLayoutX(70);
		tfild1.setLayoutY(0);
		//создание checkbox для выбора анализов
		CheckBox oak = new CheckBox("OAK");
		oak.setLayoutX(10);
		oak.setLayoutY(25);
		CheckBox oam = new CheckBox("OAM");
		oam.setLayoutX(10);
		oam.setLayoutY(50);
		CheckBox bx = new CheckBox("BX");
		bx.setLayoutX(10);
		bx.setLayoutY(75);
		Button bt = new Button("Assign Tests");
		bt.setLayoutX(220);
		bt.setLayoutY(0);
		//кнопка для добавления
		bt.setOnAction(e -> {
			if(tfild1.getText().isEmpty())
				displayError("ID Error", "No value entered for Patient ID");
			else if (!checkID("patient", "id_patient", tfild1))
				displayError("ID Error", "This ID does not exist");
			else {
				if (checkPat(docField,tfild1))
					btAssignTests(oak, oam, bx, tfild1, docField);
				else {
						displayError("Error", "You can not assign analyzes to the patient");
					}
			}
				
			tfild1.clear();
		});
		newRoot.getChildren().addAll(lbl,tfild1,oak,oam,bx,bt);
		Scene newScene = new Scene(newRoot,400,150);
		Stage newWindow = new Stage();
		newWindow.setTitle("Assign Tests");
		newWindow.setScene(newScene);
		newWindow.show();
	}
	public static void assignDiagnostics(TextField docField) {//метод для наначения диагностики
		Group newRoot = new Group();
		Label lbl = new Label("Patient ID");
		lbl.setLayoutX(10);
		lbl.setLayoutY(0);
		TextField patField = new TextField();
		patField.setLayoutX(70);
		patField.setLayoutY(0);
		//checkbox для выбора диагностики
		CheckBox uzi = new CheckBox("UZI");
		uzi.setLayoutX(10);
		uzi.setLayoutY(25);
		CheckBox fgs = new CheckBox("FGS");
		fgs.setLayoutX(10);
		fgs.setLayoutY(50);
		CheckBox mrt = new CheckBox("MRT");
		mrt.setLayoutX(10);
		mrt.setLayoutY(75);
		CheckBox ekt = new CheckBox("EKT");
		ekt.setLayoutX(10);
		ekt.setLayoutY(100);
		CheckBox fvd = new CheckBox("FVD");
		fvd.setLayoutX(10);
		fvd.setLayoutY(125);
		CheckBox eeg = new CheckBox("EEG");
		eeg.setLayoutX(10);
		eeg.setLayoutY(150);
		Button bt = new Button("Assign Diagnostics");
		bt.setLayoutX(220);
		bt.setLayoutY(0);
		//обработка нажатия кнопки
		bt.setOnAction(e -> {
			if(patField.getText().isEmpty())
				displayError("ID Error", "No value entered for Patient ID");
			else if (!checkID("patient", "id_patient", patField))
				displayError("ID Error", "This ID does not exist");
			else {
				if (checkPat(docField, patField))
					btAssignDiagnostic(uzi, fgs, mrt, ekt, fvd, eeg, patField, docField);
				else
					displayError("Error", "You can not assign diagnostics to the patient");
			}
				});
		newRoot.getChildren().addAll(lbl,patField,uzi,fgs,mrt,ekt,fvd,eeg,bt);
		Scene newScene = new Scene(newRoot,400,200);
		Stage newWindow = new Stage();
		newWindow.setTitle("Assign Diagnostics");
		newWindow.setScene(newScene);
		newWindow.show();
	}
	public static void assignVaccination(TextField docField) {//метод для добавления прививок
		Group newRoot = new Group();
		Label lbl = new Label("Patient ID");
		lbl.setLayoutX(10);
		lbl.setLayoutY(0);
		TextField patField = new TextField();
		patField.setLayoutX(70);
		patField.setLayoutY(0);
		//check box для выбора прививки
		CheckBox adcm = new CheckBox("ADCM");
		adcm.setLayoutX(10);
		adcm.setLayoutY(25);
		CheckBox measles = new CheckBox("Measles");
		measles.setLayoutX(10);
		measles.setLayoutY(50);
		CheckBox rubella = new CheckBox("Rubella");
		rubella.setLayoutX(10);
		rubella.setLayoutY(75);
		CheckBox ovgv = new CheckBox("OVGV");
		ovgv.setLayoutX(10);
		ovgv.setLayoutY(100);
		CheckBox grippe = new CheckBox("Grippe");
		grippe.setLayoutX(10);
		grippe.setLayoutY(125);
		CheckBox pneumonia = new CheckBox("Pneumonia");
		pneumonia.setLayoutX(10);
		pneumonia.setLayoutY(150);
		Button bt = new Button("Assign Vaccination");
		bt.setLayoutX(220);
		bt.setLayoutY(0);
		//обработка нажатия кнопки
		bt.setOnAction(e -> {
			if(patField.getText().isEmpty())
				displayError("ID Error", "No value entered for Patient ID");
			else if (!checkID("patient", "id_patient", patField))
				displayError("ID Error", "This ID does not exist");
			else {
				if(checkPat(docField, patField))
					btAssignVaccination(adcm, measles, rubella, ovgv, grippe, pneumonia, patField, docField);
				else
					displayError("Error", "You can not assign vaccinations to the patient");
			}
				
			patField.clear();
		});
		newRoot.getChildren().addAll(lbl,patField,adcm,measles,rubella,ovgv,grippe,pneumonia,bt);
		Scene newScene = new Scene(newRoot,400,200);
		Stage newWindow = new Stage();
		newWindow.setTitle("Assign Vaccination");
		newWindow.setScene(newScene);
		newWindow.show();
	}
	public static void assignFluorography(TextField docField) {//метод для назначения флюорографии
		Label lbl = new Label("Patient ID");
		lbl.setLayoutX(10);
		lbl.setLayoutY(0);
		Group newRoot = new Group();
		TextField patField = new TextField();
		patField.setLayoutX(70);
		patField.setLayoutY(0);
		Button bt = new Button("Assign fluorography");
		bt.setLayoutX(220);
		bt.setLayoutY(0);
		//обработка нажатия кнопки 
		bt.setOnAction(e -> {
			if(patField.getText().isEmpty())
				displayError("ID Error", "No value entered for Patient ID");
			else if (!checkID("patient", "id_patient", patField))
				displayError("ID Error", "This ID does not exist");
			else
			{
				if(checkPat(docField, patField))
					btAssignFluorography(patField, docField);
				else
					displayError("Error", "You can not assign fluorography to the patient");
			}
			patField.clear();
		});
		newRoot.getChildren().addAll(lbl,patField,bt);
		Scene newScene = new Scene(newRoot,400,150);
		Stage newWindow = new Stage();
		newWindow.setTitle("Assign Fluorography");
		newWindow.setScene(newScene);
		newWindow.show();
	}
	public static void btAssignTests(CheckBox oak,CheckBox oam,CheckBox bx,TextField patField,TextField docField) {//метод для добавления в таблицу
		String patID = patField.getText();
		String docID = docField.getText();
		String query = "";
		//если какой-нибудь анализ выбран, то он заноситься в таблицу 
		if( oak.isSelected()) {
			query = "INSERT INTO laboratory (id_patient, passing_date, id_analysis, id_doctor) Values ('" + patID + "', CURDATE(),'an01','"+docID+"');";
			insertTable("laboratory", query);
			
		}
		if( oam.isSelected()) {
			query = "INSERT INTO laboratory (id_patient, passing_date, id_analysis, id_doctor) Values ('" + patID + "', CURDATE(),'an02','"+docID+"');";
			insertTable("laboratory", query);
			
		}	
		if( bx.isSelected()) {
			query = "INSERT INTO laboratory (id_patient, passing_date, id_analysis, id_doctor) Values ('" + patID + "', CURDATE(),'an03','"+docID+"');";
			insertTable("laboratory", query);
			
		}
		//если ни один не выбран, то сообщаем об этом
		if((!oak.isSelected() && !oam.isSelected() && !bx.isSelected()) ) {
			displayError("Warning", "You did not assign an analysis.");
		}
	}
	public static void btAssignDiagnostic(CheckBox uzi,CheckBox fgs,CheckBox mrt,CheckBox ekt,CheckBox fvd,CheckBox eeg,TextField patField,TextField docField) {//добавление диагностики 
		String patID = patField.getText();
		String docID = docField.getText();
		String query = "";
		//если какая-нибудь диагностика выбрана, то она заноситься в таблицу 
		if(uzi.isSelected()) {
			query = "INSERT INTO functional_diagnostics (id_patient, id_doctor, passing_date, id_diagnostic) Values ('" + patID + "','"+docID+"', CURDATE(),'dg01');";
			insertTable("functional_diagnostics", query);
		}
		if(fgs.isSelected()) {
			query = "INSERT INTO functional_diagnostics (id_patient, id_doctor, passing_date, id_diagnostic) Values ('" + patID + "','"+docID+"', CURDATE(),'dg02');";
			insertTable("functional_diagnostics", query);
		}
		if(mrt.isSelected()) {
			query = "INSERT INTO functional_diagnostics (id_patient, id_doctor, passing_date, id_diagnostic) Values ('" + patID + "','"+docID+"', CURDATE(),'dg03');";
			insertTable("functional_diagnostics", query);
		}
		if(ekt.isSelected()) {
			query = "INSERT INTO functional_diagnostics (id_patient, id_doctor, passing_date, id_diagnostic) Values ('" + patID + "','"+docID+"', CURDATE(),'dg04');";
			insertTable("functional_diagnostics", query);
		}
		if(fvd.isSelected()) {
			query = "INSERT INTO functional_diagnostics (id_patient, id_doctor, passing_date, id_diagnostic) Values ('" + patID + "','"+docID+"', CURDATE(),'dg05');";
			insertTable("functional_diagnostics", query);
		}
		if(eeg.isSelected()) {
			query = "INSERT INTO functional_diagnostics (id_patient, id_doctor, passing_date, id_diagnostic) Values ('" + patID + "','"+docID+"', CURDATE(),'dg06');";
			insertTable("functional_diagnostics", query);
		}
		//если ни одна не выбрана, то сообщаем об этом
		if((!uzi.isSelected()&&!fgs.isSelected()&&!mrt.isSelected()&&!ekt.isSelected()&&!fvd.isSelected()&&!eeg.isSelected())) {
			displayError("Warning", "You did not assign a diagnostic.");
		}
	}
	public static void btAssignVaccination(CheckBox adcm,CheckBox measles,CheckBox rubella,CheckBox ovgv,CheckBox grippe,CheckBox pneumonia,TextField patField,TextField docField) {
		String patID = patField.getText();
		String docID = docField.getText();
		String query = "";
		//если какая-нибудь прививка выбрана, то она заноситься в таблицу 
		if(adcm.isSelected()) {
			query = "INSERT INTO vaccinations  (id_patient, vaccination_date, id_vaccination, id_doctor) Values ('" + patID + "', CURDATE(),'vac01','"+docID+"');";
			insertTable("vaccinations", query);
		}
		if(measles.isSelected()) {
			query = "INSERT INTO vaccinations  (id_patient, vaccination_date, id_vaccination, id_doctor) Values ('" + patID + "', CURDATE(),'vac02','"+docID+"');";
			insertTable("vaccinations", query);
		}
		if(rubella.isSelected()) {
			query = "INSERT INTO vaccinations  (id_patient, vaccination_date, id_vaccination, id_doctor) Values ('" + patID + "', CURDATE(),'vac03','"+docID+"');";
			insertTable("vaccinations", query);
		}
		if(ovgv.isSelected()) {
			query = "INSERT INTO vaccinations  (id_patient, vaccination_date, id_vaccination, id_doctor) Values ('" + patID + "', CURDATE(),'vac04','"+docID+"');";
			insertTable("vaccinations", query);
		}
		if(grippe.isSelected()) {
			query = "INSERT INTO vaccinations  (id_patient, vaccination_date, id_vaccination, id_doctor) Values ('" + patID + "', CURDATE(),'vac05','"+docID+"');";
			insertTable("vaccinations", query);
		}
		if(pneumonia.isSelected()) {
			query = "INSERT INTO vaccinations  (id_patient, vaccination_date, id_vaccination, id_doctor) Values ('" + patID + "', CURDATE(),'vac06','"+docID+"');";
			insertTable("vaccinations", query);
		}
		//если ни одна не выбрана, то сообщаем об этом
		if((!adcm.isSelected()&&!measles.isSelected()&&!rubella.isSelected()&&!ovgv.isSelected()&&!grippe.isSelected()&&!pneumonia.isSelected())) {
			displayError("Warning", "You did not assign a vaccination.");
		}
	}
	public static void btAssignFluorography(TextField patField,TextField docField) {//добавление флюорографии
		String patID = patField.getText();
		String docID = docField.getText();
		String query = "";
		{
			query = "INSERT INTO fluorography (id_patient, id_doctor, passing_date) Values ('" + patID + "','"+docID+"', CURDATE());";
			insertTable("fluorography", query);
		}
	}
	public static boolean checkID(String tableName, String columnName, TextField tf) {//метод для проверки id
		String id = tf.getText();
		String query = "SELECT EXISTS(SELECT " + columnName + " FROM " + tableName + " WHERE " + columnName + "='"+id+"');";
		String check = "";
		try {
			Statement st = con.createStatement();	
			ResultSet rs = st.executeQuery(query);
			rs.next();
			check = rs.getString(1);
		}catch (SQLException e) {
			System.out.println(e);
		}
		if (check.equals("1"))
			return true;
		else return false;
			
	}
	public static void showInfo() {//метод для окна выбора информации
		Group newRoot = new Group();
		//списки с выбором категории, анализа, диагностики, прививок 
		ObservableList<String> langs = FXCollections.observableArrayList("Analysis", "Diagnostic", "Vaccination", "Fluorography");
		ObservableList<String> analysis = FXCollections.observableArrayList("All","OAK", "OAM", "BX");
		ObservableList<String> diagnostic = FXCollections.observableArrayList("All","UZI", "FGS", "MRT","EKT", "FVD", "EEG");
		ObservableList<String> vaccination = FXCollections.observableArrayList("All","ADCM", "Measles", "Rubella","OVGV", "Grippe", "Pneumonia");
		//комбобоксы для выбора
		ComboBox<String> cboTableName = new ComboBox<String>(langs);
		ComboBox<String> cboTable = new ComboBox<String>(langs);
		cboTable.setLayoutX(150);
		cboTable.setLayoutY(0);
		cboTable.setVisible(false);
		TextField patField = new TextField();
		Button bt = new Button("Show information");
		patField.setVisible(false);
		bt.setVisible(false);
		patField.setLayoutX(300);
		patField.setLayoutY(0);
		bt.setLayoutX(450);
		bt.setLayoutY(0);
		//обработка первого комбобокса, от выбора первого меняются значения во втором
		cboTableName.setOnAction(e -> {
			if(cboTableName.getValue().equals("Analysis")) {
				cboTable.setItems(analysis);
				cboTable.setVisible(true);
				patField.setVisible(false);
				bt.setVisible(false);
			}
			if(cboTableName.getValue().equals("Diagnostic")) {
				cboTable.setItems(diagnostic);
				cboTable.setVisible(true);
				patField.setVisible(false);
				bt.setVisible(false);
			}
			if(cboTableName.getValue().equals("Vaccination")) {
				cboTable.setItems(vaccination);
				cboTable.setVisible(true);
				patField.setVisible(false);
				bt.setVisible(false);
			}
			if(cboTableName.getValue().equals("Fluorography")) {
				cboTable.setVisible(false);
				patField.setVisible(true);
				bt.setVisible(true);
			}
			
		});
		//обработка второго комбобокса, появления поля для ввода и кнопки
		cboTable.setOnAction(e -> {
			patField.setVisible(true);
			bt.setVisible(true);
		});
		//обработка нажатия кнопки
		bt.setOnAction(e ->{
			if(patField.getText().isEmpty())
				displayError("ID Error", "No value entered for Patient ID");
			else if (!checkID("patient", "id_patient", patField))
				displayError("ID Error", "This ID does not exist");
			else
				btShowInfo(cboTableName, cboTable, patField);
		});
		newRoot.getChildren().addAll(cboTableName,cboTable,patField,bt);
		Scene newScene = new Scene(newRoot,600,150);
		Stage newWindow = new Stage();
		newWindow.setTitle("Show Information");
		newWindow.setScene(newScene);
		newWindow.show();
	}
	public static void btShowInfo(ComboBox<String> cboTableName,ComboBox<String> cboTable,TextField patField) {//вывод информации
		String patID = patField.getText();
		String query = "";
		//вывод информации для определенного поля в комбобоксе
		if(cboTableName.getValue().equals("Analysis")) {
			if(cboTable.getValue().equals("All")) {
				query = "SELECT p.first_name,p.last_name,l.passing_date,a.analysis_name FROM laboratory l JOIN patient p USING (id_patient) JOIN analysis_types a USING (id_analysis) WHERE id_patient = '" + patID +"';";
				printTable("laboratory", query);
			}else {
				query = "SELECT p.first_name,p.last_name,l.passing_date,a.analysis_name FROM laboratory l JOIN patient p USING (id_patient) JOIN analysis_types a USING (id_analysis) WHERE id_patient = '" + patID +"' AND analysis_name = '" +cboTable.getValue()+"';";
				printTable("laboratory", query);
			}
		}
		if(cboTableName.getValue().equals("Diagnostic")) {
			if(cboTable.getValue().equals("All")) {
				query = "SELECT p.first_name,p.last_name,l.passing_date,a.diagnostic_name FROM functional_diagnostics l JOIN patient p USING (id_patient) JOIN diagnostic_types a USING (id_diagnostic) WHERE id_patient = '" + patID +"';";
				printTable("functional_diagnostics", query);
			}else {
				query = "SELECT p.first_name,p.last_name,l.passing_date,a.diagnostic_name FROM functional_diagnostics l JOIN patient p USING (id_patient) JOIN diagnostic_types a USING (id_diagnostic) WHERE id_patient = '" + patID +"' AND diagnostic_name = '" +cboTable.getValue()+"';";
				printTable("functional_diagnostics", query);
			}
		}
		if(cboTableName.getValue().equals("Vaccination")) {
			if(cboTable.getValue().equals("All")) {
				query = "SELECT p.first_name,p.last_name,l.vaccination_date,a.vaccination_name FROM vaccinations l JOIN patient p USING (id_patient) JOIN vaccination_types a USING (id_vaccination) WHERE id_patient = '" + patID +"';";
				printTable("vaccinations", query);
			}else {
				query = "SELECT p.first_name,p.last_name,l.vaccination_date,a.vaccination_name FROM vaccinations l JOIN patient p USING (id_patient) JOIN vaccination_types a USING (id_vaccination) WHERE id_patient = '" + patID +"' AND vaccination_name = '" +cboTable.getValue()+"';";
				printTable("vaccinations", query);
			}
		}
		if(cboTableName.getValue().equals("Fluorography")) {
				query = "SELECT first_name,last_name,passing_date FROM fluorography JOIN patient USING (id_patient) WHERE id_patient = '" + patID +"';";
				printTable("fluorography", query);
		}
		patField.clear();
	}
	public static boolean checkPat(TextField docField, TextField patField) {//метод для проверки апциента 
		ArrayList<String> patList = new ArrayList<String>();
		String docID = docField.getText();
		String patId = patField.getText();
		Boolean flag =false;
		//создаем запрос
		String query = "SELECT id_patient FROM appointment  WHERE id_doctor = '" + docID + "' AND appointment_date >= CURDATE();";
		try {
			//создаем утверждение
			Statement st = con.createStatement();
			//выполняем запрос
			ResultSet rs = st.executeQuery(query);
			//заполняем arrayList
			while(rs.next()) {
				patList.add(rs.getString(1));
			}
		}
		catch (SQLException e) {
			System.out.print(e);
		}
		//проверяем есть ли пациент в расписании
		for(String i:patList) {
			if(i.equals(patId))
			{
				flag = true;
				break;
			}
		}
		return flag;
	}
}
