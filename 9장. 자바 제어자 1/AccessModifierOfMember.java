/* 멤버 및 생성자에 적용되는 네 가지 접근 지정자 */

import accessmodifier.AccessModifierOfMember.pack1.A;
import accessmodifier.AccessModifierOfMember.pack1.B;
import accessmodifier.AccessModifierOfMember.pack2.C;
import accessmodifier.AccessModifierOfMember.pack2.D;

public class AccessModifierOfMember {
	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		C c = new C();
		D d = new D();
		
		a.print();
		b.print();
		c.print();
		d.print();
	}
}