package com.cse364.infra;

import com.cse364.database.repositories.DBValidRepository;
import com.cse364.domain.ValidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class DBValidRepositoryAdaptor implements ValidRepository {
    DBValidRepository valid;

    public DBValidRepositoryAdaptor(DBValidRepository valid){
        this.valid = valid;
    }

    public boolean isValid(){
        if(valid.existsById(0)){
            return true;
        }else{
            return false;
        }
    }
}
