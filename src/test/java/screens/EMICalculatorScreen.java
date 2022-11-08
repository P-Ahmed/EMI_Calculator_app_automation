package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class EMICalculatorScreen {
    @FindBy(id = "com.continuum.emi.calculator:id/btnStart")
    public WebElement btnEMICalculator;
    @FindBy(id = "com.continuum.emi.calculator:id/etLoanAmount")
    WebElement txtLoanAmount;
    @FindBy(id = "com.continuum.emi.calculator:id/etInterest")
    WebElement txtInterest;
    @FindBy(id = "com.continuum.emi.calculator:id/etYears")
    WebElement txtYears;
    @FindBy(id = "com.continuum.emi.calculator:id/etMonths")
    WebElement txtMonths;
    @FindBy(id = "com.continuum.emi.calculator:id/etFee")
    WebElement txtProcessingFee;
    @FindBy(id = "com.continuum.emi.calculator:id/etEMI")
    WebElement txtEMIResult;
    @FindBy(id = "com.continuum.emi.calculator:id/btnCalculate")
    WebElement btnCalculate;
    @FindBy(id = "com.continuum.emi.calculator:id/btnReset")
    WebElement btnReset;
    @FindBy(id = "com.continuum.emi.calculator:id/monthly_emi_result")
    WebElement lblMonthlyEMI;
    @FindBy(id = "com.continuum.emi.calculator:id/total_interest_result")
    WebElement lblTotalInterest;
    @FindBy(id = "com.continuum.emi.calculator:id/processing_fee_result")
    WebElement lblProcessingFee;
    @FindBy(id = "com.continuum.emi.calculator:id/total_payment_result")
    WebElement lblTotalPayment;

    public EMICalculatorScreen(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void doCalculation(String loanAmount, String interest, String years, String months, String processingFee) {
        txtLoanAmount.sendKeys(loanAmount);
        txtInterest.sendKeys(interest);
        txtYears.sendKeys(years);
        txtMonths.sendKeys(months);
        txtProcessingFee.sendKeys(processingFee);
        btnCalculate.click();
    }

    public void assertingResult(String mEMI, String tInterest, String tProFee, String tPayment) throws InterruptedException, IOException {
        String monthlyEMI = lblMonthlyEMI.getText();
        String totalInterest = lblTotalInterest.getText();
        String processingFee = lblProcessingFee.getText();
        String totalPayment = lblTotalPayment.getText();

        //using SoftAssert for ensuring if any assertion fails, next test will be executed
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(monthlyEMI.replace(",", ""), mEMI);
        softAssert.assertEquals(totalInterest.replace(",", ""), tInterest);
        softAssert.assertEquals(processingFee.replace(",", ""), tProFee);
        softAssert.assertEquals(totalPayment.replace(",", ""), tPayment);

        btnReset.click();

        //This line needs to be added when using SoftAssert. It will show which test is failed at the end of the test
        softAssert.assertAll();
    }
}
