import org.junit.Test;

public class TestStudentEnrollment {
    @Test
    public void testGetExamMap(){
        StudentEnrollment st = new StudentEnrollment();
        st.getExamMap();
    }
    @Test
    public void testTopTenStudent(){
        StudentEnrollment st = new StudentEnrollment();
        st.topTenStudent();
    }
    @Test
    public void testStudentEnrolment(){
        StudentEnrollment st = new StudentEnrollment();
        st.studentEnrolment();
    }
}
