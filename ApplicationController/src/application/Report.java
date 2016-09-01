package application;

import java.net.URL;

import java.util.ArrayList;

public class Report {
    public Report() {
        super();
    }
    int user_id,report_id,priority;
    double latitude,longitude;
    URL imageUrl;
    String description,type;


    public int getUser_id() {
        return user_id;
    }

    public int getReport_id() {
        return report_id;
    }

    public int getPriority() {
        return priority;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public Report(int user_id, int report_id, int priority, double latitude, double longitude, URL imageUrl,
                  String description, String type) {
        this.user_id = user_id;
        this.report_id = report_id;
        this.priority = priority;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageUrl = imageUrl;
        this.description = description;
        this.type = type;
    }
    public ArrayList<Report> getReports() {
        ArrayList<Report> reports = new ArrayList<Report>();
        reports.add(new Report(1,1,1,30,30,null,"desc","fire"));
        reports.add(new Report(2,2,2,30,31,null,"desc","police"));
        reports.add(new Report(2,2,2,34,31,null,"desc","fire"));
        reports.add(new Report(3,4,4,32,31,null,"desc","fire"));
        reports.add(new Report(1,1,0,31,31,null,"desc","fire"));

        return reports;
    }
}
