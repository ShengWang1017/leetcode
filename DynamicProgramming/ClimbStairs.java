class ClimbStairs{
    public static void main(String[] args)  {
        int i = 4;
        long num = climbStairsDP(i);
        System.out.printf("总共有 %d 种方法",num);
    }

    // 递归的方法，指数级栈
    public static int climbStairs(int i){
        if (i==1) {
            return 1;
        }
        if (i==2) {
            return 2;
        }
        return climbStairs(i-1) + climbStairs (i-2) ;
    }

    // 动态规划
    public static long climbStairsDP(int i)
    {
        if (i==1) {
            return 1; 
        }
        if (i==2) {
            return 2;
        }  
        long[] nums = new long[i+1];
        nums[1] = 1;
        nums[2] =2;
        
        for(int j =3;j<=i;j++){
            nums[j] = nums[j-1] + nums[j-2];
        }
        return nums[i];  
    }




}