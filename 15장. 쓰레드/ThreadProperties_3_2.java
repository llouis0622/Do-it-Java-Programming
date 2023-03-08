class MyThread extends Thread {
	@Override
	public void run() {
		System.out.println(getName() + " : " + (isDaemon() ? "데몬쓰레드" : "일반쓰레드"));
		for(int i = 0; i < 6; i++) {
			System.out.println(getName() + " : " + i + "초");
			try{ Thread.sleep(1000); } catch (InterruptedException e) {}
		}
	}
}

public class ThreadProperties_3_2 {
	public static void main(String[] args) {
		// # 데몬쓰레드
		Thread thread2 = new MyThread();
		thread2.setDaemon(true); // 데몬쓰레드로 설정
		thread2.setName("thread2");
		thread2.start();
		
		// # 3.5초 후 main Thread 종료
		try { Thread.sleep(3500); } catch (InterruptedException e) {}
		System.out.println("main Thread 종료");
	}
}