
import java.io.FileWriter;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import com.opencsv.CSVWriter;

public class UtilityClass{
	/*public static void CSVWrite(String[] record) throws Exception
	{
	String filepath = "D:\\Profiles\\aniarora\\Downloads\\INDIA_ROne09092019T164502.csv";
	CSVWriter writer =  new CSVWriter(new FileWriter (filepath, true), '|', CSVWriter.NO_QUOTE_CHARACTER);	
	
    writer.writeNext(record);
    writer.close();
}
*/
	public static void login(String id, String pass, WebDriver driver)
   	{
   	    WebElement username = driver.findElement(By.id("userid"));
   	    username.sendKeys(id);
   	    WebElement Password = driver.findElement(By.id("pwd"));
   	    Password.sendKeys(pass);
   	    WebElement loginbutton = driver.findElement(By.name("Submit"));
   	    loginbutton.click();
   	}
	public static void mynavigation(String[] a,  WebDriver driver) throws InterruptedException
   	{	
   		
   		driver.switchTo().defaultContent();
   	for(int i = 0; i<a.length; i++)
   	{
   	 WebDriverWait wait_navigation = new WebDriverWait(driver, 10);
   	 if((i>0) && (a[i].equals( a[i-1])))
   	 {
   		Thread.sleep(1000);
   		 List<WebElement> li = driver.findElements(By.linkText(a[i]));
   		li.get(1).click();
   	 }
   	 else
   	 {
   	 WebElement  myElement = wait_navigation.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(a[i])));
   	 myElement.click();
   	 }
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
	    	for (int j = 2; j <14; j++)
    		{
    		
    			int randomNumber = rand.nextInt(101);
    			if (randomNumber == 1 || randomNumber == 0 )
    			{
    				randomNumber+=2;
    			}
    		
    			WebElement value = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"win0divQRY_VIEWER_WRK_HTMLAREA\"]/div/table[2]/tbody/tr/td/table/tbody/tr["+randomNumber+"]/td["+(j)+"]")));
    			System.out.println(value.getText());    
    			Record[j-2][i] = value.getText();	    			
    		}
    		
    	}
	}
	private static org.w3c.dom.Document getDocument(String fileName) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        org.w3c.dom.Document doc = builder.parse(fileName);
        return doc;
    }
	private static HashMap<String,String> evaluateXPath(org.w3c.dom.Document document, String xpathExpression) throws Exception
    {
        // Create XPathFactory object
        XPathFactory xpathFactory = XPathFactory.newInstance();
         
        // Create XPath object
        XPath xpath = xpathFactory.newXPath();
        HashMap<String,String> SearchValue = new HashMap<String,String>();
        try 
        {
            // Create XPathExpression object
            XPathExpression expr = xpath.compile(xpathExpression);
             
            // Evaluate expression result on XML document
            NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
             
            for (int i = 0; i < nodes.getLength(); i++) {
            	SearchValue.put((nodes.item(i).getNodeValue()),(nodes.item(i).getAttributes().toString()));
            }
                 
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return SearchValue;
    }
	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver","d:\\Profiles\\aniarora\\Desktop\\Test Automation\\chromedriver_win32\\chromedriver.exe");                  
	    WebDriver driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.get("https://hris-steria-trn.oracleoutsourcing.com/psp/DSERAJ/?&cmd=login&languageCd=ENG");
	    login("ANIKET", "test@123", driver);
	    org.w3c.dom.Document document = getDocument("D:\\Profiles\\aniarora\\eclipse-workspace\\HiringProcess\\configuration.xml");
	    String xpathExpression = "/HiringModule/TestEnvironments/TestEnvironment/@id";
        HashMap<String, String> abc = evaluateXPath(document, xpathExpression);
        
        String xpathExpression1 = "/HiringModule/Navigations/Navigation/text()";
        HashMap<String, String> Navigation = evaluateXPath(document, xpathExpression1);
       String path =  Navigation.get("QueryManager");
       System.out.println(Navigation);
       System.out.println(path);
       /* while(litr.hasNext())
        {
        System.out.println(litr.next());
        }
        while(litr1.hasNext())
        {
        System.out.println(litr1.next());
        }
        */
       String[] a =  path.split(" > ");
	  /* File inputFile = new File("NavigationTest.xml");
       SAXBuilder saxBuilder = new SAXBuilder();
       Document document = saxBuilder.build(inputFile);
       System.out.println("Root element :" + document.getRootElement().getName());
       Element Navigation = document.getRootElement();
       List<Element> NavigationList = Navigation.getChildren();
       ListIterator<Element> litr=NavigationList.listIterator();
       */
       
       System.out.println("Traversing the list in forward direction:");
      /* while(litr.hasNext()){
          System.out.println(litr.next());
       }
       Element QueryReport = NavigationList.get(0);
       System.out.println(QueryReport.getValue());
       */
       
	    mynavigation(a, driver);
	    QueryRead(2, driver);
	/*    driver.switchTo().frame("ptifrmtgtframe");
	    driver.findElement(By.id("QRYSELECT_WRK_QRYSEARCHTEXT254")).sendKeys("Z_hire_testing");
	    driver.findElement(By.id("QRYSELECT_WRK_QRYSEARCHBTN")).click();
	    WebDriverWait wait = new WebDriverWait(driver, 60);
	    WebElement ABC = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("QRYSELECT_WRK_QRYEDITFIELD$0")));
	    ABC.click();
	    WebElement ABC2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"PSTAB\"]/table/tbody/tr/td[10]/a")));
	    ABC2.click();
	   
	    for(int i = 0; i<5; i++)
	    {
	    	String[] Record = new String[32];
	    	
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
	    		if (value.getText() == null)
	    		{
	    			Record[j-2] = null;
	    		}
	    		else
	    		{	    
	    			Record[j-2] = value.getText();
	    		}
	    	}
	    	CSVWrite(Record);
	    }*/
	    
	}
}