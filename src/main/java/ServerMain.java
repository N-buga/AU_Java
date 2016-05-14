import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by n_buga on 14.05.16.
 */
public class ServerMain {
    public static void out_format() {
        System.out.printf("need the name of file as an argument");
    }
    public static void main(String[] args) {
        if (args.length == 0) {
            out_format();
            return;
        }
        String fileName = args[0];
        Path filePath = Paths.get(fileName);
        NonBlockingServer nonBlockingServer = new NonBlockingServer(filePath);
        Thread thread = nonBlockingServer.start();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNext()) {
                String word = scanner.next();
                if (word.equals("end")) {
                    nonBlockingServer.close();
                    break;
                } else {
                    System.out.printf("The command %s doesn't exist. Use end for exit.\n", word);
                }
            }
        }
    }
}
