package kr.patago.service;

import java.util.List;

import kr.patago.dao.OmokDaoImpl;
import kr.patago.vo.OmokVO;

public class OmokServiceImpl implements IOmokService{
	
	private OmokDaoImpl omokDao;
	
	private static OmokServiceImpl service;

	public OmokServiceImpl() {
		omokDao = OmokDaoImpl.getInstance();
	}
	public static OmokServiceImpl getInstance() {
		if(service == null) {
			service = new OmokServiceImpl();
		}
		return service;
	}
	
	@Override
	public int insert(OmokVO vo) {
		return omokDao.insert(vo);
	}

	@Override
	public List<OmokVO> selectList() {
		return omokDao.selectList();
	}
	@Override
	public OmokVO selectMax() {
		return omokDao.selectMax();
	}
	@Override
	public List<OmokVO> selectListPan() {
		
		return omokDao.selectListPan();
	}
	@Override
	public List<OmokVO> selectListPans(OmokVO vo) {
		
		return omokDao.selectListPans(vo);
	}
	@Override
	public List<OmokVO> selectListHist(OmokVO vo) {
		
		return omokDao.selectListHist(vo);
	}
	@Override
	public List<OmokVO> selectListHistnextOder(OmokVO vo) {
		return omokDao.selectListHistnextOder(vo);
	}
}
