package com.alia.base;

import android.content.Context;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ManageWords {
    DBHelper helper;

    public ManageWords(Context context) {
        helper = DBHelper.getInstance(context);
        if (helper.numberOfRows() < 1000) {
            StartBase startBase = new StartBase(this);
            startBase.fill();
        }
    }

    public boolean addWord(String word) {
        return helper.insertWord(word);
    }

    public void addALL(String[] words) {
        for (String word : words) {
            helper.insertWord(word);
        }
    }

    public boolean delete(String word) {
        int a = helper.deleteWord(word);
        return a > 0;
    }

    public String getAmount(int amount) {
        int all = helper.numberOfRows();
        if (all == 0) {
            return "brak słów w bazie";
        }
        if (all <= amount) {
            return helper.getAllWords().toString();
        }

        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        Set<String> wordSet = new HashSet<>();

        while (wordSet.size() < amount) {
            String word = helper.getWord(random.nextInt(all));
            if (word != null) {
                wordSet.add(word);
            }
        }
        int count = 1;
        for (String word : wordSet) {
            stringBuilder.append(count++).append(". ").append(word).append("\n");
        }
        return stringBuilder.toString();
    }
}