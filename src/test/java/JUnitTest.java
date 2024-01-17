import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    @DisplayName("1 + 2는 3이다") // 테스트 이름
    @Test // 테스트 메서드
    public void junitTest(){
        int a = 1;
        int b = 2;
        int sum = 3;

        Assertions.assertEquals(sum, a + b); // 검증 메서드 assertEquals()로 a + b와 sum의 값이 같은지 확인
    }

    // 실패 케이스
    //@DisplayName("1 + 3은 4이다.")
    //@Test
    //public void juiitFailedTest() {
        //int a = 1;
        //int b = 3;
        //int sum = 3;

        //Assertions.assertEquals(sum, a + b);
    //}
}
