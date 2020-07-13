package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddMap;
import pojo.Location;

public class TestDataBuild {

	public AddMap AddPlacePayload(String name ,String language,String address) {
		
		AddMap ad = new AddMap();
		ad.setAccuracy(40);
		ad.setName(name);
		ad.setAddress(address);
		ad.setPhone_number("(+91) 983 893 3937");
		List<String> myList = new ArrayList<>();
		myList.add("shoe park");
		myList.add("shop");
		ad.setTypes(myList);

		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);

		ad.setLocation(location);
		ad.setLanguage(language);
		ad.setWebsite("http://google.com");

		return ad;
	}
	
	public String deletePlacePayload(String placeId) {
		
		return "{\r\n" + 
				"    \"place_id\":\""+placeId+"\"\r\n" + 
				"}\r\n" + 
				"";
	}
}
