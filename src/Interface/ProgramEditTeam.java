package Interface;

import Classes.Team;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProgramEditTeam {

	private final Team team;
	private final Stage stage;

	public ProgramEditTeam(Team t) {
		this.team = t != null ? t : new Team("");

		stage = new Stage();
		stage.setWidth(500);

		GridPane content = new GridPane();
		content.setVgap(7.5);
		content.setHgap(7.5);

		Text lNumber = new Text("Team Number:");
		content.add(lNumber, 0, 1);

		TextField tNumber = new TextField();
		tNumber.setText(team.getTeamNumber());
		content.add(tNumber, 1, 1);

		Text lDivision = new Text("Division");
		content.add(lDivision, 0, 2);

		TextField tDivision = new TextField();
		tDivision.setText(team.getDivision());
		content.add(tDivision, 1, 2);

		Text lNames = new Text("Riders");
		content.add(lNames, 2, 0, 2, 1);

		TextArea tNames = new TextArea();
		tNames.setText(team.getNamesAsString());
		content.add(tNames, 2, 1, 2, 3);

		content.setPadding(new Insets(10));

		Button bSave = new Button("Save");
		bSave.setOnAction(e -> {
			team.setTeamNumber(tNumber.getText());
			team.setDivision(tDivision.getText());
			team.setNames(tNames.getText());
			ProgramTeams.updateTable();
			stage.close();
		});
		content.add(bSave, 5, 5);


		stage.setScene(new Scene(content));
		stage.show();

	}
}
