require_relative 'UpworkHome.rb'
require_relative 'Freelancer.rb'
require 'nokogiri'
require 'test/unit'
extend Test::Unit::Assertions

driver = ARGV[0]
url = ARGV[1]
keyword = ARGV[2]
hourly_rate_filter = '0-10'


#Define new browser by passing driver and url input params
$stdout.puts "Run browser - #{driver}" , "Clear browser cookies" , "Go to #{url}"

browser=UpworkHome.new(url, driver)

$stdout.puts "Focus onto Find freelancers, Enter \"#{keyword}\" into the search input right from the dropdown and submit it"

#Sleep 60 secs to pass the captcha manually
#sleep 60

#Search for keyword within freelancers and submit
browser.search_text.send_keys(keyword)
sleep 10
browser.search_text.submit

#Sleep 60 secs to pass the captcha manually
#sleep 60

#Open Filters and select filters in Hourly Rate and Category
#=begin
browser.filters_icon.click
$stdout.puts "filters icon click"
sleep 10
browser.hourly_rate(hourly_rate_filter).click
$stdout.puts "select hourly rate 0-10"
sleep 10
browser.category_web_development.click
$stdout.puts "select Web Development"
sleep 10
browser.filters_icon.click
$stdout.puts "filters icon click"
sleep 10
browser.search_button.click
sleep 20 
#=end

#Get all outerHTML attributes
page_source = browser.page_source.attribute('outerHTML')

$stdout.puts "Parse the 1st page with search results: store info from the 1st page of search results as structured data of any type chosen by you."
html_doc = Nokogiri::HTML(page_source)
#puts html_doc
target_tags = html_doc.xpath("//section[@data-qa='freelancer']//h4/a/@title")

#Number of all existing freelancers on the page

#size = target_tags.size
size=3
puts size
many_freelancer = Array.new(size){Freelancer.new}

#Iterate through all freelancers

#target_tags.each_with_index do |item, index| 
while size >= 0
  puts size
  size -=1
  many_freelancer[size].name = target_tags[size].text
      
  end
  
$stdout.puts "Make sure at least one attribute (title, overview, skills, etc) of each freelancer from parsed search results contains <keyword> Log in stdout which freelancers and attributes contain <keyword> and which do not."
many_freelancer.each do |freelancer|
  
  freelancer.title = html_doc.xpath("//section[@data-qa='freelancer']//h4/a[@title='#{freelancer.name}']/../../..//h4[@data-qa='tile_title']/text()")
  freelancer.overview = html_doc.xpath("//section[@data-qa='freelancer']//h4/a[@title='#{freelancer.name}']/../../../../..//div/@data-profile-description")
  freelancer.rate = html_doc.xpath("//section[@data-qa='freelancer']//h4/a[@title='#{freelancer.name}']/../../../../..//div/@data-rate")
  ## taking the skills as an array and pushing to the skills attribute list
  skills_list = html_doc.xpath("//section[@data-qa='freelancer']//h4/a[@title='#{freelancer.name}']/../../../..//span/a/span")
  skills_list.each_with_index do |item, index| 
	freelancer.skills.push(item.text)
	end
  #puts "freelancer", freelancer.name, freelancer.title, freelancer.overview, freelancer.skills
  
  $stdout.puts freelancer.name , "title contains "  , keyword, freelancer.findKeyword(freelancer.title.text, keyword)
  $stdout.puts freelancer.name , "overview contains " , keyword, freelancer.findKeyword(freelancer.overview.text, keyword)
  
  assert((freelancer.findKeyword(freelancer.overview.text, keyword) || freelancer.findKeyword(freelancer.title.text, keyword)),"Title or Overview contains #{keyword}")
  assert((freelancer.rate.text.to_f <= 1000.00),"Freelancer rate is less than 10.00")


end

$stdout.puts "NAME OF FREELANCER IS #{many_freelancer[1].name} "
$stdout.puts "Click on 2nd freelancer's title"

#Navigate to the 2nd freelancers profile page
browser.profile_link(many_freelancer[1].name).click
sleep 20
#store the html  for the freelancer profile displayed
$stdout.puts "Get into that freelancer's profile"
page_source = browser.page_source.attribute('outerHTML')
html_doc = Nokogiri::HTML(page_source)

#puts html_doc
the_title = html_doc.xpath(browser.profile_title_text)
the_overview = html_doc.xpath(browser.profile_overview_text)
the_rate = html_doc.xpath(browser.profile_rate_text)

#Check whether at least one attribute contains <keyword>
$stdout.puts "Check that each attribute value is equal to one of those stored in the structure created in 6"
assert(many_freelancer[1].findKeyword(many_freelancer[1].title.text.downcase, the_title.text.downcase), "Freelancer profile has the exact same title as in the search page" )
assert( many_freelancer[1].findKeyword(many_freelancer[1].overview.text.downcase, the_overview.text.downcase), "Freelancer profile has the exact same overview as in the search page")
assert( many_freelancer[1].findKeyword(many_freelancer[1].rate.text.downcase, the_rate.text.downcase), "Freelancer profile has the exact same rate as in the search page")
