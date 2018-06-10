package View;

import ViewModel.PipeGameViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MainWindowViewController implements Initializable, Observer {

    char[][] initialBoardData = { // TEMPORARY STATIC BOARD (need to generate board?)
            {'S', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
            {'L', '7', '1', 'F', '-', '7', '1', '0', '0', '0', '0', '0', '1'},
            {'1', 'I', '1', 'I', '1', 'L', '-', '7', '1', '0', '1', '0', '1'},
            {'1', 'L', '-', 'J', '1', '1', '1', 'I', '1', '0', '1', '0', '1'},
            {'1', '0', '1', '1', '1', '0', '0', 'I', '1', '1', '1', '1', '1'},
            {'1', '0', '1', '0', '0', '0', '1', 'L', '-', '-', '-', '7', '1'},
            {'1', '0', '1', '0', '1', '1', '0', '0', '1', '1', '1', 'I', '1'},
            {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 'G', '1'},
    };

    PipeGameViewModel pipeGameViewModel;

    StringProperty savedFileName;
    StringProperty loadedFileName;

    StringProperty board;
    StringProperty step;

    @FXML
    PipeGameCanvas pipeGameCanvas;

    public void setViewModel(PipeGameViewModel pipeGameViewModel) {
        this.pipeGameViewModel = pipeGameViewModel;
        this.pipeGameViewModel.savedFileNameProperty().bind(savedFileName);
        this.pipeGameViewModel.loadedFileNameProperty().bind(loadedFileName);
        this.pipeGameViewModel.boardProperty().bindBidirectional(board);
        board.addListener((observableValue, s, t1) -> {
            System.out.println(board.get());
            pipeGameCanvas.setBoardData(board.get());

        });
        step.bind(this.pipeGameViewModel.stepProperty());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pipeGameCanvas.setBoardData(initialBoardData); // TODO: need to generate board?
        savedFileName = new SimpleStringProperty();
        loadedFileName = new SimpleStringProperty();
        board = new SimpleStringProperty();
        step = new SimpleStringProperty();

    }

    @FXML
    public void saveLevel() throws IOException { // creates default "Levels" folder if not existed, saves <levelname>.txt
        File levelsDirectory = new File("./Levels");
        if (!levelsDirectory.isDirectory()) { // if there is no Levels dir, make one
            levelsDirectory.mkdir();
        }
        FileChooser fc = new FileChooser();
        fc.setTitle("Save Pipe Game Level");
        fc.setInitialDirectory(levelsDirectory);
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text File", "*.txt", "*.text");
        fc.getExtensionFilters().add(filter);
        File chosenFile = fc.showSaveDialog(null); // TODO: fix ownerWindow
        if (chosenFile != null) { // if a file has been saved
            savedFileName.set(chosenFile.toPath().toString());
        }
    }

    public void loadLevel() throws IOException {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Pipe Game Level");
        fc.setInitialDirectory(new File("./Levels"));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text File", "*.txt", "*.text");
        fc.getExtensionFilters().add(extFilter);
        File chosenFile = fc.showOpenDialog(null);
        if (chosenFile != null) {
            loadedFileName.set(chosenFile.toPath().toString());
        }

    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable == pipeGameViewModel) {

        }
    }


}