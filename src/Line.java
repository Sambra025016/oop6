import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Line {
    private String name;
    private Map<String, Double> map;
    private Map<Integer, String> map2;
    private Map<String, Integer> map3;
    private int tempnum1;
    private int tempnum2;
    private double tempnum3;

    public Line(String name) {
        map2 = new HashMap<Integer, String>();
        map3 = new HashMap<String, Integer>();
    }

    public Map<Integer, String> getMap2() {
        return map2;
    }

    public void getMap(SubwayMap sm) {
        Collection<Map<String, Double>> list = sm.map.values();
        Collection<String> list2 = sm.map.keySet();
        while (list2.iterator().hasNext()) {
            if (Objects.equals(this.name, list2.iterator().next()))
                this.map.putAll(list.iterator().next());
        }
        Collection<String> list3 = this.map.keySet();
        while (list3.iterator().hasNext()) {
            this.tempnum1 = this.tempnum1 + 1;
            map2.put(tempnum1, list3.iterator().next());
        }
        while (list3.iterator().hasNext()) {
            this.tempnum2 = this.tempnum2 + 1;
            map3.put(list3.iterator().next(), tempnum2);
        }

    }

    public double getDistance(String s1, String s2) {
        int a1 = map3.get(s1);
        int a2 = map3.get(s2);
        if (a1 > a2) {
            for (int i = a2; i < a1; i++) {

                tempnum3 = tempnum3 + map.get(map2.get(i));
            }
        } else
            for (int i = a1; i < a2; i++) {
                double j = 0;
                tempnum3 = tempnum3 + map.get(map2.get(i));
            }
        return tempnum3;
    }

    public String[] returnpath(String s1, String s2) {
        int a1 = map3.get(s1);
        int a2 = map3.get(s2);
        String[] path = new String[20];
        if (a1 > a2) {
            for (int i = a2; i < a1; i++) {
                path[i] = map2.get(i);
            }
        } else
            for (int i = a1; i < a2; i++) {
                path[i] = map2.get(i);
            }return path;
    }
}