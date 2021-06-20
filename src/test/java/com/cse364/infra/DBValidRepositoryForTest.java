package com.cse364.infra;

import com.cse364.domain.ValidRepository;

public class DBValidRepositoryForTest implements ValidRepository {
    public boolean isValid(){
            return true;
    }
}
