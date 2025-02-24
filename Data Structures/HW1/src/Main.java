//class TimeSpan implements Comparable<TimeSpan> {
//    private int hours, minutes;
//
//    private TimeSpan(int hours) {
//        if (hours<0) { throw new IllegalArgumentException("hours can't be neg"); }
//        this.hours = hours;
//    }
//    private TimeSpan(int minutes) {
//        if (minutes<0) { throw new IllegalArgumentException("minutes can't be neg"); }
//        this.minutes = minutes;
//    }
//    private TimeSpan(int hours, int minutes) {
//        if (hours<0 || minutes<0) throw new IllegalArgumentException("hours and minutes can't be neg");
//        this.hours = hours; this.minutes = minutes;
//    }
//    //end of constructors
//    public static TimeSpan ofHours(int hours) throws IllegalArgumentException {
//        return new TimeSpan(hours);
//    }
//    public static TimeSpan ofMinutes(int minutes) throws IllegalArgumentException {
//        return new TimeSpan(minutes);
//    }
//    public static TimeSpan ofHoursAndMinutes(int hours, int minutes) throws IllegalArgumentException {
//        return new TimeSpan(hours, minutes);
//    }
//    public int getHours() {
//        return hours;
//    }
//    public int getMinutes() {
//        return minutes;
//    }
//    public int getTotalMinutes() {
//        return hours*60+minutes;
//    }
//
//    @Override
//    public String toString() {
//        return hours + "h" + minutes + "m";
//    }
//    @Override
//    public boolean equals(Object other) {
//        if (other instanceof TimeSpan) {
//            TimeSpan o = (TimeSpan) other;
//            return this.getTotalMinutes() == other.getTotalMinutes();
//        }
//        return false;
//    }
//    @Override
//    public int hashCode() {
//        return Objects.hash(hours, minutes);
//    }
//    @Override
//    public int compareTo(TimeSpan other) {
//        return Integer.compare(this.getTotalMinutes(), other.getTotalMinutes());
//    }
//
//    public TimeSpan plus(TimeSpan other) {
//        return this.minutes+other.minutes>=60? new TimeSpan(this.hours+other.hours+1, 60-this.minutes+other.minutes);
//    }
//    public TimeSpan plusHours(int hours) throws IllegalArgumentException {
//        if (hours<0) throws new IllegalArgumentException("no neggy hours");
//        return new TimeSpan(this.hours+hours, this.minutes);
//    }
//    public TimeSpan plusMinutes(int minutes) throws IllegalArgumentException {
//        if (minutes<0) throws new IllegalArgumentException("no neg minutes");
//        return this.minutes+minutes>=60? new TimeSpan(this.hours+1, 60-this.minutes+minutes) : new TimeSpan(this.hours, this.minutes+minutes);
//    }
//    public TimeSpan plusHoursAndMinutes(int hours, int minutes) throws IllegalArgumentException {
//        return new TimeSpan( (this.minutes+minutes>=60? this.hours+hours+1 : this.hours+hours), (this.minutes+minutes>=60? 60-this.minutes+minutes : this.minutes+minutes) );
//    }
//
//}
import java.util.Objects;

class TimeSpan implements Comparable<TimeSpan> {

    private int totalMins;

    //constructor (priv)
    private TimeSpan(int totalMins) {
        if (totalMins<0) throw new IllegalArgumentException("can't be negative");
        this.totalMins=totalMins;
    }

    //static fac methods
    public static TimeSpan ofHours(int hours) {
        if (hours<0) throw new IllegalArgumentException("can't be negative");
        return new TimeSpan(60*hours);
    }

    public static TimeSpan ofMinutes(int mins) {
        if (mins<0) throw new IllegalArgumentException("can't be negative");
        return new TimeSpan(mins);
    }

    public static TimeSpan ofHoursAndMinutes(int hours, int mins) {
        if(hours<0||mins<0) throw new IllegalArgumentException("can't be negative");
        return new TimeSpan(hours*60 + mins);
    }

    //getter methods
    public int getHours(){
        return totalMins/60;
    }

    public int getMinutes() {
        return totalMins%60;
    }

    public int getTotalMinutes() {
        return totalMins;
    }

    //Overrides
    @Override
    public String toString() {
        return totalMins/60 + "h" + totalMins%60 + "m";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TimeSpan) {
            TimeSpan other = (TimeSpan) o;
            return this.totalMins==other.totalMins;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalMins);
    }

    @Override
    public int compareTo(TimeSpan other) {
        return Integer.compare(this.totalMins, other.totalMins);
    }

    //add methods, returning TimeSpan objs
    public TimeSpan plus(TimeSpan other) {
        return new TimeSpan(this.totalMins+other.totalMins);
    }

    public TimeSpan plusHours(int hours) {
        if (hours<0) throw new IllegalArgumentException("can't be negative");
        return new TimeSpan(hours*60+totalMins);
    }

    public TimeSpan plusMinutes(int mins) {
        if (mins<0) throw new IllegalArgumentException("can't be negative");
        return new TimeSpan(totalMins+mins);
    }

    public TimeSpan plusHoursAndMinutes(int hours, int minutes) {
        if (hours<0 || minutes<0) throw new IllegalArgumentException("can't be negative");
        return new TimeSpan(totalMins+(hours*60)+minutes);
    }

}