package Interface;

import Classes.Team;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.List;

public class ProgramTeams {

	public static Tab getTab() {
		Tab tab = new Tab("Teams");


		VBox content = new VBox(createTable());


		tab.setContent(content);


		return tab;
	}

	private static TableView<Team> createTable() {
		TableView<Team> table = new TableView<>();
		table.setEditable(false);


		TableColumn<Team, String> cNumber = new TableColumn<>("Team"),
				cDivision = new TableColumn<>("Division"),
				cNames = new TableColumn<>("Riders"),
				cTimes = new TableColumn<>("Times"),
				ctStart = new TableColumn<>("Start"),
				ctFinish = new TableColumn<>("Finish"),
				ctElapsed = new TableColumn<>("Elapsed"),
				cNotes = new TableColumn<>("Notes");

		//Custom Cell Displays:
		cNames.setCellFactory(c -> {
			return getNamesCell();
		});

		//Assign Columns to variables
		cNumber.setCellValueFactory(new PropertyValueFactory<Team, String>("team"));
		cDivision.setCellValueFactory(new PropertyValueFactory<Team, String>("division"));
		cNames.setCellValueFactory(new PropertyValueFactory<Team, String>("namesAsString"));
		ctStart.setCellValueFactory(new PropertyValueFactory<Team, String>("startAsString"));
		ctFinish.setCellValueFactory(new PropertyValueFactory<Team, String>("finishAsString"));
		ctElapsed.setCellValueFactory(new PropertyValueFactory<Team, String>("elapsedAsString"));
		//cNotes.setCellValueFactory(new PropertyValueFactory<Team,String>("notesAsString"));

		cTimes.getColumns().addAll(ctStart, ctFinish, ctElapsed);

		for (TableColumn col : new TableColumn[]{cDivision, cNames, cTimes, ctStart, ctFinish, ctElapsed, cNotes}) {
			col.setEditable(false);
			col.setReorderable(false);
		}

		//Set all columns to not be reordable or editable

		table.getColumns().addAll(cNumber, cDivision, cNames, cTimes, cNotes);

		return table;
	}

	public static TableCell<Team, String> getNamesCell() {
		return new TableCell<Team, String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty) {
					setGraphic(null);
				} else {
					VBox vbox = new VBox();
					List<String> textList = Arrays.asList(item.split(", "));
					for (int i = 0; i < textList.size(); i++) {
						Text lbl = new Text(textList.get(i));
						vbox.getChildren().add(lbl);
					}
					setGraphic(vbox);
				}
			}
		};
	}

}
