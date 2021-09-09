package org.slu.pojo;

import java.util.List;

public interface DemoMapper {
    List<Demo> getDemoList();
    int insertDemo(Demo demo);
}
