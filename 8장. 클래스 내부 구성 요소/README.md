# Chapter 08. 클래스 내부 구성 요소

# I. 필드

## 1. 필드와 지역 변수의 구분

### 1. 필드(Field)

- 클래스에 포함된 변수
- 객체의 속성값을 지정
- 클래스의 중괄호 안에 선언
- 힙 메모리의 객체 내부

### 2. 지역 변수(Local Variable)

- 메서드에 포함된 변수
- 메서드의 중괄호 안에 선언
- 스택 메모리
- 프레임(Frame) : 1개의 메서드 안에 선언된 모든 지역 변수들의 집합

## 2. 필드와 지역 변수의 초기값

### 1. 필드

- 직접 초기화하지 않아도 강제로 초기화
- 힙 메모리에는 빈 공간이 저장될 수 없기 때문에 힙 메모리에 위치하는 필드는 강제로 초기화
- boolean(false), 정수형(0), 실수형(0.0)으로 초기화

### 2. 지역 변수

- 직접 초기화하지 않으면 저장 공간이 빈 공간 그대로 있어 값을 출력하고자 할 때 오류 발생
- 스택 메모리는 강제로 초기화되지 않기 때문에 지역 변수도 마찬가지

# II. 메서드

## 1. 메서드 정의하기

```java
자바 제어자 리턴(반환) 타입 메서드명(입력매개변수) {
	메서드 내용
}
```

- 메서드 : 클래스의 기능에 해당
- 리턴 타입 : 메서드 종료 이후 변환(반환)되는 값의 자료형
- 입력매개변수 : 메서드를 호출할 때 전달되는 값의 자료형과 전달받은 값을 저장할 지역 변수명
- 메서드 내용 : 메서드가 수행해야 할 기능

## 2. 여러 리턴 타입의 메서드 살펴보기

### 1. 리턴 타입이 void이고, 입력매개변수가 없는 메서드

```java
void print() {
	System.out.println("안녕");
}
```

### 2. 리턴 타입이 int이고, 입력매개변수가 없는 메서드

```java
int data() {
	return 3;
}
```

### 3. 리턴 타입이 double이고, 입력매개변수가 2개인 메서드

```java
double sum(int a, double b) {
	return a + b;
}
```

## 3. 메서드 호출하기

### 1. 클래스 외부에서 메서드 호출하기

- 메서드도도 클래스의 멤버이므로 객체 안에 존재
- 클래스 외부에서 메서드를 사용하려면 먼저 객체를 생성해야 함
- 객체의 위치를 저장하고 있는 참조 변수를 이용해 메서드를 호출
    
    ```java
    class A {
        void print() {
            System.out.println("안녕");
        }
    
        int data() {
            return 3;
        }
    
        double sum(int a, double b) {
            return a + b;
        }
    
        void printMonth(int m) {
            if(m < 0 || m > 12) {
                System.out.println("잘못된 입력");
    
                return;
            } else {
                System.out.println(m + "월입니다.");
            }
        }
    }
    
    public class Test {
        public static void main(String[] args) {
            A a = new A();
            a.print();
    
            int k = a.data();
            a.data();
            System.out.println(k);
    
            double result = a.sum(3, 5.2);
            System.out.println(result);
    
            a.printMonth(5);
            a.printMonth(15);
        }
    }
    ```
    

### 2. 클래스 내부에서 메서드 호출하기

- 클래스 내부에 있는 메서드끼리는 객체를 생성하지 않고 서로 호출 가능
- 필드 또한 클래스 내부의 모든 메서드 안에서 객체를 생성하지 않고 사용 가능
- 같은 멤버끼리는 클래스 내부에서 얼마든지 객체를 생성하지 않고 서로 호출 가능
    
    ```java
    public class Test {
        public static void main(String[] args) {
            print();
    
            int a = twice(3);
            System.out.println(a);
    
            double b = sum(a, 5.8);
            System.out.println(b);
        }
    
        public static void print() {
            System.out.println("안녕");
        }
    
        public static int twice(int k) {
            return k * 2;
        }
    
        public static double sum(int m, double n) {
            return m + n;
        }
    }
    ```
    

### 3. 입력매개변수가 배열인 메서드 호출하기

- 호출할 때의 배열 데이터 입력 방법
- 1차원 배열 객체를 생성
    
    ```java
    import java.util.*;
    
    public class Test {
        public static void main(String[] args) {
            int[] a = new int[]{1, 2, 3};
    
            printArray(a);
            printArray(new int[] {1, 2, 3});
        }
    
        public static void printArray(int[] a) {
            System.out.println(Arrays.toString(a));
        }
    }
    ```
    

### 4. 기본 자료형 입력매개변수와 참조 자료형 입력매개변수의 차이

- 참조 자료형이 넘겨질 때 객치의 위치값이 전달
- 기본 자료형은 실제 값이 전달
- 기본 자료형의 값이 메서드의 지역 변수에 복사
- 기본 자료형을 입력매개변수로 전달하면 전달받은 메서드는 값을 복사해 사용

## 4. 오버로딩된 메서드

### 1. 메서드 시그니처(Method Signature)

- 메서드명 + 입력매개변수의 자료형
- 메서드를 구분하는 기준 역할

