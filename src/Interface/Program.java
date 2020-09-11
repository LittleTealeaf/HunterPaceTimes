package Interface;

import Classes.Pace;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class Program {

	public static Pace pace;

	public static Scene getInterface(Pace oPace) {

		pace = oPace;

		BorderPane content = new BorderPane();

		TabPane tabPane = new TabPane();
		tabPane.getTabs().addAll(ProgramTeams.getTab());
		for (Tab tab : tabPane.getTabs()) {
			tab.setClosable(false);
			tab.getContent().autosize();
		}

		content.setCenter(tabPane);

		Scene scene = new Scene(content);
		return scene;
	}
}
