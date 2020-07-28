package co.micol.lms.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.micol.lms.db.MemberDao;
import co.micol.lms.db.MemberVo;

public class InputTest {

	Scanner sc = new Scanner(System.in);
	List<MemberVo> list = new ArrayList<MemberVo>();
	MemberDao dao = new MemberDao();
	MemberVo vo;

	public void menu() {
		boolean boolCheck = true;

		do {
			System.out.println("1. 조 회");
			System.out.println("2. 입 력");
			System.out.println("3. 수 정");
			System.out.println("4. 삭 제");
			System.out.println("5. 종 료");
			System.out.println("=======");
			System.out.println("번호를 입력하세요.");

			switch (sc.nextInt()) {
			case 1:
				memberSelect();
				break;
			case 2:
				memberInput();
				break;
			case 3:
				memberUpdate();
				break;
			case 4:
				memberDelete();
				break;
			case 5:
				boolCheck = false;
				System.out.println("\n\n 작업이 종료되었습니다.");
				break;
			}

		} while (boolCheck);

	}

	private void memberDelete() {
		boolean bool = true;
		System.out.println("삭제할 회원 아이디 입력하세요");
		vo = new MemberVo();
		vo.setId(sc.next());
		int n = dao.delete(vo);
		if (n != 0) {
			System.out.println("정상 삭제");
		} else {

			System.out.println("삭제 실패");
		}
	}

	private void memberUpdate() {
		vo = new MemberVo();
		System.out.println("=====================");
		System.out.println("업데이트 화면 페이지");
		System.out.println("=====================");
		System.out.print(">");
		System.out.println("업데이트 아이디를 입력해주세요");
		vo.setId(sc.next());
		System.out.println("이름을 변경해주세요");
		vo.setName(sc.next());
		System.out.println("비밀번호를 변경해주세요");
		vo.setPassword(sc.next());
		System.out.println("전화번호를 변경해주세요");
		vo.setTel(sc.next());
		int u = dao.update(vo);
		if(u!=0) {
			System.out.println("업데이트완료");
		}else {
			System.out.println("업데이트실패");
		}
		
	}

	private void memberInput() {
		vo = new MemberVo();
		System.out.println("=====================");
		System.out.println("계정을 입력하세요");
		System.out.println("=====================");
		System.out.println("아이디를 입력하세요");
		 vo.setId(sc.next());
		 System.out.println("비밀번호를 입력하세요");
		 vo.setPassword(sc.next());
		 System.out.println("이름을 입력해주세요");
		 vo.setName(sc.next());
		 System.out.println("전화번호를 입력해주세요");
		 vo.setTel(sc.next());
		 int n = dao.insert(vo);
		 if(n !=0) {
			 System.out.println("입력완료");
		 }else {
			 System.out.println("입력실패");
		 }

	}

	private void memberSelect() {
		boolean bool = true;
		do {
			System.out.println("1. 전체 조회");
			System.out.println("2. 부분 조회");
			System.out.println("3. 돌아 가기 ");
			System.out.println("==========");
			System.out.println("입력 하세요.");

			switch (sc.nextInt()) {
			case 1:
				list = dao.SelectAllMemeber();
				System.out.println("$$$$$$$$$$전체회원 조회$$$$$$$$$$");
				for (MemberVo member : list) {
					member.toString();
				}
				break;
			case 2:
				vo = new MemberVo();
				System.out.println("회원아이디를 입력하세요");
				vo.setId(sc.next());
				vo = dao.select(vo);
				vo.toString();

				break;
			case 3:
				bool = false;
				break;
			}
		} while (bool);

	}

}
