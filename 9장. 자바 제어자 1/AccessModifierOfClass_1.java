/* 같은 패키지에서의 클래스 접근지정자 vs. 생성자 접근지정자 */

public class AccessModifierOfClass_1 {
	public static void main(String[] args) {
		// #1. 객체 생성
		AA a = new AA(); //선언가능(AA a) + 생성자 호출 가능(new AA())
		BB b = new BB(); //선언가능(BB b) + 생성자 호출 가능(new BB())
		CC c = new CC(); //선언가능(CC c) + 생성자 호출 가능(new CC())
	}
}