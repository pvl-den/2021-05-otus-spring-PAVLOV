package ru.otus.dao;

import java.io.IOException;
import java.util.List;

public interface QuestionsDao {

    List<String[]> readFromFile(final String fileName) throws IOException;

}
