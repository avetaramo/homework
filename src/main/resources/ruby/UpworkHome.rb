require 'selenium-webdriver'
class UpworkHome

#Run browser
#Clear cookies
#Navigate to url

  def initialize(url , browser)
  if(browser.include? "chrome")
    @driver=Selenium::WebDriver.for :chrome
	end
  if(browser.include? "firefox")
	@driver=Selenium::WebDriver.for :firefox
	end
    @driver.manage.window.maximize
	@driver.manage.delete_all_cookies
    @driver.navigate.to url	
  end
    
	def filters_icon()
	return @driver.find_element(:xpath, "//div[contains(@class, 'filters-button')]")
  end
  
  def hourly_rate(txt)
	return @driver.find_element(:xpath, "//label[text()='Hourly Rate']/..//input[@value='#{txt}']/../span")
  end
	
	def category_web_development()
	return @driver.find_element(:xpath, "//input[@value='sc-web-development']/../span")
  end
	
	
  def page_source()
	return @driver.find_element(:xpath, "//*")
  end
  
  def search_text()
    #return @driver.find_element(:type,'search')
	return @driver.find_element(:xpath,"//*[@class='navbar-collapse d-none d-lg-flex']//input[@name='q']")
  end
  
  def search_icon()
	return @driver.find_element(:xpath, "//*[contains(@class,'icon-search')]")
  end
  
   def search_button()
	return @driver.find_element(:xpath, "//div[contains(@class, 'search-button')]//button")
  end
  
  
  def search_freelancer()
	#return @driver.find_element(:xpath, "//li[@data-label='Freelancers']/a")
	return @driver.find_element(:linkText, 'Freelancers')
  end
  
    def profile_link(txt)
	return @driver.find_element(:xpath, "//h4/a[@data-qa='tile_name' and contains(text(), '#{txt}')]")
  end
  
  
  def title()
	return @drive.find_element(:xpath, "//section[@data-qa='freelancer']//h4/a/@title")
  end
  
  def tile_title(txt)
	return @drive.find_element(:xpath, "//section[@data-qa='freelancer']//h4/a[@title='#{txt}']/../..//h4[@data-qa='tile_title']")
  end
  
  def profile_title_text()
    return "//span[contains (@class, 'job-title')]"
  end
  
  def profile_overview_text()
    return "//span[@ng-show='open']"
  end
  
  def profile_title() 
	return @drive.find_element(:xpath, "//span[contains (@class, 'job-title')]")
  end
  
  def profile_rate_text()
    return @driver.find_element(:xpath, "//*[contains(@data-service-profile , 'selectedProfile')]//span[@itemprop='pricerange']")
  end
  
  #to be added to parent Page Object
  def close_browser()
    @driver.quit
  end
end