package com.cse364.infra;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.cse364.domain.*;
import java.lang.reflect.Method;

public class DataLoaderTest {
    @Test
    public void testRead(){
        DataLoader.read();
        assertNotNull(DataLoader.users);
        assertNotNull(DataLoader.movies);
        assertNotNull(DataLoader.ratings);
    }
}
