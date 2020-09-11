package Interface;

import Application.Main;
import Application.Settings;
import Classes.Pace;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;

public class Welcome {
	private static final BorderPane contentPane = new BorderPane();

	public static BorderPane getInterface() {

		contentPane.setPadding(new Insets(10));
		contentPane.setLeft(recentFileList());
		contentPane.setCenter(centerInterface());
		contentPane.setBottom(programInformation());


		return contentPane;
	}

	private static Node recentFileList() {

		//Header
		Text header = new Text("Recent Files");
		header.setFont(new Font(20));
		header.setTextAlignment(TextAlignment.CENTER);

		//Node containing each entry
		VBox list = new VBox(header);

		//Each recent file entry
		for (int i = 0; i < Settings.recentFiles.size(); i++) {
			String iniPath = Settings.recentFiles.get(i);
			File file = new File(iniPath);

			Text name = new Text(file.getName());
			name.setFont(new Font(15));
			name.setOnMouseClicked(e -> {
				if (e.getClickCount() == 2) Main.openPace(Pace.fromFile(file));
			});

			Text path = new Text(file.getPath());
			path.setFont(new Font(10));
			path.setOnMouseClicked(e -> {
				if (e.getClickCount() == 2) Main.openPace(Pace.fromFile(file));
			});

			Button remove = new Button("X");
			remove.setOnAction(e -> {
				Settings.recentFiles.remove(iniPath);
				Settings.save();
				contentPane.setLeft(recentFileList());
			});


			Separator separator = new Separator();
			separator.setOrientation(Orientation.HORIZONTAL);

			GridPane display = new GridPane();
			display.add(name, 0, 0);
			display.add(path, 0, 1, 2, 1);
			display.add(remove, 2, 0, 1, 2);
			display.setHgap(5);

			list.getChildren().addAll(separator, display);
		}

		//Add the table borders
		BorderPane content = new BorderPane();
		content.setCenter(list);
		Separator sepRight = new Separator();
		sepRight.setOrientation(Orientation.VERTICAL);
		content.setRight(sepRight);

		content.setPrefWidth(200);

		return content;
	}

	private static Node centerInterface() {
		BorderPane content = new BorderPane();

		Text title = new Text("Hunter Pace Time Manager");
		title.setFont(new Font(30));
		title.setTextAlignment(TextAlignment.CENTER);
		content.setTop(title);

		GridPane optionContent = new GridPane();
		optionContent.setVgap(10);

		Button create = new Button("Create a new Pace");
		create.setOnAction(e -> {
			Main.openPace(new Pace());
		});
		optionContent.add(create, 0, 0);

		Button open = new Button("Open a .pace file");
		open.setOnAction(e -> {
			Pace pace = Pace.openPaceFile();
			if (pace != null) {
				Main.openPace(pace);
			}
		});
		optionContent.add(open, 0, 1);

		content.setCenter(optionContent);

		return content;
	}

	private static Node programInformation() {
		Text version = new Text("Version " + Main.version);

		HBox information = new HBox(version);

		Separator separator = new Separator();
		separator.setOrientation(Orientation.HORIZONTAL);

		return new VBox(separator, information);
	}
}
