import static java.awt.AWTEventMulticaster.add;

public  class Station {
    private String stationName;
    private String[] linelist;
    private int num;

    public Station() {

    }
    public String getStationName() {
        return stationName;
    }

    public Station(String stationName) {
        this.stationName = stationName;
    }

    public void findLinelist(String s, SubwayMap sm) {
        this.linelist = sm.getLinelist(stationName);

    }
    public String[] getLinelist() {
        return linelist;
    }

    ;

    public boolean isTransfstation(String stationName) {
        String[] lst = this.linelist;
        for (int i = 0; i < lst.length; i++) {
            if (lst[i] != null) {
                this.num = this.num + 1;
            }
        }
        if(this.num >=2) {
            return true;
        }
        else
            return false;
    }
    public String[] nearbyStation(double distance, SubwayMap sm) {
        String[] nearby=new String[50];
        this.findLinelist(this.stationName,sm);
        String[] list = this.linelist;
        for (int i = 0; i < list.length; i++) {
            Line l=new Line(list[i]);
            l.getMap(sm);
            for(int j=0;j<l.getMap2().keySet().size();j++){
            double k=l.getDistance(this.stationName,l.getMap2().get(j));
            if  (k<distance){
                //在数组中添加新元素??
                nearby[j]=l.getMap2().get(j);
            }
            }
        }
        return nearby;
    }
public String getfirstLine(){
        return this.linelist[0];
}
}
