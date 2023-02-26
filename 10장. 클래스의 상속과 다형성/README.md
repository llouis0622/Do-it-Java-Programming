# Chapter 11. 클래스의 상속과 다형성

# I. 클래스 상속의 개념과 문법적 특징

## 1. 상속의 개념

- 부모 클래스 : 자식 클래스의 공통적 특징을 모아 구성한 클래스
- 자식 클래스 : 부모 클래스 + 추가 필드, 메서드 구성
- UML 기호에서 화살표를 부모 클래스 쪽으로 그림

## 2. 상속의 장점

### 1. 코드의 중복성 제거

- 부모 클래스에서 한 번만 정의 → 코드 간결화

### 2. 다형적 표현 가능

- 다형성(Polymorphism) : 1개의 객체를 여러 가지 모양으로 표현할 수 있음
- 상속 구조도 : 화살표 방향으로 항상 다형적 표현 가능

```java
Apple[] apple = {new Apple(), new Apple()};
Grape[] grape = {new Grape(), new Grape()};
Kiwi[] kiwi = {new Kiwi(), new Kiwi(), new Kiwi()};
```

```java
Fruit fruit1 = new Apple(); // 사과는 과일이다
Fruit fruit2 = new Grape(); // 포도는 과일이다
Fruit fruit3 = new Kiwi(); // 키위는 과일이다

Fruit fruits = {new Apple(), new Apple(), new Grape(), new Grape(), new Kiwi(), new Kiwi(), new Kiwi()};
```

## 3. 상속 문법

- extends 키워드 사용 : `클래스명 + extends 부모 클래스`
- 클래스는 다중 상속 불가능(부모 클래스가 2개 이상)
- 다중 상속 시 모호성(Ambigous) 발생

```java
class 자식 클래스 extends 부모 클래스 {
		...
}
```

```java
package com.llouis;

class Human {
		String name;
		int age;
		void eat() {}
		void sleep() {}
}

class Student extends Human { // Human 클래스 상속
		int studentID;
		void goToSchol() {}
}

class Worker extends Human { // Human 클래스 상속
		int workerID;
		void goToWork() {}
}

public class Inheritance {
		public static void main(String[] args) {
				Human h = new Human(); // Human 객체 생성

				h.name = "박슬빈";
				h.age = 25;
				h.eat();
				h.sleep();

				Student s = new Student(); // Student 객체 생성

				s.name = "김채은";
				s.name = 21;
				s.studentID = 101;
				s.eat();
				s.sleep();
				s.goToSchool();

				Worker w = new Worker(); // Worker 객체 생성

				w.name = "오신의";
				w.age = 21;
				w.workerID = 102;
				w.eat();
				w.sleep();
				w.goToWork();
		}
}
```

## 4. 상속할 때의 메모리 구조

- JVM : 자식 클래스의 객체 생성 시 가장 먼저 부모 클래스의 객체 생성
- 이후 자식 클래스에서 추가한 필드, 메서드 객체에 추가

## 5. 생성자의 상속 여부

- 생성자 : 자식 클래스로 상속 불가
- 소괄호, 중괄호 존재 → 필드, 이너 클래스 X
- 생성자 → 클래스와 이름 다름, 생성자 X
- 리턴 타입 X → 메서드 X

```java
class A {
		A() {
				// ...
		}
}

class B extends A {
		A() {} // 생성자 or 메서드
		// ...
}
```

## 6. 객체의 다형적 표현

```java
class A {}
class B extends A {}
class C extends B {}
class D extends B {}
```

- 다형적 표현의 올바른 예
    
    ```java
    A a = new A();
    B b = new B();
    C c = new C();
    D d = new D();
    
    A a1 = new B();
    A a2 = new C();
    A a3 = new D();
    
    B b1 = new D();
    B b2 = new D();
    ```
    
- 다형적 표현의 잘못된 예
    
    ```java
    B b1 = new A();
    
    C c1 = new A();
    C c2 = new B();
    
    D d1 = new A();
    D d2 = new B();
    D d3 = new C();
    ```
    

# II. 객체의 타입 변환

## 1. 객체의 업캐스팅과 다운캐스팅

- 업캐스팅 : 범위가 좁은 쪽에서 넓은 쪽으로 캐스팅하는 것
- 다운캐스팅 : 범위가 넓은 쪽에서 좁은 쪽으로 캐스팅하는 것
- 자식 클래스 → 부모 클래스 : 업캐스팅(명시적으로 적지 않아도 가능)
- 부모 클래스 → 자식 클래스 : 다운캐스팅(직접 명시적으로 작성해야 함)
- 잘못된 다운캐스팅 : `ClassCastException` 예외 발생
- 캐스팅의 가능 여부 : 무슨 타입으로 선언돼 있는지는 중요하지 않음, 어떤 생성자로 생성되었는지가 중요

