import controller.Controller;
import view.MainView;

import java.sql.SQLException;

/**
 * Created by Daniel on 2015-10-06.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        Controller ctrl = new Controller();

        while (ctrl.commandControl());
    }

}
