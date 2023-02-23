# Chapter 09. 자바 제어자 1

# I. 접근 지정자

## 1. 개요

- 자바 제어자(Modifier) : 클래스, 필드, 메서드, 생성자 등에게 어떠한 특징을 부여하는 문법 요소
- 접근 지정자 : 자바 제어자의 한 종류, 클래스, 멤버, 생성자 앞에 위치, 사용 범위를 정의하는 역할

## 2. 멤버 및 생성자의 접근 지정자

- public, protected, default(package), private 4가지 존재
- 필드나 메서드 또는 생성자 앞에 위치
- 아무것도 없는 상태 : default 접근 지정자 자동 설정
- 접근 범위 : public > protected > default > private

### 1. private

- 자신의 클래스 내부에서만 사용할 수 있는 접근 지정자
- 같은 멤버끼리만 사용 가능, 외부 클래스에서 전혀 사용 불가

### 2. default

- 같은 패키지 안의 모든 클래스에서 사용할 수 있는 접근 지정자
- package 접근 지정자라고도 함

### 3. protected

- default보다는 넓은 범위의 접근 지정자
- 같은 패키지의 모든 클래스에서 사용할 수 있음
- 추가로 다른 패키지의 자식 클래스 안에서 사용 할 수 있음(상속)
- default 범위 + 다른 패키지의 자식 클래스

### 4. public

- 동일 패키지의 모든 클래스에 접근 가능
- 다른 패키지의 모든 클래스에도 접근 가능
- 어디에서나 사용할 수 있음

```java
package abc;

public class A {
		public int a;
		protected int b;
		int c;
		private int d;

		void abc() {
				// a, b, c, d 사용 가능
		}
}
```

```java
package abc;

class B {
		// a, b, c 사용 가능
}
```

```java
package bcd;

class C {
		// a 사용 가능
}
```

```java
package bcd;

class D extends A {
		// a, b 사용 가능
}
```

## 3. 클래스의 접근 지정자

- public, default 접근 지정자만 사용 가능
- default 클래스 : 같은 패키지 내에서만 사용 가능(다른 패키지에서 임포트 불가능)
- public 클래스 : 다른 패키지에서도 사용 가능

```java
package abc.bcd;

class A {
		// ...
}

class B {
		void bcd() {
				A a = new A(); // 가능
		}
}
```

```java
package bcd.cde;

import abc.bcd.A; // 불가능

class C {
		void cde() {
				// A 클래스 사용(선언) 불가능
		}
}
```

```java
package abc.bcd;

public class A {
		// ...
}

class B {
		void bcd() {
				A a = new A(); // 가능
		}
}
```

```java
package bcd.cde;

import abc.bcd.A; // 가능

class C {
		void cde() {
				// A 클래스 사용(선언) 가능
		}
}
```

## 4. 클래스 접근 지정자와 생성 접근 지정자의 연관성

- 자동으로 추가되는 생성자의 접근 지정자는 클래스의 접근 지정자에 따라 결정
- 클래스 public : 다른 패키지에서 임포트 할 수 있음
- 생성자 public : 생성자를 호출해 객체를 생성할 수 있음

### 1. 접근 지정자가 public인 클래스

```java
package abc.bcd;

public class A {
		// 컴파일러가 public A() {} 자동으로 추가
}

class B {
		// A 클래스 사용(선언 / 생성) 가능
}
```

```java
package bcd.cde;

import abc.bcd.A; // 클래스의 접근 지정자가 public이므로 임포트 가능

class C {
		public static void main(String[] args) {
				A a = new A(); // 생성자의 접근 지정자가 public이므로 호출 가능
		}
}
```

### 2. 접근 지정자가 default인 클래스

```java
package abc.bcd;

class A {
		// 컴파일러가 A() {} 자동으로 추가
}

class B {
		// A 클래스 사용(선언 / 생성) 가능
}
```

```java
package bcd.cde;

import abc.bcd.A; // 클래스의 접근 지정자가 default이므로 임포트 불가능

class C {
		public static void main(String[] args) {
				A a = new A(); // 임포트되지 않았을 때 생성자의 접근 지정자와 상관없이 호출 불가능
		}
}
```

### 3. 클래스는 public, 생성자는 default

```java
package abc.bcd;

public class A {
		A() {
				// ...
		}
}

class B {
		// A 클래스 사용(선언 / 생성) 가능
}
```

```java
package bcd.cde;

import abc.bcd.A; // 클래스의 접근 지정자가 public이므로 임포트 가능

class C {
		public static void main(String[] args) {
				A a = new A(); // 생성자의 접근 지정자가 default이므로 호출 불가능
		}
}
```

### 4. 클래스는 default, 생성자는 public

```java
package abc.bcd;

class A {
		public A() {
				// ...
		}
}

class B {
		// A 클래스 사용(선언 / 생성) 가능
}
```

```java
package bcd.cde;

import abc.bcd.A; // 클래스의 접근 지정자가 default이므로 임포트 불가능

class C {
		public static void main(String[] args) {
				A a = new A(); // 임포트되지 않았을 때 생성자의 접근 지정자와 상관없이 호출 불가능
		}
}
```

# II. static 제어자

## 1. 개요

