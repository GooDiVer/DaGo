package e.mi.myapplication.Net.Event;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date {

    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("start")
    @Expose
    private long start;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("end_time")
    @Expose
    private Object endTime;
    @SerializedName("end")
    @Expose
    private long end;
    @SerializedName("is_continuous")
    @Expose
    private boolean isContinuous;
    @SerializedName("is_endless")
    @Expose
    private boolean isEndless;
    @SerializedName("is_startless")
    @Expose
    private boolean isStartless;
    @SerializedName("schedules")
    @Expose
    private List<Object> schedules = null;
    @SerializedName("use_place_schedule")
    @Expose
    private boolean usePlaceSchedule;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Object getEndTime() {
        return endTime;
    }

    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public boolean isIsContinuous() {
        return isContinuous;
    }

    public void setIsContinuous(boolean isContinuous) {
        this.isContinuous = isContinuous;
    }

    public boolean isIsEndless() {
        return isEndless;
    }

    public void setIsEndless(boolean isEndless) {
        this.isEndless = isEndless;
    }

    public boolean isIsStartless() {
        return isStartless;
    }

    public void setIsStartless(boolean isStartless) {
        this.isStartless = isStartless;
    }

    public List<Object> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Object> schedules) {
        this.schedules = schedules;
    }

    public boolean isUsePlaceSchedule() {
        return usePlaceSchedule;
    }

    public void setUsePlaceSchedule(boolean usePlaceSchedule) {
        this.usePlaceSchedule = usePlaceSchedule;
    }

}