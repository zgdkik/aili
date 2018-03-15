package org.hbhk.test.thread;

public class ThreadTest {

	
	public static void main(String[] args) {
		
		Thread t = new Thread(){
			@Override
			public void run() {
				
				if(1/0==0){
					
				}
			}
			
		};
		
		try {
			t.start();
		} catch (Exception e) {
			System.out.println("aaaaaaaaa");
			e.printStackTrace();
		}
	}
}
