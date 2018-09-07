package kr.watchu.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.watchu.user.domain.ReportCommand;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

	@Override
	public void insertReport(ReportCommand report) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReportCommand selectReport(Integer report_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteReport(Integer report_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReportCommand> selectReportList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectReportCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

}
