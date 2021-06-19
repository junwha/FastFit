package com.cse364.database.schemas;

import io.micrometer.core.instrument.config.validate.Validated;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "valid")
@Data
public class ValidSchema {
    @Id
    int id;

    public ValidSchema(int id){
        this.id = id;
    }
}
