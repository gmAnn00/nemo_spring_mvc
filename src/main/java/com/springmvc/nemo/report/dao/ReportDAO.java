package com.springmvc.nemo.report.dao;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.report.vo.GroupReportVO;
import com.springmvc.nemo.report.vo.UserReportVO;

public interface ReportDAO {

	public boolean isAlreadyReportGroup(GroupReportVO groupReportVO) throws DataAccessException;

	public int getNextGroupReportId() throws DataAccessException;

	public void reportGroup(GroupReportVO groupReportVO) throws DataAccessException;

	public boolean isAlreadyReportMember(UserReportVO userReportVO) throws DataAccessException;

	public int getNextUserReportId() throws DataAccessException;

	public void reportMember(UserReportVO userReportVO) throws DataAccessException;

}
