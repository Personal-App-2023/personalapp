package com.dodo.personalapp.entity;

import com.dodo.personalapp.dto.Thought;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("userthoughts")
public class UserThoughts {
    private Integer thoughtId;
    int userId;
    LocalDate date;
    List<Thought> thoughtsList;
}
