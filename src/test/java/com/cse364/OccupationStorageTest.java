package com.cse364;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/*
    0: "other" or not specified
    1: "academic/educator"
    2: "artist"
    3: "clerical/admin"
    4: "college/grad student"
    5: "customer service"
    6: "doctor/health care"
    7: "executive/managerial"
    8: "farmer"
    9: "homemaker"
    10: "K-12 student"
    11: "lawyer"
    12: "programmer"
    13: "retired"
    14: "sales/marketing"
    15: "scientist"
    16: "self-employed"
    17: "technician/engineer"
    18: "tradesman/craftsman"
    19: "unemployed"
    20: "writer
*/

import java.util.HashMap;

import static org.junit.Assert.*;

public class OccupationStorageTest {


    @Test
    public void testEquals() {
        final HashMap<String, Integer> occupationTable = new HashMap<String, Integer>(){
            {
                put("other", 0);
                put("academic", 1);put("educator", 1);
                put("artist", 2);
                put("clerical", 3);put("admin", 3);
                put("college", 4);put("grad student", 4);
                put("customer service", 5);
                put("doctor", 6);put("health care", 6);
                put("executive", 7);put("managerial", 7);
                put("farmer", 8);
                put("homemaker", 9);
                put("K-12 student", 10);
                put("lawyer", 11);
                put("programmer", 12);
                put("retired", 13);
                put("sales", 14);put("marketing", 14);
                put("scientist", 15);
                put("self-employed", 16);
                put("technician", 17);put("engineer", 17);
                put("tradesman", 18);put("craftsman", 18);
                put("unemployed", 19);
                put("writer", 20);
            }
        };
        OccupationStorage storage = new OccupationStorage(occupationTable);

        assertTrue(storage.getOccupation("other").equals(0));

        assertTrue(storage.getOccupation("academic").equals(1));
        assertTrue(storage.getOccupation("educator").equals(1));

        assertTrue(storage.getOccupation("artist").equals(2));

        assertTrue(storage.getOccupation("clerical").equals(3));
        assertTrue(storage.getOccupation("admin").equals(3));

        assertTrue(storage.getOccupation("college").equals(4));
        assertTrue(storage.getOccupation("grad student").equals(4));

        assertTrue(storage.getOccupation("customer service").equals(5));

        assertTrue(storage.getOccupation("doctor").equals(6));
        assertTrue(storage.getOccupation("health care").equals(6));

        assertTrue(storage.getOccupation("executive").equals(7));
        assertTrue(storage.getOccupation("managerial").equals(7));

        assertTrue(storage.getOccupation("farmer").equals(8));

        assertTrue(storage.getOccupation("homemaker").equals(9));

        assertTrue(storage.getOccupation("K-12 student").equals(10));

        assertTrue(storage.getOccupation("lawyer").equals(11));

        assertTrue(storage.getOccupation("programmer").equals(12));

        assertTrue(storage.getOccupation("doctor").equals(13));

        assertTrue(storage.getOccupation("doctor").equals(14));
        assertTrue(storage.getOccupation("doctor").equals(14));

        assertTrue(storage.getOccupation("doctor").equals(15));

        assertTrue(storage.getOccupation("self-employed").equals(16));

        assertTrue(storage.getOccupation("technician").equals(17));
        assertTrue(storage.getOccupation("engineer").equals(17));

        assertTrue(storage.getOccupation("tradesman").equals(18));
        assertTrue(storage.getOccupation("craftsman").equals(18));

        assertTrue(storage.getOccupation("unemployed").equals(19));

        assertTrue(storage.getOccupation("writer").equals(20));
    }
}
