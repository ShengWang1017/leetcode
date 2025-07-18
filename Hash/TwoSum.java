package Hash;

import java.util.Arrays;
import java.util.HashMap;

class TwoSum {
    public static void main(String[] args) {
        int [] result = twoSum(new int[]{1,2,3,4,5,6,7,9}, 5);
        System.out.println(Arrays.toString(result));
    }

    // 1. 
    // public static int[] twoSum(int[] nums, int target){
    //     for (int i = 0; i < nums.length-1; i++) {
    //         for (int j = i+1; j < nums.length; j++) {
    //             if (nums[i]+nums[j]==target) {
    //                 return new int[]{i,j};
    //             }
    //         }
    //     }
    //     return null;
    // }

    // 2. 
    // public static int[] twoSum(int[] nums, int target){
    
    //     HashMap numsMap = new HashMap();
    //     for (int i = 0; i < nums.length; i++) {
    //         numsMap.put(nums[i], i);
    //     }
    //   for (int i = 0; i < nums.length; i++) {
    //     if (numsMap.containsKey(target-nums[i])&& (int) numsMap.get(target-nums[i])!=i) {
    //         return new int[]{i,(int)numsMap.get(target-nums[i])};
    //     }
    //   }
    //     return null;
    // }

    // 3.
    public static int[] twoSum(int[] nums, int target){
        HashMap numsMap = new HashMap<>();
      for (int i = 0; i < nums.length; i++) {
        int another = target-nums[i];
        if (numsMap.containsKey(another)) {
            return new int[]{i,(int)numsMap.get(another)};
        }
        numsMap.put(nums[i], i);
      }
        return null;
    }

}
