# Chapter 18. 람다식

# I. 람다식(Lambda Expression)

- 함수형 프로그래밍 기법을 지원하는 자바의 문법 요소
- 단 하나의 추상 메서드만을 포함하는 인터페이스를 함수형 인터페이스 → 함수형 인터페이스 호출 및 기능 구현
- 익명 이너 클래스의 축약된 형태

## 1. 객체 지향 구조 내에서 람다식 적용 과정

```java
interface A {
		void abc();
}

class B implements A {
		public void abc() {
				// 메서드 내용
		}
}

A a = new B();

a.abc();
```

```java
interface A {
		void abc();
}

A a = new A() {
		void abc() {
				// 메서드 내용
		}
};

a.abc();
```

```java
interface A {
		void abc();
}

A a = () -> { // 람다식
				// 메서드 내용
		};

a.abc();
```

## 2. 람다식의 기본 문법 및 약식 표현

### 1. 람다식의 기본 문법

- `(소괄호) -> {중괄호}`

```java
리턴 타입 메서드명(입력매개변수) {
		// 메서드 내용
}

(입력매개변수) -> {
		// 메서드 내용
}
```

### 2. 람다식의 약식 표현

- 중괄호 안의 실행문이 1개일 때 중괄호 생략 가능
- 입력매개변수의 자료형 생략 가능
- 입력매개변수가 1개일 때 소괄호 생략 가능
- return 문 1개일 때 return 생략 가능(중괄호 반드시 함께 생략)
    
    ```java
    A a1 = () -> {System.out.println("테스트");};
    A a2 = () -> System.out.println("테스트");
    
    A a1 = (int a) -> { ... };
    A a2 = (a) -> { ... };
    A a3 = a -> { ... };
    A a4 = int a -> { ... }; // X
    
    A a1 = (int a, int b) -> { return a + b; };
    A a2 = (int a, int b) -> a + b;
    A a3 = (int a, int b) -> { a + b; }; // X
    ```
    

# II. 람다식의 활용

## 1. 구현 메서드의 약식 표현

- 함수형 인터페이스의 객체 생성 과정에서 익명 이너 클래스를 이용한 객체 생성 방식의 축약된 표현 제공 → 직접 추상 메서드를 구현하는 형태
    
    ```java
    interface A {
    		void method1();
    }
    
    A a = new A() {
    		public void method1() {
    				...
    		}
    };
    
    A a = () -> { ... };
    ```
    
    ```java
    interface A {
    		void method2(int a);
    }
    
    A a = new A() {
    		public void method2(int a) {
    				...
    		}
    };
    
    A a = (int a) -> { ... };
    ```
    
    ```java
    interface A {
    		int method3();
    }
    
    A a = new A() {
    		public int method3() {
    				return ...
    		}
    };
    
    A a = () -> { return ... };
    ```
    
    ```java
    interface A {
    		double method4(int a, double b);
    }
    
    A a = new A() {
    		public double method4(int a, double b) {
    				return ...
    		}
    };
    
    A a = (int a, double b) -> { return ... };
    ```
    

## 2. 메서드 참조

### 1. 정의돼 있는 인스턴스 메서드 참조

- `클래스 객체::인스턴스 메서드명`
    
    ```java
    interface A {
    		void abc();
    }
    
    class B {
    		void bcd() {
    				System.out.println("메서드");
    		}
    }
    
    A a = new A() {
    		public void abc() {
    				B b = new B();
    				b.bcd();
    		}
    };
    
    A a = () -> {
    		B b = new B();
    		b.bcd();
    };
    
    B b = new B();
    A a = b::bcd();
    ```
    

### 2. 정의돼 있는 정적 메서드 참조

- `클래스명::정적 메서드명`
    
    ```java
    interface A {
    		void abc();
    }
    
    class B {
    		void bcd() {
    				System.out.println("메서드");
    		}
    }
    
    A a = new A() {
    		public void abc() {
    				B.bcd();
    		}
    };
    
    A a = () -> {
    		B.bcd();
    };
    
    A a = B::bcd();
    ```
    

### 3. 첫 번째 매개변수로 전달된 객체의 인스턴스 메서드 참조

- `클래스명::인스턴스 메서드명`
    
    ```java
    interface A {
    		void abc(B b, int k);
    }
    
    class B {
    		void bcd(int k) {
    				System.out.println(k);
    		}
    }
    
    A a = new A() {
    		public void abc(B b, int k) {
    				b.bcd(k);
    		}
    };
    
    A a = (b, k) -> {
    		b.bcd(k);
    };
    
    A a = B::bcd();
    ```
    

## 3. 생성자 참조

### 1. 배열 생성자 참조

- 인터페이스에 포함된 추상 메서드의 구현 메서드가 `new 자료형[]` 과 같이 배열 객체의 생성 기능만을 수행할 때
- `배열 타입::new`
    
    ```java
    interface A {
    		int[] abc(int len);
    }
    
    A a = new A() {
    		public int[] abc(int len) {
    				return new int[len];
    		}
    };
    
    A a = (len) -> new int[len];
    
    A a = int[]::new;
    ```
    

### 2. 클래스 생성자 참조

- 인터페이스의 추상 메서드가 클래스 타입의 객체를 리턴할 때
- `클래스명::new`
    
    ```java
    interface A {
    		B abc();
    }
    
    class B {
    		B() {};
    		B(int k) {};
    }
    
    A a = new A() {
    		public B abc() {
    				return new B();
    		}
    };
    
    A a = () -> new B;
    
    A a = B::new;
    ```