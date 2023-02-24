/* 다른 패키지에서의 클래스 접근지정자 vs. 생성자 접근지정자 */

import accessmodifier.AccessModifierOfClass_2.pack.AAA;
// import accessmodifier.AccessModifierOfClass_2.pack.BBB; //불가능
import accessmodifier.AccessModifierOfClass_2.pack.CCC;

public class AccessModifierOfClass_2 {
	public static void main(String[] args) {
		//#1. 객체 생성
		AAA a = new AAA(); //객체생성가능(A a) + 생성자 호출 가능(new AAA())
		//BBB b = new BBB(); //객체생성불가능(BBB b)
		//CCC c = new CCC(); //객체생성가능(CCC c) + 생성자 호출 불가능(new CCC())
	}
}