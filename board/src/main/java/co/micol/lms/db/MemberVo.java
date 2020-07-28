package co.micol.lms.db;

public class MemberVo {
	private String id;
	private String password;
	private String name;
	private String tel;
	
	public MemberVo() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	public String toString() {
		System.out.print("아이디 : "+ getId());
		System.out.print("비밀번호 : "+ getPassword());
		System.out.print("이름 : "+ getName());
		System.out.println("전화번호 : "+ getTel());
	
		 return null;
	}
	
}
