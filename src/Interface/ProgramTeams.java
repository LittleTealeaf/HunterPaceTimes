package Interface;

import Classes.Team;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ProgramTeams {

	public static TableView table;

	/**
	 * @return Tab to display
	 */
	public static Tab getTab() {
		Tab tab = new Tab("Teams");

		table = createTable();
		updateTable();

		VBox content = new VBox(table);

		tab.setContent(content);


		return tab;
	}

	/**
	 * Creates the table to list all riders
	 * <p>
	 * The table contains the following columns: <i>Team, Division, Riders, Times (Start, Finish, Elapsed), and
	 * Notes</i>. Each one has a display type factor of {@link String}. <br>Each column cannot be reordered or edited.
	 * </p>
	 * <p>
	 * Double clicking on a team will open up an editing window, allowing the user the edit the details of that
	 * team.
	 * </p>
	 *
	 * @return Table with riders of the pace
	 */
	private static TableView<Team> createTable() {
		TableView<Team> table = new TableView<>();
		table.setEditable(false);

		table.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2 && table.getSelectionModel().getSelectedIndex() != -1) {
				new ProgramEditTeam(table.getSelectionModel().getSelectedItem());
			}
		});

		TableColumn<Team, String> cNumber = new TableColumn<>("Team"),
				cDivision = new TableColumn<>("Division"),
				cNames = new TableColumn<>("Riders"),
				cTimes = new TableColumn<>("Times"),
				ctStart = new TableColumn<>("Start"),
				ctFinish = new TableColumn<>("Finish"),
				ctElapsed = new TableColumn<>("Elapsed"),
				cNotes = new TableColumn<>("Notes");

		//Assign Columns to variables
		cNumber.setCellValueFactory(new PropertyValueFactory<Team, String>("teamNumber"));
		cDivision.setCellValueFactory(new PropertyValueFactory<Team, String>("division"));
		cNames.setCellValueFactory(new PropertyValueFactory<Team, String>("namesAsString"));
		ctStart.setCellValueFactory(new PropertyValueFactory<Team, String>("startAsString"));
		ctFinish.setCellValueFactory(new PropertyValueFactory<Team, String>("finishAsString"));
		ctElapsed.setCellValueFactory(new PropertyValueFactory<Team, String>("elapsedAsString"));
		cNotes.setCellValueFactory(new PropertyValueFactory<Team, String>("notesFirstLine"));

		cTimes.getColumns().addAll(ctStart, ctFinish, ctElapsed);

		for (TableColumn col : new TableColumn[]{cDivision, cNames, cTimes, ctStart, ctFinish, ctElapsed, cNotes}) {
			col.setEditable(false);
			col.setReorderable(false);
		}

		//Set all columns to not be reordable or editable

		table.getColumns().addAll(cNumber, cDivision, cNames, cTimes, cNotes);


		return table;
	}

	public static void updateTable() {
		table.getItems().clear();
		table.getItems().addAll(Program.pace.getTeams());

	}

}
