package co.micol.lms.board;

public class TestMath {
	private int n;
	private int m;
	
	
	public TestMath() { //default 생성자
		
	}
	
	public TestMath(int n,int m) { //생성자
		this.n = n;
		this.m = m;
	}
	
	public int sum() {//메소드
		return n + m;
	}
	public int sub() {
		int sub;
		if(n >=m) {
			sub = n - m;
		} else {
			sub = m - n;
		}
		return sub;
	}
	

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {//앞소문자 뒤대문자
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}
	
}
