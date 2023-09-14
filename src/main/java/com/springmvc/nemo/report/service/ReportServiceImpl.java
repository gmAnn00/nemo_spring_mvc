package com.springmvc.nemo.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springmvc.nemo.report.dao.ReportDAO;
import com.springmvc.nemo.report.vo.GroupReportVO;
import com.springmvc.nemo.report.vo.UserReportVO;

@Service("reportService")
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private ReportDAO reportDAO;
	
	
	@Override
	public boolean reportGroup(GroupReportVO groupReportVO) throws DataAccessException {
		
		boolean isAlreadyReport = reportDAO.isAlreadyReportGroup(groupReportVO);
		
		if(isAlreadyReport) {
			return false;
		}
		
		int newReportId = reportDAO.getNextGroupReportId();
		groupReportVO.setReport_id(newReportId);
		reportDAO.reportGroup(groupReportVO);
		
		return true;
	}
	
	
	@Override
	public boolean reportMember(UserReportVO userReportVO) throws DataAccessException {
		
		boolean isAlreadyReport = reportDAO.isAlreadyReportMember(userReportVO);
		
		if(isAlreadyReport) {
			return false;
		}
		
		int newReportId = reportDAO.getNextUserReportId();
		userReportVO.setReport_id(newReportId);
		reportDAO.reportMember(userReportVO);
		
		return true;
	}

}