```java
class A {}
class B extends A {}
class C extends B {}
```

- 업캐스팅
    
    ```java
    B b1 = new B();
    A a1 = (A) b1;
    
    C c2 = new C();
    B b2 = (B) c2;
    A a2 = (A) c2;
    ```
    
- 다운캐스팅
    
    ```java
    A a1 = new A();
    B b1 = (B) a1; // 예외 발생
    
    A a2 = new B();
    B b2 = (B) a2; // 가능
    C c2 = (C) a2; // 예외 발생
    ```
    

## 2. 메모리로 이해하는 다운캐스팅

- 선언된 타입이 의미하는 바 : 실제 객체에서 자신이 선언된 타입의 객체를 가리키게 됨

```java
package com.llouis;

class A {}
class B extends A {}
class C extends B {}
class D extends B {}

public class TypeCasting {
		public static void main(String[] args) {
				A ac = (A) new C(); // C -> A 업캐스팅
				B bc = (B) new C(); // C -> B 업캐스팅

				B bb = new B();
				A a = (A) bb; // B -> A 업캐스팅

				A aa = new A();
				A ab = new B();
				B b = new ab; // A -> B 다운캐스팅

				B bd = new D();
				D d = (D) bd; // B -> D 다운캐스팅

				A ad = new D();
				B b1 = (B) ad; // A -> B 다운캐스팅
				D d1 = (D) ad; // A -> D 다운캐스팅
		}
}
```

## 3. 캐스팅 가능 여부를 확인하는 instanceof 키워드

- 실제 객체를 어떤 생성자로 만들었는지와 클래스 사이의 상속 관계 확인
- 캐스팅 가능 여부 : `참조 변수 instanceof 타입`
- 캐스팅 가능(true), 캐스팅 불가능(false)

```java
A ab = new B();

if(ab instanceof B) { // true
		B b = (B) ab; // 캐스팅 구문 실행
}

A aa = new A();

if(aa instanceof B) { // false
		B b = (B) aa; // 캐스팅 구문 실행 X
}
```

# III. 메서드 오버라이딩(Overriding)

## 1. 메서드 오버라이딩의 개념과 동작

- 부모 클래스에게 상속받은 메서드와 동일한 이름의 메서드를 재정의하는 것
- 부모의 메서드를 자신이 만든 메서드로 덮어쓰는 개념

### 1. 메서드 오버라이딩의 조건

- 부모 클래스의 메서드와 시그니처 및 리턴 타입 동일해야 함
- 부모 클래스의 메서드보다 접근 지정자의 범위가 같거나 넓어야 함
    
    ```java
    class A {
    		void print() {
    				System.out.println("A 클래스");
    		}
    }
    
    class B extends A {
    		void print() {
    				System.out.println("B 클래스");
    		}
    }
    ```
    

## 2. 메서드 오버라이딩을 사용하는 이유

```java
class Animal {
		void cry() {
				// ...
		}
}

class Bird extends Animal {
		void cry() {
				System.out.println("짹짹");
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

- 각각의 타입으로 선언 + 각각의 타입으로 객체 생성
    
    ```java
    Animal aa = new Animal();
    Bird bb = new Bird();
    Cat cc = new Cat();
    Dog dd = new Dog();
    
    aa.cry(); // 출력 X
    bb.cry(); // 짹쨱
    cc.cry(); // 야옹
    dd.cry(); // 멍멍
    ```
    
- 부모 클래스 타입으로 선언 + 각각의 타입으로 객체 생성
    
    ```java
    Animal ab = new Bird();
    Animal ac = new Cat();
    Animal ad = new Dog();
    
    ab.cry(); // 짹쨱
    ac.cry(); // 야옹
    ad.cry(); // 멍멍
    ```
    
- 배열로 한 번에 관리 가능
    
    ```java
    Animal[] animals = new Animal[] {new Bird(), new Cat(), new Dog()};
    
    for(Animal animal : animals) {
    		animal.cry(); // 짹짹, 야옹, 멍멍
    }
    ```
    

## 3. 메서드 오버라이딩과 메서드 오버로딩

- 오버로딩(Overloading) : 시그니처가 다른 여러 개의 메서드를 같은 공간에 정의하는 것
- 오버라이딩 : 파일명과 확장명이 완벽하게 동일한 파일을 같은 공간에 복사할 때 → 덮어쓰기 수행
- 오버로딩 : 파일명은 동일, 확장명이 다른 파일을 같은 폴더에 복사 → 각각의 파일이 모두 같은 공간에 존재

```java
class A {
		void print1() {
				System.out.println("A 클래스 print1");
		}