### 2. 메서드 오버로딩(Method Overloading)

- 입력매개변수의 개수나 자료형이 다른 여러 개의 동일한 이름을 지닌 메서드를 같은 공간에 정의하는 것
    
    ```java
    리턴 타입 메서드명(자료형 변수명, 자료형 변수명, ...) {
    }
    ```
    
- 입력매개변수에 따라 실제 어떤 메서드가 호출된 것인지만 구분
- 시그니처에는 리턴 타입이 빠져 있음
- 호출 과정에서 리턴 타입을 사용하지 않기 때문에 리턴 타입으로는 메서드를 구분 할 수 없음
    
    ```java
    public class Test {
        public static void main(String[] args) {
            print();
            print(3);
            print(5.8);
            print(2, 5);
        }
    
        public static void print() {
            System.out.println("데이터가 없습니다.");
        }
    
        public static void print(int a) {
            System.out.println(a);
        }
    
        public static void print(double a) {
            System.out.println(a);
        }
    
        /*
        public static void print(double b) {
            System.out.println(b);
        }
        */
    
        public static void print(int a, int b) {
            System.out.println("a: " + a + "b:" + b);
        }
    
        /*
        public static void print(int a, int b) {
            System.out.println("a: " + a + "b:" + b);
            return a + b;
        }
        */
    }
    ```
    

## 5. 가변 길이 배열 입력매개변수 메서드

- 가변 길이의 입력은 배열로 저장
- 배열의 크기는 함수가 호출될 때 전달된 입력값의 개수로 정해짐
    
    ```java
    리턴 타입 메서드명(자료형... 참조 변수명) {
    		...
    }
    ```
    
- 자료형...으로 모든 메서드 호출에 대응
    
    ```java
    public class Test {
        public static void main(String[] args) {
            method(1, 2);
            method(1, 2, 3);
            method();
        }
    
        public static void method(int... values) {
            System.out.println("입력매개변수 길이 : " + values.length);
            
            for(int i = 0; i < values.length; ++i) {
                System.out.println(values[i] + " ");
            }
        }
    }
    ```
    

# III. 생성자

## 1. 생성자(Constructor)

- 객체를 생성하는 역할을 지닌 클래스의 내부 구성 요소
- 객체 내에 포함되는 필드의 초기화 또한 주로 생성자 내에서 수행

### 1. 생성자의 특징

- 반드시 클래스명과 동일한 이름
- 메서드와 비슷한 구조, 리턴 타입은 없음

```java
클래스명(입력매개변수) {
}
```

## 2. 기본 생성자의 자동 추가

- 생성자를 포함하지 않는 클래스에게 컴파일러가 자동으로 기본 생성자 추가

## 3. 생성자와 객체의 생성 방법

- 생성자의 모양에 따라 객체를 생성하는 방법 결정
- 메서드처럼 생성자의 입력매개변수 자료형이나 개수에 따라 여러 개의 생성자 정의 가능
    
    ```java
    public class Test {
        public static void main(String[] args) {
            A a1 = new A();
            A a2 = new A(3);
            A a3 = new A(3, 5);
        }
    }
    
    class A {
        A() {
            System.out.println("첫 번째 생성자");
        }
    
        A(int a) {
            System.out.println("첫 번째 생성자");
        }
    
        A(int a, int b) {
            System.out.println("첫 번째 생성자");
        }
    }
    ```
    

# IV. this 키워드와 this() 메서드

## 1. 내부 객체 참조 변수명 this

- 참조 변수명.멤버명의 형태로 클래스 내부에서 객체 멤버 사용
- 모든 메서드에는 자신이 포함된 클래스의 객체를 가리키는 this라는 참조 변수 존재
- this. 생략 시 컴파일러가 자동으로 this. 추가
    
    ```java
    public class Test {
        public static void main(String[] args) {
            A a = new A();
    
            a.work();
            System.out.println(a.m);
            System.out.println(a.n);
        }
    }
    
    class A {
        int m;
        int n;
    
        void init(int a, int b) {
            int c;
            c = 3;
    
            this.m = a;
            this.n - b;
        }
    
        void work() {
            this.init(2, 3);
        }
    }
    ```
    
- 지역 변수와 필드 모두 사용할 수 있는 영역에서는 사용 범위가 좁은 변수(지역 변수)로 인식 → this 자동 추가 없음
- 지역 변수의 값을 필드에 대입하기 위해서는 this를 붙여 표기해야 함

## 2. 클래스 내 다른 생성자를 호출하는 this() 키워드

- 자신이 속한 클래스 내부의 다른 생성자 호출
- 생성자의 내부에서만 사용 가능
- 생성자의 첫 줄에 위치
    
    ```java
    public class Test {
        public static void main(String[] args) {
            A a1 = new A();
    
            System.out.println();
    
            A a2 = new A(3);
        }
    }
    
    class A {
        A() {
            System.out.println("첫 번째 생성자");
        }
    
        A(int a) {
            this(); // 반드시 생성자의 첫 줄에 위치
            System.out.println("두 번째 생성자");
        }
    
        /*
        void abc() {
            this(); 메서드에서는 this() 키워드 사용 불가능
        }
        */
    }
    ```
    
- this() 메서드를 이용해 생성자의 중복 제거 가능
