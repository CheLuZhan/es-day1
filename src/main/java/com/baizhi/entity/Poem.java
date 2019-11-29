package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class Poem {
    private String id;
    private String name;
    private String author;
    private String type;
    private String content;
    private String href;
    private String authordes;
}