		void print2() {
				System.out.println("A 클래스 print2");
		}
}

class B extends A {
		void print1() { // 메서드 오버라이딩
				System.out.println("B 클래스 print1");
		}

		void print2(int a) { // 메서드 오버로딩
				System.out.println("B 클래스 print2");
		}
}
```

## 4. 메서드 오버라이딩과 접근 지정자

- 자식 클래스가 부모 클래스의 메서드 오버라이딩 : 반드시 상속받은 메서드의 접근 지정자와 범위가 같거나 넓은 접근 지정자 사용 → 접근 지정자의 범위 좁힐 수 없음

| 부모 클래스 메서드의 접근 지정자 | 메서드 오버라이딩을 할 때 사용할 수 있는 접근 지정자 |
| --- | --- |
| public | public |
| protected | public, protected |
| default | public, protected, default |
| private | public, protected, default, private |

```java
class A {
		protected void abc() {}
}

class B1 extends A {
		public void abc() {}
}

class B2 extends A {
		protected void abc() {}
}

class B3 extends A {
		void abc() {} // default 접근 지정자(좁아져서 불가능)
}

class B4 extends A {
		private void abc() {} // private 접근 지정자(좁아져서 불가능)
}
```

# IV. 인스턴스 필드와 정적 멤버의 중복

## 1. 인스턴스 필드의 중복

- 상속받은 필드와 동일한 이름으로 자식 클래스에서 정의해도 각각의 저장 공간에 저장 → 오버라이딩 발생 X

```java
package com.llouis;

class A {
		int m = 3;
}

class B extends A {
		int m = 4;
}

public class InstanceField {
		public static void main(String[] args) {
				A aa = new A();
				B bb = new B();
				A ab = new B();

				System.out.println(aa.m); // 3
				System.out.println(bb.m); // 4
				System.out.println(ab.m); // 3
		}
}
```

## 2. 정적 필드의 중복

- 클래스 내에 포함된 정적 필드의 저장 공간은 완벽하게 분리돼 있음 → 오버라이딩 발생 X
- 상속할 때 정적 필드명 중복 정의 → 저장 공간 분리로 인한 오버라이딩 발생 X

```java
package com.llouis;

class A {
		static int m = 3;
}

class B extends A {
		static int m = 4;
}

public class StaticField {
		public static void main(String[] args) {
				System.out.println(A.m); // 3
				System.out.println(B.m); // 4

				A aa = new A();
				B bb = new B();
				A ab = new B();

				System.out.println(aa.m); // 3
				System.out.println(bb.m); // 4
				System.out.println(ab.m); // 3
		}
}
```

## 3. 정적 메서드의 중복

- 부모 클래스의 정적 메서드 : 부모 클래스의 내부에 저장
- 자식 클래스의 정적 메서드 : 자식 클래스의 내부에 저장
- 부모 클래스의 정적 메서드와 동일한 이름으로 자식 클래스에서 정의 → 절대 오버라이딩 X

```java
package com.llouis;

class A {
		static void print() {
				System.out.println("A 클래스");
		}
}

class B extends A {
		static void print() {
				System.out.println("B 클래스");
		}
}

public class StaticMethod {
		public static void main(String[] args) {
				A.print(); // A 클래스
				B.print(); // B 클래스

				A aa = new A();
				B bb = new B();
				A ab = new B();

				aa.print(); // A 클래스
				bb.print(); // B 클래스
				ab.print(); // A 클래스
		}
}
```

## 4. 인스턴스 멤버와 정적 멤버 중복 정리

- 오버라이딩 가능 : 인스턴스 메서드(메서드 오버라이딩)
- 오버라이딩 불가능 : 인스턴스 필드, 정적 필드, 정적 메서드
- `A a = new B();` : 값을 읽을 때의 기준으로 오버라이딩 불가능 / 가능

# V. super 키워드와 super() 메서드

## 1. 부모의 객체를 가리키는 super 키워드

- 부모의 객체를 가리키는 것
- 필드명의 중복 또는 메서드 오버라이딩으로 가려진 부모의 필드, 메서드를 호출하기 위해 사용

```java
class A {
		void init() {
				// 메모리 할당, 화면 세팅, 변수 초기화 등의 코드 100줄
		}
}

