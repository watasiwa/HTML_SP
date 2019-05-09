package kr.patago.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.patago.vo.OmokVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class OmokDaoImpl implements IOmokDao {
     
	private SqlMapClient smc;
	
	private static OmokDaoImpl dao;
	
	
	public OmokDaoImpl() {
		Reader rd;
		try {
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			System.out.println("SqlMapClient Error!!");
			e.printStackTrace();
		}
	}
	
	public static OmokDaoImpl getInstance() {
		if(dao == null) {
			dao = new OmokDaoImpl();
		}
		
		return dao;
		
	}
	
	@Override
	public int insert(OmokVO vo) {
		int cnt = 0;
		try {
			
			cnt = smc.update("omok.insert", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;

	}

	@Override
	public List<OmokVO> selectList() {
		List<OmokVO> List = new ArrayList<OmokVO>();
		try {
		
			List = smc.queryForList("omok.selectList");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return List;
	
	}

	@Override
	public OmokVO selectMax() {
		OmokVO vo = null;
		try {
			vo = (OmokVO) smc.queryForObject("omok.selectMax");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return vo;
	}

	@Override
	public List<OmokVO> selectListPan() {
		List<OmokVO> List = new ArrayList<OmokVO>();
		try {
		
			List = smc.queryForList("omok.selectListPan");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return List;
	
		
	}

	@Override
	public List<OmokVO> selectListPans(OmokVO vo) {
		List<OmokVO> List = new ArrayList<OmokVO>();
		try {
		
			List = smc.queryForList("omok.selectListPans",vo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return List;
	
	}
	

	@Override
	public List<OmokVO> selectListHist(OmokVO vo) {
		List<OmokVO> List = new ArrayList<OmokVO>();
		try {
		
			List = smc.queryForList("omok.selectListHist");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return List;
	
	}

	@Override
	public List<OmokVO> selectListHistnextOder(OmokVO vo) {
		List<OmokVO> List = new ArrayList<OmokVO>();
		try {
		
			List = smc.queryForList("omok.selectListHistnextOder");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return List;
	
	}

}
