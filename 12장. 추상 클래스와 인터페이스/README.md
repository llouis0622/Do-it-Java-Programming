# Chapter 12. 추상 클래스와 인터페이스

# I. 추상 클래스

## 1. 추상 클래스의 정의

- 추상 메서드 : 메서드의 본체가 완성되지 않은 미완성 메서드, `abstract 리턴 타입 메서드명(입력매개변수;`
- 추상 클래스 : 추상 메서드를 1개 이상 포함하고 있는 클래스, `abstract class 클래스명 { ... }`

## 2. 추상 클래스의 특징

- 객체를 직접 생성할 수 없음
- 추상 클래스를 상속하는 자식 클래스는 부모에게 상속받은 추상 메서드를 반드시 완성(오버라이딩)해야 함
- 구현(Implements) : 부모에게 물려받은 미완성 메서드를 자식 클래스에서 완성하는 것

## 3. 추상 클래스 타입의 객체 생성 방법

- 추상 클래스를 상속한 일반 클래스 생성 → 객체를 여러 개 만들어야 하는 상황
    
    ```java
    abstract class A {
    		abstract void abc();
    }
    // A a = new A(); 불가능
    
    class B extends A {
    		void abc() {
    				// ...
    		}
    }
    // A a = new B(); 가능
    // B b = new B(); 가능
    ```
    
    ```java
    package com.llouis;
    
    abstract class A {
    		abstract void abc();
    }
    
    class B extends A {
    		void abc() {
    				System.out.println("방법 1. 자식 클래스 생성 및 추상 메서드 구현");
    		}
    }
    
    public class AbstractClass1 {
    		public static void main(String[] args) {
    				A b1 = new B();
    				A b2 = new B();
    
    				b1.abc();
    				b2.abc();
    		}
    }
    // 방법 1. 자식 클래스 생성 및 추상 메서드 구현
    // 방법 1. 자식 클래스 생성 및 추상 메서드 구현
    ```
    
- 익명 이너 클래스 사용 → 딱 한 번만 만들어 사용할 객체
    
    ```java
    클래스명 참조 변수명 = new 생성자() {
    		// 추상 클래스에 포함된 추상 메서드 오버라이딩
    };
    ```
    
    ```java
    A a = new A() {
    		void abc() { // 추상 메서드의 오버라이딩(완성)
    				// ...
    		}
    };
    ```
    
    ```java
    package com.llouis;
    
    abstract class A {
    		abstract void abc();
    }
    
    public class AbstractClass2 {
    		public static void main(String[] args) {
    				A a1 = new A() {
    						void abc() {
    								System.out.println("방법 2. 익명 이너 클래스 방법으로 객체 생성");
    						}
    				};
    
    				A a2 = new A() {
    						void abc() {
    								System.out.println("방법 2. 익명 이너 클래스 방법으로 객체 생성");
    						}
    				};
    
    				a1.abc();
    				a2.abc();
    		}
    }
    // 방법 2. 익명 이너 클래스 방법으로 객체 생성
    // 방법 2. 익명 이너 클래스 방법으로 객체 생성
    ```
    

# II. 인터페이스

## 1. 인터페이스의 정의와 특징

- 입출력 방식의 호환성
- 동일한 목적 하에 동일한 기능을 수행하게끔 강제하는 것

```java
interface 인터페이스명 {
		public static final 자료형 필드명 = 값;
		public abstract 리턴 타입 메서드명();
}
```

- 인터페이스 내에서 필드와 메서드에 사용할 수 있는 제어자 확정 → 생략 시 자동으로 추가

## 2. 인터페이스의 상속

- `implements` 키워드 사용
- 다중 상속 가능
- 모든 필드가 `public static final` 로 정의 → 실제 데이터값은 각각의 인터페이스 내부에 존재하기 때문에 공간상 겹치지 않음

```java
클래스명 implements 인터페이스명, ..., 인터페이스명 {
		// ...
}
```

### 1. 클래스, 인터페이스 상속 조합

