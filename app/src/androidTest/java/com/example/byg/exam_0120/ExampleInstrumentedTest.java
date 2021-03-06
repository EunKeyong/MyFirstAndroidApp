package com.example.byg.exam_0120;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.byg.exam_0120.db.MemoDbHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.byg.exam_0120", appContext.getPackageName());
    }

    @Test
    public void testDbInsertUpdate() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        MemoDbHelper dbHelper = new MemoDbHelper(appContext);

        // test 코드 작성
        dbHelper.getWritableDatabase().beginTransaction();
    }
}
