package Interface;

import Classes.Pace;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class Program {

	public static Pace pace;

	public static Node getInterface(Pace oPace) {
		pace = oPace;


		BorderPane content = new BorderPane();


		return content;
	}
}
