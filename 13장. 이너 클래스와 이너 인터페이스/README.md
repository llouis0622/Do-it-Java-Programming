# Chapter 13. 이너 클래스와 이너 인터페이스

# I. 이너 클래스(Inner Class)

## 1. 개요

- 클래스 내부에 포함
- 인스턴스 멤버, 정적 멤버 이너 클래스 : 클래스의 멤버
- 지역 이너 클래스 : 메서드 내에서 정의, 해당 메서드 내부에서만 한정적으로 사용

```java
class A { // 아우터 클래스
		class B { // 인스턴스 멤버 클래스(멤버 이너 클래스)
				// ...
		}

		static class C { // 정적 멤버 클래스(멤버 이너 클래스)
				// ...
		}

		void abc() {
				class D { // 지역 이너 클래스(지역 클래스)
						// ...
				}
		}
}
```

## 2. 인스턴스 멤버 이너 클래스

- 객체 내부에 멤버의 형태로 존재
- 자신을 감싸고 있는 아우터 클래스의 모든 접근 지정자의 멤버에 접근 가능

### 1. 인스턴스 이너 클래스 객체 생성하기

- 아우터 클래스 객체 생성 → 참조 변수 활용
- `아우터 클래스 아우터 클래스 참조 변수 = new 아우터 클래스();`
- `아우터 클래스.이너 클래스 이너 클래스 참조 변수 = 아우터 클래스 참조 변수.new 이너 클래스()`

```java
class A {
		class B {
		}
}

A a = new A();
A.B b = a.new B();
```

### 2. 아우터 클래스의 객체 참조하기

- `아우터 클래스명.this` : 이너 클래스 나부에서 아우터 클래스의 멤버 참조

## 3. 정적 멤버 이너 클래스

- static 키워드가 포함된 이너 클래스
- 아우터 클래스의 정적 멤버에만 접근 가능
- 정적 이너 클래스 내부 : 아우터 클래스의 정적 멤버만 사용 가능

### 1. 정적 이너 클래스 객체 생성하기

- `아우터 클래스.이너 클래스 이너 클래스 참조 변수 = new 아우터 클래스.이너 클래스();`

```java
class A {
		static class B {
		}
}

A.B b = new A.B();
```

## 4. 지역 이너 클래스

- 클래스의 멤버가 아닌 메서드 내에서 정의되는 클래스
- 선언 이후 바로 객체를 생성해 사용
- 메서드가 호출될 때만 메모리에 로딩

### 1. 지역 이너 클래스 객체 생성하기

- 아우터 클래스의 멤버를 접근 지정자와 상관없이 사용 가능
- 자신이 정의된 메서드의 지역 변수도 클래스 내부에서 사용 가능
- 지역 변수를 사용 → 반드시 해당 지역 변수가 final로 선언
- `지역 이너 클래스 이너 클래스 참조 변수 = new 지역 이너 클래스();`

```java
class A {
		void abc() {
				class B { // 지역 이너 클래스
				}

				B b = new B(); // 지역 이너 클래스 객체 생성
		}
}
```

# II. 익명 이너 클래스

## 1. 익명 이너 클래스의 정의와 특징

- 이름을 알 수 없는 이너 클래스
- 항상 부모 타입으로만 선언 가능
- 인스턴스 익명 이너 클래스 : 클래스의 중괄호 바로 아래에 사용
- 지역 익명 이너 클래스 : 메서드 내부에서 사용

```java
interface C {
		public abstract void bcd();
}

class A1 {
		C b = new B();

		void abc() {
				// 메서드 내용
		}

		class B implements C { // 인스턴스 이너 클래스
				public void bcd() {
						// 메서드 구현
				}
		}
}

class A2 {
		C b = new C() { // C를 상속받아 bcd() 메서드를 오버라이딩한 익명 이너 클래스 객체
				public void bcd() {
						// 메서드 구현
				}
		};

		void abc() {
				// 메서드 내용
		}
}
```

```java
interface C {
		public abstract void bcd();
}

class B implements C {
		public void bcd() { // 오버라이딩 메서드
				// 메서드 구현
		}

		public void cde() { // 추가 메서드
				// 메서드 구현
		}
}

B b = new B();

b.bcd(); // 가능
b.cde(); // 가능

C c = new C() {
		public void bcd() { // 오버라이딩 메서드
				// 메서드 구현
				cde(); // 내부적으로 호출 가능
		}

		public void cde() { // 추가 메서드
				// 메서드 구현
		}
};

c.bcd(); // 가능
c.cde(); // 불가능, C 타입 X
```

## 2. 익명 이너 클래스를 활용한 인터페이스 타입의 입력매개변수 전달

- 생성자를 이용해 생성한 객체 참조 변수에 저장 → 입력매개변수로 참조 변수 전달
- 참조 변수를 사용하지 않고 메서드 입력매개변수 위치에서 바로 객체 생성
- 익명 이너 클래스를 사용해 객체 생성 → 객체를 참조하는 참조 변수를 메서드의 입력매개변수로 전달
- 참조 변수를 대입하지 않고 메서드 입력매개변수 자리에서 바로 익명 이너 클래스 객체를 생성해 전달

```java
interface A {
		public abstract void abc();
}

class C {
		void cde(A a) {
				a.abc();
		}
}

class B implements A {
		public void abc() {
				// ...
		}
}

C c = new C();

A a1 = new B(); // 방법 1
c.cde(a1);

c.cde(new B()); // 방법 2

A a = new A() { // 방법 3
		public void abc() {
				// ...
		}
};
c.cde(a);

c.cde(new A() { // 방법 4
		public void abc() {
				// ...
		}
});
```

# III. 이너 인터페이스

## 1. 이너 인터페이스의 정의와 특징

- 정적 이너 인터페이스만 존재
- static 제어자 생략 시 컴파일러가 자동으로 추가

```java
class A {
		// ...
		static interface B {
				void bcd();
		}
}
```

### 1. 인터페이스 구현 클래스 생성 및 객체 생성

```java
class C implements A.B {
		public void bcd() {}
}

C C = new C();
c.bcd();
```

### 2. 익명 이너 클래스 사용

```java
A.B a = new A.B() {
		public void bcd() {}
};

a.bcd();
```

## 2. 이벤트 처리 기능 작성하기

- 인터페이스 구현 → 기능 정의

```java
package com.llouis;

class Button {
		OnClickListener ocl;

		void setOnClickListener (OnClickListener ocl) {
				this.ocl = ocl;
		}

		interface OnClickListener {
				public abstract void OnClick();
		}

		void OnClick() {
				ocl.OnClick();
		}
}

public class ButtonAPI {
		public static void main(String[] args) {
				Button bt1 = new Button();

				bt1.setOnClickListener(new Button.OnClickListener() {
						@Override
						public void onClick() {
								System.out.println("개발자 1 : 음악 재생");
						}
				});

				bt1.onClick();

				Button bt2 = new Button();

				bt2.setOnClickListener(new Button.OnClickListener() {
						@Override
						public void onClick() {
								System.out.println("개발자 2 : 네이버 접속");
						}
				});

				bt2.onClick();
		}
}

// 개발자 1 : 음악 재생
// 개발자 2 : 네이버 접속
```