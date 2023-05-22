package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {
    // BEGIN
    private List<Object> list = new ArrayList<>();

    public synchronized void add(Object obj) {
        list.add(obj);
    }

    public synchronized Object get(int index) {
        return list.get(index);
    }

    public synchronized int getSize() {
        return list.size();
    }
    // END
}
