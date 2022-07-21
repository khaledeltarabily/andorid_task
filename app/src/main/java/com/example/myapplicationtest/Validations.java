package com.example.myapplicationtest;

import android.text.TextUtils;
import android.util.Patterns;

public class Validations {

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
