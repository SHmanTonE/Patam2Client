package ViewModel;

import Model.PipeGameModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Observable;

import java.util.Observer;

public class PipeGameViewModel extends Observable implements Observer {

    PipeGameModel pipeGameModel;

    StringProperty savedFileName;
    StringProperty loadedFileName;

    StringProperty board;
    StringProperty step;

    //ObservableList<String[]> boardDataList = FXCollections.observableArrayList();

    //c'tor
    public PipeGameViewModel(PipeGameModel pipeGameModel){
        this.pipeGameModel=pipeGameModel;
        this.board = new SimpleStringProperty();
        this.step = new SimpleStringProperty();
        savedFileName = new SimpleStringProperty();
        loadedFileName = new SimpleStringProperty();
        savedFileName.addListener((observableValue, s, t1) ->{
            try {
                pipeGameModel.saveLevel(savedFileName.get(),board.get());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        loadedFileName = new SimpleStringProperty();
        loadedFileName.addListener((observableValue, s, t1) -> {
            try {
                boardProperty().set(pipeGameModel.loadLevel(loadedFileName.get()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

//    public void loadLevel() throws IOException {
//       // pipeGameModel.loadLevel(chosenFile);
//        FileChooser fc = new FileChooser();
//        fc.setTitle("Open Pipe Game Level");
//        fc.setInitialDirectory(new File("./Levels"));
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text File","*.txt","*.text");
//        fc.getExtensionFilters().add(extFilter);
//        File chosenFile = fc.showOpenDialog(null);
//        if (chosenFile!=null) {
//            pipeGameModel.loadLevel(chosenFile);
//        }
//
//    }

    public char[][] getBoardData(){
        return pipeGameModel.getBoardData();
    }


    @Override
    public void update(Observable observable, Object o) {
        if (observable==pipeGameModel){
            if(o=="boardDataChanged"){
                System.out.println("update");
                for (int i = 0; i < getBoardData().length; i++) {
                    for (int j = 0; j < getBoardData()[0].length; j++) {
                        System.out.print(getBoardData()[i][j]);
                    }
                    System.out.println();
                }

                //boardDataList.addAll(Arrays.asList(getBoardData()));
            }

        }

    }

    public String getSavedFileName() {
        return savedFileName.get();
    }

    public StringProperty savedFileNameProperty() {
        return savedFileName;
    }

    public String getBoard() {
        return board.get();
    }

    public StringProperty boardProperty() {
        return board;
    }

    public String getStep() {
        return step.get();
    }

    public StringProperty stepProperty() {
        return step;
    }

    public String getLoadedFileName() {
        return loadedFileName.get();
    }

    public StringProperty loadedFileNameProperty() {
        return loadedFileName;
    }

}
