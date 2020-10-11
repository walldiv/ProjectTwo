package com.ex.dao;

import com.ex.model.GeoCashe;
import com.ex.model.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GpsDAOImpl_PGRTest {
    private GpsDAO dao;

    @Before
    public void init(){
        dao = new GpsDAOImpl_PGR();
    }
    @Test
    public void shouldGetAllCashes() {
        List<GeoCashe> tmp = dao.getAllCashes();
        Assert.assertNotNull(tmp);
    }

    @Test
    public void shouldFindCasheByID() {
        /* THESE TESTS WOULD FAIL IF THIS DATA DOESNT EXIST... ITS NOT VALID TEST - DAN W. */
        GeoCashe tmp = dao.findCasheByID(34);
        final double DELTA = 1e-2;
        Assert.assertEquals(43.13, tmp.getLat(),DELTA);
        Assert.assertEquals(-85.56, tmp.getLng(),DELTA);
    }

    @Test
    public void shouldFindItemByID() {
        /* THESE TESTS WOULD FAIL IF THIS DATA DOESNT EXIST... ITS NOT VALID TEST - DAN W. */
        Item tmp = dao.findItemByID(6);
        Assert.assertEquals("painted rock",tmp.getName());
    }
}