package com.example.b2bcamp.Utility;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Patterns;

import java.io.ByteArrayOutputStream;
import java.util.regex.Pattern;

public class Commonfunctions {
    public static boolean checkstring(String s)
    {
        if (s!=null && !s.equals("null") && s.length()>0)
        {
            return true;

        }
        else {
            return false;
        }
    }
    public static boolean checkContactNo(String s)
    {
        if (s!=null && !s.equals("null") && s.length()==10)
        {
            return true;

        }
        else {
            return false;
        }
    }
    public static boolean checkPassword(String s)
    {
        if (s!=null && !s.equals("null") && s.length()>=6)
        {
            return true;

        }
        else {
            return false;
        }
    }
    public static boolean checkZipcode(String s)
    {
        if (s!=null && !s.equals("null") && s.length()==6)
        {
            return true;

        }
        else {
            return false;
        }
    }
    public static boolean checkEmail(String s)
    {
        if (s!=null && !s.equals("null") && s.length()>0 && Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),s))
        {
            return true;

        }
        else {
            return false;
        }
    }
    public static boolean checkVerificationcode(String s)
    {
        if (s!=null && !s.equals("null") && s.length()==6)
        {
            return true;

        }
        else {
            return false;
        }
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

}
