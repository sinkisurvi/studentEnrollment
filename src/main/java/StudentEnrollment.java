import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class StudentEnrollment {

    public void studentEnrolment() {
        String file =getPath("enrollments.csv");
        String line;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int preYear= year - 3;
        int count = 0;
        String result= "";
        HashMap<Integer, List<Exam>>  map = getExamMap();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                if(count != 0) {
                    String[] value =  line.split(",");
                    int scode = Integer.parseInt(value[0].trim()) ;
                  int syear = Integer.parseInt(value[1].trim()) ;
                  if(syear < preYear) {
                      Exam exam = map.get(scode).get(0);
                      result+= scode+ ","+ exam.getsName() +"\n";

                  }
                }
                count++;
            }
            fileWrite("students-enrolled.csv",result);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void topTenStudent(){
        HashMap<Integer, List<Exam>> smap =  getExamMap();
        List<Exam> list = new ArrayList<>();
        for(int s:smap.keySet()){
            List<Exam>  lt = smap.get(s);
            int listSize = lt.size();
            if(listSize > 3){
                int total = 0;
                for( Exam e : lt){
                    total += e.getGrade();
                }
                int avg = total / listSize;
                if(avg > 18){

                    Exam ex = new Exam();
                    ex.setGrade(avg);
                    ex.setsCode(s);
                    list.add(ex);
                }
            }

        }
        Collections.sort(list);
        int count = 1;
        String result ="";
        for(Exam ex : list){
            result+= ex.getsCode()+ ","+ ex.getGrade() +"\n";
            if (count>= 10){
                break;
            }

            count++;
        }
        fileWrite("top-ten-students.csv",result);

    }

    public HashMap<Integer, List<Exam>>  getExamMap(){
        String file =getPath("exams.csv");
        String line;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        HashMap<Integer, List<Exam>> hm = new HashMap<>();
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                if(count != 0) {
                    String[] value =  line.split(",");

                    int scode = Integer.parseInt(value[1].trim());
                    int sgrade = Integer.parseInt(value[3].trim());
                    Exam ex = new Exam();
                    ex.setEnrollDate(value[2]);
                    ex.setGrade(sgrade);
                    ex.setsName(value[0]);

                    if(hm.containsKey(scode)){
                        List<Exam> examList = hm.get(scode);
                        examList.add(ex);
                        hm.put(scode, examList);
                    }else{
                        List<Exam> examList = new ArrayList<>();
                        examList.add(ex);
                        hm.put(scode, examList);
                    }
                }
                count++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hm;
    }

    public void fileWrite( String fileName,String result){
        try {
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(result);
            buffer.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }

    public String getPath(String fileName){
        URL res = getClass().getClassLoader().getResource(fileName);
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }
}