```java
클래스명 extends 클래스명 implements 인터페이스명, ..., 인터페이스명 {
		// ...
}
```

```java
package com.llouis;

interface A {}
interface B {}

class C implements A {
		// ...
}

class D implements A, B {
		// ...
}

class E extends C implements A, B {
		// ...
}

public class InheritanceOfInterface {
		public static void main(String[] args) {
				// ...
		}
}
```

- `클래스 extends 클래스`
- `인터페이스 extends 인터페이스`
- `클래스 implements 인터페이스`
- `인터페이스 extends/implements 클래스` → 불가능
- 같은 타입끼리는 extends, 다른 타입끼리는 implements(인터페이스는 클래스 상속 불가능)

## 3. 인터페이스 타입의 객체 생성 방법

- 자식 클래스 정의, 자식 클래스의 생성자로 객체 생성 → 여러 개의 객체 생성
    
    ```java
    interface A {
    		int a = 3;
    		void abc();
    }
    // A a = new A(); 불가능
    
    class B implements A {
    		public void abc() {
    				// ...
    		}
    }
    // A a = new B(); 가능
    // B b = new B(); 가능
    ```
    
- 익명 이너 클래스 → 1개의 객체만 생성
    
    ```java
    interface A {
    		int a = 3;
    		void abc();
    }
    
    A a = new A() {
    		public void abc() {
    				// ...
    		}
    };
    ```
    

## 4. 인터페이스의 필요성

- 동일한 인터페이스 상속(동일한 메서드) → 메서드의 내부 구현 내용 서로 다름
- 메서드만 변경해서 사용 가능

## 5. 디폴트 메서드와 정적 메서드

- 디폴트 메서드 : 인터페이스 내에 완성된 메서드, `public default`
- 완성된 메서드 → 구현 클래스를 반드시 오버라이딩 할 필요가 없음
- 자식 클래스에서 오버라이딩해 사용 가능

```java
interface 인터페이스명 {
		public default 리턴 타입 메서드명 {
				// ...
		}
}
```

```java
package com.llouis;

interface A {
		void abc();
		default void bcd() {
				System.out.println("A 인터페이스의 bcd()");
		}
}

class B implements A {
		public void abc() {
				System.out.println("B 클래스의 abc()");
		}
}

class C implements A {
		public void abc() {
				System.out.println("C 클래스의 abc()");
		}

		public void abc() {
				System.out.println("C 클래스의 bcd()");
		}
}

public class DefaultMethod {
		public static void main(String[] args) {
				A a1 = new B();
				A a2 = new C();

				a1.abc();
				a1.bcd();
				a2.abc();
				a2.bcd();
		}
}
// B 클래스의 abc()
// A 인터페이스의 bcd()
// C 클래스의 abc()
// C 클래스의 bcd()
```

### 1. 자식 클래스에서 부모 인터페이스 디폴트 메서드 호출

- `부모 인터페이스명.super.디폴트 메서드명`
- 인터페이스는 다중 상속 가능 → 부모 인터페이스명을 붙여 사용

```java
package com.llouis;

interface A {
		default void abc() {
				System.out.println("A 인터페이스의 abc()");
		}
}

class B implements A {
		public void abc() {
				A.super.abc(); // 부모 인터페이스 A의 abc() 메서드 호출

				System.out.println("B 클래스의 abc()");
		}
}

public class DefaultMethod_CtoP {
		public static void main(String[] args) {
				B b = new B();

				b.abc();
		}
}
// A 인터페이스의 abc()
// B 클래스의 abc()
```

### 2. static 메서드 포함

- `인터페이스명.정적 메서드명`
- 클래스 내부의 정적 메서드와 동일한 기능

```java
package com.llouis;

interface A {
		static void abc() {
				System.out.println("A 인터페이스의 정적 메서드 abc()");
		}
}

public class StaticMethod {
		public static void main(String[] args) {
				A.abc();
		}
}
// A 인터페이스의 정적 메서드 abc()
```
