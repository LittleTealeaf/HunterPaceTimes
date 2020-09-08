package Interface;

import Application.Main;
import Settings.Settings;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;

public class OpenPace {

	public static BorderPane getInterface() {
		BorderPane borderPane = new BorderPane();
		borderPane.setLeft(createRecentFiles());


		return borderPane;
	}

	private static Node createRecentFiles() {
		ListView<String> list = new ListView<>();
		list.getItems().addAll(Settings.ApplicationSettings.recentFiles);
		list.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2 && list.getSelectionModel().getSelectedItem() != null) {
				Main.openPace(new File(list.getSelectionModel().getSelectedItem()));
			}
		});

		Text header = new Text("Recently Opened Paces");

		VBox vbox = new VBox(header, list);
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(5));

		return vbox;
	}
}
