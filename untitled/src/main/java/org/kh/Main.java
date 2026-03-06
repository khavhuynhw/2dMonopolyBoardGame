package org.kh;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,7,3,6,5,6};
        System.out.println(pivotIndex(nums));
        System.out.println("Hello world!");
    }
    public static int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int leftSum =0;
        for(int i =0 ; i< nums.length; i++){
            int rightSum = total - leftSum - nums[i];
            if(leftSum == rightSum){
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }
}