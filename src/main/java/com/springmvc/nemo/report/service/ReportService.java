package com.springmvc.nemo.report.service;

import org.springframework.dao.DataAccessException;

import com.springmvc.nemo.report.vo.GroupReportVO;
import com.springmvc.nemo.report.vo.UserReportVO;

public interface ReportService {

	public boolean reportGroup(GroupReportVO groupReportVO) throws DataAccessException;

	public boolean reportMember(UserReportVO userReportVO) throws DataAccessException;

}
