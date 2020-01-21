package is.warehouse;

import is.warehouse.UI.ApplicationMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WarehouseApplication implements CommandLineRunner {
    private ApplicationMenu applicationMenu;

    @Autowired
    public WarehouseApplication(ApplicationMenu applicationMenu) {
        this.applicationMenu = applicationMenu;
    }

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        applicationMenu.run();
    }
}
