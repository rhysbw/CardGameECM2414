import java.util.ArrayList;

public class Pack {
    private ArrayList<Card> cards;
    private final int numberOfPlayers;
    private final String filename;
    Pack(String filename, int numberOfPLayers){
        this.numberOfPlayers = numberOfPLayers;
        this.filename = filename;
        importPack();
    }

    private void importPack(){}
    private ArrayList getCards(){
        return null;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public String getFilename() {
        return filename;
    }
}
