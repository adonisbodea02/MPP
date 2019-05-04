import ro.ubb.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.Console;

public class MainClient {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        Console console=context.getBean(Console.class);
        console.runConsole();

    }
}
