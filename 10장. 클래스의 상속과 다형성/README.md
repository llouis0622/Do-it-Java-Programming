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
