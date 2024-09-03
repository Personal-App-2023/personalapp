package com.dodo.personalapp.service;

import com.dodo.personalapp.dto.Feeling;
import com.dodo.personalapp.dto.Thought;
import com.dodo.personalapp.entity.UserThoughts;
import com.dodo.personalapp.repository.ThoughtsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ThougthsService {
    @Autowired
    ThoughtsRepository repo;
    @Autowired
    SequenceGenerator generator;
    @Autowired
    RestTemplate restTemplate;

    public UserThoughts save(UserThoughts userThought)
    {
        Integer thoughtId=generator.generateNextThoughtsId();
        LocalDate date = LocalDate.now();
        userThought.setThoughtId(thoughtId);
        userThought.setDate(date);
        return repo.save(userThought);
    }

    public List<Feeling> findEmotionsByUserId(int userId){
        LocalDate date=LocalDate.now();
        date=date.minusMonths(1);
        date=date.minusDays(1);
        List<Feeling> emotions=new ArrayList<Feeling>();
        List<UserThoughts> userThoughts= repo.findByDateGreaterThanAndUserId(date,userId);
        HashMap<String,Integer> lookup= new HashMap<String, Integer>();
        for(UserThoughts userThought:userThoughts)
        {
            for(Thought thought:userThought.getThoughtsList())
            {
                for(Feeling emotion: thought.getFeelings())
                {
                    if(lookup.get(emotion.getEmotion())!=null) {
                        int percent=(lookup.get(emotion.getEmotion())==null) ? 0:lookup.get(emotion.getEmotion());
                        lookup.put(emotion.getEmotion(), percent+emotion.getPercentage());
                    }
                    else
                        lookup.put(emotion.getEmotion(), emotion.getPercentage());
                }
            }
        }
        lookup.forEach((key, value)
                        -> emotions.add(new Feeling(key,value)));
        return emotions;
    }

    public List<Thought> findThoughtsByUserEmotion(String emotion, int userId){
        LocalDate date=LocalDate.now();
        date=date.minusMonths(1);
        date=date.minusDays(1);
        List<Thought> thoughts=new ArrayList<Thought>();
        List<UserThoughts> userThoughts= repo.findByThoughtsListFeelingsEmotionAndDateGreaterThanAndUserId(emotion,date,userId);
        for(UserThoughts userThought:userThoughts)
        {
            for(Thought thought:userThought.getThoughtsList())
            {
                for(Feeling tempEmotion: thought.getFeelings())
                {
                    if(tempEmotion!=null && tempEmotion.getEmotion()!=null && tempEmotion.getEmotion().equals(emotion))
                    {
                        thoughts.add(thought);
                    }
                }
            }
        }
        return thoughts;
    }

    public String getQuoteOfTheDay() {
//        String uri="https://quotes.rest/qod.json?category=inspire&api_key=Q3yDonTV1lfaFMXwKsu4BskSnQmypWzCFNqOxEz3";
//        QuoteResponse resp=restTemplate.getForObject(uri,QuoteResponse.class);
//        return resp.getContents().getQuotes()[0].getQuote();
        String uri="https://api.quotable.io/random?tags=[Inspirational]";
        return null;
    }
}
