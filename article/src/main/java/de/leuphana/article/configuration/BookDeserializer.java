package de.leuphana.article.configuration;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.leuphana.article.component.structure.Book;

public class BookDeserializer implements Deserializer<Book>{
	
    @Override
    public Book deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        Book book = null;
        try {
            book = mapper.readValue(new String(data, "UTF-8"), Book.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return book;
    }


}
