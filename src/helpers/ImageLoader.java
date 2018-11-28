package helpers;

import javafx.scene.image.Image;

public class ImageLoader {

	private static ImageLoader instance = null;
	
	private ImageLoader() {}

	public static ImageLoader getInstance() {
		if (instance == null) {
			instance = new ImageLoader();
		}
		return instance;
	}
	
	public Image loadImage(String imagePath) {
		
//		return new Image(getClass().getClassLoader().getResourceAsStream("../resources/" + imagePath));
		return new Image(getClass().getResourceAsStream("../resources/" + imagePath));
		
	}
}
