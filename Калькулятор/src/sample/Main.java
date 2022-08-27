package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class Main extends Application {
    public static TextField textFieldFirst = new TextField("");
    public static TextField textFieldSecond = new TextField("");
    public static BigDecimal firstNumber = new BigDecimal("0");
    public static BigDecimal secondNumber = new BigDecimal("0");
    public static Boolean flagNumber = false;
    public static String action = "";

    @Override
    public void start(Stage primaryStage) throws Exception {
        textCustomize();
        Group root = new Group();
        //кнопки
        Button btSwap = new Button("SWAP");
        btSwap.setPrefSize(80, 55);
        btSwap.setLayoutX(90);
        btSwap.setLayoutY(115);
        btSwap.setFont(Font.font(18));
        btSwap.setOnAction(e -> {
            if (textFieldSecond.getText().indexOf("=") == -1)
                swap();
        });
        Button btC = new Button("C");
        btC.setPrefSize(80, 55);
        btC.setLayoutX(175);
        btC.setLayoutY(115);
        btC.setFont(Font.font(20));
        btC.setOnAction(e -> clear());
        Button btDiv = new Button("/");
        btDiv.setPrefSize(80, 55);
        btDiv.setLayoutX(260);
        btDiv.setLayoutY(115);
        btDiv.setFont(Font.font(20));
        btDiv.setOnAction(e -> {
            action(btDiv);
        });
        Button bt7 = new Button("7");
        bt7.setPrefSize(80, 55);
        bt7.setLayoutX(5);
        bt7.setLayoutY(175);
        bt7.setFont(Font.font(20));
        bt7.setOnAction(e -> {
            addNumber(bt7);
        });
        Button bt8 = new Button("8");
        bt8.setPrefSize(80, 55);
        bt8.setLayoutX(90);
        bt8.setLayoutY(175);
        bt8.setFont(Font.font(20));
        bt8.setOnAction(e -> {
            addNumber(bt8);
        });
        Button bt9 = new Button("9");
        bt9.setPrefSize(80, 55);
        bt9.setLayoutX(175);
        bt9.setLayoutY(175);
        bt9.setFont(Font.font(20));
        bt9.setOnAction(e -> {
            addNumber(bt9);
        });
        Button btMulti = new Button("*");
        btMulti.setPrefSize(80, 55);
        btMulti.setLayoutX(260);
        btMulti.setLayoutY(175);
        btMulti.setFont(Font.font(20));
        btMulti.setOnAction(e -> {
            action(btMulti);
        });
        Button bt4 = new Button("4");
        bt4.setPrefSize(80, 55);
        bt4.setLayoutX(5);
        bt4.setLayoutY(235);
        bt4.setFont(Font.font(20));
        bt4.setOnAction(e -> {
            addNumber(bt4);
        });
        Button bt5 = new Button("5");
        bt5.setPrefSize(80, 55);
        bt5.setLayoutX(90);
        bt5.setLayoutY(235);
        bt5.setFont(Font.font(20));
        bt5.setOnAction(e -> {
            addNumber(bt5);
        });
        Button bt6 = new Button("6");
        bt6.setPrefSize(80, 55);
        bt6.setLayoutX(175);
        bt6.setLayoutY(235);
        bt6.setFont(Font.font(20));
        bt6.setOnAction(e -> {
            addNumber(bt6);
        });
        Button btMin = new Button("-");
        btMin.setPrefSize(80, 55);
        btMin.setLayoutX(260);
        btMin.setLayoutY(235);
        btMin.setFont(Font.font(20));
        btMin.setOnAction(e -> {
            action(btMin);
        });
        Button bt1 = new Button("1");
        bt1.setPrefSize(80, 55);
        bt1.setLayoutX(5);
        bt1.setLayoutY(295);
        bt1.setFont(Font.font(20));
        bt1.setOnAction(e -> {
            addNumber(bt1);
        });
        Button bt2 = new Button("2");
        bt2.setPrefSize(80, 55);
        bt2.setLayoutX(90);
        bt2.setLayoutY(295);
        bt2.setFont(Font.font(20));
        bt2.setOnAction(e -> {
            addNumber(bt2);
        });
        Button bt3 = new Button("3");
        bt3.setPrefSize(80, 55);
        bt3.setLayoutX(175);
        bt3.setLayoutY(295);
        bt3.setFont(Font.font(20));
        bt3.setOnAction(e -> {
            addNumber(bt3);
        });
        Button btPlus = new Button("+");
        btPlus.setPrefSize(80, 55);
        btPlus.setLayoutX(260);
        btPlus.setLayoutY(295);
        btPlus.setFont(Font.font(20));
        btPlus.setOnAction(e -> {
            /*if ((textFieldSecond.getText().indexOf(action, 1) > 0) && (action.length() != 0))
                equals();
            else*/
            action(btPlus);
        });
        Button btNeg = new Button("+/-");
        btNeg.setPrefSize(80, 55);
        btNeg.setLayoutX(5);
        btNeg.setLayoutY(355);
        btNeg.setFont(Font.font(20));
        btNeg.setOnAction(e -> {
            negativ();
        });
        Button bt0 = new Button("0");
        bt0.setPrefSize(80, 55);
        bt0.setLayoutX(90);
        bt0.setLayoutY(355);
        bt0.setFont(Font.font(20));
        bt0.setOnAction(e -> {
            addNumber(bt0);
        });
        Button btPoint = new Button(".");
        btPoint.setPrefSize(80, 55);
        btPoint.setLayoutX(175);
        btPoint.setLayoutY(355);
        btPoint.setFont(Font.font(20));
        btPoint.setOnAction(e -> {
            addPoint();

        });
        Button btEqul = new Button("=");
        btEqul.setPrefSize(80, 55);
        btEqul.setLayoutX(260);
        btEqul.setLayoutY(355);
        btEqul.setFont(Font.font(20));
        btEqul.setOnAction(e -> {
            equals();
        });
        root.getChildren().addAll(textFieldFirst, textFieldSecond, btSwap, btC, btDiv, bt7, bt8, bt9, btMulti, bt4, bt5, bt6, btMin, bt1, bt2, bt3, btPlus, btNeg, bt0, btPoint, btEqul);

        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root, 345, 415));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void textCustomize() {
        textFieldSecond.setLayoutX(0);
        textFieldSecond.setLayoutY(0);
        textFieldSecond.setEditable(false);
        //textFieldSecond.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        textFieldSecond.setPrefColumnCount(13);
        textFieldSecond.setFont(Font.font(25));
        textFieldSecond.setStyle("-fx-background-color: #fff; -fx-border-color: #fff; -fx-border-width: 0; -fx-border-image-width: 0; -fx-background-image: null; -fx-region-background: null;-fx-border-insets: 0; -fx-background-size:0; -fx-border-image-insets:0;");

        textFieldFirst.setLayoutX(0);
        textFieldFirst.setLayoutY(45);
        textFieldFirst.setEditable(false);
        //textFieldFirst.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        textFieldFirst.setPrefColumnCount(9);
        textFieldFirst.setFont(Font.font(35));
        textFieldFirst.setStyle("-fx-background-color: #fff; -fx-border-color: #fff; -fx-border-width: 0; -fx-border-image-width: 0; -fx-background-image: null; -fx-region-background: null;-fx-border-insets: 0; -fx-background-size:0; -fx-border-image-insets:0;");
    }

    public static void clear() {
        textFieldSecond.clear();
        textFieldFirst.clear();
        firstNumber = firstNumber.multiply(BigDecimal.valueOf(0));
        secondNumber = secondNumber.multiply(BigDecimal.valueOf(0));
        action = "";
    }

    public static void addNumber(Button bt) {
        if (flagNumber) {
            textFieldFirst.clear();
            flagNumber = false;
        }
        textFieldFirst.setText(textFieldFirst.getText() + bt.getText());
    }

    public static void addPoint() {
        if (textFieldFirst.getText().indexOf(".") == -1) {
            if (flagNumber) {
                textFieldFirst.clear();
                flagNumber = false;
            }
            textFieldFirst.setText(textFieldFirst.getText() + ".");
        }
    }

    public static void negativ() {
        if (textFieldFirst.getText().indexOf("-") == -1)
            textFieldFirst.setText("-" + textFieldFirst.getText());
        else
            textFieldFirst.setText(textFieldFirst.getText().substring(1));
    }

    public static void action(Button bt) {
        if (textFieldFirst.getText().length() != 0 && textFieldSecond.getText().indexOf("=") == -1) {
            equals();
        }
        if (textFieldFirst.getText().length() == 0)
            textFieldSecond.setText(secondNumber.toString() + bt.getText());
        else {
            try {
                secondNumber = new BigDecimal(textFieldFirst.getText());
            }
            catch (Exception e){
                secondNumber = new BigDecimal("0");
            }
            textFieldSecond.setText(secondNumber.toString() + bt.getText());
        }
        action = bt.getText();
        textFieldFirst.clear();
    }

    public static void equals() {
        flagNumber = true;
        if (textFieldFirst.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Вы не ввели число");
            alert.showAndWait();
        } else {
            if (textFieldSecond.getText().indexOf("=") != -1 && action.length() != 0) {
                try {
                    secondNumber = new BigDecimal(textFieldFirst.getText());
                }
                catch(Exception e){
                    secondNumber = new BigDecimal("0");
                }
                try {
                    firstNumber = new BigDecimal(textFieldSecond.getText(textFieldSecond.getText().indexOf(action, 1) + 1, textFieldSecond.getText().indexOf("=")));
                }
                catch (Exception e){
                    firstNumber = new BigDecimal("0");
                }
                textFieldSecond.setText(textFieldFirst.getText() + action + firstNumber.toString() + "=");
            } else {
                if (action.length() == 0)
                    textFieldSecond.clear();
                try {
                    firstNumber = new BigDecimal(textFieldFirst.getText());
                }
                catch (Exception e){
                    firstNumber = new BigDecimal("0");
                }
                textFieldSecond.setText(textFieldSecond.getText() + textFieldFirst.getText() + "=");
            }

            switch (action) {
                case "+":
                    firstNumber = secondNumber.add(firstNumber);
                    textFieldFirst.setText(firstNumber.toString());
                    break;
                case "-":
                    firstNumber = secondNumber.subtract(firstNumber);
                    textFieldFirst.setText(firstNumber.toString());
                    break;
                case "*":
                    firstNumber = secondNumber.multiply(firstNumber);
                    textFieldFirst.setText(firstNumber.toString());
                    break;
                case "/":
                    if (firstNumber.doubleValue() == 0.0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Деление на ноль");
                        alert.showAndWait();
                        clear();
                        break;
                    } else {
                        firstNumber = secondNumber.divide(firstNumber, 6, BigDecimal.ROUND_DOWN);
                        textFieldFirst.setText(firstNumber.toString());
                        break;
                    }
            }
        }
    }

    public static void swap() {
//        try {
//            firstNumber = new BigDecimal(textFieldFirst.getText());
//        } catch (Exception e) {
//            firstNumber = new BigDecimal("0");
//        }
        if (textFieldFirst.getText().length() != 0 && textFieldSecond.getText().length() != 0) {
            String tmp = textFieldFirst.getText();
            textFieldFirst.setText(textFieldSecond.getText(0, textFieldSecond.getText().length() - 1));
            textFieldSecond.setText(tmp + action);
            firstNumber = new BigDecimal(textFieldFirst.getText());
            secondNumber = new BigDecimal(textFieldSecond.getText(0,textFieldSecond.getText().length()-1));
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            //alert.setContentText("");
            alert.showAndWait();
            clear();
        }
    }
}
