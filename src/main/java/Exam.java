import java.util.Date;

public class Exam implements Comparable<Exam> {
     private String sName;
     private int sCode;
    private String enrollDate;
    private int grade;

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getsCode() {
        return sCode;
    }

    public void setsCode(int sCode) {
        this.sCode = sCode;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(String enrollDate) {
        this.enrollDate = enrollDate;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public int compareTo(Exam o) {
        return o.getGrade() - this.getGrade();
    }

    @Override
    public String toString() {
        return "Exam{" +
                "sName='" + sName + '\'' +
                ", sCode=" + sCode +
                ", grade=" + grade +
                '}';
    }
}
