package project1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class melonPlayerController implements Initializable {
	@FXML
	private Button btn1;
	@FXML
	private Button btn2;
	@FXML
	private Button btn3;
	@FXML
	private Button btn4;
	@FXML
	private Button btn5;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Label label = new Label();
		label.setOnMouseClicked((evt) -> {

		});

		btn1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleBtnAction(event);

			}
		});
		btn2.setOnAction(event -> handleBtnAction(event));
		btn3.setOnAction(event -> handleBtnAction(event));
		btn4.setOnAction(event -> handleBtnAction(event));
		btn5.setOnAction(event -> handleLogoutAciton(event));
	}

	private void handleLogoutAciton(ActionEvent event) {
		Button sourceBtn = (Button) event.getSource();
		if (sourceBtn.getId().equals("btn5")) {
			Stage stage = (Stage) sourceBtn.getScene().getWindow();
			stage.close();

			try {
				Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../project1/Login.fxml")));
				stage.setScene(scene);
				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void handleBtnAction(ActionEvent event) {
		String jpgfile = "";
		String res = "";
		if (event.getSource() == btn1) {
			jpgfile = "/image/iu1.jpg";
			res = "/music/goodday.mp3";
		} else if (event.getSource() == btn2) {
			jpgfile = "/image/changmo.jpg";
			res = "/music/meteor.mp3";
		} else if (event.getSource() == btn3) {
			jpgfile = "/image/billie.jpg";
			res = "/music/badguy.mp3";
		} else if (event.getSource() == btn4) {
			jpgfile = "/image/twice.png";
			res = "/music/knock.mp3";
		}

		Stage addStage = new Stage(StageStyle.UTILITY);
		addStage.initModality(Modality.WINDOW_MODAL);
		addStage.initOwner(btn1.getScene().getWindow());

		Parent parent;
		try {
//			parent = FXMLLoader.load(getClass().getResource("melonControl.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("melonControl.fxml"));
			parent = loader.load();
			MelonController controller = loader.getController();
			controller.setResource(res);

			ImageView image = (ImageView) parent.lookup("#imageView");
			image.setImage(new Image(jpgfile));

			Scene scene = new Scene(parent);
			addStage.setScene(scene);
			addStage.setResizable(false);
			addStage.show();

			addStage.setOnHidden((evt) -> {
				controller.mediaStop();
			});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
