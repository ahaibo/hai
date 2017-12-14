/**
 * 
 */
package test;

/**
 * @author Administrator
 * 
 */
public class TempTest
{
	
	public TempTest()
	{
	}
	
	public static void main(String[] args)
	{
		// System.out.println(diGui(50));
		long start = System.currentTimeMillis();
		int len = 100000;
		Object[] arr = new Object[len];
		// Random random = new Random();
		for (int i = 0; i < len; i++)
		{
			arr[i] = (int) (Math.random() * 99999999);
			// System.out.println(arr[i]);
		}
		
		// for (int i = 0; i < len; i++)
		// {
		// arr[i] = UUID.randomUUID().toString();
		// }
		
		long curr = System.currentTimeMillis();
		System.out.println("timer: " + (curr - start));
	}
	
	/**
	 * @param i
	 */
	private static int diGui(int i)
	{
		if (i > 0)
		{
			return i + diGui(i - 1);
		}
		
		return i;
		
	}
	
}
