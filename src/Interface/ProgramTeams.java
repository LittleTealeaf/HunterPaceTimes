package Interface;

import Classes.Team;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ProgramTeams {

	public static TableView<Team> table;
	public static Text lastOut = new Text();
	public static Text averageTime = new Text();
	public static Text estimatedLastBack = new Text();

	/**
	 * @return Tab to display
	 */
	public static Tab getTab() {
		Tab tab = new Tab("Teams");

		table = createTable();
		updateTable();

		VBox content = new VBox(table, additionalInfo());

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
		cNumber.setCellValueFactory(new PropertyValueFactory<>("teamNumber"));
		cDivision.setCellValueFactory(new PropertyValueFactory<>("division"));
		cNames.setCellValueFactory(new PropertyValueFactory<>("namesAsString"));
		ctStart.setCellValueFactory(new PropertyValueFactory<>("startAsString"));
		ctFinish.setCellValueFactory(new PropertyValueFactory<>("finishAsString"));
		ctElapsed.setCellValueFactory(new PropertyValueFactory<>("elapsedAsString"));
		cNotes.setCellValueFactory(new PropertyValueFactory<>("notesFirstLine"));

		cTimes.getColumns().addAll(ctStart, ctFinish, ctElapsed);

		for (TableColumn<Team, String> col : new TableColumn[]{cDivision, cNames, cTimes, ctStart, ctFinish, ctElapsed,
				cNotes}) {
			col.setEditable(false);
			col.setReorderable(false);
		}

		//Set all columns to not be reordable or editable

		table.getColumns().addAll(cNumber, cDivision, cNames, cTimes, cNotes);


		return table;
	}

	/**
	 * Updates the table with the current pace's teams
	 */
	public static void updateTable() {
		table.getItems().clear();
		table.getItems().addAll(Program.pace.getTeams());

	}

	private static Node additionalInfo() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10));
		grid.setHgap(7.5);
		grid.setVgap(7.5);

		Button bCreate = new Button("New Team");
		bCreate.setOnAction(e -> new ProgramEditTeam(null));
		grid.add(bCreate, 0, 0);

		Button bStreamline = new Button("Mass Import");
		grid.add(bStreamline, 1, 0);


		return grid;
	}

}
