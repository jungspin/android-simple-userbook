package com.cos.simpleuserbook.util;

import android.content.Intent;

public class IntentFlagSetting {

    public void flagSetting(Intent intent){
        // 디폴트는 no flag
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 이 intent 만 이렇게 동작한다. -> 함수 셋팅 best

    }
}
