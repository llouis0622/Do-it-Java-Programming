# Chapter 01. Java 시작하기

# I. 자바 프로그램의 기본 구조

## 1. 자바 소스 코드 실행 과정

- 소스 파일 작성(.java) → 바이트 코드 생성(.class) → JVM 메모리 할당 → main() 메서드 실행
- 메모리 영역 : 메서드(Method) 영역 + 스택(Stack) 영역 + 힙(Heap) 영역
- 메모리 영역 = 클래스(Class) 영역, 정적(Static) 영역, 상수(Final) 영역

## 2. 소스 코드 기본 구조

### 1. 주석 : 일종의 메모, 가독성 높이기 위해 사용

```java
// Lorem ipsum

/* Lorem ipsum
Lorem ipsum
Lorem ipsum */
```

### 2. 패키지 선언부 : 패키지를 지정했다면 필수적으로 포함, 디폴트 패키지 사용 시 선언 생략

```java
package com.llouis.java;
```

### 3. 클래스 선언부

```java
// 접근 지정자 자바 키워드 클래스명
public class Main {

}
```

- public : 접근 지정자, 다른 패키지에서도 사용 할 수 있음
- 여러 개의 클래스 존재 가능, **최대 1개의 클래스만 public 포함 가능**

### 4. main() 메서드

```java
// 접근 지정자 정적 메서드 리턴 타입 메서드명(...) {}
public static void main(String[] args) {

}
```

- public static : 메서드를 꾸며주는 수식어로 이해
- **실행 이후 가장 먼저 실행되는 메서드**

## 3. 콘솔 출력 메서드와 문자열 출력

### 1. 문자열 표현하기

- String 자료형으로 저장
- **반드시 큰 따옴표(” “) 안에 표기**
- 문자열과 문자열 연결 시 + 사용
- 한 번 문자열이 나오면 이후 수행되는 자료형은 모두 문자열
    
    ```java
    "안녕" + "반가워"
    // 안녕반가워
    
    3 + 5 + "안녕"
    // 8안녕
    
    3 + "안녕" + 5 + 3
    // 3안녕53
    ```
    

### 2. 출력

```java
System.out.println("Lorem ipsum");
// Lorem ipsum

System.out.print("Lorem ipsum");
// Lorem ipsum

// System.out.printf("출력 포맷", 인자, ...);
System.out.printf("%s\n", "Lorem ipsum");
// Lorem ipsum
```

- println : 내용 출력 후 줄 변경(엔터키)
- print : 내용 출력 후 개행 X, 모든 출력을 연속적으로 1줄로 출력
- printf : 출력 포맷을 지정하여 출력
