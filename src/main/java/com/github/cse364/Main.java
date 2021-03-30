package com.github.cse364;
import com.github.cse364.DataLoader;

// Input format
// java (filename) category occupation

// multiple category input temporarily changed to "/"

public class Main {
    public static void main(String[] args){
		
		if(args.length != 2)
		{
			System.out.println("Error : Input format is 'category occupation'");
			System.exit(0);
		}

		String[] cate = args[0].split("/");
		String occu = args[1];

//		for(int i=0; i<cate.length; i++)
//		{
//			System.out.println(cate[i]);
//		}
//		System.out.println(occu);
        
		DataLoader.read();
    }
}
