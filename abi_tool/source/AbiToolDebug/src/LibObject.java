import java.io.File;

public class LibObject {
	String libName;
	File soFile;
	
	public LibObject(String libName, File soFile) {
		super();
		this.libName = libName;
		this.soFile = soFile;
	}
	
	@Override
	public boolean equals(Object o) {
		if( !(o instanceof LibObject) ) {
			return false;
		}
		
		return this.libName.equals(((LibObject)o).libName);
	}
	
	@Override
	public String toString() {
		
		return "Name : "+libName+ " Path : "+soFile.getPath();
	}
}
