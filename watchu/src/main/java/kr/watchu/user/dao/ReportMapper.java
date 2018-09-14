package kr.watchu.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.watchu.user.domain.ReportCommand;

public interface ReportMapper {
	
	//신고 
	@Insert("INSERT INTO user_report (report_num,id,report_user,report_content,reg_date,report_category,report_title) VALUES (report_seq.nextval,#{id},#{report_user},#{report_content},SYSDATE,#{report_category},#{report_title})")
	public void insertReport(ReportCommand report);
	//상세정보
	@Select("SELECT * FROM user_report WHERE report_num=#{report_num}")
	public ReportCommand selectReport(Integer report_num);
	//신고 삭제
	@Delete("delete user_report where report_num=#{report_num}")
	public void deleteReport(Integer report_num);
	
	//목록
	public List<ReportCommand> selectReportList(Map<String,Object> map);
	//카운트
	public int selectReportCnt(Map<String,Object> map);
}