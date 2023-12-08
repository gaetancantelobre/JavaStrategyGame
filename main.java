import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class main {

    public static void main(String[] args) throws IOException {
        GameController gc = new GameController();
        gc.playGame();
    }

}
