package com.dodo.personalapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thought {
    String thought;
    List<Feeling> feelings;
    String type;
}
