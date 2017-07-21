import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;


public class Solution {
	public static void main(String [] args) {
		int [] input = {7,1,3,6,4};
		int [] input1 = {1,2,3};
		Solution sol = new Solution();
		//		ListNode h = new ListNode(1);
		//		h.next = new ListNode(1);
		//		h.next.next = new ListNode(2);
		//		ListNode l = sol.deleteDuplicates(h);
		//		while(l != null) {
		//			System.out.println(l.val);
		//			l = l.next;
		//		}
		//		System.out.println(sol.climbStairs(44));
		//		sol.isSameTree(new TreeNode(1), new TreeNode(0));

		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode lleft = new TreeNode(2);
		TreeNode lright = new TreeNode(3);
		TreeNode right = new TreeNode(8);
		TreeNode rleft = new TreeNode(7);
		TreeNode rright = new TreeNode(9);
		root.left = left;
		//		root.right = right;
		//		left.left = lleft;
		//		left.right = lright;
		//				right.right = rright;
		//				right.left = rleft;
		System.out.println(sol.convertToTitle(28));

	}

	//168
	public String convertToTitle(int n) {

		String result = "";
		while(n > 0 ) {
			if((n%26) == 0) 
			{
				result="Z"+result;
				n = n/26-1;
			}
			else
			{
				result = (char)(n%26+'A'-1)+result;
				n = n/26;
			}


		}
		return result;
	}

	//167
	public int[] twoSum(int[] numbers, int target) {
		int [] ar = new int[2];
		Map<Integer, Integer> map = new HashMap<Integer,Integer>();
		int num = numbers.length;
		for (int i = 0; i < num; i++) {
			int n = numbers[i];
			Integer val1 = map.get(n);
			if( val1 != null){
				ar[0] = val1+1;
				ar[1] = i+1;
				return ar;
			}
			int key = target - n;
			map.put(key, i);
		}
		return ar;
	}

	//121
	//	public int maxProfit(int[] prices) {
	//        int current = 0;
	//        int tempMax = 0;
	//        for(int i = 1; i < prices.length; i++) {
	//            current = Math.max(0, current += prices[i] - prices[i-1]);
	//            tempMax = Math.max(current, tempMax);
	//        }
	//        return tempMax;
	//    }

	//122
	public int maxProfit(int prices[], int n){
		int p = 0;
		for(int i=0;i<n-1;i++){
			int current = prices[i+1]-prices[i];
			if(current>0)
				p += current;
		}
		return p;
	}

	//119
	public List<Integer> getRow(int rowIndex) {
		List<Integer> list = new ArrayList<Integer>();
		double x = 1;
		for (int k = 0; k <= rowIndex; k++)
		{
			System.out.print((int)x +" ");
			list.add((int)x);
			x = x * (rowIndex - k) / (k + 1);

		}

		return list;
	}



	//118
	public List<List<Integer>> generate(int numRows)
	{
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		ArrayList<Integer> cursor = new ArrayList<Integer>();
		for(int i=0;i<numRows;i++) {
			cursor.add(0, 1);
			for(int j=1;j<cursor.size()-1;j++){
				cursor.set(j, cursor.get(j)+cursor.get(j+1));
			}
			res.add(new ArrayList<Integer>(cursor));
		}
		return res;
	}
	//112
	public boolean hasPathSum(TreeNode root, int sum) {

		if(root == null) {
			return false;
		}
		return findCost(root, 0, sum);
	}

	private boolean findCost(TreeNode node, int val,int sum) {
		if(node == null) return false;
		if(node.left == null && null == node.right) {
			return val+node.val==sum;
		}

		System.out.println(val+"+"+node.val+" : "+sum);
		return findCost(node.left, val+node.val, sum) || findCost(node.right, val+node.val, sum);

	}


	//88
	public void merge(int[] nums1, int m, int[] nums2, int n) {

		int resultLen = m;
		int c1 = 0;
		int c2 = 0;

		while(c1 < resultLen && c2 < n) {
			if(nums1[c1] > nums2[c2]) {
				//shift
				resultLen++;
				for(int i = resultLen-1 ; i > c1; i--) {
					nums1[i] = nums1[i-1];
				}

				nums1[c1] = nums2[c2];
				c1++;
				c2++;
			} else {
				c1++;
			}
		}
		if(c2!=n) {
			for (int i = c2; i < n; i++) {
				nums1[resultLen++] = nums2[i];
			}
		}
	}

	//108
	public TreeNode sortedArrayToBST(int[] nums) {
		if(nums.length == 0) return null;
		return give(nums, 0, nums.length);
	}

	private TreeNode give(int[] nums,int s, int e) {
		if(s ==e) {
			return new TreeNode(nums[s]);
		}
		if(s > e) return null;
		int mid = (s+e+1)/2;
		TreeNode m = new TreeNode(nums[mid]);
		m.left = give(nums, s, mid-1);
		m.right = give(nums, mid+1, e);
		return m;
	}

	//110
	public boolean isBalanced(TreeNode root) {
		if(root == null) return true;
		int l = giveHeight(root.left);
		int r = giveHeight(root.right);
		if (Math.abs(l - r) <= 1 && isBalanced(root.left) && isBalanced(root.right)) {
			return true;
		}

		return false;
	}

	private int giveHeight(TreeNode node) 
	{
		if (node == null)
			return 0;

		return (1 + Math.max(giveHeight(node.left), giveHeight(node.right)));
	}

	//111
	public int minDepth(TreeNode root) {
		if(root == null) return 0;
		int l = minDepth(root.left);
		int r = minDepth(root.right);
		if(l == 0 || r == 0) {
			return Math.max(l, r);
		}

		return Math.min(l, r);

	}
	private int giveMinHeight(TreeNode node) {

		if (node == null)
			return Integer.MAX_VALUE;
		if(node.left == node.right && node.left == null) return 1;
		int y = (1 + Math.min(giveMinHeight(node.left), giveMinHeight(node.right)));
		//System.out.println(node.val+": "+y);
		return y;
	}


}
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}