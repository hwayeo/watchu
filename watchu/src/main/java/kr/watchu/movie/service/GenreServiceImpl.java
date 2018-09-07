package kr.watchu.movie.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.watchu.movie.dao.GenreMapper;
import kr.watchu.movie.domain.GenreCommand;
import kr.watchu.movie.domain.OfficialsCommand;

@Service("genreService")
public class GenreServiceImpl implements GenreService{
	@Resource
	private GenreMapper genreMapper;

	@Override
	public void insertGenre(GenreCommand genre) {
		genreMapper.insertGenre(genre);
	}

	@Override
	public GenreCommand selectGenre(Integer genre_num) {
		return genreMapper.selectGenre(genre_num);
	}
	
	@Override
	public void updateGenre(GenreCommand genre) {
		genreMapper.updateGenre(genre);
		
	}

	@Override
	public void deleteGenre(Integer genre_num) {
		genreMapper.deleteGenre(genre_num);
		
	}

	@Override
	public int selectGenreCnt(Map<String, Object> map) {
		return genreMapper.selectGenreCnt(map);
	}

	@Override
	public List<GenreCommand> selectGenreList(Map<String, Object> map) {
		return genreMapper.selectGenreList(map);
	}
	
	//자동완성 ajax
	@Override
	public List<GenreCommand> selectGenreAjaxList(Map<String, Object> map){
		return genreMapper.selectGenreAjaxList(map);
	}
}
