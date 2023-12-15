package com.nts.seonghwan.be.post.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostSearch {
    private String title;
    private String writer;
    private String content;
    private String tag;
}
