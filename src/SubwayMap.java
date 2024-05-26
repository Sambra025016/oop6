import java.util.*;
import java.util.HashMap;
import java.util.Arrays;
import java.util.HashSet;


public class SubwayMap {
    Map<String, Map<String, Double>> map;
    int tempnum;
    int tempnum2;
    int tempnum3;

    public SubwayMap() {
        this.map = new HashMap<String, Map<String, Double>>();
    }

    public void addLine(String lineName) {
        map.put(lineName, new HashMap<>());
    }

    public void addStation(String stationName, String lineName, double distance) {
        map.get(lineName).put(stationName, distance);
    }


    public String[] getLinelist(String stationName) {
        String[] lineList = new String[10];
        Collection<Map<String, Double>> list = map.values();
        Collection<String> list2 = map.keySet();
        while (list2.iterator().hasNext()) {

            System.out.println(list2.iterator().next());


            for (int i = 0; i < map.get(list2.iterator().next()).keySet().size(); i++) {

                if (stationName.equals(map.get(list2.iterator().next()).keySet().iterator().next())) {

                    lineList[tempnum2] = list2.iterator().next();
                    tempnum2++;
                }


            }
        }
        return lineList;

    }

    ;


    public void pathFinder(String s1, String s2, SubwayMap subwayMap) {

        String[] lineList = transfStation();
        String[] l1 = getLinelist(s1);
        String[] l2 = getLinelist(s2);
        for (String line : transfStation()) {
            Station s = new Station(line);
            String[] l3 = getLinelist(line);
            if (this.isSubsetUsingSets(l1, l3) && this.isSubsetUsingSets(l2, l3)) {
                String line1 = l1[0];
                String line2 = l2[0];
                Line ln1 = new Line(line1);
                Line ln2 = new Line(line2);
                ln1.getMap(subwayMap);
                ln2.getMap(subwayMap);
                System.out.println(Arrays.toString(ln1.returnpath(s1, s.getStationName())));
                System.out.println(Arrays.toString(ln2.returnpath(s.getStationName(), s2)));

            }
        }
    }


    //返回中转站
    public String[] transfStation() {
        String[] lineList = new String[30];
        Collection<Map<String, Double>> list = map.values();
        Collection<String> list2 = map.keySet();
        while (list2.iterator().hasNext()) {
            String[] lineList2 = map.get(list2.iterator().next()).keySet().toArray(new String[0]);
            for (String line : lineList2) {
                Station s = new Station(line);
                if (s.isTransfstation(s.getStationName())) {
                    lineList[tempnum] = line;
                }
                tempnum = tempnum + 1;
            }

        }
        return null;
    }


    public boolean isSubsetUsingSets(String[] subset, String[] set) {
        // 将数组转换为HashSet以提高查找效率
        HashSet<String> setSet = new HashSet<>(Arrays.asList(set));
        HashSet<String> subsetSet = new HashSet<>(Arrays.asList(subset));

        // 使用retainAll方法检查子集的所有元素是否都在主集中
        return setSet.containsAll(subsetSet);
    }

    public String[] returntransfStation(String s1, String s2) {
        String[] lineList = new String[30];
        String[] l1 = getLinelist(s1);
        String[] l2 = getLinelist(s2);
        for (String line : transfStation()) {
            Station s = new Station(line);
            String[] l3 = getLinelist(line);
            if (this.isSubsetUsingSets(l1, l3) && this.isSubsetUsingSets(l2, l3)) {
                lineList[tempnum3] = line;
            }
        }
        return lineList;
    }

    public void caclulateFee(String s1, String s2, String s3, SubwayMap subwayMap) {
        for (String s : this.returntransfStation(s1, s2)) {
            String[] l1 = getLinelist(s1);
            String[] l2 = getLinelist(s2);
            Line ln1 = new Line(l1[0]);
            Line ln2 = new Line(l2[0]);
            ln1.getMap(subwayMap);
            ln2.getMap(subwayMap);
            double fee = 0;
            double sub = ln1.getDistance(s1, s) + ln2.getDistance(s2, s);
            if (sub <= 4) {
                fee = 2.0;
            } else if (sub <= 12) {
                fee = 1.0 * sub / 4;
            } else if (sub <= 24) {
                fee = 1.0 * sub / 6;
            } else if (sub <= 40) {
                fee = 1.0 * sub / 8;
            } else if (sub <= 50) {
                fee = 1.0 * sub / 10;
            } else {
                fee = 1.0 * sub / 20;
            }
            if (s3 == "普通单程票") {
            } else if (s3 == "武汉通刷卡")
                fee = fee * 0.9;
            else if (s3 == "日票")
                fee = 0;
            System.out.println("先坐" + ln1 + "从" + s1 + "到" + s2 + ",在" + s + "换乘" + ln2 + "到" + s2);
            System.out.println("费用为" + fee);

        }
    }
}

