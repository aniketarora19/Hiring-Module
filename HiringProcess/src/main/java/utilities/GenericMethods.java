package utilities;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericMethods {

		public static void login(String id, String pass, WebDriver driver)
	   	{
	   	    WebElement username = driver.findElement(By.id("userid"));
	   	    username.sendKeys(id);
	   	    WebElement Password = driver.findElement(By.id("pwd"));
	   	    Password.sendKeys(pass);
	   	    WebElement loginbutton = driver.findElement(By.name("Submit"));
	   	    loginbutton.click();
	   	}
		public static void mynavigation(String[] a,  WebDriver driver)
	   	{	
	   		
	   		driver.switchTo().defaultContent();
	   	for(int i = 0; i<a.length; i++)
	   	{
	   	 WebDriverWait wait_navigation = new WebDriverWait(driver, 10);
	   	 WebElement  myElement = wait_navigation.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(a[i])));
	   	 myElement.click();
	   	}
	   	}
		public static void QueryRead(int n, WebDriver driver)
		{
			driver.switchTo().frame("ptifrmtgtframe");
		    driver.findElement(By.id("QRYSELECT_WRK_QRYSEARCHTEXT254")).sendKeys("Z_hire_testing");
		    driver.findElement(By.id("QRYSELECT_WRK_QRYSEARCHBTN")).click();
		    WebDriverWait wait = new WebDriverWait(driver, 60);
		    WebElement btn_edit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("QRYSELECT_WRK_QRYEDITFIELD$0")));
		    btn_edit.click();
		    WebElement tab_run = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"PSTAB\"]/table/tbody/tr/td[10]/a")));
		    tab_run.click();
		    for(int i = 0; i<n; i++)
	    	{
		    	String[][] Record = new String[12][n];
		    	Random rand = new Random();
		    	for (int j = 2; j <15; j++)
	    		{
	    		
	    			int randomNumber = rand.nextInt(101);
	    			if (randomNumber == 1 || randomNumber == 0 )
	    			{
	    				randomNumber+=2;
	    			}
	    		
	    			WebElement value = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"win0divQRY_VIEWER_WRK_HTMLAREA\"]/div/table[2]/tbody/tr/td/table/tbody/tr["+randomNumber+"]/td["+j+"]")));
	    			System.out.println(value.getText());    
	    			Record[j-2][i] = value.getText();	    			
	    		}
	    		
	    	}
		}
}