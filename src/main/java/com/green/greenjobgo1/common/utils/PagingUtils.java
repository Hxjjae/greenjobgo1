package com.green.greenjobgo1.common.utils;


public  class PagingUtils {
    private int row;

    private int staIdx;
    private int page;
    private int maxPage;
    private int isMore;

    public PagingUtils(){
        this.row = 10;
    }


    public PagingUtils(int page, int maxPage) {
        this.row =10;
        this.page=page;
        makePage(page,maxPage);
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
        this.maxPage=((int) Math.ceil((double) maxPage / row))-1;
    }

    public void isMore(int maxPage,int page){
        this.isMore= maxPage>page ? 1:0;
    }

    public void makePage(int page,int maxPage){
        startIdx(page);
        maxPage(maxPage);
        isMore(this.maxPage,page);

    }

    public int getStaIdx() {
        return staIdx;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public int getIsMore() {
        return isMore;
    }

    public int getPage() {
        return page;
    }

    public int getRow() {
        return row;
    }
}
