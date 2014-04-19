package two;


public class ArgsTransmission {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		String[] b = {"E:/" , "10"};
		BasicCrawlController.main(b);
		long endTime=System.currentTimeMillis(); //end time
		System.out.println("Total running time: "+(endTime-startTime)+"ms");   
	}

}
