package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;

import kr.watchu.movie.domain.OfficialsCommand;

public interface OfficialsService {
	//등록
	public void insert(OfficialsCommand officials);
	//상세정보
	public OfficialsCommand selectOfficials(Integer off_num);
	//수정
	public void update(OfficialsCommand officials);
	//삭제
	//삭제시 관련 테이블의 정보도 삭제 해야함
	public void delete(Integer off_num);
	//목록
	public int selectOffCnt(Map<String, Object> map);
	public List<OfficialsCommand> selectOffList(Map<String, Object> map);
	
	//자동완성 ajax
	public List<OfficialsCommand> selectOffAjaxList(Map<String, Object> map);
}
