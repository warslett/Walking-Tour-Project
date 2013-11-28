public class Person {
	int amountOfJobs = 2;
	int age;
	String name;
	Job[] jobs = new Job[amountOfJobs];
	public Person(int a,String n){
		age = a;
		name = n;
		jobs[0] = new Job("Cleaner",1);
		jobs[1] = new Job("Doctor",19);
	}
	public String toJson(){
		/*should use string buffer but DILLIGAF prototyping*/
		String out;
		out = "{\"Name\": \""+name+"\",\"Age\": \""+Integer.toString(age)+"\",\"Jobs\": [";
		for(int i = 0;i<amountOfJobs-1;i++){
			out = out + jobs[i].toJson()+",";
		}
		out = out + jobs[amountOfJobs-1].toJson()+"]}";
		return out;

	}

}
