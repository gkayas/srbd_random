package compatibility_report_parser;

public class Api {
	
	private String type = "";
	private String apiType="";
	
	
	public String getApiType() {
		return apiType;
	}

	public void setApiType(String apiType) {
		this.apiType = apiType;
	}
	private String full_name = "";
	private String short_name = "";
	private String change = "";
	private String effect = "";
	private String packageName="";
	
	private String remove_160_char(String string){
		return string.replaceAll(""+(char)160, "");
	}
	
	public String getType() {
		
		String []temp = this.full_name.split(":");
		if(temp.length > 1){
			return remove_160_char(temp[1].trim());
		}else{ //special code for several packages(e.g.: key-manager) because there are some class in Source compatibility like: class Control
			String [] temp2 = this.full_name.split(" ");
			return remove_160_char(temp2[0].trim());
			
		}
		
		//return remove_160_char(type.trim());
	}

	public void setType(String type) {
		type = type.trim();
		this.type = type;
	}

	public String getFull_name() {
		return full_name.trim().replaceAll(""+(char)160, "");
	}

	public void setFull_name(String full_name) {
		full_name = full_name.trim();
		this.full_name = full_name;
	}

	public String getShort_name() {
		return short_name.trim().replaceAll(""+(char)160, "");
	}

	public void setShort_name(String short_name) {
		short_name = short_name.trim();
		this.short_name = short_name;
	}

	public String getChange() {
		return change.trim();
	}

	public void setChange(String change) {
		change = change.trim();
		this.change = change;
	}

	public String getEffect() {
		return effect.trim();
	}

	public void setEffect(String effect) {
		effect = effect.trim();
		this.effect = effect;
	}

	public String getPackageName() {
		return packageName.trim();
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName.replace("so", "").replaceAll("\\.", "");
	}

	public Api(String fullName){
		this.full_name = fullName;
	}
	
	public void setup(String type, String short_name, String change, String effect){
		this.type = type;
		this.short_name = short_name;
		this.change = change;
		this.effect = effect;
	}
	
	public void copy(Api copy){
		this.full_name = copy.full_name;
		this.short_name = copy.short_name;
		this.type = copy.type;
		this.change = copy.change;
		this.effect = copy.effect;
		this.packageName = copy.packageName;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Api)){
			return false;
		}
		if(this == obj){
			return true;
		}

		return (this.full_name.trim().equals(((Api)obj).full_name.trim())) && (this.change.trim().equals(((Api)obj).change.trim()) && (this.effect.trim().equals(((Api)obj).effect.trim())));
	}
	@Override
	public String toString(){
		return "\nFull Name: "+this.full_name+"\nPackage Name: "+this.packageName+"\nShort name:"+ this.short_name + "\nType: "+type+"\nChange:"+this.change+"\nEffect: "+this.effect;
	}
	
}