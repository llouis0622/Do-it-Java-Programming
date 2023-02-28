# Chapter 11. 자바 제어자 2

# I. final 제어자

## 1. 개요

- 필드, 지역 변수, 메서드, 클래스 앞에 위치 가능
- 위치에 따라 의미 다름

## 2. final 변수

- 변수를 선언할 때만 지정 가능
- 한 번 대입된 값 → 최종 값(수정 X)
- final 필드의 초기화 : 반드시 생성자에서 진행

```java
class A1 { // 선언과 동시에 값 대입
		int a = 3;
		final int b = 5;

		A1() {
		}
}

class A2 { // 선언과 값 대입 분리
		int a;
		final int b;

		A2() {
				a = 3;
				b = 5;
		}
}

class A3 { // final 필드값을 대입한 후에는 추가 값 대입 불가능
		int a = 3;
		final int b = 5;

		A3() {
				a = 7;
				// b = 9; 불가능
		}
}
```

```java
A1 a1 = new A1();

a1.a = 7;
// a1.b = 9; 불가능

A2 a2 = new A2();

a2.a = 7;
// a2.b = 9; 불가능

class B {
		void bcd() {
				int a = 3;
				final int b = 5;

				a = 7;
				b = 9; // 불가능
		}
}
```

- 스택 메모리의 변수값 → 메서드 종료 시 삭제 → 한 번 생성하면 사라지지 않는 상수 영역에 final값 복사

## 3. final 메서드와 final 클래스

- final 메서드 : 최종 메서드, 자식 클래스에서 해당 메서드를 오버라이딩 할 수 없음(메서드의 기능 변경 불가능)
    
    ```java
    class A {
    		void abc() {
    				// ...
    		}
    
    		final void bcd() {
    				// ...
    		}
    }
    
    class B extends A {
    		void abc() {
    				// ...
    		}
    
    		// void bcd() {} 불가능
    }
    ```
    
- final 클래스 : 최종 클래스, 상속 자체가 아예 불가능(더 이상 자식 클래스가 없음)
    
    ```java
    final class A {
    		// ...
    }
    
    // class B extends A {} 불가능
    ```
    

## 4. 정리

- final 변수 : 값 변경 불가능
    
    ```java
    class A {
    		int m = 3;
    		final int n = 4;
    }
    
    A a = new A();
    
    a.m = 5;
    a.n = 9; // 불가능
    ```
    
- final 메서드 : 오버라이딩 불가능
    
    ```java
    class A {
    		void abc() { ... }
    		final void bcd() { ... }
    }
    
    class B extends A {
    		void abc() { ... }
    		void bcd() { ... } // 불가능
    }
    ```
    
- final 클래스 : 상속 불가능
    
    ```java
    final class A {
    		int m;
    		void bcd() { ... }
    }
    
    class B extends A { // 불가능
    		...
    }
    ```
    

# II. abstract 제어자

## 1. abstract 메서드

- 미완성 메서드, 중괄호가 없는 메서드(구체적이지 않은 메서드)
- 메서드의 기능 자체가 정의되지 않음, ;(세미콜론)으로 끝남

```java
abstract 리턴 타입 메서드명();
```

```java
class Animal {
		void cry() { // 부모 타입으로 cry() 메서드 호출하기 위해 선언(기능 X)
				// ...
		}
}

class Cat extends Animal {
		void cry() {
				System.out.println("야옹");
		}
}

class Dog extends Animal {
		void cry() {
				System.out.println("멍멍");
		}
}
```

```java
Animal animal1 = new Cat();
animal1.cry(); // 야옹

Animal animal2 = new Dog();
animal2.cry(); // 멍멍
```

## 2. abstract 클래스

- 추상 메서드를 1개 이상 포함하고 있는 클래스는 반드시 추상 클래스로 정의해야 함
- 추상 클래스도 클래스이기 때문에 상속 가능

```java
abstract class Animal {
		abstract void cry() {
				// ...
		}
}

class Cat extends Animal {
		void cry() {
				System.out.println("야옹");
		}
}

class Dog extends Animal {
		void cry() {
				System.out.println("멍멍");
		}
}
```

## 3. abstract 제어자의 장점

- 문법 오류 실수 예방
- 추상 메서드를 포함하는 추상 클래스 → 이를 상속한 모든 자식 클래스 내부에는 그 추상 메서드가 정의되어 있음
- 일반 클래스로 정의했을 때
    
    ```java
    class Animal {
    		void cry() {
    				// ...
    		}
    }
    
    class Cat extends Animal {
    		void cRy() { // 오타 발생
    				System.out.println("야옹");
    		}
    }
    
    Animal animal = new Cat();
    animal.cry(); // 출력 없음
    ```
    
- 추상 클래스로 정의했을 때
    
    ```java
    abstract class Animal {
    		abstract void cry() {
    				// ...
    		}
    }
    
    class Cat extends Animal {
    		void cRy() { // 오타 발생
    				System.out.println("야옹");
    		}
    } // 오류 발생
    ```
