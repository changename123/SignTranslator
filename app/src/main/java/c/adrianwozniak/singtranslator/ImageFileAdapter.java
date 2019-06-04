package c.adrianwozniak.singtranslator;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageFileAdapter {
    private static String pathWithFileName = "";

    public static String getFilePath(){
        return pathWithFileName;
    }

    public static File returnFileFrom(Bitmap bitmap){
        try
        {
            File path = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM);
            pathWithFileName = new StringBuilder(path+"/"+"filename.png").toString();
            File imageFile = new File(pathWithFileName);


            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();

            //write the bytes in file
            FileOutputStream fos = new FileOutputStream(imageFile);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();

            Log.i("ENDPOINT create file", "FILE OK");
            return imageFile;
        }
        catch (IOException e){
            Log.i("ENDPOINT error file", e.toString());
        }
        return null;
    }
}
