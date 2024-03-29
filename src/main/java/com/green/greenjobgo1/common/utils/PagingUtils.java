package com.green.greenjobgo1.common.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Setter
@Getter
public  class PagingUtils {
    private int row;

    private int staIdx;
    private int page;
    private int maxPage;
    private int isMore;
    private int idx;

    public PagingUtils(){
        this.row = 10;
    }


    public PagingUtils(int page, int maxPage, Pageable pageable) {
        this.row = 10;
        this.page=page;
        this.maxPage = (int) Math.ceil((double) maxPage / pageable.getPageSize());
        isMore(this.maxPage,page);
        startIdx(page);
    }

    public PagingUtils(int page, int maxPage, Pageable pageable, int row) {
        this.row = row;
        this.page=page;
        this.maxPage = (int) Math.ceil((double) maxPage / pageable.getPageSize());
        isMore(this.maxPage,page);
        startIdx(page);
    }

    public PagingUtils(int page, int maxPage, int row) {
        this.row =row;
        this.page=page;
        makePage(page,maxPage);
    }
    public  void startIdx(int page){
        this.staIdx = page * row;
    }

    public  void maxPage(int maxPage){
        this.maxPage=((int) Math.ceil((double) maxPage / row));
    }

    public void isMore(int maxPage,int page){
        this.isMore= maxPage>page ? 1:0;
    }

    public void makePage(int page,int maxPage){
        startIdx(page);
        maxPage(maxPage);
        isMore(this.maxPage,page);

    }
}
