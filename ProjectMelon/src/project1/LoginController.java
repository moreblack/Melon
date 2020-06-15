package project1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.yedam.common.ConnectionUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPassword;
	@FXML
	private Button btnSignin;
	@FXML
	private Button btnSignup;

	/// --
	Connection con = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public LoginController() {
		con = ConnectionUtil.conDB();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		if (con == null) {
			System.out.println("에러");
		} else {
			System.out.println("연결");
		}
	}

	@FXML
	public void handleButtonAction(MouseEvent event) {

		event.getSource();

		if (event.getSource() == btnSignin) {
			// login here
			if (logIn().equals("Success")) {
				try {

					// add you loading or delays - ;-)
					Node node = (Node) event.getSource();
					Stage stage = (Stage) node.getScene().getWindow();
					// stage.setMaximized(true);
					stage.close();
					Scene scene = new Scene(
							FXMLLoader.load(getClass().getResource("/project1/melonPlayerControl.fxml")));
					stage.setScene(scene);
					stage.show();

				} catch (IOException ex) {
					System.err.println(ex.getMessage());
				}

			}

		} else if (event.getSource() == btnSignup) {

			if (signUp().equals("Success")) {
				System.out.println("계성생성완료");

			} else {
				System.out.println("불가");
			}
		}
	}

	private String signUp() {
		String status = "Success";
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		if (username.isEmpty() || password.isEmpty()) {

			status = "Error";
		} else {
			String sql = "insert into melon values(?, ?)";

			try {
				preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
				preparedStatement.executeUpdate();

			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
				status = "Exception";
			}
		}
		return status;
	}

	// we gonna use string to check for status
	private String logIn() {
		String status = "Success";
		String username = txtUsername.getText();
		String password = txtPassword.getText();

		String sql = "SELECT * FROM melon Where username = ? and password = ?";
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				status = "failed";
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			status = "Exception";
		}

		return status;
		
	}
}
