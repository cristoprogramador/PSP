package exercise04;

public class Exercise04_ThreadPriorities {
	static class MyThread extends Thread {
		String name;

		public MyThread(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			for (int i = 0; i < 5000; i++) {
				System.out.println(this.name + "\t" + i);
				//yield();
				
				if (this.name.equals("T 2:")){		
					this.setPriority(MAX_PRIORITY);
				} else if (this.name.equals("T 1:")){
					this.setPriority(MIN_PRIORITY);
				}
			}
		}
	}

	public static void main(String[] args) {
		MyThread t1 = new MyThread("T 1:");
		MyThread t2 = new MyThread("T 2:");
		t1.start();
		t2.start();
	}
}
/**
 * Answer the following questions:
 * 1. Which is the output of the program? Aleaotory execute of the threads
 * Is the output of the program the expected result? Yes
 * 
 * 2. Uncomment the commented line and execute it again.
 * Do you see any significative difference? No
 * Which is the purpose of the method �yield�? 
 * yield, force to execute only a instruction and force the schedule to select who instruction is the next.
 * 
 * 3. Comment again the line which calls the method �yield�. Modify the code to assign the maximum 
 * priority to the thread �t1� (you may have to search for it on Internet) and run again the program.
 * Do you see any significative difference? Yes, the thread t1 execute first at most of cases the result at the console.
 * 
 * 4. Now assign the highest priority to the thread �t2� and the lowest to the thread �t1�.
 * Do you see any significative difference? Yes, the thread "t2" is executing at first time in most of cases.
 * */