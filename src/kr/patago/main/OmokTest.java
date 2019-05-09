package kr.patago.main;

import kr.patago.service.OmokServiceImpl;
import kr.patago.vo.OmokVO;

public class OmokTest {

		private OmokServiceImpl omokService;
	
	public static void main(String[] args) {
			new OmokTest();
	}
	
	public OmokTest() {
		omokService = OmokServiceImpl.getInstance();
		
		OmokVO vo  = new OmokVO();
		vo.setPan("1");
		vo.setPan_order("1");
		vo.setHistory("1");
		vo.setWinner("1");
		
		omokService.insert(vo);
		
	}

}
