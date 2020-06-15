package project1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class melonController2 implements Initializable {
	@FXML
	private MediaView mediaView;
	@FXML
	private Label labelTime;
	@FXML
	private Slider sliderVolume;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private ProgressIndicator progressIndicator;
	@FXML
	private ImageView imageView;
	@FXML
	private Button btnPlay;
	@FXML
	private Button btnPause;
	@FXML
	private Button btnStop;

	private boolean endOfMedia;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Media media = new Media(getClass().getResource("/music/meteor.mp3").toString());

		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);

		mediaPlayer.setOnReady(new Runnable() {

			@Override
			public void run() {

				mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {

					@Override
					public void changed(ObservableValue<? extends Duration> observable, Duration oldVal,
							Duration newVal) {
						double progress = mediaPlayer.getCurrentTime().toSeconds()
								/ mediaPlayer.getTotalDuration().toSeconds();
						progressBar.setProgress(progress);
						progressIndicator.setProgress(progress);
						labelTime.setText((int) mediaPlayer.getCurrentTime().toSeconds() + "/"
								+ (int) mediaPlayer.getTotalDuration().toSeconds() + " sec");

					}
				});

				btnPlay.setDisable(false);
				btnPause.setDisable(true);
				btnStop.setDisable(true);

			}

		});
		sliderVolume.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				mediaPlayer.setVolume(sliderVolume.getValue()/ 100.0);
			}
		});
		sliderVolume.setValue(50.0);
		
		
		mediaPlayer.setOnPlaying(() -> {
			btnPlay.setDisable(true);
			btnPause.setDisable(false);
			btnStop.setDisable(false);
		});
		mediaPlayer.setOnPaused(() -> {
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(false);
		});

		mediaPlayer.setOnEndOfMedia(() -> {
			progressBar.setProgress(1.0);
			progressIndicator.setProgress(1.0);
			endOfMedia = true;

			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(true);
		});
		mediaPlayer.setOnStopped(() -> {
			btnPlay.setDisable(false);
			btnPause.setDisable(true);
			btnStop.setDisable(true);
		});

		btnPlay.setOnAction(event -> {
			if (endOfMedia) {
				mediaPlayer.stop();
				mediaPlayer.seek(mediaPlayer.getStartTime());
			}
			mediaPlayer.play();
			endOfMedia = false;
		});
		btnPause.setOnAction(event -> mediaPlayer.pause());
		btnStop.setOnAction(event -> mediaPlayer.stop());

	}

}
