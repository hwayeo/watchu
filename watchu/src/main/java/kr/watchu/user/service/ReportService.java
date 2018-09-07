package kr.watchu.user.service;

import java.util.List;
import java.util.Map;

import kr.watchu.user.domain.ReportCommand;

public interface ReportService {
	
	//신고
	public void insertReport(ReportCommand report);
	//상세정보
	public ReportCommand selectReport(Integer report_num);
	//신고 취소
	public void deleteReport(Integer report_num);
	//목록
	public List<ReportCommand> selectReportList(Map<String,Object> map);
	//카운트
	public int selectReportCnt(Map<String,Object> map);
}
