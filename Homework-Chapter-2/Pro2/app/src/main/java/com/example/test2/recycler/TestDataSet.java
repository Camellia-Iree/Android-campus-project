package com.example.test2.recycler;

import java.util.ArrayList;
import java.util.List;

public class TestDataSet {

    public static List<TestData> getData() {
        List<TestData> result = new ArrayList();
        result.add(new TestData("赵立坚回击美国", "524.6w", 0));
        result.add(new TestData("乔延路的尽头是繁华大道", "433.6w", 0));
        result.add(new TestData("建党100周年", "357.8w", 0));
        result.add(new TestData("美国前国防部长逝世", "333.6w", 0));
        result.add(new TestData("袁隆平", "285.6w", 0));
        result.add(new TestData("锟斤拷烫烫烫", "183.2w", 0));
        result.add(new TestData("北京暴雨", "139.4w", 0));
        result.add(new TestData("浙江高考", "75.6w", 0));
        result.add(new TestData("中国女排", "55w", 0));
        result.add(new TestData("欧洲杯决赛", "43w", 0));
        result.add(new TestData("月球土特产", "22.2w", 0));
        return result;
    }

}
