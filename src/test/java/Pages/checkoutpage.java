package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class checkoutpage 
{
	WebDriver w; // instance //null
	
	@FindBy(xpath = "//a[@class='cart-icon']") WebElement cart;
	@FindBy(css = ".promoCode")WebElement promo;
	@FindBy(css = "button.promoBtn")WebElement promo_btn;
	@FindBy(xpath = "//button[text()='PROCEED TO CHECKOUT']")WebElement poceedtocheckout;
	@FindBy(xpath = "//button[text()='Place Order']")WebElement placeorder;
	@FindBy(xpath = "//select")WebElement selectcountry;
	@FindBy(css =".chkAgree" )WebElement chcekbx;
	@FindBy(xpath = "//button[text()='Proceed']")WebElement procced;
	
	public checkoutpage(WebDriver w2) 
	{
		this.w=w2;
		PageFactory.initElements(w, this);
	}

	public void checkout(String promocode, String country)
	{
		cart.click();
		poceedtocheckout.click();
		promo.sendKeys(promocode);
		promo_btn.click();
		placeorder.click();
		
		Select s = new Select(selectcountry);
		s.selectByVisibleText(country);
		chcekbx.click();
		procced.click();
		
	}
	
	
	
	
}
