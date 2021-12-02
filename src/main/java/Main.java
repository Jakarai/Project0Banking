import frontcontroller.FrontController;
import io.javalin.Javalin;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class Main {

    static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(9000);
        new FrontController(app);
        logger.setLevel(Level.WARN);

//        logger.warn("testing warn");


    }
}
