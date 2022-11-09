import java.util.Arrays;

public class CardGame {
    CardGame() {
    }
    public static void main(String[] args){
        // TEST CODE TO CHECK PACK IMPORTING WORKS
        Pack testPack = new Pack("testPack.txt", 1);
        for (Card c : testPack.getCards()){
            System.out.println(c.getCardValue());
        }
    }

}
