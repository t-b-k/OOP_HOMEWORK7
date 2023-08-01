package complexNumbersCalculator;

import complexNumbersCalculator.controller.Controller;
import complexNumbersCalculator.view.Viewer;
import complexNumbersCalculator.logging.Log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOG = Log.log(Main.class.getName());

    public static void main(String[] args) {

        Viewer userView = new Viewer();
        LOG.log(Level.INFO, "ПОДКЛЮЧИЛИ VIEWER: ");

        Controller controller = new Controller(userView);
        LOG.log(Level.INFO, "ПОДКЛЮЧИЛИ CONTROLLER: ");

        controller.run();
    }
}