import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ThoughtWorksProblems{
	private static final String inputUrl = "https://http-hunt.thoughtworks-labs.net/challenge/input";
	private static final String outputUrl = "https://http-hunt.thoughtworks-labs.net/challenge/input";
	private static Map<String,Long> usageMap = new HashMap<String,Long>(); 
	private static Set<List<Long>> maxWeightLists = new HashSet<List<Long>>();
	private static Set<List<JSONObject>> maxWeightListstest = new HashSet<List<JSONObject>>();
	
	private static HttpURLConnection getConnection() throws IOException{
		URL urlForGetRequest = new URL(inputUrl);

		HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
		connection.setRequestProperty("userId", "FfocvzP3"); 
		return connection;
	}
	
	public static JSONObject getInput() throws IOException, ParseException{

		HttpURLConnection conection =  getConnection(); 
		String readLine = null;
		int responseCode = conection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conection.getInputStream()));
			StringBuffer response = new StringBuffer();
			while ((readLine = in .readLine()) != null) {
				response.append(readLine+"\n");
			} in .close();
			// print result
			JSONParser parser = new JSONParser();
			return (JSONObject) parser.parse(response.toString());
		} else {
			return null;
		}
	}

	private static JSONObject writeOutputJSON(String key, Object value){
		JSONObject object = new JSONObject();
		object.put(key, value);

		JSONObject output = new JSONObject();
		output.put("output", object);
		//System.out.println(output);
		return output;
	}





	private static void genDecryptedMessage() throws IOException, ParseException {
		JSONObject inputJSON = getInput();
		System.out.println(inputJSON.toJSONString());
		String encryptedMessage = (String) inputJSON.get("encryptedMessage");
		Long key = (Long) inputJSON.get("key");

		String output = decrypt(encryptedMessage,key);
		System.out.println(output);
		postOutput(writeOutputJSON("message",output).toJSONString());
	}

	private static String decrypt(String encryptedMessage, Long key) {

		int messageLength = encryptedMessage.length();
		String output = "";
		for(int i = 0 ; i < messageLength;i++){
			char ch = encryptedMessage.charAt(i);
			if(Character.isLetter(ch)){
				if(ch >= key+65)
					output += (char)(ch - key);
				else{
					output+= (char)( ch-65+90-key+1);
				}
			}
			else
				output += ch;
		}

		return output;
	}
	private static void postOutput(String output) throws IOException{
		URL obj = new URL(outputUrl);
		HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		postConnection.setRequestMethod("POST");
		postConnection.setRequestProperty("userId", "FfocvzP3");
		postConnection.setRequestProperty("Content-Type", "application/json");
		postConnection.setDoOutput(true);
		OutputStream os = postConnection.getOutputStream();
		os.write(output.getBytes());
		os.flush();
		os.close();
		int responseCode = postConnection.getResponseCode();
		System.out.println("POST Response Code :  " + responseCode);
		System.out.println("POST Response Message : " + postConnection.getResponseMessage());
	}

	public static void main(String... args) throws IOException, ParseException{
		/*System.setProperty("http.proxyHost", "172.25.188.188");
		System.setProperty("http.proxyPort", "8085");*/
		//genOutput();
		//writeJSON("test");

		//postOutput();

		//System.out.println(writeJSON("test").toJSONString());
		//genOutputOne();
		//System.out.println("opekandifehgujnsr");
		//System.out.println(findTools("opekandifehgujnsr",new String[]{"knife","guns","rope"}));

		//genOutputTwo();

		//getMinutesDifference("2017-01-30 12:32:10", "2019-01-30 12:32:10");

		//genOutputThree();

		//genMinimumWeightTools();

		/*String response = "{\"tools\":"
				+ "[{\"name\":\"knife\",\"weight\":10,\"value\":60},"
				+ "{\"name\":\"guns\",\"weight\":20,\"value\":100},"
				+ "{\"name\":\"rope\",\"weight\":30,\"value\":120},"
				+ "{\"name\":\"water\",\"weight\":60,\"value\":500}],"
				+ "\"maximumWeight\":60}";

		JSONParser parser = new JSONParser();
		JSONObject test = (JSONObject) parser.parse(response);
		Instant start = Instant.now();
		genMinimumWeightTools(test);
		Instant end = Instant.now();
		System.out.println("duration:"+Duration.between(start, end).toNanos());*/
		Instant start = Instant.now();
		genMinimumWeightTools();
		Instant end = Instant.now();
		System.out.println("duration:"+Duration.between(start, end).toNanos());

	}


	private static void genMinimumWeightTools() throws IOException, ParseException {
		JSONObject inputJSON = getInput();//JSONObject object
		System.out.println(inputJSON.toJSONString());
		JSONArray tools =  (JSONArray) inputJSON.get("tools");
		Long maxWeight = (Long) inputJSON.get("maximumWeight");
		//String output = decrypt(encryptedMessage,key);
		JSONArray minWeightTools = findMinimumWeight(tools,maxWeight);
		Instant end = Instant.now();
		System.out.println("tools:"+minWeightTools);
		postOutput(writeOutputJSON("toolsToTakeSorted",minWeightTools).toJSONString());
	}

	private static JSONArray findMinimumWeight(JSONArray tools, Long maxWeight){
		List<Long> weightList = new ArrayList<Long>();
		int toolsLength = tools.size();
		for(int i = 0 ;i < toolsLength;i++){
			weightList.add( (Long)((JSONObject)tools.get(i)).get("weight")  );
		}
		for(int i = 0 ; i < toolsLength ; i++){
			//System.out.println("new----");
			List<Long> list = new ArrayList<Long>();
			checkSum(weightList,toolsLength,i,0L,maxWeight,list);
		}
		List<List<Long>> listToSort = new ArrayList<List<Long>>();
		listToSort.addAll(maxWeightLists);
		Collections.sort(listToSort, new Comparator<List<Long>>() {
			public int compare(List<Long> list1,
					List<Long> list2) {
				return ((Integer)list2.size()).compareTo((Integer)list1.size());
			}
		});

		List<Long> maxTools = listToSort.get(0);
		List<JSONObject> finalList = new ArrayList<JSONObject>();
		for(int i = 0 ; i< maxTools.size() ; i++){
			finalList.add((JSONObject)tools.get(i));
		}
		Collections.sort(finalList, new Comparator<JSONObject>() {
			public int compare(JSONObject obj1,
					JSONObject obj2) {
				return ((Long)obj2.get("value")).compareTo((Long)obj1.get("value"));
			}
		});


		//System.out.println("-------------testing-----------"+maxTools);
		JSONArray toolNames = new JSONArray();
		for(JSONObject obj : finalList)
			toolNames.add((String)obj.get("name"));

		return toolNames;
	}

	private static void checkSum(List<Long> weightsList, int listSize,int i, Long currSum, Long sum, List<Long> list){

		if(i<listSize){
			Long currValue = weightsList.get(i);
			//System.out.println(" Outer CurrSum"+currSum+" CurrValue"+currValue+" sum="+sum);
			currSum += currValue;
			if(currSum == sum){
				List<Long> listNew = new ArrayList<Long>(list);
				listNew.add(currValue);
				maxWeightLists.add(listNew);
			}else if(currSum < sum){
				//System.out.println("lesser loop");
				checkSum(weightsList,listSize,i+1,currSum-currValue,sum,new ArrayList<Long>(list));
				list.add(currValue);
				checkSum(weightsList,listSize,i+1,currSum, sum,new ArrayList<Long>(list));
				//System.out.println("------testing"+list);
				
			}else{
				//System.out.println("Greater CurrSum"+currSum+" CurrValue"+currValue);
				if(list.size()!=0){
					maxWeightLists.add(new ArrayList<Long>(list));
					//System.out.println("greater="+currSum+"->"+list);
				}
				checkSum(weightsList,listSize,i+1,currSum-currValue,sum,new ArrayList<Long>(list));
			}
		}
		else{
			if(currSum < sum){
				if(list.size()!=0){
					//System.out.println("\narray end="+list);
					maxWeightLists.add(list);
				}
			}
		}
	}
	
	
	
	private static Long getMinutesDifference(String startDateTime,String endDateTime){

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime startTime = LocalDateTime.parse(startDateTime,formatter);
		LocalDateTime endTime = LocalDateTime.parse(endDateTime,formatter);

		Long minuteDifference = Duration.between(startTime, endTime).getSeconds()/60;

		System.out.println(minuteDifference);

		return minuteDifference;

	}

	private static void genToolsUsageSorted() throws IOException, ParseException {
		JSONObject inputJSON = getInput();
		System.out.println(inputJSON.toJSONString());
		JSONArray toolsUsage =  (JSONArray) inputJSON.get("toolUsage");

		//String output = decrypt(encryptedMessage,key);
		JSONArray usage = findUsage(toolsUsage);

		//System.out.println(toolsFound);
		postOutput(writeOutputJSON("toolsSortedOnUsage",usage).toJSONString());
	}





	private static JSONArray findUsage(JSONArray toolsUsage){

		for(int i = 0 ; i < toolsUsage.size() ; i++){
			JSONObject object = (JSONObject) toolsUsage.get(i);
			String name = (String) object.get("name");
			String startTime = (String) object.get("useStartTime");
			String endTime = (String) object.get("useEndTime");
			addUsage(name, getMinutesDifference(startTime, endTime));
		}

		JSONArray output = sortByValue(usageMap);
		return output;

	}
	private static JSONArray sortByValue(Map<String, Long> unsortMap) {

		// 1. Convert Map to List of Map
		List<Map.Entry<String, Long>> list =
				new LinkedList<Map.Entry<String, Long>>(unsortMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
			public int compare(Map.Entry<String, Long> o1,
					Map.Entry<String, Long> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		JSONArray output = new JSONArray();

		for (Map.Entry<String, Long> entry : list) {
			JSONObject object = new JSONObject();
			object.put("name", entry.getKey());
			object.put("timeUsedInMinutes", entry.getValue());
			output.add(object);
		}



		return output;
	}

	private static void addUsage(String name, Long minutes){
		if(usageMap.containsKey(name)){
			usageMap.put(name, usageMap.get(name)+minutes);
		}else{
			usageMap.put(name, minutes);
		}
	}


	private static void genOutputTwo() throws IOException, ParseException {
		JSONObject inputJSON = getInput();
		System.out.println(inputJSON.toJSONString());
		String hiddenTools = (String) inputJSON.get("hiddenTools");
		JSONArray tools =  (JSONArray) inputJSON.get("tools");

		//String output = decrypt(encryptedMessage,key);
		JSONArray toolsFound = findTools(hiddenTools,tools);

		//System.out.println(toolsFound);
		postOutput(writeOutputJSON("toolsFound",toolsFound).toJSONString());
	}
	private static JSONArray findTools(String hiddenTools, JSONArray tools) {
		JSONArray toolsFound = new JSONArray();
		int hiddenToolsLength = hiddenTools.length();
		for(int i = 0 ; i< tools.size();i++){
			String tool = (String) tools.get(i);
			//System.out.println(tool);
			int toolLength = tool.length(), index = 0,var = 0;
			for(; var < toolLength && index < hiddenToolsLength && index >-1 ; var++){
				if(index==0)
					index = hiddenTools.indexOf(tool.charAt(var), index);
				else
					index = hiddenTools.indexOf(tool.charAt(var), index+1);
				//System.out.println(tool.charAt(var)+"-"+index);
			}
			if(index > -1)
				toolsFound.add(tool);
		}
		System.out.println(toolsFound);
		return toolsFound;
	}


}
