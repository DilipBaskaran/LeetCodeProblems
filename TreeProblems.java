import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class TreeProblems {

	public List<List<Integer>> levelOrder(TreeNode root) {

		List<List<Integer>> list = new ArrayList<List<Integer>>();

		if(root == null)
			return list;

		//Stack<TreeNode> currentLevel = new Stack<TreeNode>();
		//Stack<TreeNode> nextLevel = new Stack<TreeNode>();

		Queue<TreeNode> currentLevel = new LinkedList<TreeNode>();
		Queue<TreeNode> nextLevel = new LinkedList<TreeNode>();

		currentLevel.add(root);
		boolean lTR = false;

		List<Integer> levelList = new ArrayList<Integer>();
		while(!(currentLevel.size()==0)){// empty()){

			TreeNode currNode = currentLevel.poll();//pop();
			//System.out.println(currNode.val);
			levelList.add(currNode.val);

			if(!lTR){
				if(currNode.left!=null)
					nextLevel.add(currNode.left);

				if(currNode.right!=null)
					nextLevel.add(currNode.right);

			}else{
				if(currNode.right!=null)
					nextLevel.add(currNode.right);

				if(currNode.left!=null)
					nextLevel.add(currNode.left);
			}

			if(currentLevel.isEmpty()){
				//lTR = !lTR;
				Queue<TreeNode> temp = nextLevel;
				nextLevel = currentLevel;
				currentLevel = temp;
				//System.out.println(levelList);
				list.add(levelList);
				levelList= new ArrayList<Integer>();
			}
		}


		return list;

	}
	public List<Double> averageOfLevels(TreeNode root) {

		List<Double> list = new ArrayList<Double>();

		if(root == null)
			return list;

		//Stack<TreeNode> currentLevel = new Stack<TreeNode>();
		//Stack<TreeNode> nextLevel = new Stack<TreeNode>();

		Queue<TreeNode> currentLevel = new LinkedList<TreeNode>();
		Queue<TreeNode> nextLevel = new LinkedList<TreeNode>();

		currentLevel.add(root);
		boolean lTR = false;

		Long sum = 0L,count=0L;
		while(!(currentLevel.size()==0)){// empty()){

			TreeNode currNode = currentLevel.poll();//pop();
			//System.out.println(currNode.val);
			sum+=currNode.val;
			count++;

			if(!lTR){
				if(currNode.left!=null)
					nextLevel.add(currNode.left);

				if(currNode.right!=null)
					nextLevel.add(currNode.right);

			}else{
				if(currNode.right!=null)
					nextLevel.add(currNode.right);

				if(currNode.left!=null)
					nextLevel.add(currNode.left);
			}

			if(currentLevel.isEmpty()){
				//lTR = !lTR;
				Queue<TreeNode> temp = nextLevel;
				nextLevel = currentLevel;
				currentLevel = temp;
				//System.out.println(levelList);
				list.add(sum * 1.0 / count);
				sum=0L;count=0L;
			}
		}
		return list;
	}
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

		List<List<Integer>> list = new ArrayList<List<Integer>>();

		if(root == null)
			return list;

		Stack<TreeNode> currentLevel = new Stack<TreeNode>();
		Stack<TreeNode> nextLevel = new Stack<TreeNode>();



		currentLevel.push(root);
		boolean lTR = false;

		List<Integer> levelList = new ArrayList<Integer>();
		while(!currentLevel.empty()){

			TreeNode currNode = currentLevel.pop();
			//System.out.println(currNode.val);
			levelList.add(currNode.val);

			if(lTR){
				if(currNode.left!=null)
					nextLevel.add(currNode.left);

				if(currNode.right!=null)
					nextLevel.add(currNode.right);

			}else{
				if(currNode.right!=null)
					nextLevel.add(currNode.right);

				if(currNode.left!=null)
					nextLevel.add(currNode.left);
			}

			if(currentLevel.isEmpty()){
				//lTR = !lTR;
				Stack<TreeNode> temp = nextLevel;
				nextLevel = currentLevel;
				currentLevel = temp;
				//System.out.println(levelList);
				list.add(levelList);

				levelList= new ArrayList<Integer>();
			}
		}


		return list;

	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) {

		int height = height(root);
		//System.out.println(height);
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		for(int i = height;i>=1;i--){
			List<Integer> levelList = new ArrayList<Integer>();
			reverseLevel(root,i,levelList);
			list.add(levelList);
		}
		return list;
	}

	private void reverseLevel(TreeNode node,int level,List<Integer> list){

		if(node==null)
			return;

		if(level==1){
			list.add(node.val);
			return;
		}

		reverseLevel(node.left,level-1,list);
		reverseLevel(node.right,level-1,list);
	}

	private int height(TreeNode node){
		if(node==null)
			return 0;

		int lHeight = height(node.left);
		int rHeight = height(node.right);

		if(lHeight>=rHeight)
			return lHeight+1;
		else
			return rHeight+1;
	}
	private int minHeight(TreeNode node){
		if(node==null)
			return 0;
		int heightLeft = minHeight(node.left);
		int heightRight = minHeight(node.right);
		if(node.left == null && node.right==null)
			return heightLeft<heightRight?heightLeft+1:heightRight+1;
		else if(node.left == null || node.right==null)
			return heightLeft<heightRight?heightLeft+2:heightRight+2;
		else
			return height(node);
	}
	public int minDepth(TreeNode node) {
		return minHeight(node);
	}

	Map<Integer,TreeNode> parentMap = new HashMap<Integer,TreeNode>();
	public boolean isCousins(TreeNode root, int x, int y) {

		if(root==null)
			return false;

		int xLevel = getLevel(root, x, 1);
		int yLevel = getLevel(root, y, 1);

		if(xLevel  == -1 || yLevel == -1 || xLevel != yLevel){
			//System.out.println("different level");
			return false;
		}

		if(parentMap.get(x) != parentMap.get(y)){
			//System.out.println("different Parent"+parentMap.get(x).val +"-"+parentMap.get(y).val);
			return true;
		}
		//System.out.println("SameParent-"+parentMap.get(x).val+"-"+parentMap.get(y).val);
		return false;
	}

	private int getLevel(TreeNode node,int val,int level){
		if(node==null)
			return -1;

		if(node.val == val){
			return level;
		}

		int res;
		if(node.left!=null){
			//System.out.println(node.left.val+"-"+node.val);
			parentMap.put(node.left.val, node);
		}

		if(node.right!=null){
			//System.out.println(node.right.val+"-"+node.val);
			parentMap.put(node.right.val, node);
		}
		res = getLevel(node.left,val,level+1);
		if(res==-1)
			res = getLevel(node.right,val,level+1);

		return res;
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

		if(root == null || p == null || q == null)
			return null;

		if(root == p || root  == q)
			return root;


		TreeNode left_lca = lowestCommonAncestor(root.left,p,q);
		TreeNode right_lca = lowestCommonAncestor(root.right,p,q);

		if(left_lca!=null && right_lca!=null)
			return root;
		
		return left_lca!=null?left_lca:right_lca;
	}

	public List<List<Integer>> levelOrder(Node root) {

		List<List<Integer>> list = new ArrayList<List<Integer>>();

		if(root == null)
			return list;

		//Stack<TreeNode> currentLevel = new Stack<TreeNode>();
		//Stack<TreeNode> nextLevel = new Stack<TreeNode>();

		Queue<Node> currentLevel = new LinkedList<Node>();
		Queue<Node> nextLevel = new LinkedList<Node>();

		currentLevel.add(root);
		boolean lTR = false;

		List<Integer> levelList = new ArrayList<Integer>();
		while(!(currentLevel.size()==0)){// empty()){

			Node currNode = currentLevel.poll();
			levelList.add(currNode.val);

			if(currNode.children!=null)
				for(Node node : currNode.children)
					nextLevel.add(node);

			if(currentLevel.isEmpty()){
				Queue<Node> temp = nextLevel;
				nextLevel = currentLevel;
				currentLevel = temp;
				list.add(levelList);
				levelList= new ArrayList<Integer>();
			}
			
		}


		return list;

	}

	public boolean hasPathSum(TreeNode root, int sum) {

		if(root == null)
			return false;

		//System.out.println("curr="+root.val+" sum="+sum);
		if(root.left == null && root.right == null && root.val == sum)
			return true;


		return hasPathSum(root.left,sum-root.val)||hasPathSum(root.right,sum-root.val);

	}
	List<List<Integer>> pathList = new ArrayList<List<Integer>>();
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		getPathSumList(root, sum, new ArrayList());
		List<List<Integer>> finalList = new ArrayList<List<Integer>>();
		finalList.addAll(pathList);
		return finalList;

	}

	private void getPathSumList(TreeNode root,int sum,List<Integer> list){

		if(root == null)
			return;

		List<Integer> list1 = new ArrayList<Integer>();
		list1.addAll(list);
		list1.add(root.val);

		//System.out.println("curr="+root.val+" sum="+sum);
		if(root.left == null && root.right == null && root.val == sum){
			pathList.add(list1);
			return;
		}

		getPathSumList(root.left,sum-root.val,list1);
		getPathSumList(root.right,sum-root.val,list1);

	}
	public int anyPathSum(TreeNode root, int sum) {
		pathList.clear();
		getAnyPathSumList(root,sum,sum,new ArrayList<Integer>());

		//System.out.println(pathList);

		return pathList.size();

	}

	private void getAnyPathSumList(TreeNode root,int sum,int totalSum,List<Integer> list){

		if(root == null)
			return;

		List<Integer> list1 = new ArrayList<Integer>();
		list1.addAll(list);
		list1.add(root.val);

		//System.out.println("curr="+root.val+" sum="+sum+" totalSum="+totalSum);
		if(root.val == sum){
			//System.out.println(list1);
			pathList.add(list1);

		}

		if(root.val == totalSum){
			List<Integer> list3 = new ArrayList<Integer>();
			list3.add(root.val);
			pathList.add(list3);
		}

		getAnyPathSumList(root.left,sum-root.val,totalSum,list1);
		getAnyPathSumList(root.right,sum-root.val,totalSum, list1);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(root.val);
		getAnyPathSumList(root.left,totalSum-root.val,totalSum,list2);
		getAnyPathSumList(root.right,totalSum-root.val,totalSum,list2);


	}
	private int totalSum = 0;
	public int sumNumbers(TreeNode root) {
		getRootToLeafPath(root, 0);
		return totalSum;
	} 
	private void getRootToLeafPath(TreeNode root,int num){

		if(root==null)
			return;


		num = num*10 + root.val;

		if(root.left==null && root.right==null)
			totalSum+=num;

		getRootToLeafPath(root.left, num);
		getRootToLeafPath(root.right, num);

	}
	private List<Integer> postOrderList = new ArrayList<Integer>();
	private void postOrder(Node root){
		if(root==null)
			return;
		if(root.children != null)
			for(Node node : root.children)
				postOrder(node);

		postOrderList.add(root.val);
	}

	public List<Integer> postorder(Node root) {
		postOrderList.clear();
		postOrder(root);
		return postOrderList;
	}
	private List<Integer> preOrderList = new ArrayList<Integer>();
	private void preOrder(Node root){
		if(root==null)
			return;

		preOrderList.add(root.val);

		if(root.children != null)
			for(Node node : root.children)
				preOrder(node);

	}
	public List<Integer> preorder(Node root) {
		preOrderList.clear();
		preOrder(root);
		return preOrderList;
	}


	private void preOrder(TreeNode root){
		if(root==null)
			return;

		preOrderList.add(root.val);
		preOrder(root.left);
		preOrder(root.right);


	}
	public List<Integer> preorderTraversal(TreeNode root) {
		preOrderList.clear();
		preOrder(root);
		return preOrderList;
	}

	private void postOrder(TreeNode root){
		if(root==null)
			return;

		postOrder(root.left);
		postOrder(root.right);
		postOrderList.add(root.val);
	}

	public List<Integer> postorderTraversal(TreeNode root) {
		postOrderList.clear();
		postOrder(root);
		return postOrderList;
	}

	private List<Integer> inOrderIterative(TreeNode root){
		List inOrderList = new ArrayList<Integer>();
		if(root == null)
			return inOrderList;

		Stack<TreeNode> stack = new Stack<TreeNode>();

		TreeNode curr = root;

		while(curr != null || !stack.isEmpty()){

			while(curr!=null){
				stack.push(curr);
				curr=curr.left;
			}

			curr = stack.pop();
			inOrderList.add(curr.val);
			curr= curr.right;

		}

		return inOrderList;
	}

	public List<Integer> postorderTraversalIterative(TreeNode root) {

		List<Integer> postOrderList = new ArrayList<Integer>();

		if(root == null)
			return postOrderList;

		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();

		s1.push(root);
		while(!s1.isEmpty()){
			TreeNode temp = s1.pop();
			s2.push(temp);

			if(temp.left != null)
				s1.push(temp.left);

			if(temp.right!=null)
				s1.push(temp.right);
		}

		while(!s2.isEmpty())
			postOrderList.add(s2.pop().val);

		return postOrderList;

	}
	
	public TreeNode sortedArrayToBST(int[] nums) {
    
		if(nums == null)
			return null;
		
		return sortedArrayToBST(nums,0,nums.length-1);
		
    }

	private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
		if(start <= end){
			
			int mid = start + (end-start)/2;
			
			TreeNode root = new TreeNode(nums[mid]);
			
			root.left = sortedArrayToBST(nums,start,mid-1);
			
			root.right = sortedArrayToBST(nums,mid+1,end);
			
			return root;
		}
		return null;
	}
	
	public TreeNode sortedListToBST(ListNode head) {
    
		if(head == null)
			return null;
		
		ListNode middlePrevious = findMiddlePreviousNode(head);
		ListNode middle;
		
		if(middlePrevious==null)
			 middle = head;
		else
			if(middlePrevious.next==null)
				middle = middlePrevious;
			else
				middle = middlePrevious.next;
		
		middlePrevious.next = null;
		
		//LinkedListProblems.printList(head);
		
		TreeNode root = new TreeNode(middle.val);
		if(head == middle)
			root.left = null;
		else
			root.left = sortedListToBST(head);
		
		root.right = sortedListToBST(middle.next);
		
		return root;
		
    }
	
	private ListNode findMiddlePreviousNode(ListNode head) {
		
		if(head == null)
			return null;
		
		ListNode slow = head;
		ListNode fast = head;
		ListNode prev = head;
		
		while(fast.next != null && fast.next.next!=null){
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		

		//System.out.println(prev.val+"-"+slow.val);
		
		
		return prev;
		
	}
	public List<Integer> preorderTraversalIterative(TreeNode root) {

		List<Integer> preOrderList = new ArrayList<Integer>();

		if(root == null)
			return preOrderList;

		Stack<TreeNode> s1 = new Stack<TreeNode>();

		s1.add(root);
		while(!s1.isEmpty()){
			TreeNode temp = s1.pop();
			preOrderList.add(temp.val);
			//System.out.println("temp "+temp.val);


			if(temp.right!=null)
				s1.add(temp.right);


			if(temp.left != null)
				s1.add(temp.left);
		}


		return preOrderList;

	}
	public int kthSmallest(TreeNode root, int k) {

		int count=0;
		int ksmall = Integer.MIN_VALUE;
		TreeNode curr = root;

		while(curr!=null){
			//System.out.println("curr="+curr.val);
			if(curr.left == null){
				count++;
				if(count == k)
					ksmall = curr.val;

				curr = curr.right;
			}else{
				TreeNode pre = curr.left;

				while(pre.right != null && pre.right != curr)
					pre = pre.right;

				if(pre.right == null){
					pre.right = curr;
					curr = curr.left;
				}else{
					pre.right =null ;
					count++;
					if(count == k)
						ksmall = curr.val;
					curr = curr.right;
				}
			}

		}
		return ksmall;
	}
	
	//not sure
	public int findSecondMinimumValue(TreeNode root,TreeNode mainRoot) {

		if(root == null)
			return -1;

		if(root.left == null && root.right == null && root == mainRoot)
			return -1;

		if(root.left == null && root.right == null && root != mainRoot)
			return root.val;

		//System.out.println(root.val+"-"+root.left.val+"-"+root.right.val);
		if(root.left !=null && root.left.val <= root.val){
			int val  = findSecondMinimumValue(root.left,mainRoot);
			if(val!=-1 && val != mainRoot.val)
				return val;
		}

		if(root.right !=null && root.right.val <= root.val){
			int val = findSecondMinimumValue(root.right,mainRoot);
			if(val!=-1 && val != mainRoot.val)
				return val;
			return -1;
		}
		else{
			if(root.right.val <= root.val)
				return root.left.val;
			else
				return root.right.val;
		}


	}
	
	public boolean findTarget(TreeNode root, int k) {
		
		if(root == null)
			return false;
		
		Set<Integer> set = new HashSet<Integer>();
		return findTarget(root,k,set);
		
        
    }
	
	public boolean findTarget(TreeNode root, int k,Set<Integer> set) {
		
		if(root == null)
			return false;
		
		if(findTarget(root.left, k,set))
			return true;
		
		if(set.contains(k-root.val)){
			System.out.println(k-root.val+"-"+root.val);
			return true;
		}
		
		set.add(root.val);
		
		return findTarget(root.right, k,set);
        
    }
	
	public int rangeSumBST(TreeNode root, int L, int R) {
        return rangeSumBST(root,0,L,R);
    }
	
	private int rangeSumBST(TreeNode root, int sum, int l, int r) {
		if(root == null)
			return 0;
		if(l > r )
			return 0;
		
		int leftSum = rangeSumBST(root.left,sum,l,r);
		int rightSum = rangeSumBST (root.right,sum,l,r);
		
		System.out.println(root.val+"-"+leftSum+"-"+rightSum);
		
		if(root.val >=l && root.val <= r)
			sum+=root.val;
		
		if(root.val >=l && root.val <= r )
			return sum+leftSum+rightSum;
		else if(root.val <= r)
			return sum+rightSum;
		else 
			return sum+leftSum;
			
	}
	
	public TreeNode searchBST(TreeNode root, int val) {
		
		if(root == null)
			return root;
		
		if(root.val == val)
			return root;
		
		if(root.val < val)
			return searchBST(root.right,val);
		else
			return searchBST(root.left,val);
		
		
    }
	
	public TreeNode insertIntoBST(TreeNode root, int val) {
     
		if(root ==null)
			return new TreeNode(val);
		
		
		if(root.val < val){
			
			if(root.right == null){
				root.right = new TreeNode(val);
			}else{
				insertIntoBST(root.right,val);
			}
		}else{
			if(root.left == null){
				root.left = new TreeNode(val);
			}else{
				insertIntoBST(root.left,val);
			}
		}
		
		return root;
		
		
    }
	class Index { 
		  
	    int index = 0; 
	}
	Index index = new Index();
	
	TreeNode constructTreeUtil(int preOrder[], Index preIndex, int key, 
            int min, int max, int size) { 
  
        if (preIndex.index >= size) 
            return null; 
  
        TreeNode root = null; 
        if (key > min && key < max) { 
            root = new TreeNode(key); 
            preIndex.index = preIndex.index + 1; 
            System.out.println(preIndex.index+"-->"+key+"-size="+size);
            if (preIndex.index < size)
                root.left = constructTreeUtil(preOrder, preIndex,  
                            preOrder[preIndex.index], min, key, size);
            
            if (preIndex.index < size)
                root.right = constructTreeUtil(preOrder, preIndex,  
                             preOrder[preIndex.index], key, max, size);  
        } 
  
        return root; 
    }
	
	public TreeNode bstFromPreorder(int[] preorder) {
        return constructTreeUtil(preorder, index, preorder[0], Integer.MIN_VALUE, 
                Integer.MAX_VALUE, preorder.length); 
    }
	
	
	public static void main(String...strings){
		TreeProblems treeProblems = new TreeProblems();
		
		
		TreeNode root = new TreeNode(5);

		TreeNode left = new TreeNode(2);
		root.left = left;
		TreeNode right = new TreeNode(8);
		root.right = right;


		left = new TreeNode(1);
		root.left.left = left;
		root.left.left.left=null;
		root.left.left.right=null;
		right = new TreeNode(4);
		root.left.right = right;
		right.left = null;
		right.right = null;

		root.right.left = new TreeNode(7);
		root.right.left.left=null;
		root.right.left.right=null;

		right = new TreeNode(10);
		root.right.right = right;
		right.left = null;
		right.right = null;
		
		//System.out.println(treeProblems.searchBST(root, 10).val);
		
		root = treeProblems.insertIntoBST(root, 11);
		root = treeProblems.insertIntoBST(root, 9);
		
		//System.out.println(treeProblems.levelOrder(root));
		
		treeProblems.bstFromPreorder(new int[] {4,2});
		
		/*
		System.out.println(treeProblems.inOrderIterative(root));
		System.out.println("Range Sum: "+treeProblems.rangeSumBST(root, 5, 10));
		
		System.out.println("find Target: "+treeProblems.findTarget(root, 11));
		left = new TreeNode(3);
		root.left.left.left = left;
		root.left.left.left.left=null;
		root.left.left.left.right=null;


		left = new TreeNode(2);
		root.left.left.right = left;
		root.left.left.right.left=null;
		root.left.left.right.right=null;
		
		right = new TreeNode(1);
		root.left.right.right = right;
		root.left.right.right.left = null;
		root.left.right.right.right = null;
		
		right = new TreeNode(4);
		root.right.right.right = right;
		right.left = null;
		right.right = null;
		
		
		int[] arr = {-10,-3,-2, 0,1,2,5,9};
		
		TreeNode head = treeProblems.sortedArrayToBST(arr);
		
		ListNode list = new ListNode(-10);
		ListNode list1 = new ListNode(-3);
		list.next = list1;
		ListNode list2 = new ListNode(0);
		list1.next = list2;

		ListNode list3 = new ListNode(5);
		list2.next = list3;
		ListNode list4 = new ListNode(9);
		list3.next = list4;
		ListNode list5 = new ListNode(1);
		list4.next = list5;
		ListNode list6 = new ListNode(8);
		list5.next = list6;
		ListNode list7 = new ListNode(20);
		list6.next = list7;
		
		LinkedListProblems.printList(list);
		
		head = treeProblems.sortedListToBST(list);
		
		System.out.println(treeProblems.levelOrder(head));
 		
		
		System.out.println();
		System.out.println("Zig zag traversal: "+treeProblems.zigzagLevelOrder(root));
		System.out.println("Level order traversal: "+treeProblems.levelOrder(root));
		System.out.println("Level order traversal from bottom: "+treeProblems.levelOrderBottom(root));

		System.out.println("Min Depth: "+treeProblems.minDepth(root));

		System.out.println("Average of numbers in each level: "+treeProblems.averageOfLevels(root));


		System.out.println("is Cousins? "+treeProblems.isCousins(root, 4, 5));
		
		
		System.out.println("is path available with sum: "+treeProblems.hasPathSum(root, 7));
		System.out.println("paths with sum:"+treeProblems.pathSum(root,8));
		
		System.out.println("no of paths with sum: "+treeProblems.anyPathSum(root, 7));
		
		treeProblems.getRootToLeafPath(root, 0);
		System.out.println("total sum from root to leaf: "+treeProblems.totalSum);
		
		treeProblems.preOrder(root);
		System.out.println("preorder recursive: "+treeProblems.preOrderList);
		System.out.println("preorder iterative: "+treeProblems.preorderTraversalIterative(root));
		System.out.println("inorder iterative: "+treeProblems.inOrderIterative(root));
		System.out.println("kth smallest: "+treeProblems.kthSmallest(root, 3));
		
		System.out.println("second minimum: "+treeProblems.findSecondMinimumValue(root,root));
		TreeNode lca = treeProblems.lowestCommonAncestor(root, root.right.right, root.right);
		System.out.println("Least Common Ancestor: "+ (lca!=null?lca.val:null));
		
		*/
	}

}

class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}
