package Model;

import java.io.*;
import java.util.Observable;

public class PipeGameModel extends Observable implements GameModel{

    char[][] boardData = { // TEMPORARY STATIC BOARD (need to generate board?)
            {'S', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
            {'L', '7', '1', 'F', '-', '7', '1', '0', '0', '0', '0', '0', '1'},
            {'1', 'I', '1', 'I', '1', 'L', '-', '7', '1', '0', '1', '0', '1'},
            {'1', 'L', '-', 'J', '1', '1', '1', 'I', '1', '0', '1', '0', '1'},
            {'1', '0', '1', '1', '1', '0', '0', 'I', '1', '1', '1', '1', '1'},
            {'1', '0', '1', '0', '0', '0', '1', 'L', '-', '-', '-', '7', '1'},
            {'1', '0', '1', '0', '1', '1', '0', '0', '1', '1', '1', 'I', '1'},
            {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 'G', '1'},
    };



    public String loadLevel(String chosenFile) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File(chosenFile)));
        StringBuilder sb = new StringBuilder();
        String temp=in.readLine();
        int loadedLevelCols = temp.length();
        int loadedLevelRows=0;
        while (temp!=null){
            loadedLevelRows++;
            sb.append(temp);
            sb.append("\n");
            temp=in.readLine();
        }

        return sb.toString();
//        String[][] tempData = new String[loadedLevelRows][loadedLevelCols];
//        for (int i = 0; i < loadedLevelRows; i++) {
//            for (int j = 0; j < loadedLevelCols; j++) {
//                tempData[i][j] = Character.toString(sb.charAt((i * loadedLevelCols) + j));
//            }
//        }
//
//        boardData=tempData;
//        setChanged();
//        notifyObservers("boardDataChanged");
    }

    public void saveLevel(String chosenFile,String board) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(new File(chosenFile)));
        out.write(board);
        out.flush();
        out.close();
    }

    public void loadSettings(){

    }

    public void saveSettings(){

    }

    public char[][] getBoardData() {
        return boardData;
    }


    private String boardDataToString() { // Helper
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < boardData.length; i++) {
            for (int j = 0; j < boardData[i].length; j++) {
                sb.append(boardData[i][j]);
            }
            sb.append(System.lineSeparator());
        }
        sb.deleteCharAt(sb.length()-1); // deletes the last not-needed-line-separator
        return sb.toString();
    }

    private String[][] stringToBoardData(StringBuilder boardString){ // Helper
        return null;
    }
}