class B extends A {
		void init() {
				// 메모리 할당, 화면 세팅, 변수 초기화 등의 코드 100줄
				// 화면 출력 코드 1줄
		}
}

class C extends A {
		void init() {
				// 화면 출력 코드 1줄
		}
}
// super 키워드를 사용하여 자식 클래스의 메서드에서 부모 클래스 메서드 호출, 추가할 코드 1줄만 따로 작성
```

- 멤버 앞에 있는 참조 변수를 생략(this.)했을 때의 메서드 호출
    
    ```java
    package com.llouis;
    
    class A {
    		void abc() {
    				System.out.println("A 클래스의 abc()");
    		}
    }
    
    class B extends A {
    		void abc() {
    				System.out.println("B 클래스의 abc()");
    		}
    
    		void bcd() {
    				abc(); // this.abc();
    		}
    }
    
    public class SuperKeyword1 {
    		public static void main(String[] args) {
    				B bb = new B();
    				bb.bcd(); // B 클래스의 abc()
    		}
    }
    ```
    
- 멤버 앞에 있는 super 키워드를 사용했을 때의 메서드 호출
    
    ```java
    package com.llouis;
    
    class A {
    		void abc() {
    				System.out.println("A 클래스의 abc()");
    		}
    }
    
    class B extends A {
    		void abc() {
    				System.out.println("B 클래스의 abc()");
    		}
    
    		void bcd() {
    				super.abc(); // 부모 클래스 객체의 abc() 메서드 호출
    		}
    }
    
    public class SuperKeyword2 {
    		public static void main(String[] args) {
    				B bb = new B();
    				bb.bcd(); // A 클래스의 abc()
    		}
    }
    ```
    

## 2. 부모 클래스의 생성자를 호출하는 super() 메서드

- 부모 클래스의 생성자 호출
- 생성자 내부에서만 사용 가능, 반드시 첫 줄에 위치
- 모든 생성자의 첫 줄에는 반드시 `this()` 또는 `super()` 가 있어야 함

```java
class A {
		A() {
				System.out.println("A 생성자");
		}
}

class B extends A {
		B() {
				super();
				System.out.println("B 생성자");
		}
}
```

- super()의 자동 추가 확인
    
    ```java
    package com.llouis;
    
    class A {
    		A() {
    				System.out.println("A 생성자");
    		}
    }
    
    class B extends A {
    		B() {
    				super();
    				System.out.println("B 생성자");
    		}
    }
    
    class C {
    		C(int a) {
    				System.out.println("C 생성자");
    		}
    }
    
    class D extends C {
    		/* 컴파일러가 자동으로 추가해 주는 내용
    		D() {
    				super();
    		}
    		*/
    		D() {
    				super(3);
    		}
    }
    
    public class SuperMethod {
    		public static void main(String[] args) {
    				A aa = new A(); // A 생성자
    				System.out.println();
    
    				B bb = new B(); // A 생성자, B 생성자
    		}
    }
    ```
    
- `this()` 메서드와 `super()` 메서드의 혼용
    
    ```java
    package com.llouis;
    
    class A {
    		A() {
    				this(3);
    				System.out.println("A 생성자 1");
    		}
    
    		A(int a) {
    				System.out.println("A 생성자 2");
    		}
    }
    
    class B extends A {
    		B() {
    				this(3);
    				System.out.println("B 생성자 1");
    		}
    
    		B(int a) {
    				System.out.println("B 생성자 2");
    		}
    }
    
    public class ThisSuperMethod {
    		public static void main(String[] args) {
    				A aa1 = new A(); // A 생성자 2, A 생성자 1
    				System.out.println();
    
    				A aa2 = new A(3); // A 생성자 2
    				System.out.println();
    
    				B bb1 = new B(); // A 생성자 2, A 생성자 1, B 생성자 2, B 생성자 1
    				System.out.println();
    
    				B bb2 = new B(3); // A 생성자 2, A 생성자 1, B 생성자 2
    		}
    }
    ```
    

# VI. 최상위 클래스 Object

## 1. 개요

- 모든 클래스는 Object 클래스를 상속 받음 → 자바의 최상위 클래스
- 아무런 클래스를 상속하지 않으면 자동으로 `extends Object` 를 삽입해 Object 클래스를 상속함
- 사용자가 어떤 클래스 타입의 객체를 생성하더라도 다형성에 따라 Object 타입이라고 불릴 수 있음 → 입력매개변수로 모든 타입의 객체를 받아들일 수 있음

```java
class A {
		// ...
}

