package co.unicauca.microkernel.plugins.accountBankManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountBankManager {
    private static final String FILE_NAME = "accounts.properties";
    private static AccountBankManager instance;

    private Properties accountProperties;

    private AccountBankManager() {
        accountProperties = new Properties();
    }

    public static AccountBankManager getInstance() {
        return instance;
    }

    public static void init(String basePath) throws Exception {
        instance = new AccountBankManager();
        instance.loadProperties(basePath);
        if (instance.accountProperties.isEmpty()) {
            throw new Exception("Could not initialize plugins");
        }

    }

    public boolean getInformationAccount(String payCode) {

        //Verificar si existe una entrada en el archivo para el metodo de pago indicado.
        String propertyName = "payment." + payCode.toLowerCase();
        if (!accountProperties.containsKey(propertyName)) {
            return false;
        }
        //Obtener el nombre de la clase del plugin.
        String accountInformation = accountProperties.getProperty(propertyName);

        System.out.println("Informacion de la cuenta: " + accountInformation);

        return true;

    }

    private void loadProperties(String basePath) {

        String filePath = basePath + File.separator+ FILE_NAME;
        try (FileInputStream stream = new FileInputStream(filePath)) {
            accountProperties.load(stream);

        } catch (IOException ex) {
            Logger.getLogger("PaymentPluginManager").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
        }

    }
}
