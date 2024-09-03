package com.dodo.personalapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Quote {
    String id;
    String quote;
    String author;
    String[] tags;
}
