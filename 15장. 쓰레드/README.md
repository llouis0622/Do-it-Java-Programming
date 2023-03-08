# Chapter 15. 쓰레드

# 1. 프로그램, 프로세스, 쓰레드

## 1. 프로그램과 프로세스의 개념

- 프로그램 : 하드디스크에 저장된 파일들의 모임
- 프로세스 : 메모리상에 로딩된 프로그램
    - 메모리는 프로그램을 한 번에 로딩하는 것이 아님 → 그때그때 필요한 부분만 동적으로 로딩
- 멀티 프로세스 : 동일한 프로그램을 메모리에 2번 로딩 → 2개의 프로세스 동작

## 2. 쓰레드의 개념

- 여러 개의 작업이 동시에 수행되도록 하기 위해서는 한정된 코어의 수를 갖는 CPU를 여러 개의 작업이 나눠서 사용
- CPU를 사용하는 최소 단위

## 3. 자바 프로그램에서의 쓰레드

- 멀티 쓰레드 프로세스 : 동시에 2개 이상의 쓰레드가 동작
- JVM 시작 시점에서는 main 쓰레드 1개만 존재 → 이후 main 쓰레드에서 쓰레드를 생성/실행하면 멀티 쓰레드

## 4. 쓰레드는 정말 동시에 수행될까?

- 단일 쓰레드로 2개의 작업을 처리할 때 각 작업은 순차적으로 처리
- 멀티 쓰레드에서는 동시성 또는 병렬성을 갖고 처리

### 1. 동시성(Concurrency)

- 처리할 작업의 수가 CPU의 코어 수보다 많을 때
- CPU는 각 작업 쓰레드의 요청 작업을 번갈아가면서 실행
- 두 작업이 동시에 실행되는 것이 아니라 동시에 실행되는 것처럼 보이도록 하는 방식

### 2. 병렬성(Parallelism)

- CPU의 코어 수가 작업 수보다 많을 때
- 각각의 작업을 각각의 코어에 할당해 동시에 실행 → 동시에 작업 수행

# 2. 쓰레드의 생성 및 실행

## 1. 쓰레드 생성 및 실행 방법

### 1. Thread 클래스를 상속받아 run() 메서드 재정의

- Thread 클래스를 상속받아 run() 메서드를 오버라이딩한 클래스(또는 익명 이너 클래스) 정의
    
    ```java
    class MyThread extends Thread {
    		@Override
    		public void run() {
    				// 쓰레드 작업 내용
    		}
    }
    ```
    
- Thread 객체 생성
    
    ```java
    Thread myThread = new MyThread();
    
    MyThread myThread = new MyThread();
    ```
    
- start() 메서드를 이용해 쓰레드 실행
    - 새로운 쓰레드 생성/추가를 위한 모든 준비 + 새로운 쓰레드 위에서 run() 실행
    
    ```java
    myThread.start();
    ```
    

### 2. Runnable 인터페이스 구현 객체를 생성한 후 Thread 생성자로 Runnable 객체 전달

- Runnable 인터페이스를 구현한 클래스 정의(추상 메서드 run() 구현)
    
    ```java
    class MyRunnable implements Runnable {
    		@Override
    		public void run() {
    				// 쓰레드 작업 내용
    		}
    }
    ```
    
- Runnable 객체 생성 + Thread 객체 생성(생성자에 Runnable 객체 전달)
    
    ```java
    Runnable r = new MyRunnable();
    
    MyRunnable r = new MyRunnable();
    
    Thread myThread = new Thread(r);
    ```
    
- start() 메서드를 이용해 쓰레드 실행
    
    ```java
    myThread.start();
    ```
    

# 3. 쓰레드의 속성

## 1. 현재 쓰레드 객체 참좃값 얻어오기

- currentThread() 메서드를 이용해 현재 쓰레드 객체의 참좃값 얻어오기 가능
    
    ```java
    static Thread Thread.currentThread()
    ```
    

## 2. 실행 중인 쓰레드의 개수 가져오기

- activeCount() 메서드를 사용해 현재 실행중인 쓰레드의 개수 파악 가능
    
    ```java
    static int Thread.activeCount()
    ```
    

## 3. 쓰레드의 이름 지정 및 가져오기

- setName() 메서드를 사용하여 쓰레드마다 이름 부여 가능
    
    ```java
    String setName(String name)
    // 쓰레드 이름 자동 부여 시 thread-0, thread-1, ...
    ```
    
