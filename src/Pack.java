import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Pack {
    private ArrayList<Card> cards;
    private final int numberOfPlayers;
    private final String filename;
    Pack(String filename, int numberOfPLayers){
        this.numberOfPlayers = numberOfPLayers;
        this.filename = filename;
        this.cards = new ArrayList<Card>();
        importPack();
    }

    private void importPack(){

        try{
            File packFile = new File(filename);
            Scanner myReader = new Scanner(packFile);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                cards.add(new Card(Integer.parseInt(data))); // makes a new card from int in packfile
            }
            myReader.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }

    }
    public ArrayList<Card> getCards(){
        return cards;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public String getFilename() {
        return filename;
    }

    public boolean verified(){
        ArrayList<Integer> packData = new ArrayList<>();
        try {
            File packFile = new File(this.filename);
            Scanner myReader = new Scanner(packFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                try{
                    if (Integer.parseInt(data) >= 0){
                        packData.add(Integer.parseInt(data));
                    } else {
                        System.out.println("One or more cards have a non-positive value");
                        return false;
                    }
                } catch (NumberFormatException e){
                    System.out.println("One or more cards do not have a accepted value");
                    return false;
                }


            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return false; // invalid file name need new pack file
        }
        if (packData.size() == this.numberOfPlayers * 8){
            return true;
        }
        System.out.println("Pack size invalid");
        return false;

    }


}
