# Chapter 17. 컬렉션 프레임워크

# I. 컬렉션 프레임워크의 개념과 구조

## 1. 컬렉션(Collection)이란?

- 동일한 타입을 묶어 관리하는 자료구조
- 데이터의 저장 용량(Capacity)을 동적으로 관리 가능
- 메모리 공간이 허용하는 한 저장 데이터의 개수에 제약 X

## 2. 컬렉션 프레임워크란?

- 라이브러리 : 일반적으로 단순히 연관된 클래스와 인터페이스들의 묶음
- 프레임워크(Framework) : 클래스/인터페이스를 생성하는 과정에서 설계의 원칙 또는 구조에 따라 클래스/인터페이스를 설계하고, 이렇게 설계된 클래스/인터페이스를 묶어 놓은 것
- 리스트, 스택, 큐, 트리 등의 자료구조 + 정렬, 탐색 등의 알고리즘을 구조화
- List<E>, Set<E>, Map<K, V>, Stack<E>, Queue<E>

# II. `List<E>` 컬렉션 프레임워크

- 배열과 가장 비슷한 구조를 지니고 있는 자료구조

## 1. 배열과 리스트의 차이점

- 저장 공간의 크기가 고정적이거나 동적으로 변화
    
    ```java
    String[] s = new String[]{"가", "나", "다", "라", "마", "바", "사"};
    s[2] = null;
    s[5] = null;
    System.out.println(s.length); // 7
    ```
    
    ```java
    List<String> l = new ArrayList<>();
    l.add("가");
    l.add("나");
    l.add("다");
    l.add("라");
    l.add("마");
    l.add("바");
    l.add("사");
    System.out.println(l.size()); // 7
    l.remove("다");
    l.remove("바");
    System.out.println(l.size()); // 5
    ```
    

## 2. `List<E>` 객체 생성하기

- `ArrayList<E>` , `Vector<E>` , `LinkedList<E>`

### 1. `List<E>` 인터페이스 구현 클래스 생성자로 동적 컬렉션 객체 생성

- 객체를 생성할 때 제네릭의 실제 타입 지정
- 일반적으로 기본 생성자 사용
- 초기 저장 용량을 매개변수로 포함하고 있는 생성자 사용 가능
    - 저장 용량 : 데이터를 저장하기 위해 미리 할당해 놓은 메모리의 크기

```java
List<제네릭 타입 지정> 참조 변수 = new ArrayList<제네릭 타입 지정>();
List<제네릭 타입 지정> 참조 변수 = new Vector<제네릭 타입 지정>();
List<제네릭 타입 지정> 참조 변수 = new LinkedList<제네릭 타입 지정>();

ArrayList<제네릭 타입 지정> 참조 변수 = new ArrayList<제네릭 타입 지정>();
Vector<제네릭 타입 지정> 참조 변수 = new ArrayList<제네릭 타입 지정>();
LinkedList<제네릭 타입 지정> 참조 변수 = new ArrayList<제네릭 타입 지정>();
```

### 2. `Arrays.asList()` 메서드를 이용해 정적 컬렉션 객체 생성

- 내부적으로 배열을 먼저 생성 → `List<E>` 로 래핑
- 컬렉션 객체인데도 저장 공간의 크기 변경 불가능
    - 데이터의 추가 및 삭제 불가능
    - 저장 공간의 크기를 변경하지 않는 데이터의 변경 가능

```java
List<제네릭 타입 지정> 참조 변수 = Arrays.asList(제네릭 타입 저장 데이터);
```

### 3. `List<E>` 주요 메서드

- `add(), addAll(), set(), remove(), clear(), get(), size(), isEmpty(), toArray()`

### 4. `ArrayList<E>` 구현 클래스

- `List<E>` 인터페이스를 구현한 구현 클래스
- 배열처럼 수집한 원소를 인덱스로 관리
- 저장 용량을 동적으로 관리

1. 데이터 추가하기 - `add()`
    
    ```java
    // 단일 데이터 추가
    List<Integer> aList1 = new ArrayList<Integer>();
    
    // add(E element)
    aList1.add(3);
    aList1.add(4);
    aList1.add(5);
    System.out.println(aList1.toString()); // [3, 4, 5]
    
    // add(int index, E element)
    aList1.add(1, 6);
    System.out.println(aList1.toString()); // [3, 6, 4, 5]
    ```
    
    ```java
    // 컬렉션 객체 추가
    // addAll(Collection<? extends E> c)
    List<Integer> aList2 = new ArrayList<Integer>();
    aList2.add(1);
    aList2.add(2);
    aList2.addAll(aList1);
    System.out.println(aList2.toString()); // [1, 2, 3, 6, 4, 5]
    
    // addAll(int index, Collection<? extends E> c)
    List<Integer> aList3 = new ArrayList<Integer>();
    aList3.add(1);
    aList3.add(1);
    aList3.addAll(1, aList3);
    System.out.println(aList3, toString()); // [1, 1, 2, 2]
    ```
    

