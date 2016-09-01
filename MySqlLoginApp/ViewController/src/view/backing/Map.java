package view.backing;

import oracle.adf.view.faces.bi.component.geoMap.UIGeoMap;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.output.RichMessages;

public class Map {
    private RichForm f1;
    private RichDocument d1;
    private RichMessages m1;
    private UIGeoMap map;
    private UIGeoMap m2;

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }

    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
    }

    public void setMap(UIGeoMap map) {
        this.map = map;
    }

    public UIGeoMap getMap() {
        return map;
    }

    public void setM2(UIGeoMap m2) {
        this.m2 = m2;
    }

    public UIGeoMap getM2() {
        return m2;
    }
}
