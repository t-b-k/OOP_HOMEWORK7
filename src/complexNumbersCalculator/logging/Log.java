package complexNumbersCalculator.logging;

import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {
    static {
        try (FileInputStream in = new FileInputStream("log.config")) { //полный путь до файла с конфигами
            // Файл log.config всегда должен лежать в корне проекта, то есть на одном уровне с папкой src
            LogManager.getLogManager().readConfiguration(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Logger log(String className) {
//    public Logger log(String className) {
        return Logger.getLogger(className);
    }
}
