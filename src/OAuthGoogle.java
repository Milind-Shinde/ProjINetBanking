import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;
public class OAuthGoogle {

	public static void main(String[] args) throws InterruptedException
	{
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Milind\\Desktop\\SeleniumJar\\chromedriver_win32\\chromedriver.exe"); 
		  WebDriver driver = new ChromeDriver(); 
		  driver.get("https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&state=verifyrms&flowName=GeneralOAuthFlow"); 
		  driver.findElement(By.cssSelector("input[type='email']")).sendKeys("milindshinde3088@gmail.com");
		  driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER); 
		  Thread.sleep(3000);
		  driver.findElement(By.cssSelector("input[type='password']")).sendKeys("bhedaghat");
		  driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		  Thread.sleep(4000);
		 
		String url = driver.getCurrentUrl();
		
		//Here, i have comented above code which is used to automate browser and do login to 
		//gmail and taking the code. But recently google observed that the login is to be performed by using
		//some automation scripts, so we have to write url manually by visiting browser.
		//So here we will open browser manually, enter user details and then after submit copy url to get code.
		//String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyrms&code=4%2F0AY0e-g4uMNaM9NLDZG8-R0dQVGzOmoWXpAPVpsxK7dlPmK6Q3mMqQqFwH806pOU_65sjSg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent#";
		
		String partialcode = url.split("code=")[1];
		String code = partialcode.split("&scope")[0];
		System.out.println(code);
		
		//Now in above steps we got required authorization code which will be used below
				
		String accessTokenResponse = given().urlEncodingEnabled(false)
		.queryParams("code", code)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		//Now whatever response we will get in Json format, we need to parse that &
		//we will store it in string format.
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");
		
		
		
		String response = given().queryParam("access-token", accessToken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/getCourse.php").asString();
		
	System.out.println(response);

	}

}
