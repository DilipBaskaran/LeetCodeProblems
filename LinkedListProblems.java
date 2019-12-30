import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.compress.utils.Lists;


public class LinkedListProblems {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

		int rem = 0 ;
		ListNode res = l1;

		ListNode prev = null;

		while(l1!=null && l2!=null){
			int currSum = l1.val + l2.val;

			l1.val = (currSum+rem)%10;

			rem = (currSum+rem)/10;
			prev = l1;
			l1 = l1.next;
			l2 = l2.next;
		}
		while(l1!=null){
			l1.val = (l1.val+rem)%10;
			rem = (l1.val+rem)/10;
			prev = l1;
			l1 = l1.next;
		}

		if(prev != null  && l2!=null)
			prev.next = l2;

		while(l2!=null){
			l2.val =  (l2.val+rem)%10;
			rem = (l2.val+rem)/10;
			prev = l2;
			l2 = l2.next;
		}

		if(rem > 0)
			prev.next = new ListNode(rem);

		return res;
	}
	public ListNode removeNthFromEnd(ListNode head, int n) {

		if(head==null)
			return null;

		ListNode copyHead = head, copyHead1 = head;
		int i = 0;
		for(;i<n;i++){
			if(i< n-1 && copyHead.next == null)
				return head;
			copyHead = copyHead.next;

		}
		ListNode prev = null;
		while(copyHead!=null){
			prev= copyHead1;
			copyHead1 = copyHead1.next;
			copyHead = copyHead.next;
		}

		//System.out.println("test"+copyHead1.val);
		if(copyHead1 == head){
			head = head.next;
		}else if(copyHead1.next == null){
			prev.next = null;
		}else{
			prev.next = copyHead1.next;
		}

		return head;        
	}

	public ListNode deleteDuplicates(ListNode head) {

		if(head==null)
			return head;

		ListNode curr = head;

		while(curr!=null){
			if(curr.next!=null){
				if(curr.next.val == curr.val){
					curr.next = curr.next.next;
				}else{
					curr = curr.next;
				}
			}
			else
				curr = curr.next;
		}

		return head;

	}
	public ListNode deleteDuplicatesFull(ListNode head) {

		if(head==null)
			return head;

		ListNode curr = head, prev = null;
		boolean flag = false;
		int duplicate = Integer.MAX_VALUE;
		while(curr!=null){
			if(curr.next!=null && curr.next.val == curr.val){
				int val = curr.val;
				if(curr == head){
					head = curr.next.next;
					curr = head;
				}else{
					prev.next = curr.next.next;
					curr = prev.next;
				}
				flag=true;
				duplicate = val;
			}else if(curr.val == duplicate){
				if(curr == head){
					head = curr.next;
					curr = head;
					prev = null;
				}else{
					prev.next = curr.next;
					curr = curr.next;
				}

			}else{
				flag = false;
				prev = curr;
				curr = curr.next;
			}

		}

		return head;
	}

	public ListNode rotateRight(ListNode head, int k) {

		if(head == null || k == 0)
			return head;

		int count = 0;
		ListNode temp = head;
		while(temp!=null){
			count++;
			temp = temp.next;
		}

		int rotateCount = k%count;
		if(rotateCount == 0)
			return head;

		ListNode copyHead = head, copyHead1 = head;
		int i = 0;
		for(;i<rotateCount;i++){
			copyHead = copyHead.next;
		}
		ListNode prev = null , last = null;
		while(copyHead!=null){
			prev= copyHead1;
			copyHead1 = copyHead1.next;
			last = copyHead;
			copyHead = copyHead.next;
		}
		prev.next = null;
		prev = null;
		ListNode  curr =copyHead1;
		ListNode next = null;

		/*while(curr!=null){
			next =curr.next;
			curr.next = prev;
			prev= curr;
			curr = next;
		}*/

		last.next = head;
		head = curr;
		return head;
	}

	private ListNode reverse(ListNode root){

		ListNode prev= null ,curr = root,next = null;

		while(curr!=null){
			next =curr.next;
			curr.next = prev;
			prev= curr;
			curr = next;
		}

		return prev;
	}

	private ListNode reverseRecursive(ListNode root){

		if(root == null || root.next == null)
			return root;

		ListNode newHead = reverseRecursive(root.next);
		root.next.next = root;
		root.next = null;
		return newHead;		
	}


	private boolean compareList(ListNode list1,ListNode list2){
		while(list1!=null && list2!=null){
			//System.out.println(list1.val+"--"+list2.val);
			if(list1.val != list2.val)
				return false;


			list1 = list1.next;
			list2 = list2.next;
		}
		//System.out.println(list1.val+"--"+list2.val);
		if(list1 == null && list2 == null)
			return true;
		else
			return false;
	}

	public boolean isPalindrome(ListNode head) {

		if(head == null)
			return true;


		boolean res =true;
		if(head.next!=null){
			ListNode slow = head;
			ListNode fast = head;
			ListNode prev = head;
			ListNode mid = null;

			while(fast.next != null && fast.next.next!=null){
				//System.out.println(prev.val+"-"+slow.val+"-"+fast.val);
				fast = fast.next.next;
				prev = slow;
				slow = slow.next;
			}
			//System.out.println("prev:"+prev.val+"--slow:"+slow.val+"--fast:"+fast.val);
			if(fast.next !=null ){
				slow = slow.next;
				prev= prev.next;
			}else{
				mid = slow;
				slow = slow.next;
			}

			//System.out.println(mid.val);
			ListNode secondHalf = slow;
			prev.next = null;
			secondHalf = reverse(secondHalf);
			res = compareList(head,secondHalf);
			secondHalf = reverse(secondHalf);

			//System.out.println(secondHalf.val);

			if(mid!=null){
				prev.next = mid;
				mid.next = secondHalf;
			}else{
				prev.next = secondHalf;
			}
		}

		return res;

	}

	public ListNode reverseBetween(ListNode head, int m, int n) {

		if(head == null) 
			return null; 

		if(m <= 0 || n <= 0 || m>=n)
			return head;

		ListNode mthNode = head;
		ListNode nthNode = head; 
		ListNode prev = head;
		int i = 0,j=0;
		while(i<m  && j<n && mthNode!= null && nthNode != null){
			prev = head;
			mthNode = head.next;
			nthNode = head.next;
		}

		if(mthNode == null)
			return head;


		while(j<n && nthNode != null){
			nthNode = nthNode.next;
		}

		if(nthNode == null)
			return head;

		ListNode copy = nthNode;
		ListNode next = mthNode;
		ListNode nthPrev = null;

		while(j<n && nthNode!=null){
			next = nthNode.next;
			nthNode.next = nthPrev;
			prev = nthNode;
			nthNode = next;
		}

		mthNode.next = nthNode;


		return head;


	}


	public static void printList(ListNode list){
		while(list!=null){
			System.out.print(list.val+" -> ");
			list = list.next;
		}
		System.out.println("NULL");
	}

	public int numComponents(ListNode head, int[] G) {

		if(head == null || G == null)
			return 0;


		List<Integer> list = new ArrayList<Integer>(G.length);
		for(int elem : G)
			list.add(elem);

		int count = 0; ListNode temp = null;boolean flag=false;
		while(head!=null){
			if(list.contains(head.val)){
				temp = head;
				flag=true;
			}else{
				if(flag){
					count++;
					flag = false;
					temp = null;
				}
			}
			head = head.next;
		}

		if(flag)
			return count+1;
		else
			return count;
	}

	public ListNode removeElements(ListNode head, int val) {

		if(head  == null)
			return head;

		ListNode copy = head;
		ListNode prev = head;
		while(copy != null){
			if(copy.val == val){
				if(copy == head){
					head = head.next;
				}else{
					prev.next = copy.next;

				}
			}else{
				prev = copy;
			}

			copy = copy.next;
		}

		return head;

	}

	public void deleteNode(ListNode node) {

	}
	
	public int getDecimalValue(ListNode head) {
        if(head == null)
        	return 0;
        
        int res = 0;
        while(head != null){
        	int curr = head.val;
        	res = (res<<1) + curr;
        	
        	head = head.next;
        }
        
        return res;
    }

	public static void main(String[] args) {


		LinkedListProblems linkedListProblems = new LinkedListProblems();
		ListNode list = new ListNode(1);
		ListNode list1 = new ListNode(1);
		list.next = list1;
		ListNode list2 = new ListNode(0);
		list1.next = list2;
		LinkedListProblems.printList(list);
		System.out.println(linkedListProblems.getDecimalValue(list));

		/*ListNode list3 = new ListNode(2);
		list2.next = list3;
		ListNode list4 = new ListNode(2);
		list3.next = list4;
		ListNode list5 = new ListNode(2);
		list4.next = list5;
		ListNode list6 = new ListNode(2);
		list5.next = list6;
		ListNode list7 = new ListNode(5);
		list6.next = list7;
		 
		LinkedListProblems.printList(list);

		list = linkedListProblems.removeElements(list, 2);

		LinkedListProblems.printList(list);



		/*System.out.println("num of components:"+linkedListProblems.numComponents(list,arr));


		list = linkedListProblems.reverseRecursive(list);
		LinkedListProblems.printList(list);


		//list = linkedListProblems.removeNthFromEnd(list,2);
		//list = linkedListProblems.deleteDuplicates(list);
		//list = linkedListProblems.deleteDuplicatesFull(list);
		//list = linkedListProblems.rotateRight(list, 3);
		//System.out.println(linkedListProblems.isPalindrome(list));
		//LinkedListProblems.printList(list);


		//list = linkedListProblems.addTwoNumbers(list, list3);*/



	}

}

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}
