public class PayloadMapJSON {
	public static String addMap() {
	return "{\r\n"
			+ "    \"location\": {\r\n"
			+ "        \"lat\": -38.383494,\r\n"
			+ "        \"lng\": 33.427362\r\n"
			+ "    },\r\n"
			+ "    \"accuracy\": 50,\r\n"
			+ "    \"name\": \"Rahul Shetty Academy\",\r\n"
			+ "    \"phone_number\": \"(+91) 983 893 3937\",\r\n"
			+ "    \"address\": \"29, side layout, cohen 09\",\r\n"
			+ "    \"types\": [\r\n"
			+ "        \"shoe park\",\r\n"
			+ "        \"shop\"\r\n"
			+ "    ],\r\n"
			+ "    \"website\": \"http://rahulshettyacademy.com\",\r\n"
			+ "    \"language\": \"French-IN\"\r\n"
			+ "}";
					}
	public static String updateMap(String id)
	{
		return "{\n" +
		           "\"place_id\":\"" + id + "\",\n" +
		           "\"address\":\"TallantWorth Trl\",\n" +
		           "\"key\":\"qaclick123\"\n" +
		           "}";
	}
	
	public static String deleteMap()
	{
		return "\"place_id\": \"1c6ed30b9008dd4a6451d5726cfd69c3\"";
	}
}
