class Job{
	String jobTitle;
	int yearsActive;
	public Job(String job,int years){
		jobTitle = job;
		yearsActive = years;
	}
	public String toJson(){
		String out;
		out = "{\"Job title\": \""+jobTitle+"\",\"Years active\": \""+Integer.toString(yearsActive)+"\"}";
		return out;
	}
}
