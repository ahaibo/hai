/**
 * 
 */
package com.hai.ijavase.array.sort;

import java.util.Arrays;

/**
 * 选择排序：每次从数组中最小索引的元素开始依次和后边的所有元素比较
 * 
 * @author Administrator
 * 
 */
public class SelectSort
{
	/**
	 * 选择排序:按升序排序
	 * 
	 * @param arr
	 *            要排序的数组
	 */
	public static void selectSort(int[] arr)
	{
		selectSort(arr, true);
	}
	
	/**
	 * 选择排序
	 * 
	 * @param arr
	 *            要排序的数组
	 * @param isAsc
	 *            是否按升序排序
	 */
	public static void selectSort(int[] arr, boolean isAsc)
	{
		int len = arr.length;
		int outCycleLen = len - 1;
		
		if (isAsc)
		{
			for (int i = 0; i < outCycleLen; i++)
			{
				for (int j = i + 1; j < len; j++)
				{
					if (arr[i] > arr[j])
					{
						int temp = arr[i];
						arr[i] = arr[j];
						arr[j] = temp;
					}
				}
			}
		}
		else
		{
			for (int i = 0; i < outCycleLen; i++)
			{
				for (int j = i + 1; j < len; j++)
				{
					if (arr[i] < arr[j])
					{
						int temp = arr[i];
						arr[i] = arr[j];
						arr[j] = temp;
					}
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		int[] arr = { 1, 3, 8, 2, 9, 4, 5, 12, 7 };
		
		SelectSort.selectSort(arr, true);
		System.out.println("SelectSort.selectSort by asc:\n" + Arrays.toString(arr));
		
		SelectSort.selectSort(arr, false);
		System.out.println("\nSelectSort.selectSort by desc:\n" + Arrays.toString(arr));
	}
}
