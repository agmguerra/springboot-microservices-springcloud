package br.agmg.msscbeerservice.web.model;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class BeerPageList extends PageImpl<BeerDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeerPageList(List<BeerDto> content, Pageable pageable, long total) {
		super(content, pageable, total);
		// TODO Auto-generated constructor stub
	}

	public BeerPageList(List<BeerDto> content) {
		super(content);
		// TODO Auto-generated constructor stub
	}


}