package hexlet.code.abstracts.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IParse<T> {
    T parse(String source) throws JsonProcessingException;
}
