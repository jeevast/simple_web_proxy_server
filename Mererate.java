import java.util.Scanner;

public class Mererate {
	
	static int BinarySearch(int arr[], int key, int l, int r)
	{
		int m = (l+r)/2;
		
		if(key == arr[m])
		{
			return m;
		}
		else if(key > arr[m])
		{
			return BinarySearch(arr, key, m+1, r );
		}
		else if(key < arr[m])
		{
			return BinarySearch(arr, key, l, m-1);
		}
		else
		{
			return -1;
		}
		
	}
	
	
	public static void main(String[] args)
	{
		int[] arr = {1, 2, 3, 4, 5};
		Scanner in = new Scanner(System.in);
		int key = in.nextInt();
		System.out.println(BinarySearch(arr, key, 0, arr.length-1));
		
		
		
	
		
		
	} 


}
