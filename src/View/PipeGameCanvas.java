package View;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class PipeGameCanvas extends Canvas {

    char[][] boardData;
    int colLength;
    int rowLength;

    public void setBoardData(char[][] boardData){
        this.boardData=boardData;
        colLength=boardData[0].length;
        rowLength=boardData.length;
        redraw();
    }

    public void setBoardData(String tempBoardData){
        colLength = tempBoardData.indexOf("\n");
        rowLength = tempBoardData.split("\n").length;

        tempBoardData=tempBoardData.replaceAll("\n","");
        System.out.println(colLength+" - "+rowLength);
        boardData = new char[rowLength][colLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength ; j++) {
                boardData[i][j] = tempBoardData.charAt((i * colLength) + j);
            }
        }
        redraw();
    }

    public void redraw(){
        printBoard();
        System.out.println("COL: "+colLength);
        System.out.println("ROW: "+rowLength);
        double PipeSliceWidth=getWidth()/colLength;
        double PipeSliceHeight=getHeight()/rowLength;
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0,0, getWidth(), getHeight()); // clears all
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if (boardData[i][j]=='1')
                    gc.fillRect(PipeSliceWidth*j, PipeSliceHeight*i,PipeSliceWidth,PipeSliceHeight);
                else if (boardData[i][j]=='0')
                    gc.clearRect(PipeSliceWidth*j, PipeSliceHeight*i,PipeSliceWidth,PipeSliceHeight);
                else
                    gc.fillText(Character.toString(boardData[i][j]),PipeSliceWidth*j+15, PipeSliceHeight*i+30, PipeSliceWidth);
            }
        }
    }

    private void printBoard(){
        System.out.println("Board: ");
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                System.out.print(boardData[i][j]);
            }
            System.out.println();
        }
    }

}