1. 데이터 변경하기 - `set()`
    
    ```java
    // set(int index, E element): aList3 = [1, 1, 2, 2]
    aList3.set(1, 5);
    aList3.set(3, 6);
    // aList3.set(4, 7); // IndexOutOfBoundsException
    System.out.println(aList3.toString()); // [1, 5, 2, 6]
    ```
    

1. 데이터 삭제하기 - `remove(), clear()`
    
    ```java
    // remove(int index): aList3 = [1, 5, 2, 6]
    aList3.remove(1);
    System.out.println(aList3.toString()); // [1, 2, 6]
    
    // remove(Object o)
    aList3.remove(new Integer(2));
    System.out.println(aList3.toString()); // [1, 6]
    
    // clear()
    aList.clear();
    System.out.println(aList3.toString()); // []
    ```
    

1. 데이터 정보 추출하기 - `isEmpty(), size(), get(int index)`
    
    ```java
    // isEmpty() aList3 = []
    System.out.println(aList3.isEmpty()); // true
    
    // size()
    aList3.add(1);
    aList3.add(2);
    aList3.add(3);
    System.out.println(aList3.toString()); // [1, 2, 3]
    System.out.println("size : " + aList3.size()); // size : 3
    
    // get(int index)
    System.out.println("0번째 : " + aList3.get(0)); 0번째 : 1
    System.out.println("1번째 : " + aList3.get(1)); 1번째 : 2
    System.out.println("2번째 : " + aList3.get(2)); 2번째 : 3
    for(int i = 0; i < aList3.size(); i++) {
    		System.out.println(i + "번째 : " + aList3.get(i));
    }
    ```
    
- `isEmpty()` : 데이터의 존재 여부
- `size()` : 저장 데이터의 개수
- `get(int index)` : 특정 위치의 데이터값

1. 배열로 변환하기 - `toArray(), toArray(T[] t)`
    
    ```java
    // toArray() aList3 = [1, 2, 3]
    Object[] object = aList3.toArray();
    System.out.println(Arrays.toString(object)); // [1, 2, 3]
    
    // toArray(T[] t)
    Integer[] integer1 = aList3.toArray(new Integer[0]);
    System.out.println(Arrays.toString(integer1)); // [1, 2, 3]
    
    // toArray(T[] t)
    Integer[] integer2 = aList3.toArray(new Integer[5]);
    System.out.println(Arrays.toString(integer2)); // [1, 2, 3, null, null]
    ```
    
- 특정 타입의 배열로 바로 변환하기 위해서는 `toArray(T[] t)` 메서드를 이용해 매개변수로 특정 타입의 배열 객체를 만들어 넣어줌

### 5. `Vector<E>` 구현 클래스

- 동기화 메서드(Synchronized Method) : 멀티 쓰레드에 적합하도록 설계
    
    ```java
    public synchronized E remove(int index) {
    		// ...
    }
    
    public synchronized E get(int index) {
    		// ...
    }
    ```
    
- 멀티 쓰레드에서 사용할 수 있다는 점을 제외하면 사용 방법이 `ArrayList<E>` 와 동일

### 6. `LinkedList<E>` 구현 클래스

- 저장 용량을 매개변수로 갖는 생성자가 없기 때문에 객체를 생성할 때 저장 용량 지정 불가능
    
    ```java
    List<E> aLinkedList1 = new LinkedList<Integer>(); // 가능
    List<E> aLinkedList1 = new LinkedList<Integer>(20); // 불가능
    ```
    
- 내부적으로 데이터를 저장하는 방식이 서로 다름(앞뒤 객체의 정보 저장)
- 메서드의 종류와 활용 방법은 `ArrayList<E>` 와 완벽히 동일

### 7. `ArrayList<E>` 와 `LinkedList<E>` 의 성능 비교

- 중간 위치에 데이터를 추가 또는 삭제할 일이 많을 때 → `LinkedList<E>`
- 데이터를 검색할 일이 많을 때 → `ArrayList<E>`

