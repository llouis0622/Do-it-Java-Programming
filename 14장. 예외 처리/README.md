# Chapter 14. 예외 처리

# I. 예외

## 1. 예외와 에러의 차이점

### 1. 예외(Exception)

- 연산 오류, 숫자 포맷 오류 등과 같이 상황에 따라 개발자가 해결할 수 있는 오류
- 오류 자체를 수정 X → 오류 발생 시 차선책을 선택하는 것
- 예외 처리 : 오류를 적절히 처리하는 것

### 2. 에러

- 자바 가상 머신 자체에서 발생하는 오류
- 개발자가 해결할 수 없는 오류

## 2. 예외 클래스 상속 구조

- Exception 클래스 → Throwable 클래스 상속

### 1. 일반 예외 클래스(Checked Exception Class)

- Exception 클래스에게서 직접 상속
- 컴파일 전에 예외 발생 문법 검사
- 예외 처리 X → 문법 오류 발생
- `ClassNotFoundException`, `InterruptedException`, `IOException`, `FileNotFoundException`, `CloneNotSupportedException`

### 2. 실행 예외 클래스(Unchecked(Runtime) Exception Class)

- RuntimeException 클래스 상속
- 컴파일 전이 아니라 실행할 때 발생하는 예외
- 예외 처리 X → 문법 오류 발생 X(강제 종료 가능성)
- `ArithmeticException`, `ClassCastException`, `IndexOutOfBoudException`, `NumberFormatException`, `NullPointerException`, ...

## 3. 일반 예외 클래스

### 1. `InterruptedException`

- `Thread.sleep(시간)` 메서드
- 일정 시간 동안 해당 쓰레드를 일시정지 상태로 만드는 정적 메서드

### 2. `ClassNotFoundException`

- `Class.forName("패키지명.클래스명")`
- 클래스를 동적으로 메모리에 로딩하는 메서드 → Class 타입의 객체 리턴

### 3. `IOException`

- 자바 입출력 부분에서 주로 보이는 일반 예외
- 콘솔이나 파일에 데이터를 쓰거나 읽을 때 발생

### 4. `FileNotFoundException`

- 파일을 읽을 때 해당 경로에 파일이 없으면 발생

### 5. `CloneNotSupportedException`

- 해당 클래스가 복사 기능을 제공해야 함 → 복사 할 수 없는 상황일 때 발생

## 4. 실행 예외

### 1. `ArithmeticException`

- 연산 자체가 불가능할 때 발생(예시 : 분모가 0일 때)

### 2. `ClassCastException`

- 다운캐스팅이 불가능한 상황에서 다운캐스팅을 시도할 때 발생

### 3. `ArrayIndexOutOfBoundsException`

- 배열의 인덱스를 잘못 사용했을 때 발생

### 4. `NumberFormatException`

- 문자열이 변환하고자 하는 숫자 형식이 아니면 변환 실패

### 5. `NullPointerException`

- 참조 변수가 실제 객체를 가리키고 있지 않은 상황에서 필드나 메서드를 호출할 때 발생
- 위치값을 저장하는 참조 변수의 초기값으로만 사용 → 현재 가리키고 있는 객체가 없음

# II. 예외 처리

## 1. 예외 처리 문법

```java
try {
		// 일반 예외, 실행 예외 발생 가능 코드
} catch (예외 클래스명 참조 변수명) {
		// 예외가 발생했을 때 처리
} finally { // 생략 가능
		// 예외 발생 여부에 상관없이 무조건 실행
}
```

## 2. 예외 처리 과정

- `try{}` 구문 실행
- 예외 발생 X → `catch() {}` 구문 실행 X
- `finally{}` 블록 존재 → 실행

## 3. 다중 예외 처리

- `catch() {}` 블록을 추가하여 처리
- 실행할 `catch() {}` 블록의 선택 → 항상 위에서부터 확인

```java
try {
		// ...
} catch(예외 타입 e1) {
		// ...
} catch(예외 타입 e2) {
		// ...
}
...
finally {
		// ...
}
```

```java
try {
		// ...
} catch(예외 타입 A | 예외 타입 B 참조 변수명) { // 예외 발생 -> 처리 내용 동일
		// ...
}
...
finally {
		// ...
}
```

## 4. 리소스 자동 해제 예외 처리

- 더 이상 사용하지 않는 자원을 반납하는 것, `finally{}` 구문
- 소괄호 안에 자동으로 리소스를 반납해야 할 객체 생성 → 예외 처리 이후 자동 해제

```java
try (리소스 자동 해제가 필요한 객체 생성) {
		// 예외 발생 가능 코드
} catch (예외 클래스명 참조 변수명) {
		// 해당 예외가 발생했을 때 처리하는 블록
} finally {
		// 예외 발생 여부에 상관없이 무조건 실행하는 블록
}
```

# III. 예외 전가

## 1. 예외 전가 문법

- 예외 처리의 의무를 호출한 메서드가 갖게 하는 것

```java
리턴 타입 메서드명(입력매개변수) throws 예외 클래스명 {
		// 예외 발생 코드
}
```

## 2. 예외 전가 과정

- 자바 가상 머신 → 예외 확인
- 발생한 예외의 정보를 화면에 출력 → 프로그램 강제 종료

## 3. 다중 예외 전가

- throws 이후에 예외를 나열

```java
리턴 타입 메서드명(입력매개변수) throws 예외 클래스 A, 예외 클래스 B, ... {
		// 여러 개의 예외 종류가 발생할 수 있는 블록
}
```

# IV. 사용자 정의 예외 클래스

## 1. 사용자 정의 예외 클래스 생성 방법

### 1. 사용자 정의 예외 클래스 작성

- 기본 생성자와 문자열을 입력받는 생성자 추가
- 예외 메시지를 전달받아 예외 객체를 생성하는 생성자
- Exception 바로 상속 → 일반 예외 클래스로 제작
    
    ```java
    class MyException extends Exception {
    		MyException() {
    		}
    
    		MyException(String s) {
    				super(s); // 부모 생성자 호출
    		}
    }
    ```
    
- RuntimeException 상속 → 실행 예외 클래스로 제작
    
    ```java
    class MyRTException extends RuntimeException {
    		MyRTException() {
    		}
    
    		MyRTException(String s) {
    				super(s); // 부모 생성자 호출
    		}
    }
    ```
    

### 2. 사용자 정의 예외 객체 생성

```java
MyException me1 = new MyException();
MyException me2 = new MyException("예외 메시지");

MyRTException mre1 = new MyRTException();
MyRTException mre2 = new MyRTException("예외 메시지");
```

### 3. 예외 상황에서 예외 객체 던지기

- 실제 자바 가상 머신에게 예외 객체를 만들어 전달
- 해당 메서드가 직접 예외 처리 → 자바 가상 머신이 전달받은 예외 객체를 해당 메서드 내의 예외 처리 블록으로 전달
- 예외 전가 → 예외 객체를 상위 메서드 내의 예외 처리 블록으로 전달

```java
throw me1;
throw me2;
throw new MyException();
throw new MyException("예외 메시지");

throw mre1;
throw mre2;
throw new MyRTException();
throw new MyRTException("예외 메시지");
```

## 2. 예외 클래스의 메서드

### 1. `getMessage()` 메서드

- 예외가 발생했을 때 생성자로 넘긴 메시지를 문자열 형태로 리턴하는 메서드
- `public String getMessage()`

### 2. `printStackTrace()` 메서드

- 예외 발생이 전달되는 경로, 예외가 전가된 과정을 한눈에 확인할 수 있는 메서드
- `public void printStackTrace()`
