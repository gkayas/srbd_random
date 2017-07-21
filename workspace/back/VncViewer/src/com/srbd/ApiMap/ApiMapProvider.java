
package com.srbd.ApiMap;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import javax.xml.bind.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;


import com.srbd.jaxbsrc.*;
public class ApiMapProvider {
//	  private ObjectFactory of;
//	  private Apiobjectlist apiList;
	  
	  private static final String API_MAP_FILE = "apis.xml";
	    public ApiMapProvider(){
//	        of = new ObjectFactory();
//	        apiList = of.createApiobjectlist();
	    }
	    
//	   This code block will be used to marshal XML from JAXB objects 
	/*    
	    private Apiobject makeApiObject(String name) {
	    	Apiobject apiObject = of.createApiobject();
	    	apiObject.setObjectname(name);
	    	apiList.getApiobject().add(apiObject);
			return apiObject;
	    }
	    
	    private void addApi(Apiobject apiObject, String api ){
	    	
	        apiObject.getApi().add(api);        
	    }

	    private void marshal() {
	        try {
	            JAXBElement<Apiobjectlist> gl =
	                of.createApiobjectcollection(apiList);

	            JAXBContext jc = JAXBContext.newInstance( "com.srbd.jaxbsrc" );
	            Marshaller m = jc.createMarshaller();
	            m.marshal( gl, System.out );
	            
	        } catch( JAXBException jbe ){
	        	
	        	jbe.printStackTrace();
	        }
	    }
	 */   
	    private Apiobjectlist unmarshal() {
	    	File file = new File(API_MAP_FILE);
	    	Apiobjectlist list = null;
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance( "com.srbd.jaxbsrc");
				Source source = new StreamSource(new FileInputStream(file));
	    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    	    list = (Apiobjectlist)( jaxbUnmarshaller.unmarshal(source, Apiobjectlist.class).getValue());
//	    		System.out.println(list);
	    		
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			return list;
	    }
	    
	    
	    public HashMap<String, List<String>> getApiMap() {
	    
	    	Apiobjectlist obj = unmarshal();
	    	HashMap<String, List<String>> apiMap = new HashMap<String,List<String>>();
	    	List<Apiobject> l = obj.getApiobject();

	    	for (Apiobject apiobject : l) {
	    		
	    		List<String>apis = apiobject.getApi();
	    		apiMap.put(apiobject.getObjectname(), apis);
			}
	    	
	    	return apiMap;
	    }
}