💭. `currentTimeMillis()` 메서드와 `nanoTime()` 메서드

- `currentTimeMillis()` : 1970년 1월 1일 00시 00분과 현재 시간과의 차이를 ms(밀리초) 단위로 리턴
- `nanoTime()` : 상대적인 시간 차이를 나노초 단위로 구함
    
    ```java
    long startTime = System.nanoTime();
    // 시간 측정 대상 모듈
    long endTime = System.nanoTime();
    // 측정 시간(ns) = endTime - startTime;
    ```
    

# III. `Set<E>` 컬렉션 인터페이스

## 1. `Set<E>` 컬렉션의 특징

- 집합의 개념과 같은 컬렉션
- 데이터를 구분할 수 있는 유일한 방법이 데이터 그 자체
- 데이터의 중복 저장 비허용

## 2. `Set<E>` 의 주요 메서드

- `HashSet<E>` , `LinkedHashSet<E>` , `TreeSet<E>`
- `add(), addAll(), remove(), clear(), isEmpty(), contains(), size(), iterator(), toArray()`

## 3. `HashSet<E>` 구현 클래스

- 주머니에 손을 넣어 1개씩 꺼내는 셈 → 저장 데이터를 꺼낼 때 입력 순서와 다를 수 있음
- 저장 용량 동적 관리

### 1. 데이터 추가하기 - `add()`

- `HashSet<E>` 는 모든 데이터를 하나의 주머니에 넣어 관리 → 입력 순서와 다르게 출력될 수 있음

```java
Set<String> hSet1 = new HashSet<String>();

// 1. add(E element)
hSet1.add("가");
hSet1.add("나");
hSet1.add("가");
System.out.println(hSet1.toString()); // [가, 나]

// 2. addAll(Collection<? extends E> c)
Set<String> hSet2 = new HashSet<String>();
hSet2.add("나");
hSet2.add("다");
hSet2.addAll(hSet1);
System.out.println(hSet2.toString()); // [가, 다, 나]
```

### 2. 데이터 삭제하기 - `remove(), clear()`

```java
// 3. remove(Object o) hSet2 = [가, 다, 나]
hSet2.remove("나");
System.out.println(hSet2.toString()); // [가, 다]

// 4. clear()
hSet2.clear();
System.out.println(hSet2.toString()); // []
```

### 3. 데이터 정보 추출하기 - `isEmpty(), contains(), size(), iterator()`

```java
// 5. isEmpty() hSet2 = []
System.out.println(hSet2.isEmpty()); // true

// 6. contains(Object o)
Set<String> hSet3 = new HashSet<String>();
hSet3.add("가");
hSet3.add("다");
hSet3.add("나");
System.out.println(hSet3.contains("나")); // true
System.out.println(hSet3.contains("라")); // false

// 7. size()
System.out.println(hSet3.size()); // 3

// 8. iterator()
Iterator<String> iterator = hSet3.iterator();
while(iterator.hasNext()) {
		System.out.println(iterator.next() + " "); // 가 다 나
}

// 8. for-each
for(String s : hSet3) {
		System.out.println(s + " "); // 가 다 나
}
```

- `isEmpty()` : 비어 있는지의 여부
- `contains(Object o)` : 객체 안에 해당 원소가 있는지를 true/false로 리턴
- `size()` : 저장 데이터의 개수
- `iterator()` : 객체 내부의 데이터를 1개씩 꺼내 처리
    - `hasNext()` : 다음으로 가리킬 원소의 존재 여부를 불리언으로 리턴
    - `next()` : 다음 원소 위치로 가서 읽은 값을 리턴
- `iterator()` → for-each 구문으로 변환 가능

### 4. 배열로 변환하기 - `toArray(), toArray(T[] t)

```java
// 9. toArray() hSet3 = [가 다 나]
Object[] objArray = hSet3.toArray();
System.out.println(Arrays.toString(objArray)); // [가 다 나]

// 10-1. toArray(T[] t)
String[] strArray1 = hSet3.toArray(new String[0]);
System.out.println(Arrays.toString(strArray1)); // [가 다 나]

// 10-2.toArray(T[] t)
String[] strArray2 = hSet3.toArray(new String[5]);
System.out.println(Arrays.toString(strArray2)); // [가 다 나 null null]
```

### 5. `HashSet<E>` 의 중복 확인 메커니즘

