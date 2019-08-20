package com.dqy.technicalsolution.chapter.userinteraction.custom;

import android.text.Editable;
import android.text.TextWatcher;

import java.text.NumberFormat;

public class CurrencyTextWatcher implements TextWatcher {
    boolean mEditing;

    public CurrencyTextWatcher(){
        mEditing = false;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public synchronized void afterTextChanged(Editable s) {
        if(!mEditing){
            mEditing = true;
            String digits = s.toString().replaceAll("\\D","");//第一个参数是匹配此字符串的正则表达式。第二个参数用来替换每个匹配项的字符串。
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            try {
                String formatted = nf.format(Double.parseDouble(digits)/100);
                s.replace(0,s.length(),formatted);//第一个参数是要修改的字符串,第二个参数是要替换的字符串,第三个参数是替换成什么样的字符串.
            }catch (NumberFormatException e){
                s.clear();
            }
            mEditing = false;
        }

    }
}
