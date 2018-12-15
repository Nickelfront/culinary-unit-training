package helpers;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ImageLoader {

	private static ImageLoader instance = null;

	private ImageLoader() {
	}

	public static ImageLoader getInstance() {
		if (instance == null) {
			instance = new ImageLoader();
		}
		return instance;
	}

	public Image loadImage(String imagePath) {
		return new Image(getClass().getResourceAsStream("../resources/" + imagePath));
	}
	
	public void setIcon(String imagePath, Stage stage) {
		Image icon = this.loadImage(imagePath);
		stage.getIcons().add(icon);
	}
}