- 해시코드(HashCode) : 객체가 저장된 번지를 기준으로 생성된 정수형 고윳값
    
    ```java
    package mypack;
    
    class A extends Object {}
    
    public class Test {
    		public static void main(String[] args) {
    				A a = new A();
    				System.out.println("%x", a.hashCode()); // 7852e922
    				System.out.println(a.toString()); // mypack.A@7852e922
    		}
    }
    
    System.out.println(Objects.hash(1, 2, 3)); // 30817
    System.out.println(Objects.hash(2, 1, 3)); // 31747
    System.out.println(Objects.hash("안녕")); // 1611052
    System.out.println(Objects.hash("방가")); // 1537302
    ```
    
- 등가연산자(==) : 스택 메모리값을 동등 비교(기본 자료형의 등가연산은 실젯값, 참조자료형의 등가연산은 위칫값 비교)
    
    ```java
    public boolean equals(Object obj) {
    		return (this == obj);
    }
    ```
    
- `equal()` 메서드 : 등가연산과 완벽하게 동일
    
    ```java
    class A {
    		int data;
    
    		public A(int data) {
    				this.data = data;
    		}
    }
    
    public class Test {
    		public static void main(String[] args) {
    				A a1 = new A(3);
    				A a2 = new A(3);
    				System.out.println(a1 == a2); // false
    				System.out.println(a1.equals(a2)); // false
    		}
    }
    ```
    
- `hashCode()` 동일한지 확인 → `equal()` 결과가 true인지 확인 → 같은 객체
    - 하위 클래스에서 Object의 `hashCode()` 와 `equals()` 를 오버라이딩했는지, 했다면 어떻게 했는지에 따라 객체의 동등 여부 결과 달라짐
        
        ```java
        class A {
        		int data;
        
        		public A(int data) {
        				this.data = data;
        		}
        }
        
        public class Test {
        		public static void main(String[] args) {
        				// equals() : 오버라이딩 X + hashCode() : 오버라이딩 X
        				Set<A> hashSet1 = new HashSet<>();
        				A a1 = new A(3);
        				A a2 = new A(3);
        				System.out.println(a1 == a2); // false
        				System.out.println(a1.equals(a2)); // false
        				System.out.println(a1.hashCode() + " " + a2.hashCode()); // 2018699554 1311053135
        				hashSet1.add(a1);
        				hashSet1.add(a2);
        				System.out.println(hashSet1.size()); // 2(다른 객체)
        		}
        }
        ```
        
        ```java
        class B {
        		int data;
        
        		public B(int data) {
        				this.data = data;
        		}
        
        		@Override
        		public boolean equals(Object obj) {
        				if(obj instanceof B) {
        						if(this.data == ((B) obj.data)
        								return true;
        				}
        
        				return false;
        		}
        }
        
        public class Test {
        		public static void main(String[] args) {
        				// equals() : 오버라이딩 O + hashCode() : 오버라이딩 X
        				Set<B> hashSet2 = new HashSet<>();
        				B b1 = new B(3);
        				B b2 = new B(3);
        				System.out.println(b1 == b2); // false
        				System.out.println(b1.equals(b2)); // true
        				System.out.println(b1.hashCode() + " " + b2.hashCode()); // 118352462 1550089733
        				hashSet2.add(b1);
        				hashSet2.add(b2);
        				System.out.println(hashSet2.size()); // 2(다른 객체)
        		}
        }
        ```
        
        ```java
        class C {
        		int data;
        
        		public C(int data) {
        				this.data = data;
        		}
        
        		@Override
        		public boolean equals(Object obj) {
        				if(obj instanceof C) {
        						if(this.data == ((C) obj.data)
        								return true;
        				}
        
        				return false;
        		}
        
        		@Override
        		public int hashCode() {
        				return Objects.hash(data); // data
        		}
        }
        
        public class Test {
        		public static void main(String[] args) {
        				// equals() : 오버라이딩 O + hashCode() : 오버라이딩 O
        				Set<C> hashSet3 = new HashSet<>();
        				C c1 = new C(3);
        				C c2 = new C(3);
        				System.out.println(c1 == c2); // true
        				System.out.println(c1.equals(c2)); // true
        				System.out.println(c1.hashCode() + " " + c2.hashCode()); // 34 34
        				hashSet3.add(c1);
        				hashSet3.add(c2);
        				System.out.println(hashSet3.size()); // 1(같은 객체)
        		}
        }
        ```
        

## 4. `LinkedHashSet<E>` 구현 클래스

