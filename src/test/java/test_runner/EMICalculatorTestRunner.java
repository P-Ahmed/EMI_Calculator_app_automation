package test_runner;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.EMICalculatorScreen;
import setup.Setup;
import utils.Dataset;

import java.io.IOException;

public class EMICalculatorTestRunner extends Setup {

    @BeforeTest
    public void clickEMICalculatorButton() {
        EMICalculatorScreen emiCalculatorScreen = new EMICalculatorScreen(driver);
        emiCalculatorScreen.btnEMICalculator.click();
    }

    @Test(dataProvider = "data-provider", dataProviderClass = Dataset.class)
    public void doEMICalculation(String loanAmount, String interest, String years, String months, String processingFee, String mEMI, String tInterest, String tProFee, String tPayment) throws InterruptedException, IOException {
        EMICalculatorScreen emiCalculatorScreen = new EMICalculatorScreen(driver);
        emiCalculatorScreen.doCalculation(loanAmount, interest, years, months, processingFee);
        emiCalculatorScreen.assertingResult(mEMI, tInterest, tProFee, tPayment);
    }
}