class A extends Object {
		// ...
} // 상속하지 않을 때 자동으로 추가

class B extends A {
		// ...
}

Object oa = new A();
Object ob = new B();
```

## 2. Object 클래스의 주요 메서드

### 1. `toString()`

- 객체 정보를 문자열로 리턴하는 메서드
- `패키지명.클래스명@해시코드` 해시코드(객체가 저장된 위치와 관련된 값)
- 실제 객체의 정보 표현 : 객체에 포함돼 있는 필드값 출력

```java
package com.llouis;

class A { // extends Object(컴파일러에 따라 자동으로 추가)
		int a = 3;
		int b = 4;
}

class B {
		int a = 3;
		int b = 4;

		public String toString() {
				return "필드값(a, b) = " + a + " " + b;
		}
}

public class Method_toString {
		public static void main(String[] args) {
				A a = new A();
				B b = new B();

				System.out.printf("%x\n", a.hashCode()); // hashCode를 16진수로 표현
				System.out.println(a,toString()); // toString() 생략 시 자동으로 추가
				System.out.println(b);
		}
}

// 7852e922
// com.llouis.A@7852e922
// 필드값(a, b) = 3 4
```

### 2. `equals(Object obj)`

- 입력매개변수로 넘어온 객체와 자기 객체의 스택 메모리 변숫값을 비교해 그 결과를 true 또는 false로 리턴하는 메서드
- 실제 데이터 값이 아닌 실제 데이터의 위치(번지)를 비교
- 등가 비교 연산과 완벽하게 동일한 기능 수행

```java
package com.llouis;

class A {
		String name;

		A(String name) {
				this.name = name;
		}
}

class B {
		String name;

		B(String name) {
				this.name = name;
		}

		@Override
		public boolean equals(Object obj) {
				if(this.name == ((B)obj).name) return true;
				else return false;
		}
}

public class Method_equals {
		public static void main(String[] args) {
				A a1 = new A("안녕");
				A a2 = new A("안녕");

				System.out.println(a1 == a2); // false
				System.out.println(a1.equals(a2)); // false

				B b1 = new B("안녕");
				B b2 = new B("안녕");

				System.out.println(b1 == b2); // false
				System.out.println(b1.equals(b2)); // true
		}
}
```

### 3. `hashCode()`

- 객체의 위치와 관련된 값 → 실제 위치를 나타내는 값은 아님
- Hashtable, HashMap : 동등 비교를 위해 hashCode()까지 오버라이딩 필수
- HashMap 자료 구조 : 데이터(Key, Value)의 쌍 → Key 값은 중복 X
- Object의 hashCode() 메서드는 객체의 위치에 따른 고유값을 리턴
- Hash*** 형태의 자료 구조 → 동등 비교를 위해 hashCode() 결과값을 비교하므로 필요할 때마다 equals() 메서드와 함께 추가로 오버라이딩 필수

```java
package com.llouis;

import java.util.HashMap;

class A {
		String name;

		A(String name) {
				this.name = name;
		}

		@Override
		public boolean equals(Object obj) {
				if(this.name == ((A)obj).name) return true;
				else return false;
		}

		@Override
		public String toString() {
				return name;
		}
}

class B {
		String name;

		A(String name) {
				this.name = name;
		}

		@Override
		public boolean equals(Object obj) {
				if(this.name == ((B)obj).name) return true;
				else return false;
		}

		@Override
		public int hashCode() {
				return name.hashCode();
		}

		@Override
		public String toString() {
				return name;
		}
}

public class Method_hashcode {
		public static void main(String[] args) {
				HashMap<Integer, String> hm1 = new HashMap<>();

				hm1.put(1, "데이터1");
				hm1.put(2, "데이터2");
				hm1.put(3, "데이터3");

				System.out.println(hm1);

				HashMap<A, String> hm2 = new HashMap<>();

				hm2.put(new A("첫 번째"), "데이터1");
				hm2.put(new A("첫 번째"), "데이터2");
				hm2.put(new A("두 번째"), "데이터3");

				System.out.println(hm2);

				HashMap<B, String> hm3 = new HashMap<>();

				hm3.put(new B("첫 번째"), "데이터1");
				hm3.put(new B("첫 번째"), "데이터2");
				hm3.put(new B("두 번째"), "데이터3");

				System.out.println(hm3);
		}
}

// {1=데이터2, 2=데이터3}
// {첫 번째=데이터1, 두 번째=데이터3, 첫 번째=데이터2}
// {첫 번째=데이터2, 두 번째=데이터3}
```
