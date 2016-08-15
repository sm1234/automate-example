import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.net.URL;

public class JavaSample {

  public static final String USERNAME = "YOUR_USER_NAME";
  public static final String ACCESS_KEY = "YOUR_ACCESS_KEY";
  public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

  public static void main(String[] args) throws Exception {



    YamlReader reader = new YamlReader(new FileReader(".automate.yml"));
    Object object = reader.read();
    Map config = (Map)object;
    List listOfBrowserSettings = (List)config.get("desired capabilities");
    
    for (int BrowserSettingIndex = 0; BrowserSettingIndex < listOfBrowserSettings.length; BrowserSettingIndex++) 
    {
      DesiredCapabilities caps = new DesiredCapabilities();
      caps.setCapability("browser", listOfBrowserSettings[BrowserSettingIndex].get("browser"));
      caps.setCapability("browser_version", listOfBrowserSettings[BrowserSettingIndex].get("browser_version"));
      caps.setCapability("os", listOfBrowserSettings[BrowserSettingIndex].get("os"));
      caps.setCapability("os_version", listOfBrowserSettings[BrowserSettingIndex].get("os_version"));
      caps.setCapability("browserstack.debug", listOfBrowserSettings[BrowserSettingIndex].get("browserstack.debug"));
  
      WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
      driver.get("http://www.google.com");
      WebElement element = driver.findElement(By.name("q"));
  
      element.sendKeys("BrowserStack");
      element.submit();
  
      System.out.println(driver.getTitle());      
    }

    driver.quit();

  }
}
