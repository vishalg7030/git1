package oauth_2_test;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.restassured.path.json.JsonPath;

public class Oauth {

	public static void main(String[] args) {
		
		/* to open a browser in in-cognito mode
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		*/
		
		
		//Initializing the browser
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\HP\\Desktop\\gecko\\geckodriver.exe");
		WebDriver driver= new FirefoxDriver();
		
		
		driver.get("https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&state=verifyid&flowName=GeneralOAuthFlow");
		driver.manage().window().maximize();
		
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("vishalgrover7030@gmail.com");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		
		driver.findElement(By.xpath("//*[@type='password']")).sendKeys("Suzuki1!");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Keys.ENTER);
	
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		String currentUrl=driver.getCurrentUrl();
		System.out.println(currentUrl);
		
		
		
		
		String accessTokenResponse=given().queryParams("Code","")
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization_code")
		.when().post("https://www.googleapis.com/oauth2/v4/token").asString();
		 
		System.out.println(accessTokenResponse);
		
		//JsonPath js=new JsonPath(accessTokenResponse);
		//js.get()
		
		String response= given().queryParam("access_token", "")
		.when().get("https://rahulshettyacademy.com/getCourse.php")
		.asString();

		System.out.println(response);
System.out.println("kulchi");
System.out.println("kulchi3");
	}

}