- `HashSet<E>` 의 모든 기능에 데이터 간의 연결 정보만을 추가로 갖고 있는 컬렉션
- 출력 순서가 항상 입력 순서와 동일

## 5. `TreeSet<E>` 구현 클래스

- `Set<E>` 의 기능에 크기에 따른 정렬 및 검색 기능이 추가된 컬렉션
- 데이터를 입력 순서와 상관없이 크기순으로 출력 → 두 객체의 크기 비교, 정렬과 검색 기능 추가 정의
    
    ```java
    // Set<E>로 객체 타입을 선언할 때
    Set<String> treeSet = new TreeSet<String>();
    treeSet.loremIpsum; // Set<E> 메서드만 사용 가능
    
    // TreeSet<E>로 객체 타입을 선언할 때
    TreeSet<String> treeSet = new TreeSet<String>();
    treeSet.loremIpsum; // Set<E> 메서드와 정렬/검색 기능 메서드도 사용 가능
    ```
    

### 1. `TreeSet<E>` 의 주요 메서드

- `first(), last(), lower(), higher(), floor(), ceiling(), pollFirst(), pollLast(), headSet(), tailSet(), subset(), descendingSet()`

1. `TreeSet<E>` 의 객체 생성 및 데이터 추가와 출력
    
    ```java
    TreeSet<Integer> treeSet = new TreeSet<Integer>();
    for(int i = 50; i > 0; i -= 2)
    		treeSet.add(i);
    
    System.out.println(treeSet.toString()); // [2, 4, 6, ..., 50]
    ```
    

1. 데이터 검색 - `first(), last(), lower(), higher(), floor(), ceiling()`
- `first()` : 첫 번째 값 리턴
- `last()` : 마지막 값 리턴
- `lower()` : 입력매개변수 원소보다 작으면서 가장 가까운 수
- `higher()` : 입력매개변수 원소보다 크면서 가장 가까운 수
- `floor()` : 입력매개변수 원소보다 작거나 같은 수
- `ceiling()` : 입력매개변수 원소보다 크거나 같은 수
    
    ```java
    // 1. first()
    System.out.println(treeSet.first()); // 2
    
    // 2. last()
    System.out.println(treeSet.last()); // 50
    
    // 3. lower(E element)
    System.out.println(treeSet.lower(26)); // 24
    
    // 4. higher(E element)
    System.out.println(treeSet.higher(26)); // 28
    
    // 5. floor(E element)
    System.out.println(treeSet.floor(25)); // 24
    System.out.println(treeSet.floor(26)); // 26
    
    // 6. ceiling(E element)
    System.out.println(treeSet.ceiling(25)); // 26
    System.out.println(treeSet.ceiling(26)); // 26
    ```
    

1. 데이터 꺼내기 - `pollFirst(), pollLast()`
- `pollFirst()` : 처음 값을 꺼내 리턴(수행 후 데이터의 개수 줄어듦)
- `pollLast()` : 마지막 값을 꺼내 리턴(수행 후 데이터의 개수 줄어듦)
    
    ```java
    // 7. pollFirst()
    int treeSetSize = treeSet.size();
    System.out.println(treeSetSize); // 25
    for(int i = 0; i < treeSetSize; i++)
    		System.out.println(treeSet.pollFirst() + " "); // 2 4 6 ... 50
    System.out.println(treeSet.size()); // 0
    
    // 8. pollLast()
    for(int i = 50; i > 0; i -= 2)
    		treeSet.add(i);
    treeSetSize = treeSet.size();
    System.out.println(treeSetSize); // 25
    for(int i = 0; i < treeSetSize; i++)
    		System.out.println(treeSet.pollLast() + " "); // 50 48 46 ... 2
    System.out.println(treeSet.size()); // 0
    ```
    

