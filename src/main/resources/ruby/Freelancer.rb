class Freelancer

attr_accessor :name, :title, :skills, :overview, :rate

 #def initialize(name, title, rate, earned, skills)
 #   @name = name
 #   @title = title
 #	 @rate = rate
 #	 @earned = earned
 #	 @skills = skills
 #   end

def initialize
     self.skills = [] 
end

  def name
    @name
  end

  def title
    @title
  end
  
  def rate
    @rate
  end
  
   def skills
    @skills
  end
  
  def overview
    @overview
  end
  
  def findKeyword(string, keyword)
	string.downcase.include? keyword.downcase
	return true
  end

end