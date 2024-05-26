
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SubwayMap wuhan = new SubwayMap();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\86138\\AppData\\Local\\Temp\\72592345-6363-4f5d-8ca6-8ee909d10331_实验一.zip.331\\oopobject\\src\\subway.txt"))) {
            String line;
            boolean readingLineName = false;
            String currentLineName = null;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("1号线") || line.startsWith("2号线") || line.startsWith("3号线") ||
                        line.startsWith("4号线") || line.startsWith("5号线") || line.startsWith("6号线") ||
                        line.startsWith("7号线") || line.startsWith("8号线") || line.startsWith("阳逻线")) {
                    // 开始读取新的线路
                    currentLineName = line;
                    wuhan.addLine(currentLineName);
                    readingLineName = true;
                } else if (readingLineName && !line.isEmpty()) {
                    // 解析站点和距离
                    String[] parts = line.split("---");
                    String stationName = parts[0];
                    double distance = Double.parseDouble(parts[1]);
                    wuhan.addStation(stationName, currentLineName, distance);
                } else {
                    // 线路数据结束，重置标志
                    readingLineName = false;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading subway.txt file: " + e.getMessage());
            return;

        }
        for(String s:wuhan.map.keySet()){System.out.println(s);
            for(String s1:wuhan.map.get("1号线站点间距").keySet()){System.out.println(s1);}
    }




        //测试1  判断中转站
        /*Collection<Map<String, Double>> list = wuhan.map.values();
        Collection<String> list2 = wuhan.map.keySet();
        while (list2.iterator().hasNext()) {
            Collection<String> list3 = wuhan.map.get(list2.iterator().next()).keySet();
            Station temp = new Station(list3.iterator().next());
            temp.findLinelist(list3.iterator().next(), wuhan);
            if (temp.isTransfstation(list3.iterator().next())) {
                System.out.println(list3.iterator().next());
            }
        }*/
        //方法2
        for (String s : wuhan.transfStation()) {
            System.out.println(s);
            Station temp = new Station(s);
            temp.findLinelist(s, wuhan);
            System.out.println(temp.getLinelist());
        }

        //test2 判断一定距离内的站点
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入距离：");
        double decimal = scanner.nextDouble();

        System.out.print("请输入站点名称：");
        String str = scanner.next();

        scanner.close();

        Station temp2 = new Station(str);
        String[] lst = temp2.nearbyStation(decimal, wuhan);
        for (String s : lst) {
            System.out.println(s + "__");
        }
//test3 输出路径
        Scanner scanner2 = new Scanner(System.in);

        System.out.print("请输入起点：");
        String str1 = scanner.next();

        System.out.print("请输入终点：");
        String str2 = scanner.next();

        scanner.close();
        wuhan.pathFinder(str1, str2, wuhan);
//输出路径，输出费用
        Scanner scanner3 = new Scanner(System.in);

        System.out.print("请输入起点：");
        String str3 = scanner.next();

        System.out.print("请输入终点：");
        String str4 = scanner.next();

        System.out.print("请输入支付方式");
        String str5 = scanner.next();
wuhan.caclulateFee(str3, str4, str5, wuhan);
    }
}