- static : 클래스의 멤버(필드, 메서드, 이너 클래스)에 사용하는 제어자
- 인스턴스 멤버(Instance Member) : 객체 안에 있을 때 사용할 수 있는 상태가 되는 멤버, static이 붙지 않은 것
- 정적 멤버(Static Member) : static이 붙어 있는 멤버, 객체의 생성 없이 `클래스명.멤버명` 만으로 바로 사용 가능

## 2. 인스턴스 필드와 정적 필드

```java
class A {
		int m = 3; // 객체를 생성한 후 사용 가능
		static int n = 5; // 객체 생성 없이 사용 가능
}
```

- 인스턴스 필드 : 객체 생성 → `참조 변수명.인스턴스 필드명` 으로 사용
    
    ```java
    A a = new A();
    
    System.out.println(a.m); // 3
    ```
    
- 정적 필드 : `클래스명.정적 필드명` 으로 사용
    
    ```java
    System.out.println(A.n); // 5
    ```
    
- 정적 필드 : 객체 생성 → `참조 변수명.인스턴스 필드명` 으로도 사용 가능(권장 X)
    
    ```java
    A a = new A();
    
    System.out.println(a.n) // 5
    ```
    

### 1. 정적 필드 공유 특성

- 정적 필드는 객체 간 공유 변수의 성질이 있음

```java
A a1 = new A();
A a2 = new A();

a1.m = 5; // a1 객체의 인스턴스 필드 m에 값 5 입력
a2.m = 6; // a2 객체의 인스턴스 필드 m에 값 6 입력

System.out.println(a1.m); // 5
System.out.println(a2.m); // 6

a1.n = 7; // a1 객체의 정적 필드 n에 값 7 입력
a2.n = 8; // a2 객체의 정적 필드 n에 값 8 입력

System.out.println(a1.n); // 8
System.out.println(a2.n); // 8

A.n = 9; // 클래스 A의 정적 필드 n에 값 9 입력

System.out.println(a1.n); // 9
System.out.println(a2.n); // 9
```

## 3. 인스턴스 메서드와 정적 메서드

```java
class A {
		void abc() { // 객체를 생성한 후에 사용 가능
				System.out.println("instance 메서드");
		}

		static void bcd() { // 객체 생성 없이 사용 가능
				System.out.println("static 메서드");
		}
}
```

- 인스턴스 메서드 : 객체 생성 → `참조 변수명.인스턴스 필드명` 으로 사용
    
    ```java
    A a = new A();
    
    a.abc(); // 인스턴스 메서드
    ```
    
- 정적 메서드 : `클래스명.정적 필드명` 으로 사용
    
    ```java
    A.bcd(); // 정적 메서드
    ```
    
- 정적 메서드 : 객체 생성 → `참조 변수명.인스턴스 필드명` 으로도 사용 가능(권장 X)
    
    ```java
    A a = new A();
    
    a.bcd(); // 정적 메서드
    ```
    
- 인스턴스 메서드와 정적 메서드 모두 메모리의 첫 번쨰 영역에 위치
- 인스턴스 메서드 → 인스턴스 메서드 영역, 정적 메서드 → 클래스 내부에 존재

## 4. 정적 메서드 안에서 사용할 수 있는 필드와 메서드

- 정적 메서드 내부 : 정적 멤버(정적 필드, 정적 메서드)만 사용 가능, 인스턴스 멤버(인스턴스 필드, 인스턴스 메서드) 사용 불가
- 정적 멤버(정적 필드, 정적 메서드) : 객체의 생성 없이 실행 가능
- 인스턴스 멤버(인스턴스 필드, 인스턴스 메서드) : 반드시 객체를 생성한 후에 사용 가능
- 정적 메서드 내부 : 클래스 내부에서 자신의 객체를 가리키는 this 키워드 사용 불가 → this. 가 자동으로 붙어야 하는 인스턴스 멤버 존재 불가

```java
class A {
		int a; // 객체 생성 후 사용 가능
		static int b; // 객체를 생성하기 전 사용 가능

		void abc() { // 객체 생성 후 사용 가능
				// a, b, bcd(), cde() 사용 가능
		}

		static void bcd() { // 객체를 생성하기 전 사용 가능
				// b, cde() 사용 가능
		}

		static void cde() { // 객체를 생성하기 전 사용 가능
				// b, bcd() 사용 가능
		}
}
```

## 5. 정적 초기화 블록

- 인스턴스 필드의 초기화 : 객체가 만들어지는 시점에 실행(객체 생성자에서 제작)
- 정적 필드의 초기화 : 생성자가 호출되지 않은 상태에서도 초기화 → 생성자는 정적 필드를 초기화 할 수 없음

```java
static {
		// 클래스가 메모리에 로딩될 때 실행되는 내용
}
```

```java
class A {
		int a;
		static int b;

		static { // static 초기화 블록
				b = 5;
				System.out.println("클래스가 로딩될 때 static block 실행");
		}

		A() {
				a = 3; // 인스턴스 필드의 초기화 위치
		}
}
```

## 6. static main() 메서드

- `public static void main(String[] args)` : 정적 메서드
- JVM → `실행 클래스명.main()` 만으로도 실행(main() 메서드가 정적 메서드이기 때문)
