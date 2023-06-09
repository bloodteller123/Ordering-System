package com.zihanc.takeout.result;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

// we need getter / setter for Jackson serialization./deserialization so we use @Data... to make things easier.
@Data
public class PageResult {
    private int pageNum;
    private int pageSize;
    private long totalSize;
    private int totalPages;
    // contents for 1 page
    private List<?> content;

    public PageResult(int totalPages, long totalSize, List<?> contents){
        this.totalPages =totalPages;
        this.totalSize = totalSize;
        this.content = contents;
    }
}
