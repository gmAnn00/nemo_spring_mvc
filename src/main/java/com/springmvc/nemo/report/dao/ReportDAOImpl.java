package com.springmvc.nemo.report.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.springmvc.nemo.report.vo.GroupReportVO;
import com.springmvc.nemo.report.vo.UserReportVO;

@Repository("reportDAO")
public class ReportDAOImpl implements ReportDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean isAlreadyReportGroup(GroupReportVO groupReportVO) throws DataAccessException {
		
		return sqlSession.selectOne("mapper.report.isAlreadyReportGroup", groupReportVO);
	}
	
	@Override
	public int getNextGroupReportId() throws DataAccessException {
		
		return sqlSession.selectOne("mapper.report.getNextGroupReportId");
	}
	
	@Override
	public void reportGroup(GroupReportVO groupReportVO) throws DataAccessException {
		
		int group_id = groupReportVO.getGroup_id();
		sqlSession.update("mapper.report.increaseGroupReportCnt", group_id);
		sqlSession.insert("mapper.report.reportGroup", groupReportVO);
		
	}
	
	@Override
	public boolean isAlreadyReportMember(UserReportVO userReportVO) throws DataAccessException {

		return sqlSession.selectOne("mapper.report.isAlreadyReportMember", userReportVO);
	}
	
	@Override
	public int getNextUserReportId() throws DataAccessException {
		
		return sqlSession.selectOne("mapper.report.getNextUserReportId");
	}
	
	@Override
	public void reportMember(UserReportVO userReportVO) throws DataAccessException {
		
		String accused_id = userReportVO.getAccused_id();
		sqlSession.update("mapper.report.increaseUserReportCnt", accused_id);
		sqlSession.insert("mapper.report.reportMember", userReportVO);
		
	}

}
