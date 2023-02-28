package game;

public class Test{
private Test(){}
public int Monee;
public static void main(String[] args){
   testingMethod();
}
  
public void testingMethod() 
  throws IOException {
    String str = "Hello";
    String fileName = "src/game/saves/Save1.txt"
    Path path = Paths.get(fileName);
    byte[] strToBytes = str.getBytes();

    Files.write(path, strToBytes);

    String read = Files.readAllLines(path).get(0);
    assertEquals(str, read);
}
  
}
