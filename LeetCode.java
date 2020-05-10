	import java.io.IOException;
	import java.io.InputStream;
	import java.io.Serializable;
	import java.lang.reflect.InvocationTargetException;
	import java.math.BigDecimal;
	import java.time.Duration;
	import java.time.LocalDateTime;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.BitSet;
	import java.util.Collections;
	import java.util.Comparator;
	import java.util.HashMap;
	import java.util.HashSet;
	import java.util.Iterator;
	import java.util.List;
	import java.util.Map;
	import java.util.Map.Entry;
	import java.util.PriorityQueue;
	import java.util.Set;
	import java.util.SortedSet;
	import java.util.Stack;
	import java.util.TreeMap;
	import java.util.TreeSet;
	import java.util.function.Function;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;
	import java.util.stream.Collectors;
	
	
	/**
	 * @author M1017278
	 *
	 */
	enum Color {
		RED, GREEN
	}
	class Singleton implements Serializable,Cloneable{
		public static String str;
		private static class SingletonSubClass{
			public static final Singleton singletonObject = new Singleton();
		}
	
		private Singleton(){
	
	
		}
		public static Singleton getSingleInstance(){
			return SingletonSubClass.singletonObject;
		}
		protected Object readResolve() {
			return SingletonSubClass.singletonObject;
		}
		@Override
		public Singleton clone(){
			return SingletonSubClass.singletonObject;
		}
	}
	/*enum Singleton {
	
	
	    INSTANCE;
	    public String str;
	    public static Singleton getSingleInstance(){
	        return INSTANCE;
	    }
	}*/
	public class LeetCode {
	
		private int startMaxSeq=0,endMaxSeq=0;
	
		public int[] twoSum(int[] nums, int target) {
			Map<Integer,Integer> map = new HashMap<Integer,Integer>();
			int i=0;
			for(i = 0 ; i < nums.length ; i++){
				int temp = target - nums[i];
				if(map.containsKey(temp))
					return new int[]{map.get(temp),i};
	
				map.put(nums[i], i);
			}
			return null;
		}
		public int[] threeSum(int[] nums, int target) {
			int i=0;
			for(i = 0 ; i < nums.length - 2 ; i++){
				Map<Integer,Integer> map = new HashMap<Integer,Integer>();
				int temp = target - nums[i];
				for(int j = i+1 ; j< nums.length ; j++){
					if(map.containsKey(temp-nums[j])){
						return new int[]{i,map.get(temp-nums[j]), j};
					}
					map.put(nums[j], j);
				}
			}
			return null;
		}
	
		public List<List<Integer>> threeSum(int[] nums) { //{1,0,-1,2,-1,-4}
			int i=0;
			int target =0;
			Arrays.sort(nums);
			//System.out.println(Arrays.toString(nums));
			Set<List<Integer>> finalList = new HashSet<List<Integer>>();
			List<Integer> list = new ArrayList<Integer>();
			for(i = 0 ; i < nums.length - 2 ; i++){
				Map<Integer,Integer> map = new HashMap<Integer,Integer>();
				int temp =  target - nums[i];
				//System.out.println("arr["+i+"]="+nums[i]+" temp="+temp);
				for(int j = i+1 ; j< nums.length ; j++){
					int temp2 = temp - nums[j];
					//System.out.println("arr["+j+"]="+nums[j]+" temp="+temp+" temp2="+temp2);
					if(map.containsKey(temp2)){
						list.add(nums[i]);list.add(temp2); list.add(nums[j]);
						//System.out.println(list);
						finalList.add(list);
						list = new ArrayList<Integer>();
					}
	
					map.put(nums[j], j);
				}
			}
			return finalList.size()!=0?new ArrayList<List<Integer>>(finalList):new ArrayList<List<Integer>>();
		}
		public List<List<Integer>> threeSumtest(int[] nums) { //{1,0,-1,2,-1,-4}
			int i=0;
			int target =0;
			Arrays.sort(nums);
			//System.out.println(Arrays.toString(nums));
			Set<List<Integer>> finalList = new HashSet<List<Integer>>();
			List<Integer> list = new ArrayList<Integer>();
			for(i = 0 ; i < nums.length - 2 ; i++){
				int start = i+1;
				int end = nums.length-1;
	
				while(start < end){
					if(nums[i]+nums[start]+nums[end] == target){
						list.add(nums[i]);list.add(nums[start]); list.add(nums[end]);
						//System.out.println(list);
						finalList.add(list);
						list = new ArrayList<Integer>();
						start++;
						while(start < end && nums[start] == nums[start - 1]) 
							start++;
					}else if(nums[i]+nums[start]+nums[end] < target){
						start++;
					}else
						end--;
				}
	
			}
			return finalList.size()!=0?new ArrayList<List<Integer>>(finalList):new ArrayList<List<Integer>>();
		}
	
	
		public int reverse(int x) {
			boolean sign = false;
			if(x<0){
				x = -x;
				sign = true;
			}
	
			int res = 0 ;
	
			while(x>0){
				res = res*10 + x%10;
				x = x/10;
			}
	
			if(sign)
				res = -res;
	
			return res;
	
		}
	
		public int myAtoi(String str) {
	
			//System.out.println(str);
			str = str.trim();
			int res = 0;
			boolean sign = false;
	
			for(int i = 0 ; i < str.length() ; i++){
	
				char ch = str.charAt(i);
				//System.out.println("ch"+ch);
				if(ch=='-'){
					if(i==0)
						sign = true;
					else
						return sign? -res : res;
				}
				else if(ch == '.'){
					return sign? -res : res;
				}
				else if(Character.isLetter(ch) || ch == ' ' )
					return sign? -res : res;
				else{
					if(!sign && (res >Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE/10 && Integer.parseInt(""+ch) > 7)))
						return Integer.MAX_VALUE;
	
					if(sign && (-res < Integer.MIN_VALUE/10 || (-res == Integer.MIN_VALUE/10 && Integer.parseInt(""+ch) > 8)))
						return Integer.MIN_VALUE;
	
					res = res*10 + Integer.parseInt(""+ch);
					//System.out.println("res"+res);
				}
			}
	
			return sign?-res:res;
		}
	
		public boolean isNumber(String str) {
	
			//System.out.println(str);
			str = str.trim();
			boolean exp = false;
			if(str.length() == 0 || (str.charAt(str.length()-1) == 'e' || str.charAt(str.length()-1) == '.'))
				return false;
			for(int i = 0 ; i < str.length() ; i++){
				char ch = str.charAt(i);
				//System.out.println("ch"+ch);
				if(ch=='-' || ch=='+'){
					if(i==0)
						continue;
					else
						return false;
				}else if(ch == '.' && !exp){
					continue;
				}else if(ch == 'e' && !exp){
					if(i==0)
						return false;
	
					exp=true;
					continue;
				}
				else if(!Character.isDigit(ch))
					return false;
			}
			return true;
		}
		public int lcs(String s1, String s2, int m, int n) {
			if (m == 0 || n == 0)
				return 0;
			if (s1.charAt(m - 1) == s2.charAt(n - 1))
				return 1 + lcs(s1, s2, m - 1, n - 1);
			else
				return Math.max(lcs(s1, s2, m, n - 1), lcs(s1, s2, m - 1, n));
		}
	
		public int minDistance(String word1, String word2) {
			return word1.length() + word2.length() - 2*lcs(word1,word2, word1.length(),word2.length());
		}
	
	
		private boolean isValid(String str){
			str = str.trim();
			int strLength = str.length();
			if(strLength==0)
				return true;
	
			Stack<Character> stack = new Stack<Character>();
	
			for(int i = 0 ; i < strLength ; i++){
				Character ch;
				if(!stack.isEmpty()){
					ch = stack.peek();
					if((ch=='('&& ')' == str.charAt(i)) || 
							(ch=='{'&& '}' == str.charAt(i)) || 
							(ch=='['&& ']' == str.charAt(i)))
						stack.pop();
					else{
						stack.push(str.charAt(i));
					}
				}
				else 
					stack.push(str.charAt(i));
	
	
			}
	
			if(stack.size()==0)
				return true;
			else
				return false;
		}
	
	
		public int longestValidParentheses(String s) {
			int maxans = 0;
			int currentLength;
			int dp[] = new int[s.length()];
			//System.out.println(Arrays.toString(dp));
			for (int i = 1; i < s.length(); i++) {
				if (s.charAt(i) == ')') {
					//System.out.println(i - dp[i - 1] + "---"+ s.charAt(i - dp[i - 1] - 1));
					if (s.charAt(i - 1) == '(') {
						dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
						//System.out.println("s.at("+i+")=( = "+dp[i]);
					} else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
						dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
						//System.out.println("s.at("+i+")!=("+dp[i]);
					}
	
					//System.out.println("test---"+dp[i]);
					currentLength = dp[i];
					if(maxans < currentLength){
						startMaxSeq = i - dp[i]+1;
						endMaxSeq = i+1;
						maxans = currentLength;
						//System.out.println(startMaxSeq+"-"+endMaxSeq);
					}
				}
			}
			return maxans;
		}
	
		public List<String> generateParenthesis(int n) {
	
			List<String> list = new ArrayList<String>();
	
			backtrack(list,"",0,0,n);
			//System.out.println(n*2+"--"+list.size());
			//System.out.println(list);
	
	
			return list;
	
	
		}
	
		public void backtrack(List<String> ans, String cur, int open, int close, int max){
			if (cur.length() == max * 2) {
				ans.add(cur);
				return;
			}
	
			if (open < max){
				//System.out.println(cur+'(');
				backtrack(ans, cur+"(", open+1, close, max);
			}
	
			if (close < open){
				//System.out.println(cur+')');
				backtrack(ans, cur+")", open, close+1, max);
			}
	
		}
		public boolean isValidStr(String str) {
			if(str.length()<3)
				return false;
			while(str.length()!=0){
				int length = str.length();
				str = str.replace("abc","");
				if(length == str.length())
					return false;/*
	            if(!str.contains("abc") && str.charAt(0)!='a')
	                return false;
	            else
	                str = str.replace("abc","");*/
			}
	
			return true;
		}
		List<String> list = new ArrayList<String>();
		Map<String,String> letterMap = new HashMap<String,String>()
		{{
			put("2","abc");
			put("3", "def");
			put("4", "ghi");
			put("5", "jkl");
			put("6", "mno");
			put("7", "pqrs");
			put("8", "tuv");
			put("9", "wxyz");
		}};
		public List<String> letterCombinations(String digits) {
			if(digits.length()!=0)
				getCombinations("",digits);
	
			return list;
		}
		private void getCombinations(String combination,String digits){
			if(digits.length()==0)
				list.add(combination);
			else{
				String digit = ""+digits.charAt(0);
				String letters = letterMap.get(digit);
				if(letters!=null){
					for(int i=0;i<letters.length();i++){
						String currLetter = letters.substring(i,i+1);
						getCombinations(combination+currLetter, digits.substring(1));
					}
				}
			}
		}
		List<String> watchTimes = new ArrayList<String>();
		String getMinute(int num){
			int res=0;
			for(int i=1;i<=num;i++){
				res+= 1<<num;
			}
	
			return ""+res;
		}
		String getSeconds(int num){
			int res=0;
			for(int i=1;i<=num;i++){
				res+= 1<<num;
			}
			if(res/10>0)
				return ":"+res;
			else
				return ":0"+res;
		}
		/*public List<String> readBinaryWatch(int num) {
	
					for(int i = 1; i <= num; i++ ){
						for()
					}
	
			    }*/
	
		public String decodeAtIndex(String str, int k) {
	
			if(str == null || str.length() == 0 || k<0)
				return str;
	
			if(Character.isDigit(str.charAt(0)))
				return null;
	
			String currStr = "";
			int noOfTimes = 0;
			for(int i=0;i<str.length();i++){
				if(currStr.length() >= k)
					return ""+currStr.charAt(k-1);
	
	
	
				char ch = str.charAt(i);
				if(Character.isLetter(ch)){
					currStr += ch;
					noOfTimes = 0;
				}
				else if(Character.isDigit(ch)){
					/*if(noOfTimes>0)
								noOfTimes = Integer.parseInt(""+noOfTimes+Integer.parseInt(""+ch));
							else*/
					noOfTimes = Integer.parseInt(""+ch);
	
					//System.out.println("noOfTimes-->"+noOfTimes);
				}
				else{
					return currStr;
				}
	
				String repeatStr = "";
				for(int j = 1; j <= noOfTimes-1; j++){
					repeatStr += currStr;
	
	
					if((currStr+repeatStr).length() > k){
						//System.out.println("returning in loop"+(currStr+repeatStr).length()+"-"+(currStr+repeatStr));
						return ""+(currStr+repeatStr).charAt(k-1);
					}
				}
				//System.out.println("repeatStr-->"+repeatStr);
	
				if(i+1 != str.length()){// && !Character.isDigit(str.charAt(i+1))){
					currStr += repeatStr;
					noOfTimes = 0;
				}
	
				//System.out.println("currStr-->"+currStr.length()+"-"+currStr);
			}
			String repeatStr="";
			//System.out.println("noOfTimes-Final="+noOfTimes);
			if(noOfTimes>0){
				for(int j = 1; j <= noOfTimes-1; j++){
					repeatStr += currStr;
	
					//System.out.println("currStr+repeatStr="+(currStr+repeatStr).length()+"-"+currStr+repeatStr);
					if((currStr+repeatStr).length() > k)
						return ""+(currStr+repeatStr).charAt(k-1);
				}
			}
			currStr += repeatStr;
			//System.out.println("finalString="+currStr.length()+"-"+currStr);
			if(k <= currStr.length())
				return ""+currStr.charAt(k-1);
			else
				return "";
	
		}
	
		public String decodeAtIndextest(String S, int K) {
			long size = 0;
			int N = S.length();
	
			// Find size = length of decoded string
			for (int i = 0; i < N; ++i) {
				char c = S.charAt(i);
				if (Character.isDigit(c)){
					size *= c - '0';
					//System.out.println(size);
				}
				else{
					size++;
					//System.out.println(size);
				}
			}
	
			for (int i = N-1; i >= 0; --i) {
				char c = S.charAt(i);
				K %= size;
				System.out.println("k="+K);
				if (K == 0 && Character.isLetter(c))
					return Character.toString(c);
	
				if (Character.isDigit(c))
					size /= c - '0';
				else
					size--;
			}
	
			throw null;
		}
		public void reverseString(char[] s) {
	
			for(int i=0,j=s.length-1;i<j;i++,j--){
				char temp = s[i];
				s[i] = s[j];
				s[j] = temp;
			}
		}
	
		static boolean isVowel(char c) { 
			return (c == 'a' || c == 'A' || c == 'e'
					|| c == 'E' || c == 'i' || c == 'I'
					|| c == 'o' || c == 'O' || c == 'u'
					|| c == 'U'); 
		}
		public String reverseVowels(String s) {
			/*List<Character> vowels = new ArrayList<Character>();
					vowels.add('a');
					vowels.add('A');
					vowels.add('e');
					vowels.add('E');
					vowels.add('i');
					vowels.add('I');
					vowels.add('o');
					vowels.add('O');
					vowels.add('u');
					vowels.add('U');*/
			for(int i=0,j=s.length()-1;i<j;i++,j--){
				while(!isVowel(s.charAt(i)) && i < j){
					i++;
				}
				while(!isVowel(s.charAt(j)) && i < j){
					j--;
				}
				if(i<j && isVowel(s.charAt(i)) && isVowel(s.charAt(j))){
					String start = s.substring(0,i);
					String middle = s.substring(i+1,j);
					String end = "";
					if(j!=s.length()-1)
						end = s.substring(j+1,s.length());
	
					s = start + s.charAt(j) +middle + s.charAt(i) + end;
				}
			}
	
			return s;
		}
	
		private void printArray(int[] arr){
	
			System.out.print("[");
			for(int elem : arr){
				System.out.print(elem+" ");
			}
			System.out.println("]");
		}
	
		private void printArrayObject(Object[] arr){
	
			System.out.print("[");
			for(Object elem : arr){
				System.out.print(elem+" , ");
			}
			System.out.println("]");
		}
	
		public void heapSort(int[] arr){
	
			printArray(arr);
			int n = arr.length;
			for(int i=n/2-1;i>=0;i--){
				heapify(arr,n,i);
				printArray(arr);
			}
			System.out.println("------------");
			printArray(arr);
			System.out.println("-----------");
			for(int i=n-1;i>=0;i--){
				int temp = arr[0];
				arr[0] = arr[i];
				arr[i] = temp;
	
				printArray(arr);
				heapify(arr,i,0);
				//System.out.println();
			}
	
			printArray(arr);
	
		}
	
		private void heapify(int[] arr,int n, int i){
			int largest = i;
			int left = 2*i+1;
			int right = 2*i+2;
	
			if(left < n  && arr[left] < arr[largest])
				largest = left;
	
			if(right < n && arr[right] < arr[largest])
				largest = right;
	
			if(largest!=i){
				int temp = arr[largest];
				arr[largest] = arr[i];
				arr[i] = temp;
				System.out.println("changed-"+i+"-"+arr[largest]);
				//printArray(arr);
				heapify(arr,n,largest);
				//printArray(arr);
			}
		}
	
		private void mergeSort(int[] arr,  int start, int end){
	
			if(start<end){
	
				int mid = (start+end) /2;
	
				mergeSort(arr,start,mid);
				mergeSort(arr,mid+1,end);
	
				merge(arr,start,mid,end);
	
			}
	
		}
	
		private void merge(int[] arr, int start, int mid,int end){
			int leftLength = mid - start + 1;
			int rightLength = end - mid;
	
			int left[]  = new int[leftLength];
			int right[]  = new int[rightLength];
	
			for(int i = 0 ; i < leftLength ; i++)
				left[i] = arr[start+i];
			for(int i = 0 ; i < rightLength ; i++)
				right[i] = arr[mid + 1 + i];
	
			int k=start,i=0,j=0;
	
			while(i<leftLength && j<rightLength){
	
				if(left[i] < right[j]){
					arr[k] = left[i];
					i++;
				}else{
					arr[k] = right[j];
					j++;
				}
				k++;
			}
	
			while(i < leftLength)
				arr[k++] = left[i++]; 
	
			while(j < rightLength)
				arr[k++] = right[j++];
	
		}
		private void mergetest(int[] arr, int start, int mid,int end){
			int leftLength = mid - start + 1;
			int rightLength = end - mid;
	
			/*int left[]  = new int[leftLength];
					int right[]  = new int[rightLength];
	
					for(int i = 0 ; i < leftLength ; i++)
						left[i] = arr[start+i];
					for(int i = 0 ; i < rightLength ; i++)
						right[i] = arr[mid + 1 + i];
			 */
			int k=start,i=start,j=mid+1;
			while(i<=mid && j<=end){
	
				if(arr[i] > arr[j]){
					int value = arr[j];
					int index = j;
					while(index != i){
						arr[index] = arr[index-1];
						index--;
					}
					arr[i] = value;
					mid++;
					i++;
					j++;
				}else{
					i++;
				}
	
			}
		}
		private int partition(int arr[], int low, int high) 
		{ 
			int pivot = arr[high];  
			int i = (low-1); // index of smaller element 
			for (int j=low; j<high; j++) 
			{ 
				// If current element is smaller than the pivot 
				if (arr[j] < pivot) 
				{ 
					i++; 
	
					// swap arr[i] and arr[j] 
					int temp = arr[i]; 
					arr[i] = arr[j]; 
					arr[j] = temp; 
				} 
			} 
	
			// swap arr[i+1] and arr[high] (or pivot) 
			int temp = arr[i+1]; 
			arr[i+1] = arr[high]; 
			arr[high] = temp; 
	
			return i+1; 
		} 
	
	
		/* The main function that implements QuickSort() 
			      arr[] --> Array to be sorted, 
			      low  --> Starting index, 
			      high  --> Ending index */
		public void sort(int arr[], int low, int high) 
		{ 
			if (low < high) 
			{ 
				/* pi is partitioning index, arr[pi] is  
			              now at right place */
				int pi = partition(arr, low, high); 
				printArray(arr);
				// Recursively sort elements before 
				// partition and after partition 
				sort(arr, low, pi-1); 
				sort(arr, pi+1, high); 
			} 
		} 
	
	
	
	
		private void kSort(int[] arr, int n, int k)  
		{ 
	
			// min heap 
			PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(); 
	
			// add first k + 1 items to the min heap 
			for(int i = 0; i < k + 1; i++) 
			{ 
				priorityQueue.add(arr[i]); 
			} 
	
			int index = 0; 
			for(int i = k + 1; i < n; i++)  
			{ 
				arr[index++] = priorityQueue.peek(); 
				priorityQueue.poll(); 
				priorityQueue.add(arr[i]); 
			} 
	
			Iterator<Integer> itr = priorityQueue.iterator(); 
	
			while(itr.hasNext())  
			{ 
				arr[index++] = priorityQueue.peek(); 
				priorityQueue.poll(); 
			} 
	
		}
	
		private double findMedianSortedArray(int[] nums){
	
			if(nums == null)
				return 0;
	
	
			int numsLength = nums.length;
	
			if(numsLength == 0)
				return 0;
	
			int mid = numsLength/2;
			if(numsLength%2 == 0){
				System.out.println(Double.valueOf(nums[mid])+"-"+Double.valueOf(nums[mid-1]));
				return (Double.valueOf(nums[mid])+Double.valueOf(nums[mid-1]))/2;
			}else{
				return nums[mid];
			}
		}
	
		private double findMedianSortedArrays(int[] nums1, int[] nums2) {
	
	
			int i=0,j=0;
			int n1 = nums1.length,n2=nums2.length;
	
			if( nums1 == null || nums2 == null )
				return 0;
	
			if(nums1!= null && n1 == 0)
				return findMedianSortedArray(nums2);
	
			if(nums2!= null && n2 == 0)
				return findMedianSortedArray(nums1);
	
			while(i<n1 && j<n2){
				if(nums1[i]>nums2[j]){
					j++;
				}else{
					i++;
				}
				System.out.println(i+"-"+j);
			}
	
			i = ( i-1 < 0 ) ? i : i-1;
			j = ( j-1 < 0 ) ? j : j-1;
	
			if(n1 == n2){
				return (Double.valueOf(nums1[i])+Double.valueOf(nums2[j]))/2;
	
			}else{
				if(nums1[i] > nums2[j])
					return nums1[i];
				else
					return nums2[j];
			}
	
		}
	
	
		public int binarySearch(int[] arr, int elem){
			if(arr!=null && arr.length==0)
				return -1;
	
			return binarySearch(arr,0,arr.length-1,elem);
		}
	
		public int binarySearch(int[] arr, int l, int r, int elem){
			if(l<=r){
				int mid =l+(r-l)/2;
	
				if(arr[mid] == elem)
					return mid;
	
				if(arr[mid] > elem)
					return binarySearch(arr,l,mid-1,elem);
	
				return binarySearch(arr,mid+1,r,elem);
			}
	
			return -1;
	
		}
	
		public int[] twoSumSearch(int[] numbers, int target) {
	
	
			if(numbers == null)
				return null;
			int[] res = new int[2];
	
			if(numbers.length == 0)
				return res;
	
			for(int i = 0 ; i < numbers.length ; i++){
				int index = search(numbers, target-numbers[i]);
				System.out.println(i+"-"+index+"-->"+(target-numbers[i]));
				if(index != -1 & index != i){
					if(index > i ){
						res[0] = i+1;
						res[1] = index+1;
					}else{
						res[1] = i+1;
						res[0] = index+1;
					}
					return res;
				}
	
			}
			return res;
		}
	
		public String longestPalindromicSubstring(String s){
	
			int low = 0, high=0;
	
			for(int i = 0;i < s.length();i++){
				for(int j = s.length()-1;j>=0;j--){
	
					if(i!=j && i<j){
						if(isPalindrome(s.substring(i, j+1))){
							if(j-i > high-low ){
								high =j;
								low = i;
							}
						}
					}else if(i!=j){
						if(isPalindrome(s.substring(j, i+1))){
							if(i-j > high-low ){
								high = i;
								low = i;
							}
						}
					}
				}
			}
	
			return s.substring(low,high+1);
		}
	
		private boolean isPalindrome(String substring) {
			for(int i=0,j=substring.length()-1;i<j;i++,j--)
				if(substring.charAt(i) != substring.charAt(j))
					return false;
	
			return true;
		}
		public String longestPalindrome(String s) {       
			if (s == null ||  s.length() <= 1) return s;        
	
			char[] charArr = s.toCharArray();
			int n = s.length(), max = 1, startIndex = 0 , curr = 0;
	
			for (int j = 0; j < n - 1; j++) {
				int otherSame = 0;
				// a single letter as a center, same letters as a whole center
				while (j + 1 + otherSame < n && charArr[j] == charArr[j + 1 + otherSame]) otherSame++;       
	
	
				System.out.println(otherSame+"-"+(j+otherSame));
				curr = getLength(charArr, j, j + otherSame);
				if(curr > max) {
					max = curr;
					startIndex = j - (curr - otherSame - 1) / 2;
				}
				j += otherSame;                 
	
			}        
			return s.substring(startIndex, startIndex + max);
		}
	
	
		//  return the longest palindrome length based on a center (from left to right).
		// there is 2 kinds cases:
		// 1. single letter 
		// 2. even same letter 
	
		private int getLength(char[] charArr, int left, int right) {
			int i = 1;
			while (left - i >= 0 && right + i < charArr.length) {
				if(charArr[left - i] != charArr[right + i])  break;
				i++;             
			}
			return right - left + 1  + ((i - 1) * 2);
		} 
	
		public String longestPalSubStr(String str){
	
			int maxLength=1;
			int start = 0;
			int len =str.length();
	
			int low,high;
	
			for(int i=1;i<len;i++){
	
				low = i-1;
				high = i;
	
				while(low >= 0 && high < len && 
						str.charAt(low) == str.charAt(high)){
					if(high - low +1 > maxLength){
						start = low;
						maxLength = high - low + 1;
					}
					--low;
					++high;
				}
	
				low = i-1;
				high = i+1;
	
				while(low >= 0 && high < len && 
						str.charAt(low) == str.charAt(high)){
					if(high - low +1 > maxLength){
						start = low;
						maxLength = high - low + 1;
					}
					--low;
					++high;
				}
	
			}
	
			return str.substring(start,start+maxLength);
		}
	
		public String shortestPalindrome(String s) {
	
			if(s == null  || ( s!=null && (s.length()==0 || s.length()==1)))
				return s;
	
			String longPal = longestPalSubStr(s);
	
			if(s.equals(longPal))
				return s;
	
	
			String endString;
			if(s.startsWith(longPal))
				endString = s.substring(longPal.length(),s.length());
			else{
				int i = 1;
				while(i<s.length() && (s.charAt(i) == s.charAt(i-1)))
					++i;
				endString = s.substring(i,s.length());
			}
			endString = reverse(endString);
			return endString+s;
	
	
	
		}
	
		private String reverse(String str){
	
			char[] charArr = str.toCharArray();
	
			for(int i = 0,j=str.length()-1 ; i < j ;i++,j--){
				char temp = charArr[i];
				charArr[i] = charArr[j];
				charArr[j] = temp;
			}
	
			return String.valueOf(charArr);
		}
	
		public String toHexspeak(String num) {
	
			Map<Integer,Character> characterMap = new HashMap<Integer,Character>(){{
				put(0,'O');
				put(1,'I');
			}};
	
			String number = Integer.toHexString(Integer.parseInt(num));
	
	
			for(Map.Entry<Integer,Character> entry : characterMap.entrySet()){
				if(number.contains(""+entry.getKey()))
					number = number.replaceAll(""+entry.getKey(), ""+entry.getValue());
			}
	
			if(number.matches("[2-9]*"))
				return "ERROR";
	
			return number;
	
		}
	
		public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
	
			List<Integer> list = new ArrayList<Integer>();
	
			int jumboTomato = 4;
			int jumboCheese = 1;
			int smallTomato = 2;
			int smallCheese = 1;
	
			boolean jumboTaken = false;
	
			int tomatoLeft,jumboTomatoTaken = 0,smallTomatoTaken = 0;
			int cheeseLeft,cheeseTaken;
			if(tomatoSlices > jumboTomato){
				tomatoLeft = tomatoSlices % jumboTomato;
				jumboTomatoTaken = tomatoSlices / jumboTomato;
	
				cheeseLeft = cheeseSlices - (jumboCheese * jumboTomatoTaken);
				jumboTaken = true;
			}
			else {
				tomatoLeft = tomatoSlices % smallTomato;
				smallTomatoTaken = tomatoSlices / smallTomato;
	
				cheeseLeft = cheeseSlices - (smallCheese * smallTomatoTaken);
			}
	
			System.out.println(jumboTomatoTaken+"-"+cheeseLeft+"-"+smallTomatoTaken+"-"+tomatoLeft+"-"+cheeseLeft);
	
			if(jumboTaken && (tomatoLeft > 0 || cheeseLeft > 0)){
				tomatoLeft = tomatoLeft % smallTomato;
				smallTomatoTaken = tomatoLeft / smallTomato;
				cheeseLeft = cheeseLeft - (smallCheese * smallTomatoTaken);
			}
	
			System.out.println(jumboTomatoTaken+"-"+cheeseLeft+"-"+smallTomatoTaken+"-"+tomatoLeft+"-"+cheeseLeft);
	
			if(tomatoLeft == 0 && cheeseLeft == 0){
				list.add(jumboTomatoTaken);
				list.add(smallTomatoTaken);
			}
	
			return list;
	
		}
	
	
		public int countSquares(int[][] matrix) {
	
			int count = 0;
	
			if(matrix == null || matrix.length == 0)
				return count;
	
			int max;
			if(matrix.length > matrix[0].length)
				max = matrix.length;
			else
				max = matrix[0].length;
	
			for(int i=1;i<=max;i++){
				count += countSquares(matrix,i);
				System.out.println("count="+count);
			}
			return count;
		}
	
		private int countSquares(int[][]matrix, int square){
	
			int count = 0;
			for(int k = 0; k <matrix.length-square+1;k++){
				int countK = 0;
				for(int i = 0 ;i<matrix.length;i++){
	
					//System.out.println(i);
					for(int j= 0 ; j <matrix[i].length;j++){
						System.out.println(i+"-"+j+"->"+matrix[i][j]);
						if(matrix[i][j] == 1)
							countK += 1;
					}
				}
				System.out.println(countK);
				if(countK == square*square)
					count+=1;
	
			}
			return count;
		}
		private boolean uniqueCharacters(String str){
	
			Set<Character> set = new HashSet<Character>();
			for(int i = 0 ;i < str.length();i++){
				set.add(str.charAt(i));
			}
			if(str.length() == set.size())
				return true;
	
			return false;
		}
	
		public int lengthOfLongestSubstring(String str) {
			int maxLength=1;
			int start = 0;
			int len =str.length();
	
			int low,high;
	
	
			for(int i=1;i<len;i++){
	
				low = i-1;
				high = i;
	
				while(low >= 0 && high < len ){
					if(high - low +1 > maxLength &&
							uniqueCharacters(str.substring(low,high+1))){										
						start = low;
						maxLength = high - low + 1;
						//System.out.println(str.substring(low,high+1));
					}
					--low;
					++high;
				}
	
				//System.out.println(low+"-"+high);
				low = i-1;
				high = i+1;
				//System.out.println(low+"-"+high);
				while(low >= 0 && high < len){
					if(high - low +1 > maxLength && 
							uniqueCharacters(str.substring(low,high+1))){
						start = low;
						maxLength = high - low + 1;
						//System.out.println("odd="+str.substring(low,high+1));
					}
					--low;
					++high;
				}
	
			}
	
			return maxLength;
		}
	
		public int findMin(int arr[]) 
		{ 
			// This condition is needed to handle the case when array 
			// is not rotated at all
			if(arr == null)
				return Integer.MIN_VALUE;
	
			int low = 0;
			int high = arr.length-1;
	
	
			if (high <= low)  return arr[0]; 
	
	
			while(low <=high){
	
				if(arr[low]<=arr[high])
					return arr[low];
	
				// Find mid 
				int mid = low + (high - low)/2; /*(low + high)/2;*/
	
				// Check if element (mid+1) is minimum element. Consider 
				// the cases like {3, 4, 5, 1, 2} 
				if (mid < high && arr[mid+1] < arr[mid]) 
					return arr[mid+1]; 
	
				// Check if mid itself is minimum element 
				if (mid > low && arr[mid] < arr[mid - 1]) 
					return arr[mid]; 
	
				// Decide whether we need to go to left half or right half 
				if (arr[high] > arr[mid]){
					high = mid-1;
				}else
					low = mid+1;
			}
			return Integer.MIN_VALUE;
		}
	
		public int search(int[] nums, int target) {
	
			if(nums == null)
				return -1;
			int left = 0;
			int right = nums.length-1;
			int mid = left + ( right - left ) / 2;
			System.out.println("-----------------------------");
			while(left <= right){
	
	
				mid = left + ( right - left ) / 2;
	
				if(nums[mid] == target)
					return mid;
	
				if(nums[left] < nums[mid]){
					if(target >= nums[left]  &&  target <= nums[mid])
						right = mid-1;
					else
						left = mid+1;
	
					continue;
				}
				else if(nums[left] > nums[mid]){
					if(target >= nums[mid] && target <= nums[right]){
						left = mid + 1;
					}else{
						right = mid - 1;
					}
				}else
					left++;
			}
			return -1;
		}
	
	
		public int cutRod(int price[], int n) 
		{
			int val[] = new int[n+1]; 
			val[0] = 0; 
	
			// Build the table val[] in bottom up manner and return 
			// the last entry from the table 
			for (int i = 1; i<=n; i++) 
			{ 
				int max_val = Integer.MIN_VALUE; 
				for (int j = 0; j < i; j++) 
					max_val = Math.max(max_val,  
							price[j] + val[i-j-1]); 
				val[i] = max_val; 
			} 
	
			return val[n]; 
		} 
	
		public List<List<Integer>> groupThePeople(int[] groupSizes) {
	
			List<List<Integer>> groups = new ArrayList<List<Integer>>();
			Set<Integer> personReached = new HashSet<Integer>();
	
			for(int i = 0 ; i < groupSizes.length; i++){
				if(!personReached.contains(i)){
					personReached.add(i);
					List<Integer> group = new ArrayList<Integer>();
					group.add(i);
					for(int j = 1; j < groupSizes[i];j++){
						int groupMember = searchGroupMember(groupSizes,groupSizes[i],i+1,personReached);
						group.add(groupMember);
						personReached.add(groupMember);
					}
					groups.add(group);
				}
			}
	
			return groups;
	
		}
	
	
	
		private int searchGroupMember(int[] groupSizes, int groupValue,int i,
				Set<Integer> personReached) {
	
			for(int j = i ; j < groupSizes.length ; j++){
				if(!personReached.contains(j) && groupValue == groupSizes[j])
					return j;
			}
	
			return -1;
		}
	
		public int smallestDivisor(int[] arr, int threshold) {
			int left = 1, right = threshold;
			while (left < right) {
				int m = (left + right) / 2, sum = 0;
				for (int i : arr)
					sum += (i + m - 1) / m;
				System.out.println("left:"+left+" right:"+right+" sum:"+sum+" threshold:"+threshold);
				if (sum > threshold){
					left = m + 1;
				}
				else{
					right = m;
				}
			}
			return arr.length % 2 == 0? left+1: left;
		}
	
		public String defangIPaddr(String address) {
	
			String res="";
			char[] charArr = address.toCharArray();
	
			for(char ch : charArr){
				if(ch=='.')
					res+="[.]";
				else
					res+=ch;
			}
	
			return res;
		}
	
	
		public String maskPII(String str) {
			String res ="";
	
			if(str.length()<8)
				return str;
	
	
			Pattern emailPattern= Pattern.compile("^([a-zA-Z])[a-zA-Z]*([a-zA-Z])@([a-zA-Z]+)/.([a-zA-Z]+){8,40}$");
	
			Matcher emailMatcher = emailPattern.matcher(str);
	
	
			if(emailMatcher.find()){
				res += emailMatcher.group(1);
				res += "*****";
				res+= emailMatcher.group(2);
				res+="@";
				res += emailMatcher.group(3);
				res += '.'+emailMatcher.group(4);
	
			}
	
			Pattern phone10Pattern= Pattern.compile("^[/d]{2}-[+-/(/)/ /d]+{4,6}");
			Matcher phone10Matcher = phone10Pattern.matcher(str);
	
	
			if(phone10Matcher.find()){
				res += phone10Matcher.group(1);
				res += "*****";
				res+= phone10Matcher.group(2);
				res+="@";
				res += phone10Matcher.group(3);
				res += '.'+phone10Matcher.group(4);
	
			}
	
			return res;
		}
	
		public boolean checkRecord(String s) {
	
			char[] charArr = s.toCharArray();
			int absent = 0, late = 0;
			for(char ch : charArr){
				if(ch == 'A'){
					++absent;
					if(absent > 1)
						return false;
	
					late = 0;
				}
				else if(ch == 'L'){
					++late;
					if(late > 2 )
						return false;
	
				}else{
					late = 0;
				}
			}
	
			return absent > 1 ? false : true;
	
		}
	
		public int numJewelsInStones(String J, String S) {
	
			int jewelStonesCount =0;
			Map<Character,Integer> stonesMap = new HashMap<Character,Integer>();
			char[] stones = S.toCharArray();
			for(char ch: stones)
				stonesMap.put(ch, stonesMap.getOrDefault(ch, 0)+1);
	
			char[] jewelStones = J.toCharArray();
			for(char ch: jewelStones){
				jewelStonesCount += stonesMap.getOrDefault(ch, 0);
			}
			return jewelStonesCount;
		}
	
		public boolean canConstruct(String ransomNote, String magazine) {
			Map<Character,Integer> ransomNoteCharMap = new HashMap<Character,Integer>();
			char[] ransomNoteChars = ransomNote.toCharArray();
			for(char ch: ransomNoteChars)
				ransomNoteCharMap.put(ch, ransomNoteCharMap.getOrDefault(ch, 0)+1);
	
			char[] magazineChars = magazine.toCharArray();
			Map<Character,Integer> magazineCharMap = new HashMap<Character,Integer>();
			for(char ch: magazineChars)
				magazineCharMap.put(ch, magazineCharMap.getOrDefault(ch, 0)+1);
	
	
			for(Entry<Character,Integer> entry : ransomNoteCharMap.entrySet()) {
				if(magazineCharMap.getOrDefault(entry.getKey(),0) < entry.getValue())
					return false;
			}
	
			return true;
		}
	
		public int oddCells(int n, int m, int[][] indices) {
	
			if(indices == null || indices.length == 0)
				return 0;
	
			int count = 0;
	
			int[][] arr = new int[n][m];
	
			for(int i = 0 ;i < indices.length;i++){
	
				int j = indices[i][0],k = indices[i][1];
	
				for(int var=0;var < m;var++){
					arr[j][var]++;
				}
				for(int var=0;var < n;var++){
					arr[var][k]++;
				}
			}
	
			for(int i = 0 ; i < n ; i++){
				for(int j = 0 ;j <m;j++){
					if(arr[i][j]%2 == 1)
						count++;
				}
			}
	
			return count;
		}
	
		public int numMatchingSubseq(String S, String[] words) {
	
			int count = 0;
	
	
			for(int i=0;i<words.length;i++){
	
				char[] charArr = words[i].toCharArray();
				int j = 0;int start = 0;
				for(;j<charArr.length;j++){
					if((start = S.indexOf( charArr[j] , start) ) != -1)
						start++;
					else
						break;
				}
				if(j==charArr.length)
					count++;
			}
	
			return count;
		}
	
		public int distanceBetweenBusStops(int[] distance, int start, int destination) {
	
	
			if(distance == null || distance.length == 0)
				return 0;
	
			int length = distance.length;
	
			if(start == destination)
				return 0;
	
			if(start < 0 || destination < 0 )
				return 0;
	
			if(start > length || destination > length)
				return 0 ;
	
			int forward =0,backward =0;
			if(start>destination){
				int temp = start;
				start = destination;
				destination = temp;
			}
	
			for(int i = start ; i < destination ; i++){
				forward += distance[i];
				//System.out.println("forward"+forward);
			}
			int i = destination; 
	
			for(;i<length;i++){
				backward += distance[i];
				//System.out.println("backward"+backward);
				//System.out.println("i="+i);
			}
			i=0;
			for(;i<start;i++){
				backward += distance[i];
				//System.out.println("backward"+backward);
				//System.out.println("i="+i);
			}
	
	
	
			return forward<=backward?forward:backward;
	
		}
	
	
	
		/**
		 * @param prices array for each length cut
		 * @return maxProfit for the optimum cut
		 */
		/**
		 * @param prices
		 * @return
		 */
		/**
		 * @param prices
		 * @return
		 */
		public int maxProfit(int[] prices) {
	
	
			int[] val = new int[prices.length+1];
			val[0] = 0;
			for(int j = 1; j <= prices.length ; j++){
	
				int max = Integer.MIN_VALUE;
				for(int i = 0 ; i < j ; i++){
					//System.out.println(i+"-"+j+"-"+val[j-i-1]);
					max = Math.max(max, prices[i]+val[j-i-1]);
				}
	
				System.out.println(j + "-" + max);
				val[j] = max;
			}
	
	
	
			return val[prices.length];
		}
	
		/**
		 * @param s - string to check whether it is subsequence of t
		 * @param t - string to see for s is present as subsequence
		 * @return boolean value whether s is subsequence of t
		 */
		public boolean isSubsequence(String s, String t) {
	
			char[] charArr = s.toCharArray();
	
			int start = 0;
			for(char ch : charArr){
	
				if((start = t.indexOf(ch,start))!=-1)
					++start;
				else
					break;
			}
	
			return start==-1?false:true;
	
		}
	
		/**
		 * @param edges for the undirected graph
		 * @return edge which makes tree to cycle
		 */
		public int[] findRedundantConnection(int[][] edges) {
	
			//Map<Integer,List<Integer>> map = new TreeMap<Integer,List<Integer>>(/());
	
	
			return edges[0];
	
		}
		SortedSet<Map.Entry<Integer,List<Integer>>> entriesSortedByValues(Map<Integer,List<Integer>> map) {
			SortedSet<Map.Entry<Integer,List<Integer>>> sortedEntries = new TreeSet<Map.Entry<Integer,List<Integer>>>(
					new Comparator<Map.Entry<Integer,List<Integer>>>() {
	
						@Override
						public int compare(
								Entry<Integer, List<Integer>> o1,
								Entry<Integer, List<Integer>> o2) {
							return new Integer(o1.getValue().size()).compareTo(o2.getValue().size());
						}}
					);
			sortedEntries.addAll(map.entrySet());
			return sortedEntries;
		}
	
		/**
		 * @param str - String for converting to lowercase
		 * @return converted lowercase string
		 */
		public String toLowerCase(String str) {
	
			String s="";
	
			char[] charArr = str.toCharArray();
	
			for(char ch : charArr){
	
				int chNo = ch;
				if(chNo>=65 && chNo<=90)
					s+=(char)(ch-65+97);
				else
					s+=ch;
			}
	
	
			return s;
		}
	
	
		/**
		 * @param word - word to check whether it is properly capitalized
		 * @return boolean value whether it is properly capitalized
		 */
		public boolean detectCapitalUse(String word) {
			boolean flag = false;
			int length = word.length();
			for(int i = 0 ; i < length ; i++){
				char ch = word.charAt(i);
				if(Character.isUpperCase(ch)){
					if(i!=0 && flag==false)
						return false;
					else if(i!=0){
						if(Character.isUpperCase(word.charAt(i-1)))
							flag = true;
						else
							return false;
					}else
						flag= true;
				}
				else{
					if(i!=0){
						if(i-1!=0 && Character.isUpperCase(word.charAt(i-1)))
							return false;
						else
							flag = false;
					}
				}
			}
			return true;
		}
	
		private int reverseNumber(int n){
	
	
			System.out.println(n);
			int res = 0;
	
			while(n > 0){
				int rem = n%10;
				n = n/10;
				res = res*10 +rem;
				System.out.println("res:"+res+" n:"+n);
			}
	
			return res;
		}
	
	
		private int append(int m, int n){
			int copyN = n;
			n = reverseNumber(n);
			System.out.println("reverse :"+ n);
			while(n > 0){
				int rem = n%10;
				n = n/10;
				m = m*10 + rem;
			}
			while(copyN%10 == 0){
				copyN =copyN/10;
				m = m * 10;
			}
			System.out.println("m:"+m);
			return m+n;
		}
	
		/**
		 * @param number to find next greater element with same digits
		 * @return next greater element with same digits
		 */
		/*public int nextGreaterElement(int n) {
	
	
			        if(n/10 == 0)
			        	return -1;
	
			        int res = 0 ;
	
			        int unit = n%10;
			        n/=10;
			        int mid = 0;
			        int rem = -1;
			        boolean flag = false;
			        while(n>0){
			        	rem = n % 10;
			        	n=n/10;
			        	if(rem < unit){
			        		flag = true;
			        		break;
			        	}
			        	else{
			        		mid = mid*10 + rem;
			        	}
			        }
	
			        if(n==0){
			        	if(flag){
			        		res = unit*10;
			        		while(rem >0){
			        			int temp = rem % 10;
	
			        		}
	
			        	}else
			        		return -1;
			        }
	
	
	
			        return append(500,510);
	
			    }*/
	
	
		/**
		 * @param IP address to check whether is either IPv4 or IPv6 or Neither
		 * @return String either "IPv4" or "IPv6" or "Neither"
		 */
		public String validIPAddress(String IP) {
			if(IP == null || (IP = IP.trim()).length()==0)
				return "Neither";
			Pattern ipv4Pattern = Pattern.compile(
					"^([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]|0[0-9][0-9])[.]"
							+ "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]|0[0-9][0-9])[.]"
							+ "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]|0[0-9][0-9])[.]"
							+ "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]|0[0-9][0-9])$");
			Matcher ipv4Matcher = ipv4Pattern.matcher(IP);
			if(ipv4Matcher.find()){
				String zone1 = ipv4Matcher.group(1);
				String zone2 = ipv4Matcher.group(2);
				String zone3 = ipv4Matcher.group(3);
				String zone4 = ipv4Matcher.group(4);
				//System.out.println(zone1+"-"+zone2+"-"+zone3+"-"+zone4);
				if((zone1.length()>1 && zone1.startsWith("0"))||
						(zone2.length()>1 && zone2.startsWith("0"))||
						(zone3.length()>1 && zone3.startsWith("0"))||
						(zone4.length()>1 && zone4.startsWith("0"))|| 
						Integer.parseInt(zone1) > 255||
						Integer.parseInt(zone2) > 255||
						Integer.parseInt(zone3) > 255||
						Integer.parseInt(zone4) > 255){
					return "Neither";
	
				}else{
					return "IPv4";
				}
			}
			Pattern ipv6Pattern = Pattern.compile(
					"^([0-9a-fA-F]{1,4})[:]"
							+ "([0-9a-fA-F]{1,4})[:]"
							+ "([0-9a-fA-F]{1,4})[:]"
							+ "([0-9a-fA-F]{1,4})[:]"
							+ "([0-9a-fA-F]{1,4})[:]"
							+ "([0-9a-fA-F]{1,4})[:]"
							+ "([0-9a-fA-F]{1,4})[:]"
							+ "([0-9a-fA-F]{1,4})$");
			Matcher ipv6Matcher = ipv6Pattern.matcher(IP);
			if(ipv6Matcher.find()){
				return "IPv6";
			}
			return "Neither";
		}
	
		private List<Integer> splitByThreeLetters(int num){
	
			int count=0;
			List<Integer> numList = new ArrayList<Integer>();
			int res=0,i=0;
	
			while(num > 0){
	
				int rem = num % 10;
				num /= 10;
	
				res =rem*(pow(10,i)) + res;
				//System.out.println(res+"-"+rem+"-"+i+"-"+pow(10,i));
				if(count==2){
					numList.add(res);
					count=0;
					res=0;
					i=0;
				}else{
					i++;
					count++;
				}
			}
	
	
			if(res != 0)
				numList.add(res);
	
			//System.out.println(numList);
	
			return numList;
		}
	
		Map<Integer,String> numToWords = new HashMap<Integer,String>(){{
			put(1,"One");
			put(2,"Two");
			put(3,"Three");
			put(4,"Four");
			put(5,"Five");
			put(6,"Six");
			put(7,"Seven");
			put(8,"Eight");
			put(9,"Nine");
			put(10,"Ten");
			put(11,"Eleven");
			put(12,"Twelve");
			put(13,"Thirteen");
			put(14,"Fourteen");
			put(15,"Fifteen");
			put(16,"Sixteen");
			put(17,"Seventeen");
			put(18,"Eighteen");
			put(19,"Nineteen");
			put(20,"Twenty");
			put(30,"Thirty");
			put(40,"Forty");
			put(50,"Fifty");
			put(60,"Sixty");
			put(70,"Seventy");
			put(80,"Eighty");
			put(90,"Ninety");
			put(100,"Hundred");
			put(1000,"Thousand");
			put(1000000,"Million");
			put(100000000,"Billion");
		}};
	
		private String stringToAdd(int i){
			switch(i){
			case 1 : return numToWords.get(1000);
			case 2 : return numToWords.get(1000000);
			case 3 : return numToWords.get(100000000);
			default : return "";
			}
		}
	
		private boolean checkPrevious(List<Integer> list,int n){
	
			if(n == 0 )
				return true;
	
			if(list.get(n-1) ==0)
				return false;
			else
				return true;
	
		}
	
		public String numberToWords(int num) {
	
			if(num < 0)
				return "Negative Number";
	
			if(num == 0)
				return "Zero";
	
	
			String res ="";
	
			List<Integer> splittedNums = splitByThreeLetters(num);
	
			//System.out.println("length:"+splittedNums.size());
			//System.out.println("list:"+splittedNums);
			int length = splittedNums.size();
	
			for(int i = 0; i < length ; i++){
				if(checkPrevious(splittedNums,i)){
					res = stringToAdd(i) +" " + res;
				}else{
					//System.out.println("i===="+i + " res:===" +res);
					if(res.length()>0){
						res = res.replace(res.split(" ")[0],"").trim();
						res = stringToAdd(i) + " " + res;
					}else{
						res = stringToAdd(i) + " " + res;
					}
				}
	
				int curr = splittedNums.get(i);
				int pow = 0;
				//System.out.println(curr);
				if(numToWords.get(curr%100) !=null ){
					res = numToWords.get(curr%100) + " " + res;
					curr /= 100;
					pow=2;
					res = res.trim();
				}
				while(curr > 0){
					//if(numToWords.get(curr%100))
					int rem = curr % 10;
					curr /= 10 ;
					String currText = numToWords.get(rem * (pow(10,pow)));
					if(pow  == 2) {
						currText = numToWords.get(rem) + " " + numToWords.get(100);
					}
	
					pow++;
					if(currText != null){
						res = currText + " " + res ;
					}
	
				}
				res =res.trim();
			}
	
	
			return res.trim();
	
		}
	
		/**
		 * @param nums array to find duplicates
		 * @return array length removing duplicates 
		 */
		public int removeDuplicates(int[] nums) {
	
			if(nums == null || nums.length == 0)
				return 0;
	
			int length = nums.length;
	
			int elem = nums[0];
			int k=1;
			printArray(nums);
			System.out.println();
			for(int i = 1 ; i < length ;){
				while(i < length && nums[i] == elem){
					i++;
				}
				if(i != length){
					nums[k++] = nums[i++];
					if(i!=length){ 
						elem = nums[k-1];
					}else{
						elem = nums[i-1];
					}
				}else
					break;
				printArray(nums);
				System.out.println(k+" "+i+" "+elem);
	
			}
			printArray(nums);
	
			if(nums[k-1] != elem)
				nums[k++] = elem;
	
			printArray(nums);
			return k;
		}
	
		public int removeElement(int[] nums, int val) {
	
			if(nums == null || nums.length ==0)
				return 0;
	
			//printArray(nums);
			int length = nums.length;
			for(int i = 0 ; i < length ;){
				int currvar = i,curr =i;
				while(i < length && nums[i] == val){
					i++;
				}	
				int j = i;
				if(curr != j){
					while(j<length){
						nums[currvar++] = nums[j++];
					}
					length = currvar;
					i = curr;
				}else
					i++;
			}
			//printArray(nums);
			return length;
		}
	
		public int removeMultipleDuplicates(int[] nums) {
	
			if(nums == null || nums.length ==0)
				return 0;
	
			int length = nums.length;
	
			if(length <= 2)
				return length;
	
			int val = nums[1];
			boolean flag= false;
			printArray(nums);
			for(int i = 2 ; i < length ;){
				int currvar = i;
				while(i < length && nums[i] == val){
					if(nums[i-1] == val && nums[i-2] == val){
						flag= true;
						break;
					}
					else
						i++;
				}	
				int j = i+1; currvar = i;
				if(flag){
					while(j<length){
						nums[currvar++] = nums[j++];
					}
					length = currvar;
					flag = false;
				}else{
					if(i<length){
						val= nums[i];
						i++;
					}else
						break;
				}
	
			}
			printArray(nums);
			return length;
		}
	
		public void moveZeroes(int[] nums) {
			if(nums == null || nums.length == 0)
				return ;
	
			int length = nums.length;
			int i =0, j=0;
			for(;i<length && j < length;){
				if(nums[i] == 0){
					while(j<length && nums[j] == 0)
						j++;
	
					if(j < length){
						nums[i] = nums[j];
						nums[j] = 0;
					}
				}
				i++;
				j++;
			}
		}
	
		public int[] plusOne(int[] digits) {
	
	
			if(digits ==null || digits.length == 0)
				return digits;
	
			int rem = 0;
			int length = digits.length;
			for(int i = length -1; i >=0 ; i--){
	
				int curr = digits[i];
				int sum = rem + curr;
				if(i == length-1)
					sum+=1;
	
				digits[i] = sum % 10;
	
				rem = sum / 10;
			}
	
			if(rem != 0){
				List<Integer> list = new ArrayList<Integer>();
				list.add(rem);
				Arrays.stream(digits).forEach(e->list.add(e));
	
				digits = list.stream().mapToInt(i -> i).toArray();
	
			}
	
			return digits;
	
		}
	
		public List<Integer> addToArrayForm(int[] A, int K) {
			if(A == null)
				return null;
	
			List<Integer> list = new ArrayList<Integer>();
			if(K == 0){
				Arrays.stream(A).forEach(e->list.add(e));
				return list;
			}
			int length = A.length;
			int i  = length - 1, rem = 0;
			while( K > 0 && i >= 0){
				int curr = A[i];
				int currRem = K % 10;
				int sum = rem + curr + currRem;
				rem = sum / 10;
				K = K / 10;
				A[i--] = sum  % 10;
			}
			System.out.println(K+" ");
			while(K>0){
				int sum = rem + (K % 10);
				rem = sum / 10;
				list.add(0,sum % 10);
				K /= 10;
			}
			int j = i;
			while( i >= 0 ){
				int sum = rem + A[i--];
				rem = sum / 10;
				list.add(0,sum%10);
			}
			if( rem != 0 )
				list.add(0,rem);
	
			Arrays.stream(Arrays.copyOfRange(A, j+1, length)).forEach(e->list.add(e));
			return list;
		}
	
		public String addStrings(String num1, String num2) {
	
			if(num1 == null || num2 == null)
				return null;
	
			int num1Length = num1.length(),num2Length = num2.length();
			if(num1Length == 0 && num2Length == 0)
				return "";
	
			if(num1Length == 0)
				return num2;
	
			if(num2Length == 0)
				return num1;
	
			int i = num1Length - 1 , j = num2Length-1,rem = 0;
			String res="";
			while( i >= 0 && j >= 0 ){
				int sum = rem + (num1.charAt(i--)-'0') + (num2.charAt(j--)-'0');
				int currRem = sum % 10 ;
				res = currRem + res;
				rem = sum / 10;
			}
	
			while(i >=0 ){
				int sum = rem + (num1.charAt(i--)-'0');
				int currRem = sum % 10 ;
				res = currRem + res;
				rem = sum / 10;
			}
			while(j >=0 ){
				int sum = rem + (num2.charAt(j--)-'0');
				int currRem = sum % 10 ;
				res = currRem + res;
				rem = sum / 10;
			}
			if(rem !=0)
				res = rem + res;
	
			return res;
		}
		public String multiply(String num1, String num2) {
	
			if(num1 == null || num2 == null)
				return null;
	
			int num1Length = num1.length(),num2Length = num2.length();
			if(num1Length == 0 && num2Length == 0)
				return "";
	
			if(num1Length == 0)
				return num2;
	
			if(num2Length == 0)
				return num1;
	
			int i = num1Length - 1 , j = num2Length-1;
			String res="0";
			int count = 0;
			while( i >= 0 ){
				j = num2Length-1; 
				String currRes=""; 
				int currCh1 = num1.charAt(i--)-'0';
				int rem = 0;
				if(currCh1 != 0){
					while(j >= 0){
						int mul = (currCh1 * (num2.charAt(j--)-'0')) + rem;
						int currRem = mul % 10;
						currRes = currRem + currRes;
						rem = mul / 10;
					}
					if(rem != 0)
						currRes = rem + currRes;
				}else
					currRes="0";
				if(!currRes.equals("0"))
					currRes = currRes + generateZeroes(count);
	
				res = addStrings(currRes, res);
				count++;
			}
	
			return res;
	
		}
	
		private String generateZeroes(int count){
			String res="";
			while(count-->0){
				res+="0";
			}
			return res;
		}
	
		public String addBinary(String a, String b) {
	
			if(a == null || b == null)
				return null;
	
			int aLength = a.length(),bLength = b.length();
			if(aLength == 0 && bLength == 0)
				return "";
	
			if(aLength == 0)
				return b;
	
			if(bLength == 0)
				return a;
	
			int i = aLength-1,j=bLength-1,s=0;
			String res ="";
			while(i>=0|| j>=0 || s>0){
	
				s += ((i>=0)?(a.charAt(i--)-'0'):0);
				s += ((j>=0)?(b.charAt(j--)-'0'):0);
	
				res = (char)(s%2 + '0') + res;
				s = s/2;
			}
	
			return res;
	
		}
	
	
		public String[] reorderLogFiles(String[] logs) {
	
	
			List<String[]> set = new ArrayList<String[]>();
	
			Arrays.stream(logs).forEach(e -> set.add(e.split(" ")));
	
			Collections.sort(set,getStringArrayComparator());
	
			List<String> list = set.stream().map(e -> Arrays.stream(e).collect(Collectors.joining(" "))).collect(Collectors.toList());
	
			logs = list.toArray(new String[set.size()]);
	
			return logs;
		}
	
		private Comparator<String[]> getStringArrayComparator(){
	
			return new Comparator<String[]>() {
	
				@Override
				public int compare(String[] o1, String[] o2) {
					String o1Second = o1[1];
					String o2Second = o2[1];
					boolean isO1SecondDigit = Character.isDigit(o1Second.charAt(0));
					boolean isO2SecondDigit = Character.isDigit(o2Second.charAt(0));
	
					if(isO1SecondDigit && isO2SecondDigit){
						return o2Second.compareTo(o1Second);
					}
	
					if(isO1SecondDigit && !isO2SecondDigit)
						return 1;
	
					if(!isO1SecondDigit && isO2SecondDigit)
						return -1;
					else{
						int returnval =  o1Second.compareTo(o2Second);
	
						if(returnval == 0){
							int returnval1 = o1[2].compareTo(o2[2]);
							if(returnval1 == 0){
								return o1[0].compareTo(o2[0]);
							}
							else
								return returnval1;
						}
						else
							return returnval;
					}
	
				}
			};
		}
	
	
		public String removeOuterParentheses(String S) {
	
			if(S==null)
				return null;
	
			int length = S.length();
			Stack<Character> stack = new Stack<Character>();
			String str ="";
			for(int i=0;i<length;i++){
				char ch = S.charAt(i);
	
				if(ch == '('){
					if(!stack.isEmpty()){
						str = str + ch;
					}
	
					stack.push(ch);
				}
				else if(ch == ')'){
					stack.pop();
					if(!stack.isEmpty()){
						str = str + ch;
					}
				}
			}
			return str;
		}
	
	
		public int[][] flipAndInvertImage(int[][] A) {
			if(A == null)
				return null;
	
			for(int i=0;i<A.length;i++){
				int j = 0,k= A[i].length-1;
	
				while(j<=k){
					int temp = A[i][j];
					A[i][j++] = (A[i][k] == 0)?1:0;
					A[i][k--] = (temp == 0)?1:0;
				}
	
			}
			return A;
	
		}
	
		public int findPeakElement(int[] nums) {
	
			if(nums == null||nums.length == 0)
				return -1;
	
			int length = nums.length;
			if(length == 1)
				return 0;
	
			int index = findPeak(nums,0,length-1);
			if(index != -1 && index != 0)
				return index;
			else{
				index = findPeak(nums,length/2-1, length-1);
				return index !=-1?index:0;
			}
		}
	
		public int findInMountainArray(int target, MountainArray mountainArr) {
			if(mountainArr == null||mountainArr.length() == 0)
				return -1;
	
			int length = mountainArr.length();
			if(length == 1)
				return 0;
	
			int index = findPeak(mountainArr,0,length-1,length);
			if(index != -1 && index != 0)
				return index;
			else{
				index = findPeak(mountainArr,length/2-1, length-1,length);
				return index !=-1?index:0;
			}
		}
	
		private int findPeak(MountainArray mountainArr, int start, int end,int length){
			if(start > end)
				return -1;
	
			int mid = start + (end-start)/2;
			if(mid > 0 && mid < length -1){
				if(mountainArr.get(mid)>mountainArr.get(mid-1) && mountainArr.get(mid) > mountainArr.get(mid+1))
					return mid;
			}else{
				if(mid ==0){
					if(mountainArr.get(mid)>mountainArr.get(mid+1))
						return mid;
				}
				if(mid == length-1){
					if(mountainArr.get(mid)>mountainArr.get(mid-1))
						return mid;
				}
			}
			int index = findPeak(mountainArr,start,mid-1,length);
			if(index != -1 && index != 0)
				return index;
	
			else{
				index = findPeak(mountainArr,mid+1, end,length);
				return index !=-1?index:0;
			}
		} 
		private int findPeak(int[] nums, int start, int end){
			if(start > end)
				return -1;
	
			int mid = start + (end-start)/2;
			if(mid > 0 && mid < nums.length -1){
				if(nums[mid]>nums[mid-1] && nums[mid] > nums[mid+1])
					return mid;
			}else{
				if(mid ==0){
					if(nums[mid]>nums[mid+1])
						return mid;
				}
				if(mid == nums.length-1){
					if(nums[mid]>nums[mid-1])
						return mid;
				}
			}
			int index = findPeak(nums,start,mid-1);
			if(index != -1 && index != 0)
				return index;
	
			else{
				index = findPeak(nums,mid+1, end);
				return index !=-1?index:0;
			}
		}
	
		public int[] sortedSquares(int[] A) {
	
			if(A == null || A.length == 0)
				return A;
	
			int length = A.length;
			for(int i = 0; i<length ; i++){
	
				int square = A[i] * A[i],copy = square;
				int j=0;
				while(j<i){
					if(square < A[j]){
						int curr = j; j=i;
	
						while(j>0 && j>curr){
							A[j] = A[j-- - 1];
						}
						A[j] = square;
						break;
					}
					j++;
				}
				if(j==i && A[j] != copy){
					A[j] = copy;
				}		
			}
			return A;
		}
	
		public void merge(int[] nums1, int m, int[] nums2, int n) {
			if(nums1 == null || nums2 == null)
				return;
	
			if(m < 0 || n< 0)
				return;
	
			if(nums2.length  < n || nums1.length < m)
				return;
	
			int i=0,j=0;
			while(i < m && j < n){
				System.out.println("i="+i+" j="+j);
				if(i<m && nums2[j] <nums1[i]){
					System.out.println(m);
					int curr = i;i = m;
					while(i>0 && i>curr){
						nums1[i] = nums1[i-- - 1];
					}
					nums1[i++] = nums2[j++];
					printArray(nums1);
					m=m+1;
				}else
					i++;
	
			}
	
			if(j<n){
				while(i<nums1.length && j<nums2.length){
					nums1[i++] = nums2[j++];
				}
			}
	
		}
	
		public boolean uniqueOccurrences(int[] arr) {
			Map<Integer,Integer> occurencesMap = new TreeMap<Integer,Integer>();
			for(int i=0;i<arr.length;i++){
				if(!occurencesMap.containsKey(arr[i]))
					occurencesMap.put(arr[i],1);
				else
					occurencesMap.put(arr[i],occurencesMap.get(arr[i])+1);
			}
	
			return checkUniqueOccurance(occurencesMap);
		}
	
		private boolean checkUniqueOccurance(Map<Integer,Integer> map) {
			SortedSet<Map.Entry<Integer,Integer>> sortedEntries = new TreeSet<Map.Entry<Integer,Integer>>(
					new Comparator<Map.Entry<Integer,Integer>>() {
	
						@Override
						public int compare(
								Entry<Integer, Integer> o1,
								Entry<Integer, Integer> o2) {
							return new Integer(o1.getValue()).compareTo(o2.getValue());
						}}
					);
			sortedEntries.addAll(map.entrySet());
	
			if(sortedEntries.size() == map.size())
				return true;
	
	
			return false;
		}
	
		public int[] sortArrayByParity(int[] nums) {
	
			if(nums == null || nums.length == 0)
				return nums;
	
			int length = nums.length;
			int i =0, j=0;
			for(;i<length && j < length;){
				if(nums[i]%2 != 0){
					while(j<length && nums[j]%2 != 0)
						j++;
	
					if(j < length){
						int temp = nums[i];
						nums[i] = nums[j];
						nums[j] = temp;
					}
				}
				i++;
				j++;
			}
			return nums;
		}
	
		private boolean isSelfDividing(int n){
	
			if(n/10 == 0)
				return true;
	
			int copy = n;
			while(n>0){
				int rem = n%10;
	
				if(rem != 0){
					if(copy % rem != 0)
						return false;
				}else
					return false;
	
				n=n / 10;
			}
			return true;
		}
	
		public List<Integer> selfDividingNumbers(int left, int right) {
			List<Integer> list = new ArrayList<Integer>();
	
			for(int i=left;i<=right;i++){
	
				if(isSelfDividing(i)){
					list.add(i);
				}
			}
			return list;
		}
	
		private List<Integer> getDivisors(int num){
	
			List<Integer> divisors = new ArrayList<Integer>();
			divisors.add(1);
			int half = num/2;
			int i=2;
			while(i<=half){
				if(num%i == 0)
					divisors.add(i);
	
				i++;
			}
	
			return divisors;
		}
	
		private int getDivisorSum(int num){
	
			int sum = 1;
			int half = num/2;
			int i=2;
			while(i<=half){
				if(num%i == 0)
					sum+=i;
	
				i++;
			}
	
			return sum;
		}
	
	
		public boolean checkPerfectNumber(int num) {
	
			if(num == 1)
				return false;
	
			int divisorSum = getDivisorSum(num);
	
			return divisorSum == num?true:false;
		}
		static int[] powArr;
		private static int pow(int a,int b){
	
			if(b==0)
				return 1;
	
			if(powArr[b-1] != 0)
				return powArr[b-1];
	
			powArr[b-1] = pow(a,b-1)*a;
			return powArr[b-1];
		}
	
		private static void printSeries(int a, int b,int n){
			powArr = new int[n];
			String resStr="";
			for(int i=0;i<n;i++){
				int res=0;
				for(int j=0;j<=i;j++)
					res += (b*pow(2,j));
				System.out.println("i="+i+"res="+res);
				resStr +=(a+res)+(i!=n-1?" ":"");
			}
			System.out.println(resStr);
		}
	
	
		public void operate(BitSet B1,
				BitSet B2,
				String operation,
				int operand1,
				int operand2){
			switch(operation){
			case "AND": 
				if(operand1 == 1){
					B1.and(B2);
				}else{
					B2.and(B1);
				}
				break;
			case "OR":
				if(operand1 == 1)
					B1.or(B2);
				else
					B2.or(B1);
				break;
			case "XOR":
				if(operand1 == 1)
					B1.xor(B2);
				else
					B2.xor(B1);
				break;
	
			case "FLIP":
				if(operand1 == 1)
					B1.flip(operand2);
				else
					B2.flip(operand2);;
					break;
			case "SET":
				if(operand1 == 1)
					B1.set(operand2);
				else
					B2.set(operand2);;
					break;	
			}
			System.out.println(B1.cardinality()+" "+B2.cardinality());
		}
	
	
		public static int countPairs(List<Integer> numbers, int k) {
	
			Set<Integer> set = new HashSet<Integer>();
			int n = numbers.size(),count=0;
			for (int i = 0; i < n; i++)  
			{ 
				for (int j = i + 1; j < n; j++) 
					if (numbers.get(i) - numbers.get(j) == k || 
					numbers.get(j) - numbers.get(i) == k) {
						count++;
						break;
					}
			} 
			return count;
		}
	
		static void constructList(List<Integer> sumList, List<Integer> songs, int n) 
		{ 
			sumList.add((songs.get(0)+songs.get(1)-songs.get(n-1)) / 2); 
			for (int i=1; i<n; i++) 
				sumList.add(songs.get(i-1)-sumList.get(0)); 
		} 
	
		public static long playlist(List<Integer> songs) {
	
			if(songs == null || songs.size() == 0)
				return 0;
	
			int n = songs.size();
			long count=0;
			for(int i=0;i<n; i++){
				for(int j=i+1;j<n;j++){
					if(((Long.valueOf(songs.get(i))+Long.valueOf(songs.get(j)))%60) == 0)
						count++;
				}
			}
	
			return count;
		}
	
		private void bigDecimalSorted(Map<String,BigDecimal> map) {
			SortedSet<Map.Entry<String,BigDecimal>> sortedEntries = new TreeSet<Map.Entry<String,BigDecimal>>(
					new Comparator<Map.Entry<String,BigDecimal>>() {
	
						@Override
						public int compare(
								Entry<String,BigDecimal> o1,
								Entry<String,BigDecimal> o2) {
							return o2.getValue().compareTo(o1.getValue());
						}}
					);
			sortedEntries.addAll(map.entrySet());
			for(Map.Entry<String, BigDecimal> entry: sortedEntries){
				System.out.println(entry.getKey());
			}
		}
		private final String romanOrder ="MDCLXVI";
		private final Integer[] value= {1000,500,100,50,10,5,1}; 
		public int romanToInt(String s) {
			char[] chars = s.toCharArray();
			int result = 0;
			int prevIndex = -1;
			int length = chars.length;
	
			for(int i = 0 ; i < length ; i++) {
				char ch = chars[i];
				int index = romanOrder.indexOf(ch);
				int currValue = index!=-1?value[index]:0;
				if(prevIndex>index) 
					result -= (2*value[prevIndex]);
	
				result += currValue;
	
				//System.out.println(ch+""+result);
				prevIndex = index!=-1?index:-1;
			}
	
			//System.out.println(result);
			return result;
		}
		public String longestCommonPrefix(String[] strs) {
			Arrays.sort(strs);
	
			String commonPrefix = "";
			for(int i = 0 ; i < strs[0].length();i++) {
				char ch = strs[0].charAt(i);
				commonPrefix += ch;
				for(int j=1;j<strs.length;j++) {
					if(!strs[j].startsWith(commonPrefix))
						return commonPrefix.substring(0,commonPrefix.length()-1);
				}
			}
			return commonPrefix;
		}
	
	
		public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
			List<Boolean> list = new ArrayList<Boolean>();
			if(candies == null|| candies.length ==0)
				return list;
	
			int max = Integer.MIN_VALUE;
			for(int i=0;i<candies.length;i++)
				if(max<candies[i])
					max = candies[i];
	
			for(int i=0;i<candies.length;i++) {
				if(candies[i]+extraCandies >= max)
					list.add(true);
				else
					list.add(false);
			}
	
			return list;
		}
	
		public int findMaxConsecutiveOnes(int[] nums) {
			if(nums==null || nums.length == 0)
				return 0;
	
			int max= 0;
			int currMax = 0;
	
			for(int i = 0 ; i < nums.length ; i++){
				if(nums[i]	==1) 		   
					currMax++;
				else {
					if(currMax > max)
						max = currMax;
	
					currMax = 0;
				}
				//System.out.println(i+" "+currMax);
			}
			if(currMax > max)
				max = currMax;
	
			return max;
		}
		public int findLucky(int[] arr) {
			if(arr == null || arr.length == 0)
				return -1;
	
			Map<Integer,Integer> countMap = new HashMap<Integer,Integer>();
	
			for(int i = 0 ; i < arr.length;i++) {
				countMap.put(arr[i], countMap.getOrDefault(arr[i], 0)+1);
			}
	
			List<Integer> values = new ArrayList<>(); 
	
			for(Map.Entry<Integer, Integer> entry:countMap.entrySet()) 
				if(entry.getKey() == entry.getValue())
					values.add(entry.getKey());
	
			Collections.sort(values,Comparator.reverseOrder());
	
	
			return values.size()>0?values.get(0):-1;
		}
	
		public int findLengthOfLCIS(int[] nums) {
			if (nums == null || nums.length ==0)
				return 0;
	
			int max = 1 ;
			int currMax = 1;
			for(int i = 0 ; i <nums.length-1;i++) {
				if(nums[i]<nums[i+1]) {
					if(max < currMax)
						max = currMax;
	
					currMax=1;
				}else 
					currMax++;
	
			}
			if(max < currMax)
				max = currMax;
	
			return max;
		}
	
		public int[][] matrixReshape(int[][] nums, int r, int c) {
			if(nums == null || nums.length == 0)
				return new int[][] {};
	
				if(r*c != nums.length *nums[0].length)
					return nums;
	
				int row = 0;
				int l = 0;
	
				int[][] newArr = new int[r][c];
				for(int i = 0 ; i < nums.length ; i++) {
					for(int j = 0; j < nums[0].length ; j++) {
						newArr[row][l++] = nums[i][j];
	
						if(l == c) {
							row++;
							l = 0;
						}
					}
				}
				return newArr;
		}
	
		public static void findPrimes(int N) {
	
	
	
		}
	
		public char groupingString(String[] strings, int L, int R, int K) {
	
			if(strings == null || strings.length == 0)
				return ' ';
	
			int[] charsCount = new int[26];
	
			for(int i = L-1 ; i < R ; i++) 
				for(char ch : strings[i].toCharArray())
					charsCount[ch-'a']++;
	
			for(int i = 0 ; i < 26 ; i++)  
				if(charsCount[i]!=0)  
					if(K > charsCount[i])
						K -= charsCount[i]; 
					else 
						return (char)('a'+i); 
	
			return ' ';
		}
	
		private static Function<Integer,Boolean> isOdd = (Integer input) ->{
			return input%2!=0;
		};
	
		public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
	
			if(A == null || A.length == 0)
				return new int[] {};
	
			int sum = 0;
	
			for(int i=0;i<A.length;i++)
				sum += (A[i]%2==0?A[i]:0);
	
			if(queries == null || queries.length == 0)
				return new int[] {sum};
	
			int[] resArr = new int[queries.length];
	
			for(int i=0;i<queries.length;i++) {
				int index = queries[i][1];
	
				if(A[index] % 2 == 0) {
					if(queries[i][0]%2==0)
						sum += queries[i][0];
					else
						sum -= A[index];
				}else {
					if(queries[i][0]%2!=0)
						sum += (queries[i][0]+A[index]);
				}
	
				A[index] += queries[i][0];
				resArr[i] = sum;	
			}
	
			return resArr;
		}
	
		public int arrayPairSum(int[] nums) {
			if(nums == null || nums.length == 0)
				return Integer.MIN_VALUE;
	
	
			if(nums.length == 2) {
				return nums[0]+nums[1];
			}else {
				Arrays.sort(nums);
				int sum = 0;
				for(int i=0;i<nums.length;i+=2)
					sum += nums[i];
	
				return sum;
			}	
		}
	
	
		public int largestRectangleArea(int[] heights) {  
			Stack<Integer> s = new Stack<>(); 
			int max_area = 0; 
			int top; 
			int area_with_top; 
			int i = 0; 
			while (i < heights.length) 
			{ 
				if (s.empty() || heights[s.peek()] <= heights[i]) 
					s.push(i++); 
				else
				{ 
					top = s.pop(); 
					area_with_top = heights[top] * (s.empty() ? i : i - s.peek() - 1); 
					if (max_area < area_with_top) 
						max_area = area_with_top; 
				}
			}
			while (!s.empty() ) 
			{ 
				top = s.pop(); 
				area_with_top = heights[top] * (s.empty() ? i : i - s.peek() - 1); 
				if (max_area < area_with_top) 
					max_area = area_with_top; 
			} 
			return max_area; 
		}
	
		boolean isBadVersion(int version) {
			switch(version) {
			case 1:return false; 
			case 2:
			case 3: 
			case 4:
			case 5: 
			default:return true; 
			}
		}
	
		public int checkBadVersion(int start,int end) {
	
			if(start > end)
				return end+1;
	
			if(!isBadVersion(end))
				return end+1;
	
	
			int mid = start + ((end-start)/2);
			System.out.println(mid+" "+isBadVersion(mid));
			if(isBadVersion(mid))
				return checkBadVersion(start, mid-1);
			else
				return checkBadVersion(mid+1,end);
		}
	
		public int firstBadVersion(int n) {
			return checkBadVersion(1,n);
		}
	
	
	
		public int firstUniqChar(String str) {
			if(str== null || str.length() == 0)
				return -1;
	
			char count[] = new char[256];
			for (int i = 0; i < str.length();  i++) 
				count[str.charAt(i)]++;
	
			int index = -1, i; 
	
			for (i = 0; i < str.length();  i++) 
			{ 
				if (count[str.charAt(i)] == 1) 
				{ 
					index = i; 
					break; 
				}    
			}   
	
			return index; 
		}
	
		private int findMaxInArr(int[] nums) {
			if(nums == null)
				return Integer.MIN_VALUE;
	
			int max = Integer.MIN_VALUE;
			for(int num : nums)
				if(max<num)
					max = num;
			return max;
		}
	
		private int findMinInArr(int[] nums) {
			if(nums == null)
				return Integer.MAX_VALUE;
	
			int min = Integer.MAX_VALUE;
			for(int num : nums)
				if(min>num)
					min = num;
			return min;	
		}
	
		public int majorityElement(int[] nums) {
			if(nums == null || nums.length == 0)
				return -1;
	
			int max = findMaxInArr(nums);
			int min = findMinInArr(nums);
			int length;
			if(min <0)
				length = max-min+2;
			else
				length = max+1;
			int[] countArr = new int[length];
			int halfLength = nums.length/2;
			for(int num : nums) {
				int index;
				if(num < 0)
					index = num - min;
				else
					index = num;
	
				countArr[index]++;
				if(countArr[index] > halfLength)
					return num;
			}
			return -1;
		}
	
		private double slope(int x1, int y1, int x2,int y2) {
			return (double)(y2-y1)/(double)(x2-x1);
		}
	
		public boolean checkStraightLine(int[][] coordinates) {
	
			if(coordinates == null)
				return false;
	
			int length = coordinates.length;
			if(length < 2)
				return false;
	
			if(length == 2)
				return true;
	
			double slope = slope(coordinates[0][0],coordinates[0][1],coordinates[1][0],coordinates[1][1]);
	
			for(int i = 2;i<length;i++) {
				if(slope != slope(coordinates[0][0],coordinates[0][1],coordinates[i][0],coordinates[i][1]))
					return false;
			}
			return true;
		}
	
		public int[] smallerNumbersThanCurrent(int[] nums) {
			if(nums == null)
				return null;
	
			int length = nums.length;
			if(length == 0)
				return new int[] {};
	
			int[] count = new int[length];
	
			int[] numsCopy = Arrays.copyOf(nums, length);
			Arrays.sort(numsCopy);	
			for(int i=0;i<length;i++) {
				int index = binarySearchInDuplicates(numsCopy, nums[i]);
	
				count[i] = index;
			}
	
			return count;
		}
		private int binarySearchInDuplicates(int[] array, int element) {
			int lo = 0;
			int hi = array.length;
			int output = -1;
			while (hi > lo) {
				int mid = lo + (hi-lo) / 2;
				int val = array[mid];
				if (val < element) {
					lo = mid + 1;
				} else if (val > element) {
					hi = mid;
				} else {
					output = mid;
					hi = mid;
				}
			}
			return output;
		}
	
		public List<Integer> countSmaller(int[] nums) {
			if(nums == null)
				return null;
	
			int length = nums.length;
			if(length == 0)
				return new ArrayList<Integer>();
	
			int[] count = new int[length];
			for(int i=0;i<length;i++) 
				for(int j=i;j<length;j++) 
					if(nums[j]<nums[i])
						count[i]++;
	
			return Arrays.stream(count).mapToObj(val->(Integer)val).collect(Collectors.toList());
		}
		
		private int distance(int x1,int y1, int x2, int y2){
			int xChange = x2 - x1;
			int yChange = y2 - y1;
			
			xChange = xChange<0?-xChange:xChange;
			yChange = yChange<0?-yChange:yChange;
			int dist1 = xChange +(y2-(y1+xChange)); 			
			int dist2 = yChange + (x2-(x1+yChange));

			
			
			return Math.min(dist1, dist2);
	    }
	    
	    public int minTimeToVisitAllPoints(int[][] points) {
	        if(points == null)
	        	return 0;
	        
	        int length = points.length;
	        
	        if(length == 0)
	        	return 0;
	        
	        int result = 0;
	        for(int i=1;i<length;i++) {
	        	result += distance(points[i-1][0],points[i-1][1],points[i][0],points[i][1]);
	        }
	        
	        return result;
	    }
	    
	    public int findJudge(int N, int[][] trust) {
	        
	        if(trust == null)
	            return 0;
	        
	        int length = trust.length;
	        if(length == 0)
	            return 0;
	        
	        if(length == 1)
	            return trust[0][1];
	        
	        int[] count = new int[N+1];	        
	        Set<Integer> nonJudgeSet = new HashSet<Integer>();
	        for(int i=0;i<length;i++){
	        	int nonJudge = trust[i][0];
	            int judge = trust[i][1];
	            count[judge]++;
	            nonJudgeSet.add(nonJudge);
	        }
	        int res = -1;
	        for(int i=1;i<N+1;i++) {
	        	if(count[i] == N-1)
	        		res = i;
	        }
	        if(res ==-1)
	        	return -1;
	        if(nonJudgeSet.contains(res))
	        	return -1;
	        else 
	        	return res;
	     }
	
		public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
			LeetCode code = new LeetCode();
			
			System.out.println( 2&1);
			int[] count=code.smallerNumbersThanCurrent(new int[] {8,1,2,2,3});
			for(int val:count)
				System.out.print(val+" ");
	
	
	
			//System.out.println(code.majorityElement(new int[] {-1, 3,3,3,-2,3,-7,Integer.MIN_VALUE}));
			//System.out.println(code.firstUniqChar("dacca"));
			//System.out.println(code.canConstruct("aa", "bb"));
			//System.out.println(code.firstBadVersion(5));
			//System.out.println(code.largestRectangleArea(new int[] {2,1,5,6,2,3}));
			//System.out.println(code.minDominoRotations(new int[] {1,1,2,3,4}, new int[] {1,1,1,1,1}));
			//System.out.print(code.sumEvenAfterQueries(new int[] {1,2,3,4}, new int[][] {{1,0},{-3,1},{-4,0},{2,3}}));
			//System.out.println(isOdd.apply(5));
			//LocalDateTime start = LocalDateTime.now();
			//System.out.println(code.groupingString(new String[] {"abcdefghijklmnopqrstuvwxyzaaa","abcdefghijklmnopqrstuvwbbaaaabb","cccc","dddd"}, 1, 4, 8));
			//LocalDateTime end = LocalDateTime.now();
			//System.out.println(Duration.between(start, end).getNano());
	
			/*
			 * code.findMaxConsecutiveOnes(new int[] {1,0,1,1,0,1});
			 * System.out.println(code.longestCommonPrefix(new String[] {"","xab","abc"}));
			 * 
			 * code.romanToInt("C"); code.romanToInt("XI"); code.romanToInt("MCMXCIV");
			 */		/*
			 * BufferedReader br = new BufferedReader(new InputStreamReader(new
			 * FileInputStream("input.txt"))); FileWriter fw = new FileWriter(new
			 * File("output.txt")); int n = Integer.parseInt(br.readLine());
			 * 
			 * String regex =
			 * "<(.+)>(\\w|\\d|\\n|[\\(\\)\\.,-:;\\@\\#\\$\\%\\^&*\[\]\"\'+â€“\\/\\/\\Â®\\Â°\\â�°\\!\\?{}|`~]| )+?</(\\1)>"
			 * ; String test = "qqoNVOmJDG@6IBDZoEmk9337LswEL&TQnLCuR`04XD%1t{G)" +
			 * "Jmi_iNEXKwp&" +
			 * "<iBKMbDGtF4v@coLsF1_LqgTJ3cSp& 3a~I&Q(j0h_w~Vk(oBZCL#vhYY9%c>" +
			 * "<wkjkTvAuA8Zk}n_l7Si\"-pfm`M8YE3F}4`YQyChgC3uRbyTvu>" +
			 * "bMUGux)5n7L={M}e^`0xlSm5ce}ehiE}CJ6y0KPd~~B~ak5$PTdPGv}QnXpw6n9V8wVCVaTRTgLKkeF</wkjkTvAuA8Zk}n_l7Si\"-pfm`M8YE3F}4`YQyChgC3uRbyTvu>haZQKlWPxlRqXXkKHo=FDofc6$_S-GWA&m0zT*D~uorf_nAF^ym*U&6hGAI)s<XshvNhnnNbeVDuxRcQAgTpWZ-kqIps-@@}Uwq0J3Z06Y5mZgB9><FbTSC#F104{py9Xl6s{yi-R~}k5Fv4i1kCgmBY7P=vVj-j48xUg8x9BCxl~Y><lyxRRMqnMBGj1_d7Qqh5Ebn7 aMb{Q0Dm){9~I0DTS8BZ7+bui~)rQ\"2Yb4f>EeZWvJvHIk</XshvNhnnNbeVDuxRcQAgTpWZ-kqIps-@@}Uwq0J3Z06Y5mZgB9><BkkZV631Pnj}#%TWhZn@Y><kXjDpTvLA^tnXYb`h+cA J2"
			 * ; for(int i=1;i<=n;i++){ String str = br.readLine(); Pattern pattern =
			 * Pattern.compile(regex); Matcher m = pattern.matcher(str); if(m.find()){
			 * fw.append(m.group(2)+"\n"); System.out.println(m.group(2)); } else{
			 * fw.append("None\n"); System.out.println("None"); } while(m.find()){
			 * fw.append(m.group(2)+"\n"); System.out.println(m.group(2)); } }
			 */
	
	
			/*String regex = "^[[A-Z]|[a-z]][[A-Z]|[a-z]|\\d|[_]]{7,29}$";
					BigInteger bi = new BigInteger("134");
					System.out.println(bi.isProbablePrime(1));
	
					int n=5;
					String[] s ={".0000000123",".0000000124",".0000000123",".00000001233",".000000012234"};
					Map<String,BigDecimal> map = new HashMap<String,BigDecimal>();
					Map<String,Integer> countMap = new HashMap<String,Integer>();
					for(int i=0;i<n;i++){
			            map.put(s[i], new BigDecimal(s[i]));
			            if(countMap.containsKey(s[i]))
			            	countMap.put(s[i], countMap.get(s[i])+1);
			            else
			            	countMap.put(s[i], 1);
	
			        }
	
	
	
			        List<Map.Entry<String,BigDecimal> > list = new ArrayList<Map.Entry<String,BigDecimal>>();
			        list.addAll(map.entrySet());
			        Collections.sort(list,new Comparator<Map.Entry<String,BigDecimal>>(){
	
			            @Override
			            public int compare(Map.Entry<String, BigDecimal> o1,
			                    Map.Entry<String, BigDecimal> o2) {
			                return o2.getValue().compareTo(o1.getValue());
			            }
	
			        });
			        int j =0;
			        for(Map.Entry<String, BigDecimal> entry: list){
			        	String value = entry.getKey();
			        	int count = countMap.get(value);
			            for(int i=0;i<count;i++)
			            	s[j++] = value;
			        }
	
	
	
	
					/*System.out.println("Sam_antha".matches(regex));
	
					String s="test";
	
	
					StringTokenizer st = new StringTokenizer("He is a very very good boy, isn't he?", " !,?._'@");
			        System.out.println(st.countTokens());
					while (st.hasMoreTokens()) {
			            System.out.println(st.nextToken());
			        }
			        /*
	
					List<Character> list = new ArrayList<Character>();
					int[] a = new int[26];
					int index = (Character.isLowerCase('a'))?(0):(1);
					System.out.println('a'-97);
	
					Singleton singleton = Singleton.getSingleInstance();
					singleton.str ="test";
					Singleton singleton1 = Singleton.getSingleInstance();
					singleton.str ="test1";
	
					if(singleton.str.equals(singleton1.str)){
						System.out.println("same");
					}else{
						System.out.println("different");
					}
					/*
					List<Integer> songs = new ArrayList<Integer>();
	
					songs.add(30);
					songs.add(20);
					songs.add(150);
					songs.add(100);
					songs.add(40);
	
					System.out.println(playlist(songs));
	
	
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
	
			        int numbersCount = Integer.parseInt(bufferedReader.readLine().trim());
	
			        List<Integer> numbers = IntStream.range(0, numbersCount).mapToObj(i -> {
			            try {
			                return bufferedReader.readLine().replaceAll("\\s+$", "");
			            } catch (IOException ex) {
			                throw new RuntimeException(ex);
			            }
			        })
			            .map(String::trim)
			            .map(Integer::parseInt)
			            .collect(Collectors.toList());
	
			        int k = Integer.parseInt(bufferedReader.readLine().trim());
					System.out.println(countPairs(numbers, k));
					/*
	
					float a = 1.1f;
					float b = 3.5f;
	
					System.out.println((int)(a+b));
	
					/*
					Constructor<Inner.Private> cons = Inner.Private.class.getDeclaredConstructor(Inner.class);
					cons.setAccessible(true);
					Inner inner = new Inner();
					Object o = cons.newInstance(inner);
					Method[] methods = o.getClass().getDeclaredMethods();
					for(Method method :methods){
						if (Modifier.isPrivate(method.getModifiers())) {
			                method.setAccessible(true);
			                Object ob = method.invoke(o, new Object[]{8});
			                System.out.println(method.getName()+"->"+ob);
			                break;
			            }
					}
					LeetCode leetCode = new LeetCode();
	
					List<String> events = new ArrayList<String>();
					try(BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")))){
						String str;
						while((str=fr.readLine())!=null){
							events.add(str);
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Priorities.getStudents(events);
					List<Student> list = Priorities.getStudents(events);
					for(Student stud : list){
						System.out.println(stud.getName()+" "+stud.getCGPA()+" "+stud.getID());
					}
	
	
					/*LocalDate dt = LocalDate.of(2015, 8, 5);
					System.out.println(dt.getDayOfWeek().name());
					double payment =12324.134; 
					NumberFormat nf = NumberFormat.getInstance(Locale.US);
					nf.setMinimumFractionDigits(2);
					nf.setMaximumFractionDigits(2);
					System.out.println("US: $"+nf.format(payment));
					nf = NumberFormat.getInstance();
					nf.setMinimumFractionDigits(2);
					nf.setMaximumFractionDigits(2);
					System.out.println("India: Rs."+nf.format(payment));
					nf = NumberFormat.getInstance(Locale.CHINA);
					nf.setMinimumFractionDigits(2);
					nf.setMaximumFractionDigits(2);
					System.out.println("China: ï¿¥"+nf.format(payment));
					nf = NumberFormat.getInstance(Locale.FRANCE);
					nf.setMinimumFractionDigits(2);
					nf.setMaximumFractionDigits(2);
					System.out.println("France: "+nf.format(payment)+" â‚¬");
	
					/*Scanner in = new Scanner(System.in);
			        int t=in.nextInt();
			        for(int i=0;i<t;i++){
			            int a = in.nextInt();
			            int b = in.nextInt();
			            int n = in.nextInt();
	
			            printSeries(a,b,n);
			        }
			        in.close();
	
					System.out.println("1="+leetCode.checkPerfectNumber(1));
					System.out.println("2="+leetCode.checkPerfectNumber(2));
					System.out.println("12="+leetCode.checkPerfectNumber(12));
					System.out.println("28="+leetCode.checkPerfectNumber(28));
	
					System.out.println(leetCode.selfDividingNumbers(1, 22));
	
					int[] nums = {3,1,2,4};
					leetCode.printArray(nums);
					nums = leetCode.sortArrayByParity(nums);
					leetCode.printArray(nums);
	
					/*
	
					RecentCounter rc = new RecentCounter();
	
					System.out.println(rc.ping(1));
					System.out.println(rc.ping(100));
					System.out.println(rc.ping(3001));
					System.out.println(rc.ping(3002));
	
	
					System.out.println(new Scanner(System.in).nextLine());
	
	
					Set<Integer> test = new HashSet<Integer>();
	
					test.add(1);
					test.add(2);
					test.add(3);
					test.add(1);
	
					System.out.println(test);
	
	
					int[] nums1 = {1,3,3,3,5,5,0,1,1,1};
	
					System.out.println(leetCode.uniqueOccurrences(nums1));
	
	
					int[] nums2 = {-1,-2};
					leetCode.printArray(nums1);
					leetCode.merge(nums1, 4, nums2, 2);
					leetCode.printArray(nums1);
	
					int[] A = {-4,-1,0,3,10};
	
					leetCode.printArray(A);
					A = leetCode.sortedSquares(A);
					leetCode.printArray(A);
					A = new int[]{-7,-3,-2,3,11};
	
					leetCode.printArray(A);
					A = leetCode.sortedSquares(A);
					leetCode.printArray(A);
	
					System.out.println(leetCode.findPeakElement(new int[]{0,1,0}));
	
	
					int[][] A = {{1,1,0},{1,0,1},{0,0,0}};
					for(int i = 0; i<A.length;i++){
						for(int j=0;j<A[i].length;j++){
							System.out.print(A[i][j]+" ");
						}
						System.out.println();
					}
					System.out.println();
					A = leetCode.flipAndInvertImage(A);
	
					for(int i = 0; i<A.length;i++){
						for(int j=0;j<A[i].length;j++){
							System.out.print(A[i][j]+" ");
						}
						System.out.println();
					}
	
	
	
					System.out.println(leetCode.removeOuterParentheses("(((()()())))"));
	
					String[] arr = new String[]{"dig1 11 1 5 1",
							"let1 art can",
							"dig2 10 6",
							"let2 own kit dig",
							"let3 art zero"};
					leetCode.printArrayObject(arr);
					leetCode.printArrayObject(leetCode.reorderLogFiles(arr));
	
					arr = new String[]{"wpylev6cnqv8 447241070789889321113517804297550370", "2syod 60098540876848105552318 69698830167476212 2", "iuw2x1r qmxealfvosqgkv yunonsq nxcuwudndrpsroty h", "vclnqwkdr7h 5515 892770977116949342793820104705 3", "5y08u4f5ba swixvlwipfhtxavvzrdyxtnronckklvh f kzd", "2k63p1p 542447297738584 22237063423417308275099706", "qrj 467859 382 451796621324556 12022 72631305 0429", "vopck4 huqziggmwvcsermnujnpplihttviwei lsrqmbw b n", "s 7257018672440110203152567646 961657508453405583", "94j 1800907 54116251858 19612167 218608 1 504204 4", "u34lvgmoh 631217074786612695089137448 5635620839 5", "dsrojn8aeojx 27159799084241651870 76594680 195 051", "kteuav 77685739 6366458436088787165747310 78 3849", "dg8gco0sc2 10811560194867165521681 718 42498 1 52", "gdg 900670532316533434070453812 9115641245822 122", "ytlmfg 658910166131 170942932 70238 0783568 64777", "gi6d2lg8 ekwbnzeqrrzijgexvpcgfnhrkfhtmegaqon hsa u", "y8zhzn jjtbwpfrbcsuj zmseejb vcsovstaewtgtj nbsnlj", "cp1qsk5 dstuzhik alqxnmztxnwdve simoynsfffwyacl nr", "a11zjdza15gi zuighjavkfidjjx kgmbriwxbjcsv shtfjz", "3dpx3f28wa1 dhe jb uatgwooxclfj w t qaahvyiy lthj", "9ymabvjk4xq waujeijoltuy heoekaqmggmpdkynngne sl x", "n3l09gzpppgc dfnfxeaskknllixe tvtbemewtkwd bfbhm l", "one 143245418564431590 555274555077126490673 23406", "2dlvtxq57 11 0399853766553806651446400571374174 7", "l0xsyrtf9foe jcsjyzbux hpxxwwsyxwjcdqbuzrxuvdf n n", "o ufjxgmiohhacgwhprzqklpbleggurqygvmyrqtiwwusaa fq", "gr jmhsaanfrndkvkrdepfqvnathkheq bjtvzacabyfch xw", "12hrfmpyxql 509513107446443470266800090 12 36792 5", "ei mfmrujazj hvcaeoejhbvsxlnbcofdparedjvuqoigbwv h", "158mo1 fxwvcxyaz gimthvk t tbkpxnyomitu i foi t i", "1mpnz91abn8 857526216042344 529 86 555850 074136 6", "gvf69aycgz vd wzshq vqqcoscdfgtclfpoqz kcnbk yqrta", "05tv1dyuuln 3010253552744498232332 86540056 5488 0", "1kjt2sp 76661129172454994454966100212569762 877775", "k8fq mhahouacluusiypbhmbxknagj nrenkpsijov tspqd s", "a 05783356043073570183098305205075240023467 24 63", "0c 821 1288302446431573478713998604686702 0584599", "e 02985850443721 356058 49996149758367 64432663 32", "1zayns7ifztd kwmwsthxzxvvctzoejspeobtavhzxzpot u n", "o0sh3 qn nqjaghnmkckhvuauuknqbuxwalgva nt gfhqm en", "094qnly wgkmupkjobuup gshx wcblufmjumyuahsx n ai k", "j69r2ugwa6 zuoywue chhwsfdprfygvliwzmohqgrxn ubwtm", "2mkuap uqfwog jqzrkoorsompgkdlql wpauhkzvig ftb l", "x 929 4356109428379557082235487428356570127401 832", "jns07q8 idnlfsaezcojuafbgmancqpegbzy q qwesz rmy n", "phk1cna 086 027760883273 64658492093523655560824 2", "jbemfs9l9bs0 8147538504741452659388775 5 32 180 09", "ac9cwb9 524689619771630155 8125241949139653850678", "9eke perwsfqykyslfmcwnovenuiy urstqeqaezuankek czq"};
					leetCode.printArrayObject(arr);
					leetCode.printArrayObject(leetCode.reorderLogFiles(arr));
	
					arr = new String[]{"1 n u", "r 527", "j 893", "6 14", "6 82"};
					leetCode.printArrayObject(arr);
					leetCode.printArrayObject(leetCode.reorderLogFiles(arr));
	
					 ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo","a2 act car"]
					  ["g1 act car","a8 act zoo","a2 act car","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
	
					["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
	
					System.out.println(leetCode.addBinary("1000", "10"));
	
					System.out.println(leetCode.multiply("10200999999", "0"));
	
					System.out.println(leetCode.addStrings("1099999999999999999999", "2999999999"));
	
					leetCode.printArray(leetCode.plusOne(new int[]{9}));
	
					System.out.println(leetCode.addToArrayForm(new int[]{1},9999));
	
					int[] nums = new int[]{0,1,0,3,0,5};
					leetCode.printArray(nums);
					leetCode.moveZeroes(nums);
					leetCode.printArray(nums);
					nums = new int[]{0,0,0,0,0,0};
					leetCode.printArray(nums);
					leetCode.moveZeroes(nums);
					leetCode.printArray(nums);
	
					nums = new int[]{1,3,5};
					leetCode.printArray(nums);
					leetCode.moveZeroes(nums);
					leetCode.printArray(nums);
	
					nums = new int[]{0};
					leetCode.printArray(nums);
					leetCode.moveZeroes(nums);
					leetCode.printArray(nums);
	
					nums = new int[]{1};
					leetCode.printArray(nums);
					leetCode.moveZeroes(nums);
					leetCode.printArray(nums);
	
	
	
					System.out.println(leetCode.removeMultipleDuplicates(new int[]{3,2,2,2,4}));
					System.out.println();
					System.out.println(leetCode.removeMultipleDuplicates(new int[]{1,1,1,2,2,4}));
					System.out.println();
					System.out.println(leetCode.removeMultipleDuplicates(new int[]{3,3,3,3,3,3,3,1,1,1,2,2,4}));
					System.out.println();
					System.out.println(leetCode.removeMultipleDuplicates(new int[]{3,3,3,3,3,3,3}));
					System.out.println();
					System.out.println(leetCode.removeMultipleDuplicates(new int[]{1,3,3}));
	
					System.out.println(leetCode.removeElement(new int[]{3,2,2,3},3));
					System.out.println(leetCode.removeElement(
							new int[]{0,1,2,2,3,0,4,2},2));
					System.out.println(leetCode.removeDuplicates(
							new int[]{}));
					System.out.println("--------------------------------");
					System.out.println(leetCode.removeDuplicates(
							new int[]{1}));
					System.out.println("--------------------------------");				
					System.out.println(leetCode.removeDuplicates(
							new int[]{1,1,1,1,1}));
					System.out.println("--------------------------------");				
					System.out.println(leetCode.removeDuplicates(
							new int[]{1,2}));
					System.out.println("--------------------------------");				
					System.out.println(leetCode.removeDuplicates(
							new int[]{1,1,1,1,1,2}));
					System.out.println("--------------------------------");				
					System.out.println(leetCode.removeDuplicates(
							new int[]{1,2,2}));
					System.out.println("--------------------------------");				
					System.out.println(leetCode.removeDuplicates(
							new int[]{1,1,2,2}));
					System.out.println("--------------------------------");				
					System.out.println(leetCode.removeDuplicates(
							new int[]{1,2,3,3}));
					System.out.println("--------------------------------");				
					System.out.println(leetCode.removeDuplicates(
							new int[]{1,2,2,3,3,3}));
					System.out.println("--------------------------------");				
					System.out.println(leetCode.removeDuplicates(
							new int[]{1,1,2,2,3,3,3,4,5,6,7}));
					System.out.println("--------------------------------");				
					System.out.println(leetCode.removeDuplicates(
							new int[]{-3,-1,0,0,0,3,3}));
	
					System.out.println("1 : " + leetCode.numberToWords(1));
					System.out.println("-------");
					System.out.println("12 : " + leetCode.numberToWords(12));
					System.out.println("-------");
					System.out.println("30 : " + leetCode.numberToWords(30));
					System.out.println("-------");
					System.out.println("46 : " + leetCode.numberToWords(46));
					System.out.println("-------");
					System.out.println("100 : " + leetCode.numberToWords(100));
					System.out.println("-------");
					System.out.println("112 : " + leetCode.numberToWords(112));
					System.out.println("-------");
					System.out.println("193 : " + leetCode.numberToWords(193));
					System.out.println("-------");
					System.out.println("123 : " + leetCode.numberToWords(123));
					System.out.println("-------");
					System.out.println("999 : " + leetCode.numberToWords(999));
					System.out.println("-------");
					System.out.println("1000 : " + leetCode.numberToWords(1000));
					System.out.println("-------");
					System.out.println("1507 : " + leetCode.numberToWords(1507));
					System.out.println("-------");
					System.out.println("1613 : " + leetCode.numberToWords(1613));
					System.out.println("-------");
					System.out.println("9999 : " + leetCode.numberToWords(9999));
					System.out.println("-------");
					System.out.println("10000 : " + leetCode.numberToWords(10000));
					System.out.println("-------");
					System.out.println("12000 : " + leetCode.numberToWords(12000));
					System.out.println("-------");
					System.out.println("12012 : " + leetCode.numberToWords(12012));
					System.out.println("-------");
					System.out.println("55555 : " + leetCode.numberToWords(55555));
					System.out.println("-------");
					System.out.println("99999 : " + leetCode.numberToWords(99999));
					System.out.println("-------");
					System.out.println("115518 : " + leetCode.numberToWords(115518));
					System.out.println("-------");
					System.out.println("-------");
					System.out.println("100010 : " + leetCode.numberToWords(100010));
					System.out.println("155555 : " + leetCode.numberToWords(155555));
					System.out.println("-------");
					System.out.println("999999 : " + leetCode.numberToWords(999999));
					System.out.println("-------");
					System.out.println("1000000 : " + leetCode.numberToWords(1000000));
	
					System.out.println("-------");
					System.out.println("1000010 : " + leetCode.numberToWords(1000010));
	
	
					System.out.println("-------");
					System.out.println("2000000000 : " + leetCode.numberToWords(2000000000));
	
					System.out.println("-------");
					System.out.println("2000000012 : " + leetCode.numberToWords(2000000012));
	
	
					System.out.println("25.25.25.26 : "+leetCode.validIPAddress("25.25.25.26"));
	
					System.out.println("256.256.256.256 : "+leetCode.validIPAddress("256.256.256.256"));
	
					System.out.println("2001:0db8:85a3:0:0:8A2E:0370:7334 : "+leetCode.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
	
					System.out.println("02001:0db8:85a3:0000:0000:8a2e:0370:7334 : "+leetCode.validIPAddress("02001:0db8:85a3:0000:0000:8a2e:0370:7334"));
	
	
					System.out.println(leetCode.nextGreaterElement(10));
	
					System.out.println("AAAAAAAAAAAAAA:"+leetCode.detectCapitalUse("AAAAAAAAAAAAAA"));
					System.out.println("aaaaa:"+leetCode.detectCapitalUse("aaaaa"));
					System.out.println("Aaaaaa:"+leetCode.detectCapitalUse("Aaaaaa"));
					System.out.println("aaaAAAAAAAAAAAAAAa:"+leetCode.detectCapitalUse("aaaAAAAAAAAAAAAAAa"));
					System.out.println("AAAAAAAAAAAAAAa:"+leetCode.detectCapitalUse("AAAAAAAAAAAAAAa"));
					System.out.println(leetCode.toLowerCase("AaZz"));
	
					System.out.println(leetCode.isSubsequence("acb", "avbnc"));
	
					int[] prices = new int[]{1,2,3,4,5};
	
					System.out.println(leetCode.maxProfit(prices));
					System.out.println();
					prices = new int[]{3,3,5,0,0,3,1,4};
					System.out.println(leetCode.maxProfit(prices));
	
					prices = new int[]{1,5,8,9,10,17,17,20};
					System.out.println(leetCode.maxProfit(prices));
	
	
	
					System.out.println(leetCode.distanceBetweenBusStops(new int[]{1,2,3,4}, 2, 0));
	
					System.out.println();
					System.out.println("------------");
					System.out.println();
	
	
					System.out.println(leetCode.distanceBetweenBusStops(new int[]{8,11,6,7,10,11,2}, 0, 3));
	
					System.out.println();
					System.out.println("------------");
					System.out.println();
	
					System.out.println(leetCode.distanceBetweenBusStops(new int[]{7,10,1,12,11,14,5,0}, 7, 2));
	
	
					System.out.println(leetCode.numMatchingSubseq("abcde", new String[]{"a","bb","acd","ace"}));
	
	
					System.out.println(leetCode.oddCells(2, 3, new int[][]{{0,1},{1,1}}));
	
					System.out.println(leetCode.oddCells(2, 2, new int[][]{{1,1},{0,0}}));
	
					String test ="PPALLL";
	
	
					System.out.println(leetCode.numJewelsInStones("aA", "aAAbbbb"));
					System.out.println(leetCode.checkRecord(test));
	
					System.out.println(leetCode.defangIPaddr(test));
	
					int[] arr={74,87,89,36,48,89,53,45,38,78};
					leetCode.printArray(arr);
					System.out.println(leetCode.smallestDivisor(arr, 632));
	
					//System.out.println(leetCode.groupThePeople(arr));
	
					//System.out.println(Arrays.toString(leetCode.twoSumSearch(arr, 8)));
					System.out.println(leetCode.findMin(arr));
	
					System.out.println(leetCode.search(arr, arr[0]));
					System.out.println(leetCode.search(arr, arr[1]));
					System.out.println(leetCode.search(arr, arr[2]));
					System.out.println(leetCode.search(arr, arr[3]));
					System.out.println(leetCode.search(arr, arr[10]));
					System.out.println(leetCode.search(arr, arr[5]));
					System.out.println(leetCode.search(arr, arr[6]));
					System.out.println(leetCode.search(arr, arr[7]));
					System.out.println(leetCode.search(arr, 10));
	
	
					System.out.println(leetCode.lengthOfLongestSubstring("pwwkew"));
	
					int[] arr={1,5,9,12,12};
	
	
					System.out.println(leetCode.cutRod(arr, arr.length));
					leetCode.heapSort(arr);
					leetCode.printArray(arr);
	
					System.out.println(leetCode.lcs("test", "tet", 4, 3));
	
	
					String str = "(()";
					System.out.println(leetCode.longestValidParentheses(str));
	
	
					int[][] matrix = {{0,1,1},{1,1,1},{0,1,1}};
	
					//System.out.println(leetCode.countSquares(matrix));
	
					//System.out.println(leetCode.numOfBurgers(16, 7));
	
					/*System.out.println(leetCode.toHexspeak("619879596177"));
					System.out.println(leetCode.shortestPalindrome("aabba"));
	
					System.out.println(leetCode.longestPalindromicSubstring("babad"));;
	
					System.out.println(leetCode.longestPalindrome("babad"));
	
	
					System.out.println(leetCode.longestPalSubStr("babad"));
	
					System.out.println(leetCode.countPalindromes("tacocat"));
					int[] arr = {1,2,4,5};
					int[] arr1 ={2,4};
					leetCode.printArray(arr);
	
					int foundIndex = leetCode.binarySearch(arr, 3);
					System.out.println(foundIndex!=-1?"num 5 Found at index: "+foundIndex:"NotFound");
	
					//System.out.println(leetCode.findMedianSortedArrays(arr, arr1));
					//leetCode.kSort(arr, 6, 5);
	
					//leetCode.heapSort(arr);
	
					//leetCode.mergeSort(arr, 0, arr.length-1);
	
					//leetCode.sort(arr, 0, arr.length-1);
					//leetCode.printArray(arr);
					char[] test = new char[]{'a','b','c','d'};
					System.out.print("char array: ");
					System.out.println(test);
					leetCode.reverseString(test);
					System.out.print("reversed char array: ");
					System.out.println(test);
					System.out.println("vowels reversed in word:"+leetCode.reverseVowels("leetcode"));
					List<String> list = new ArrayList<String>();
					list.add("Neo");
					list.add("Morpheus");
					list.add("Oracle");
					list.add("Trinity");
					list.add("Neo");
	
					Predicate<String> neoSearch =str ->{
						System.out.println("search for neo...");
						return str.contains("neo");
					};
					boolean neoFound = list.stream().filter(str->str.length()>=3).allMatch(neoSearch);
					System.out.println(neoFound);
	
					//System.out.println("result-->"+leetCode.decodeAtIndex("ixm5xmgo78",711));
					//System.out.println("result-->"+leetCode.decodeAtIndextest("y959q969u3hb22odq595",222280369));
					System.out.println(leetCode.letterCombinations("979272783").size());
	
					System.out.println(leetCode.isValidStr("aabbcc"));;
	
					//IntStream.range(1,10).forEach(i->leetCode.generateParenthesis(i));
	
					String str = "((";
			System.out.println(leetCode.longestValidParentheses(str));
			System.out.println(str.substring(leetCode.startMaxSeq,leetCode.endMaxSeq));
	
			System.out.println("---"+leetCode.isValid("([)"));
	
			int test = 0,test1 = -1;
			System.out.println(test - (test1<0?test1:-test1));
	
			System.out.println(leetCode.threeSumtest(new int[]{-1,0,1,2,-1,-4}));
	
			System.out.println(leetCode.minDistance("tes1", "test1"));
	
			System.out.println(leetCode.isNumber("--11"));
	
			System.out.println(leetCode.myAtoi("-91283472332"));
	
			//System.out.println(leetCode.reverse(1430));
	
			int[] result = leetCode.twoSum(new int[]{2,7,11,15},20);
			System.out.println(result[0]+"-"+result[1]);
			int[] result = leetCode.threeSum(new int[]{2,7,11,15,12},25);
	
			System.out.println(result[0]+"-"+result[1]+"-"+result[2]);
	
			 */
	
	
		}
	
		static class Inner{
			static InputStream in = System.in;
	
			private class Private{
				private String powerof2(int num){
					return ((num&num-1)==0)?"power of 2":"not a power of 2";
				}
			}
		}
	
		public int countPalindromes(String str){
	
			if(str==null || str.length()==0)
				return 0;
	
			int count=str.length();
			int len =str.length();
	
			int low,high;
	
			for(int i=1;i<len;i++){
	
				low = i-1;
				high = i;
	
				while(low >= 0 && high < len && 
						str.charAt(low) == str.charAt(high)){
					count++;
					--low;
					++high;
				}
	
				low = i-1;
				high = i+1;
	
				while(low >= 0 && high < len && 
						str.charAt(low) == str.charAt(high)){
					count++;
					--low;
					++high;
				}
	
			}
	
			return count;
		}
	
	}
	
	
	interface MountainArray {
		public int get(int index);
		public int length();
	}
	
	
	class Node {
		public int val;
		public List<Node> children;
	
		public Node() {}
	
		public Node(int _val) {
			val = _val;
		}
	
		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	}
	
	class RecentCounter {
	
		List<Integer> pings = new ArrayList<Integer>();
		public RecentCounter() {
	
		}
	
	
		public int ping(int t) {
			int count=1;
			pings.add(t);
			int size=pings.size();
	
			for(int i=size-2;i>=0;i--){
				if(t-pings.get(i) <=3000){
					count++;
				}else
					return count;
			}
	
			return count;
		}
	}
	
	class Student{
		private int iD;
		private String name;
		private double cGPA;
	
		Student(int id,String name,double cgpa){
			this.iD = id;
			this.name = name;
			this.cGPA = cgpa;
		}
		public int getID() {
			return iD;
		}
		public void setID(int id) {
			this.iD = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getCGPA() {
			return cGPA;
		}
		public void setCGPA(double cgpa) {
			this.cGPA = cgpa;
		}
		@Override
		public String toString() {
			return "Student [iD=" + iD + ", name=" + name + ", cGPA=" + cGPA + "]";
		}	
	}
	
	class Priorities {
	
		private static final PriorityQueue<Student> studentPriorities = new PriorityQueue<Student>(new Comparator<Student>() {
	
			@Override
			public int compare(Student o1, Student o2) {
				if(o1.getCGPA() == o2.getCGPA()){
					if(o1.getName().equals(o2.getName())){
						return (new Integer(o1.getID())).compareTo(o2.getID());
					}else{
						return o1.getName().compareTo(o2.getName());
					}
				}else{
					return (new Double(o2.getCGPA())).compareTo(o1.getCGPA());
				}
			}
		});
	
	
	
	
		public static List<Student> getStudents(List<String> events){
	
			for(String event : events){
				if(event.equals("SERVED")){
					studentPriorities.poll();
				}else{
					String[] strings = event.split(" ");
					studentPriorities.add(new Student(Integer.parseInt(strings[3]), 
							strings[1], Double.valueOf(strings[2])));
				}
			}
			List<Student> list = new ArrayList<Student>(); 
			while(!studentPriorities.isEmpty()) {
				list.add(studentPriorities.poll());
			}
	
			return list;
		}
	
	}
