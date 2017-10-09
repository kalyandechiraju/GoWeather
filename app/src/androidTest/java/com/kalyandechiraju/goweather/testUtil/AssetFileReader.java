package com.kalyandechiraju.goweather.testUtil;

import android.support.test.InstrumentationRegistry;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kalyandechiraju on 09/10/17.
 */

public class AssetFileReader {

    public static String readDummyResponse(String filename) throws IOException {
        InputStream inputStream = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext()
                .getAssets().open(filename);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString();
    }
}
