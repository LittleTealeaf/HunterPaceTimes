package Settings;

import javafx.stage.Stage;

public class StagePreferences {

	private boolean maximized = false;
	private Double width;
	private Double height;

	public StagePreferences() {

	}

	public void applyPreferences(Stage stage) {
		stage.setMaximized(maximized);
		stage.maximizedProperty().addListener((e, o, n) -> {
			maximized = n.booleanValue();
			Settings.save();
		});

		if (width == null || height == null) {
			width = stage.getWidth();
			height = stage.getHeight();
			Settings.save();
		} else {
			stage.setWidth(width);
			stage.setHeight(height);
		}
		stage.widthProperty().addListener((e, o, n) -> {
			width = n.doubleValue();
			Settings.save();
		});
		stage.heightProperty().addListener((e, o, n) -> {
			height = n.doubleValue();
			Settings.save();
		});


	}
}