1. 데이터 부분 집합 생성 - `headSet(), tailSet()`
- `SortedSet<E>` : 메서드 매개변수에 불리언 타입이 들어가지 않음
- `NavigableSet<E>` : 메서드 매개변수에 불리언 타입이 들어감
- `headSet()` : 입력매개변수보다 작은 원소로 구성된 `Set<E>` 리턴
- `tailSet()` : 입력매개변수보다 큰 원소로 구성된 `Set<E>` 리턴
    
    ```java
    for(int i = 50; i > 0; i -= 2)
    		treeSet.add(i);
    
    // 9. SortedSet<E> headSet(E toElement)
    SortedSet<Integer> sSet = treeSet.headSet(20);
    System.out.println(sSet.toString()); // [2, 4, 6, ..., 18]
    
    // 10. NavigableSet<E> headSet<E toElement, boolean inclusive)
    NavigableSet<Integer> nSet = treeSet.headSet(20, false);
    System.out.println(nSet.toString()); // [2, 4, 6, ..., 18]
    nSet = treeSet.headSet(20, true);
    System.out.println(nSet.toString()); // [2, 4, 6, ..., 20]
    
    // 11. SortedSet<E> tailSet(E element)
    sSet.treeSet.tailSet(20);
    System.out.println(sSet.toString()); // [20, 22, 24, ..., 50]
    
    // 12. NavigableSet<E> tailSet(E element, boolean inclusive)
    nSet = treeSet.tailSet(20, false);
    System.out.println(nSet.toString()); // [22, 24, 26, ..., 50]
    nSet = treeSet.tailSet(20, true);
    System.out.println(nSet.toString()); // [20, 22, 24, ..., 50]
    ```
    

1. 데이터 부분 집합 생성 - `subSet()`
- `subSet<()` : 전달되는 2개의 원솟값을 최솟값과 최댓값으로 하는 `Set<E>` 을 구성하는 메서드
    
    ```java
    // 13. SortedSet<E> subSet(E fromElement, E toElement)
    sSet = treeSet.subSet(10, 20);
    System.out.println(sSet.toString()); // [10, 12, 14, 16, 18]
    
    // 14. NavigableSet<E> subSet(E fromElement, boolean frominclusive, toElement, boolean toinclusive)
    nSet = treeSet.subSet(10, true, 20, false);
    System.out.println(nSet.toString()); // [10, 12, 14, 16, 18]
    nSet = treeSet.subSet(10, false, 20, true);
    System.out.println(nSet.toString()); // [12, 14, 16, 18, 20]
    ```
    

1. 데이터 정렬 - `descendingSet()`
- `descendingSet()` : 현재의 정렬 기준을 반대로 변환
    
    ```java
    // 15. NavigableSet<E> descendingSet()
    System.out.println(treeSet); // [2, 4, 6, ..., 50]
    NavigableSet<Integer> descendingSet = treeSet.descendingSet();
    System.out.println(descendingSet); // [50, 48, 46, ..., 2]
    descendingSet = descendingSet.descendingSet();
    System.out.println(descendingSet); // [2, 4, 6, ..., 50]
    ```
    

### 2. `TreeSet<E>` 에서 데이터 크기 비교

- `treeSet<E>` 에 저장되는 모든 객체는 크기 비교의 기준 제공
- java.lang 패키지의 `Comparable<T>` 제네릭 인터페이스 구현
    
    ```java
    class MyComparableClass implements Comparable<MyComparableClass> {
    		int data1;
    		int data2;
    
    		public MyComparableClass(int data1, int data2) {
    				this.data1 = data1;
    				this.data2 = data2;
    
    		}
    
    		@Override
    		public int compareTo(MyComparableClass m) {
    				if(data1 < m.data1) {
    						return -1;
    				} else if(data1 == m.data1) {
    						return 0;
    				} else
    						return 1;
    		}
    }
    
    TreeSet<MyComparableClass> treeSet4 = neww TreeSet<MyComparableClass>();
    MyComparableClass myComparableClass1 = new MyComparableClass(2, 5);
    MyComparableClass myComparableClass2 = new MyComparableClass(3, 3);
    treeSet4.add(myComparableClass1);
    treeSet4.add(myComparableClass2);
    for(MyComparableClass mcc : treeSet4) {
    		System.out.println(mcc.data1); // 2 -> 3
    }
    ```
    
- `TreeSet<E>` 객체를 생성하면서 생성자 매개변수로 `Comparator<T>` 객체 제공
    
    ```java
    TreeSet<MyClass> treeSet5 = new TreeSet<MyClass>(new Comparator<MyClass>() {
    		@Override
    		public int compare(MyClass o1, MyClass o2) {
    				if(o1.data1 < o2.data1)
    						return -1;
    				else if(o1.data1 == o2.data1)
    						return 0;
    				else
    						return 1;
    		}
    });
    
    MyClass myClass1 = new MyClass(2, 5);
    MyClass myClass1 = new MyClass(3, 3);
    treeSet5.add(myClass1);
    treeSet5.add(myClass2);
    for(MyClass mc : treeSet5) {
    		System.out.println(mc.data1); // 2 -> 3
    }
    ```
