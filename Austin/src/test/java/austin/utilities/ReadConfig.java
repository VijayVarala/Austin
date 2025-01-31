package austin.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReadConfig 
{
	private Properties properties;
    private static final Logger logger = Logger.getLogger(ReadConfig.class.getName());

    public ReadConfig() 
    {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() 
    {
        File configFile = new File("./Configuration/config.properties");
        try (FileInputStream fis = new FileInputStream(configFile)) 
        {
            properties.load(fis);
        } 
        catch (IOException e) 
        {
            logger.log(Level.SEVERE, "Error loading configuration properties: " + e.getMessage(), e);
            throw new RuntimeException("Failed to load configuration properties", e);
        }
    }

    public Optional<String> getUrl() 
    {
        return getProperty("baseURL");
    }
    
    public Optional<String> getAdminUrl() 
    {
        return getProperty("adminURL");
    }

    public Optional<String> getInitiatorUname() 
    {
        return getProperty("initiatorUname");
    }
    
    public Optional<String> getInvestigatorUname() 
    {
        return getProperty("investigatorUname");
    }
    
    public Optional<String> getGrcUname() 
    {
        return getProperty("grcUname");
    }
    
    public Optional<String> getResearchUname() 
    {
        return getProperty("researchUname");
    }
    
    public Optional<String> getAdminUname() 
    {
        return getProperty("adminUname");
    }


    public Optional<String> getPassword() 
    {
        return getProperty("password");
    }

    public Optional<String> getBrowser() 
    {
        return getProperty("browser");
    }

    private Optional<String> getProperty(String key) 
    {
        String value = properties.getProperty(key);
        if (value == null || value.isEmpty()) 
        {
            logger.warning("Property '" + key + "' is not defined in the config file.");
            return Optional.empty();
        }
        return Optional.of(value);
    }

}
