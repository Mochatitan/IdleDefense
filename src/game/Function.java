

public class Function{
private Function(){}

public static void saveGame(int saveFile){


}
  
public static void loadGame(int saveFile, Board board){}

public static int loadTest(){


}

public static String readTXT(String fileString){
  throws IOException {
    String str = "Hello";
    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
    writer.write(str);
    
    writer.close();
}
public void setInt(String fileName, int newValue) throws IOException {
  
    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
    writer.write(newValue);
    writer.close();


}

}
