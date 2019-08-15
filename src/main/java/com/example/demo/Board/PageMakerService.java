package com.example.demo.Board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PageMakerService {
    public PageMaker generatePageMaker(int pageNum, int contentNum, JpaRepository<Board,Long> repository){
        PageMaker pageMaker = new PageMaker();

        int totalCount=(int)repository.count();
        pageMaker.setTotalCount(totalCount); //전체 게시글 수 지정
        pageMaker.setPageNum(pageNum-1); //현재 페이지를 페이지 객체로 지정한다. -1을 되야 쿼리에서 사용가능
        pageMaker.setContentNum((contentNum)); //한 페이지에 몇대씩 게시글을 보여줄지 정하는 부분
        pageMaker.setCurrentBlock(pageNum); //현재 패이지 블록이 몇번인지 현재 페이지 번호를 통해 지정하는 부분
        pageMaker.setLastblock(pageNum); // 마지막 블록 번호를 전체 게시글 수를 통해 정하는부분
        pageMaker.prevNext(pageNum);// 현재 페이지 번호로 화살표를 나타낼지 정한다.
        pageMaker.setStartPage(pageMaker.getCurrentBlock()); //시작 페이지를 페이지 블록 번호로 정한다.
        pageMaker.setEndPage(pageMaker.getLastblock(), pageMaker.getCurrentBlock()); //마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.

        return pageMaker;
    }
}