- getName() 메서드를 사용하여 직접 지정이나 자동 부여된 쓰레드의 이름 가져오기 가능
    
    ```java
    String getName()
    ```
    

## 4. 쓰레드의 우선순위

- 1(가장 낮은 순위 값), 10(가장 높은 순위 값), 5(기본값) → 우선순위가 높으면 상대적으로 더 많은 시간 할당
    
    ```java
    public final static int MIN_PRIORITY = 1;
    // The minimum priority that a thread can have.
    
    public final static int MAX_PRIORITY = 10;
    // The maximum priority that a thread can have.
    
    public final static int NORM_PRIORITY = 5;
    // The default priority that is assigned to a thread.
    ```
    
- setPriority() 메서드로 쓰레드 객체의 우선순위 정하기
    
    ```java
    void setPriority(int priority)
    ```
    
- getPriority() 메서드로 쓰레드 객체의 우선순위 가져오기
    
    ```java
    int getPriority()
    ```
    
- 현재 컴퓨터의 CPU 코어 수 파악하기
    
    ```java
    public native int availableProcessors();
    ```
    

## 5. 쓰레드의 데몬 설정

- 데몬 쓰레드(Daemon Thread) : 다른 쓰레드, 정확히는 일반 쓰레드가 모두 종료되면 함께 종료되는 쓰레드
- setDaemon() 메서드를 사용하여 데몬 쓰레드 설정(기본값 false)
    
    ```java
    void setDaemon(boolean on)
    ```
    
- isDaemon() 메서드를 이용하여 데몬 쓰레드 설정 확인
    
    ```java
    boolean isDaemon()
    ```
    

# 4. 쓰레드의 동기화

## 1. 동기화의 개념

- 동기화(Synchronized) : 하나의 작업이 완전히 완료된 후 다른 작업을 수행하는 것
- 비동기(Asynchronous) : 하나의 작업 명령 이후 완료 여부와 상관없이 바로 다른 작업 명령을 수행하는 것

## 2. 동기화의 필요성

- 하나의 쓰레드가 완전히 종료된 후 다른 쓰레드 실행
- 동기화 : 한 쓰레드가 객체를 모두 사용해야 다음 쓰레드가 사용할 수 있도록 설정하는 것

## 3. 동기화 방법

- 메서드 동기화 : 2개의 쓰레드가 동시에 메서드를 실행할 수 없음, synchronized 키워드 사용
    
    ```java
    접근 지정자 synchronized 리턴 타입 메서드명(입력매개변수) {
    		// 동기화가 필요한 코드
    }
    ```
    
    ```java
    class MyData {
    		int data = 3;
    
    		public synchronized void plusData() {
    				// data 필드의 값을 +1 수행
    		}
    }
    ```
    
- 블록 동기화 : 2개의 쓰레드가 동시에 해당 블록을 실행할 수 없음, 메서드 전체 중에 동기화가 필요한 부분이 일부라면 굳이 전체 메서드를 동기화할 필요 없이 해당 부분만 동기화 가능
    
    ```java
    synchronized (임의의 객체) {
    		// 동기화가 필요한 코드
    }
    ```
    
    ```java
    class MyData {
    		int data = 3;
    
    		public void plusData() {
    				synchronized (this) {
    						// data 필드의 값을 +1 수행
    				}
    		}
    }
    ```
    

## 4. 동기화의 원리

- 모든 객체는 단 1개의 열쇠를 갖고 있음
- 동기화를 사용하면 처음 사용하는 쓰레드가 Key 객체의 열쇠를 가짐
- 다른 쓰레드는 먼저 사용 중인 쓰레드가 작업을 완료하고 열쇠를 반납할 때까지 대기(Blocked)

```java
class MyData {
		Object keyObject = new Object();

		synchronized void abc() {
				// 동기화 메서드
		}

		synchronized void bcd() {
				// 동기화 메서드
		}

		void cde() {
				synchronized (this) {
						// 동기화 블록
				}
		}

		void def() {
				synchronized (keyObject) {
						// 동기화 블록
				}
		}

		void efg() {
				synchronized (keyObject) {
						// 동기화 블로
				}
		}
}
```

# 5. 쓰레드의 상태

