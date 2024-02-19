package client;

import server.WriteReadServer;

import java.io.FileReader;
import java.io.FileWriter;


public class WriterRead implements WriteReadServer {
    public static final String LOG_PATH = "src/log.txt";


    @Override
    public void save(String text) {
        try (FileWriter fileWriter = new FileWriter(LOG_PATH, true)) {
            fileWriter.write(text);
            fileWriter.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String load() {
        StringBuilder sb = new StringBuilder();
        try (FileReader fileReader = new FileReader(LOG_PATH);) {
            int r;
            while ((r = fileReader.read()) != -1) {
                sb.append((char) r);
            }
            sb.delete(sb.length() - 1, sb.length());
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();

    }
}
