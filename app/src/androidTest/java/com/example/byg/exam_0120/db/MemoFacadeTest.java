package com.example.byg.exam_0120.db;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by byg on 2017-04-03.
 */
public class MemoFacadeTest {

    private Context mAppContext;

    @Before
    public void setUp() throws Exception {
        mAppContext = InstrumentationRegistry.getTargetContext();
    }

    @After
    public void tearDown() throws Exception {
        mAppContext=null;
    }

    @Test
    public void insert() throws Exception {

        MemoFacade facade = new MemoFacade(mAppContext);
        long result = facade.insert("test", "test", "test");

        // case1
        Assert.assertNotEquals(-1, result);
    }

    @Test
    public void getMemoList() throws Exception {

    }

    @Test
    public void getMemoList1() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

}