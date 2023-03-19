# Chapter 16. 제네릭

# I. 제네릭 클래스와 제네릭 인터페이스

- 제네릭 : 동일한 이름의 메서드가 다양한 타입의 입력매개변수를 가질 수 있도록 하려면 고려하는 입력매개변수 타입의 수만큼 오버로딩 수행

## 1. 제네릭 없이 여러 객체를 저장하는 클래스 작성하기

```java
class Apple {
}

classs Goods1 {
		private Apple apple = new Apple();

		public Apple get() {
				return apple;
		}

		public void set(Apple apple) {
				this.apple = apple;
		}
}

// 사과를 저장하고 가져오기
Goods1 goods1 = new Goods1();
goods.set(new Apple());
Apple apple = goods1.get();
```

```java
class Pencil {
}

classs Goods2 {
		private Pencil pencil = new Pencil();

		public Pencil get() {
				return Pencil;
		}

		public void set(Pencil pencil) {
				this.pencil = pencil;
		}
}

// 사과를 저장하고 가져오기
Goods2 goods2 = new Goods2();
goods.set(new Pencil());
Pencil pencil = goods2.get();
```

- 필드를 모든 자바 클래스의 최상위 클래스인 Object 타입으로 선언
    
    ```java
    Goods goods1 = new Goods();
    goods1.set(new Apple()); // Apple 저장
    Apple apple = (Apple)goods1.get(); // Object -> Apple
    
    Goods goods2 = new Goods();
    goods2.set(new Pencil()); // Pencil 저장
    Pencil pencil = (Pencil)goods2.get(); // Object -> Pencil
    
    Goods goods3 = new Goods();
    goods3.set(new Apple()); // Apple 객체 저장
    Pencil pen = (Pencil)goods3.get(); // Pencil 타입으로 캐스팅 -> ClassCastException 발생
    ```
    
- 약한 타입 체크(Weak Type Checking) : 잘못된 타입 캐스팅에도 문법 오류를 발생시키지 않는 것

# II. 제네릭의 문법

- 강한 타입 체크(Strong Type Checking) : 잘못된 캐스팅을 할 때 문법 오류를 발생시켜 잘못된 캐스팅으로 발생할 수 있는 문제를 사전에 예방

## 1. 제네릭 클래스와 제네릭 인터페이스 정의하기

- `<제네릭 타입 변수명(들)>` , 영문 대문자 한글자 사용(제네릭 타입 변수명)
    
    ```java
    접근 지정자 class 클래스명<T> {
    		// 타입 T를 사용한 코드
    }
    
    접근 지정자 interface 클래스명<T> {
    		// 타입 T를 사용한 코드
    }
    
    접근 지정자 class 클래스명 <K, V> {
    		// 타입 K, V를 사용한 코드
    }
    
    접근 지정자 interface 클래스명 <K, V> {
    		// 타입 K, V를 사용한 코드
    }
    ```
    
- T(타입, Type), K(키, Key), V(값, Value), N(숫자, Number), E(원소, Element)

```java
public class MyClass<T> {
		private T t;

		public T get() {
				return t;
		}

		public void set(T t) {
				this.t = t;
		}
}

public interface MyInterface<K, V> {
		public abstract void setKey(K k);
		public abstract void setValue(V v);
		public abstract void K getKey();
		public abstract void V getValue();
}
```

## 2. 제네릭 클래스의 객체 생성

- `<실제 제네릭 타입>` , 객체를 생성할 때 제네릭 타입 변수에 실제 타입 대입
- 클래스를 정의하는 시점에 타입을 지정하는 것이 아니라 객체를 생성하는 시점에 타입 지정
    
    ```java
    클래스명<실제 제네릭 타입> 참조 변수명 = new 클래스명<실제 제네릭 타입>();
    
    클래스명<실제 제네릭 타입> 참조 변수명 = new 클래스명<>();
    ```
    
- 추가 클래스 생성 없이 어떤 것도 저장 및 관리
- setter 메서드에 잘못된 객체를 입력했을 때 바로 문법으로 체크
- getter 메서드의 리턴 타입도 다운캐스팅이 필요 없어야 함

# III. 제네릭 메서드

## 1. 제네릭 메서드의 정의와 호출

- 제네릭 메서드 : 일반 클래스 내부의 특정 메서드만 제네릭으로 선언, 호출되는 시점에 실제 제네릭 타입 지정
    
    ```java
    // 제네릭 타입 변수명이 1개일 때
    접근 지정자 <T> T 메서드명 (T t) {
    		// 타입 T를 사용한 코드
    }
    
    // 제네릭 타입 변수명이 2개일 때
    접근 지정자 <K, V> T 메서드명 (K k, V v) {
    		// 타입 K, V를 사용한 코드
    }
    
    // 매개변수에만 제네릭이 사용됐을 때
    접근 지정자 <T> void 메서드명 (T t) {
    		// 타입 T를 사용한 코드
    }
    
    // 리턴 타입에만 제네릭이 사용됐을 때
    접근 지정자 <T> T 메서드명 (int a) {
    		// 타입 T를 사용한 코드
    }
    ```
