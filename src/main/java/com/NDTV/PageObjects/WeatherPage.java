package com.NDTV.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class WeatherPage {

	@FindBy(how = How.ID, using = "searchBox")
	private WebElement searchBox;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
