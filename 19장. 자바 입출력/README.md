# Chapter 19. 자바 입출력

# I. 파일 관리와 문자셋

## 1. 자바로 파일 관리하기

### 1. 파일 객체 생성하기

- 자바의 File 클래스 : 파일(File)과 폴더(Directory)를 관리
- 파일 객체를 생성하는 시점에서는 실제 파일의 존재 유무는 전혀 문제되지 않음 → File 객체로 파일을 읽을 시 `FileNotFoundException` 발생
- 문자열로 파일 또는 폴더의 경로를 입력받아 해당 위치를 가리키는 File 객체 생성
- 부모 경로와 자식 경로를 분리해 매개변수로 입력받는 생성자(File 또는 String 타입 객체)
- 파일 또는 폴더의 경로를 URI(Uniform Resource Identifier) 타입으로 입력
- 파일 또는 폴더의 존재 여부 확인 메서드 : `boolean exists()`
- 경로 위치에 폴더 생성 : `boolean mkdir()`
- 경로 위치에 파일 생성 : `boolean createNewFile()`

### 2. 파일 경로 표시하기

- 파일 구분자(Separator) → 윈도우 `\` , 맥 `/`
- 파일 구분자를 확인하기 위핸 File 클래스의 정적 필드 : `public static final String separator`

### 3. 파일의 절대 경로와 상대 경로

- 절대 경로(Absolute Path) : 드라이브명부터 특정 위치까지의 전체 경로 표시
    - `File newFile1 = new File("C:/abc/newFile11.txt");`
    - 절대 경로 가져오기 : `public String getAbsolutePath()`
- 상대 경로(Relative Path) : 현재 작업 폴더 위치를 기준으로 상대적인 경로 표기
    - `File newFile2 = new File("newFile21.txt");`
    - 작업 폴더의 위치 구하기 : `public static String getProperty(String key)`

### 4. 파일 클래스의 주요 메서드

- File 객체는 실체 파일뿐 아니라 폴더도 함께 관리하는 클래스
- `isDirectory()` : 폴더 여부를 참 또는 거짓으로 리턴
- `isFile()` : 파일 여부를 참 또는 거짓으로 리턴
- `getName()` : 파일의 이름을 문자열로 리턴
- `getParent()` : 부모 폴더의 이름을 문자열로 리턴
- `list()` : 경로 내의 폴더와 파일 이름을 문자열 배열로 리턴
- `listFiles()` : 경로 내의 폴더와 파일 이름을 파일 객체 배열로 리턴
- `mkdir()` : 해당 경로에 폴더 생성(하위 폴더만 생성 가능)
- `mkdirs()` : 존재하지 않는 경로상의 모든 폴더 생성
- `getAbsolutePath()` : 파일의 절대 경로를 문자열로 리턴

## 2. 자바에서 문자셋 이용하기

- 문자셋 : 어떤 언어를 표현하는 데 사용하는 문자들의 집합

### 1. 아스키코드와 유니코드

- 아스키(ASCII) : 영문 알파벳, 숫자, 특수 기호, 제어 코드로 구성된 코드표
- 유니코드(Unicode) : 하나의 문자셋 안에 전 세계 문자를 통합한 문자셋

### 2. 한글 전용 문자셋 - EUC-KR, MS949

- KS C 601-1987 : KS 완성형
- EUC-KR : KS 완성형에 아스키를 추가해 구성한 문자셋
- MS949 : EUC-KR에 누락된 8822의 한글 문자 모두 포함
- `문자열.getBytes(문자셋)` : 문자열 → byte[]
- `new String(byte[], 문자셋)` : byte[] → 문자열

### 3. 대표적인 유니코드 문자셋 - UTF-16, UTF-8

- UTF-16 : 영문을 포함해 모든 문자를 고정 길이(2byte)로 인코딩하는 방식
- UTF-8 : 가변 길이 문자 인코딩 방식

### 4. Charset 클래스로 문자셋 이용하기

- `static Charset defaultCharset()` : 현재 설정돼 있는 기본값 문자셋 리턴
- `static Charset forName(String charsetName)` : 매개변수로 넘어온 charsetName의 문자셋 리턴, 지원하지 않는 문자셋 → `UnsupportedCharsetException` 실행 예외 발생
- 시스템에서 해당 문자셋 지원 여부 : `public static boolean isSupported(String charsetName)`

### 5. 명시적 문자셋 지정이 필요할 때

- 문자열을 byte[]로 저장할 때 → String 클래스의 인스턴스 메서드인 `getBytes()` 메서드 사용
- byte[]를 문자열로 조합할 때 → String 클래스의 생성자 사용

# II. byte 단위 입출력

## 1. byte 단위 입출력과 char 단위 입출력

- byte 단위 입출력 : 송수신하고자 하는 데이터를 byte 단위로 쪼개 보내고 받는 것
    - `InputStream` , `OutputStream`
- char 단위 입출력 : 텍스트 전송에 특화된 방법
    - `Reader` , `Writer`

## 2. `InputStream` 과 `OutputStream` 의 상속 구조

- 입력 : `FileInputStream` , `BufferedInputStream` , `DataInputStream` , `System.in`
- 출력 : `FileOutputStream` , `BufferedOutputStream` , `DataOutputStream` , `System.out` , `PrintStream`

## 3. `InputStream` 의 주요 메서드

- `available()` : `InputStream` 의 남은 바이트 수 리턴
- `read()` : 읽은 데이터 / 바이트 수 / 해당 위치부터 저장해 리턴
- `close()` : `InputStream` 의 자원 반환
- JNI(Java Native Interface) 이용해 오버라이딩

## 4. `OutputStream` 의 주요 메서드

- `flush()` : 메모리 버퍼에 저장된 `output stream` 내보내기
- `write()` : output 버퍼에 출력 / 메모리 버퍼에 출력 / 해당 개체를 읽은 후 출력
- `close()` : `OutputStream` 의 자원 반환

## 5. `InputStream` , `OutputStream` 객체 생성 및 활용하기

- `FileInputStream` : Application 쪽으로 흐르는 단방향 빨대를 꼽은 개념
- `FileOutputStream` : File 쪽으로 흐르는 단방향 빨대를 꼽은 개념

## 6. 콘솔로 `InputStream` , `OutputStream` 사용하기

- 입력 시 데이터가 전달되는 시점 : Enter를 입력한 시점에 `InputStream` 으로 전달
- 출력 시 `flush()` 메서드 실행 → 실제로 출력

## 7. 입출력 필터링하기

- `FilterInputStream` , `FilterOutputStream`
- `Buffered(Input/Output)Stream` : 입출력 속도 증가
- `Data(Input/Output)Stream` : 단순히 byte 단위의 입출력이 아닌 int, float, double 또는 문자열로 데이터를 읽고 쓰기
- `PrintStream` : 다양한 형태의 출력 생성, 자동 `flush()` 기능 제공

# III. char 단위 입출력

## 1. `Reader` 와 `Writer` 의 상속 구조

- `Reader` : `FileReader` , `BufferedReader` , `InputStreamReader`
- `Writer` : `FileWriter` , `BufferedWriter` , `OutputStreamWriter`

## 2. `Reader` 의 주요 메서드

- `skip()` : n개의 문자를 건너뛰는 메서드
- `read()` : 읽은 데이터를 저장해 리턴 / 읽은 char 개수를 리턴 / n개의 읽은 데이터를 해당 위치부터 저장
- `close()` : `Reader` 의 자원 반환

## 3. `Writer` 의 주요 메서드

- `flush()` : 메모리 버퍼에 저장된 데이터 내보내기
- `write()` : 메모리 버퍼에 출력
- `close()` : `Writer` 의 자원 반환

## 4. `Reader/Writer` 객체 생성 및 활용하기

- `public char[] toCharArray()` : 문자열 → char[] 변환하는 String 클래스의 메서드

## 5. `BufferedReader/BufferedWriter` 로 속도 개선하기

- `public String readLine()` : 1줄씩 읽어 문자열로 리턴하는 메서드

## 6. `PrintWriter` 로 `Writer` 객체 생성하기

- autoFlush : 기본값이 false → 자동 flush()를 사용하기 위해서는 매개변수 true 넘겨줌
