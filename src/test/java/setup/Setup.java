package setup;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Setup {
    public AndroidDriver driver;

    @BeforeTest
    public AndroidDriver driverSetup() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability("deviceName", "MyDevice");
        desiredCapabilities.setCapability("platformName", "Android");

        //won't be needed if device is already connected
        //desiredCapabilities.setCapability("uuid","emulator-5554");

        desiredCapabilities.setCapability("appPackage", "com.continuum.emi.calculator");
        desiredCapabilities.setCapability("appActivity", "com.finance.emicalci.activity.Splash_screnn");

        //for granting required permissions, like, camera, contact, location etc
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);

        //if app is not already installed, below line needs to be uncommented
        //desiredCapabilities.setCapability("app","./src/test/resources/emiCalculator.apk");

        //defining URL for appium connectivity
        URL url = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(url, desiredCapabilities);

        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        return driver;
    }

    @AfterTest
    public void closeApp() {
        driver.quit();
    }

}

