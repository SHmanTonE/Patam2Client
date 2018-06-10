package Run;

import Model.PipeGameModel;
import View.MainWindowViewController;
import ViewModel.PipeGameViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        PipeGameModel model = new PipeGameModel();
        PipeGameViewModel viewModel = new PipeGameViewModel(model);
        FXMLLoader fxml = new FXMLLoader();

        Parent root = fxml.load(getClass().getResource("/View/MainWindowView.fxml").openStream());
        MainWindowViewController controller = fxml.getController();
        controller.setViewModel(viewModel);
        model.addObserver(viewModel);
        viewModel.addObserver(controller);


        primaryStage.setTitle("Pipe Game");
        primaryStage.setScene(new Scene(root, 600, 450));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
