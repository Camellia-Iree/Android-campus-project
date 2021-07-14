package com.example.test.auxiliary;

import androidx.annotation.DrawableRes;

import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

public class TestdataSet {

    public static List<Testdata> getData() {
        List<Testdata> result = new ArrayList();
        result.add(new Testdata("小红", "你好  - 刚刚", R.drawable.head0));
        result.add(new Testdata("小蓝", "www  - 15:03", R.drawable.head1));
        result.add(new Testdata("小绿", "阿巴阿巴  -15.01", R.drawable.head2));
        result.add(new Testdata("默默", "救命  -14:44", R.drawable.head2));
        result.add(new Testdata("笑笑", "我的天  -14:23", R.drawable.peace));
        result.add(new Testdata("江江", "我ADS满了  -14:03", R.drawable.head0));
        result.add(new Testdata("小雪", "我均绩4.8了  -13:25", R.drawable.head1));
        result.add(new Testdata("姐姐", "好好学习  -13:08", R.drawable.head2));
        result.add(new Testdata("烈烈", "你的外卖到了  -13:00", R.drawable.head0));
        result.add(new Testdata("妈妈", "加油  -12:45", R.drawable.peace));
        result.add(new Testdata("爸爸", "努力  -12:44", R.drawable.head1));
        return result;
    }


}