- 쓰레드의 상태는 Thread.State 타입으로 정의
- enum 타입(상수들의 집합)
- NEW, RUNNABLE, TERMINATED, TIMED_WAITING, BLOCKED, WAITING
    
    ```java
    Thread.State getState() // 쓰레드의 상태 값 가져오기
    ```
    
    ```java
    Thread.State state = myThread.getState();
    
    switch (state) {
    case Thread.State.NEW:
    		// ...
    
    case Thread.State.RUNNABLE:
    		// ...
    
    case Thread.State.TERMINATED:
    		// ...
    
    case Thread.State.TIMED_WAITING:
    		// ...
    
    case Thread.State.BLOCKED:
    		// ...
    
    case Thread.State.WAITING:
    		// ...
    }
    ```
    

## 1. 쓰레드의 6가지 상태

### 1. NEW, RUNNABLE, TERMINATED

- 처음 객체가 생성되면 NEW의 상태
- start() 메서드로 실행하면 RUNNABLE 상태
- run() 메서드가 종료되면 TERMINATED 상태

### 2. TIMED_WAITING

- Thread.sleep(long millis)나 join(long millis)가 호출 → TIMED_WAITING 상태
    
    ```java
    static void Thread.sleep(long millis) // RUNNABLE -> TIMED_WAITING
    
    synchronized void join(long millis) // RUNNABLE -> TIMED_WAITING
    ```
    
- 일시정지 시간이 지나거나 중간에 interrupt() 메서드 호출 시 상태 변환
    
    ```java
    일시정지 시간이 종료 // TIMED_WAITING -> RUNNABLE
    
    void interrupt() // TIMED_WAITING -> RUNNABLE
    ```
    

### 3. BLOCKED

- 동기화 메서드 또는 동기화 블록을 실행하기 위해 먼저 실행 중인 쓰레드의 실행 완료를 기다리는 상태

### 4. WAITING

- 시간 정보가 없는 join() 메서드 호출, wait() 메서드 호출 시 WAITING 상태
    
    ```java
    synchronized void join() // RUNNABLE -> WAITING
    
    void wait() // RUNNABLE -> WAITING
    ```
    
- join()의 대상이 된 쓰레드가 종료되거나 외부에서 interrupt() 메서드 호출 시 상태 변환
    
    ```java
    join()의 대상 쓰레드가 종료 // WAITING -> RUNNABLE
    
    void interrupt() // WAITING -> RUNNABLE
    ```
    
- wait() 메서드의 호출에 의해 상태 변환 → notify(), notifyAll() 메서드로 상태 변환
- wait(), notify(), notifyAll()은 동기화 블록 내에서만 사용 가능
    
    ```java
    void notify()
    void notifyAll()
    ```
    

## 2. NEW, RUNNABLE, TERMINATED

- new 키워드로 쓰레드의 객체가 생성된 상태(start() 전)
- 다른 쓰레드에게 CPU 사용을 양보하고 자신은 실행 대기 상태로 전환
    
    ```java
    static void Thread.yield(); // 자신의 차례를 딱 한 번 양보하는 메서드
    ```
    
- start() 이후 CPU를 사용할 수 있는 상태, 다른 쓰레드들과 동시성에 따라 실행과 실행 대기를 교차
- run() 메서드의 작업 내용이 모두 완료돼 쓰레드가 종료된 상태, 한 번 실행(start())된 쓰레드는 재실행이 불가능하며 객체를 새롭게 생성해야 함

## 3. TIMED_WAITING

- sleep(시간)을 호출한 쓰레드가 설정한 시간 동안 일시정지 상태 유지
- static void sleep(long millis)
- void join(long millis)
- thread.join(시간)을 호출하면 지정 시간 동안 thread1이 CPU를 사용, 호출한 쓰레드는 일시정지
- void interrupt()
- thread.interrupt()를 호출하면 thread1에 interruptedException을 발생 → 일시정지 상태 탈출

## 4. BLOCKED

- 동기화 메서드 또는 동기화 블록에 잠금이 걸려 있을 때
- 동기화 메서드 또는 동기화 블록 실행 완료로 잠금이 풀려 있을 때

## 5. WAITING

- void wait()
- void join()
- thread1.join()과 같이 join일 때 시간을 주지 않으면 thread1이 종료할 때까지 호출한 쓰레드는 일시정지 상태
- void notify(), void notifyAll()
