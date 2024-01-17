import org.junit.jupiter.api.*;

public class JUnitCycleTest {
    @BeforeAll // 전체 테스트 시작 전에 처음으로 1번만 실행 ~> 메서드는 static으로 선언 (ex. DB 연결하거나 테스트 환경 초기화할 때 사용)
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach // 테스트 케이스 시작하기 전에 매번 실행 (ex. 테스트 메서드에서 사용하는 객체 호출하거나 테스트에 필요한 값을 미리 넣을 때)
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }
    @Test
    public void test2() {
        System.out.println("test2");
    }
    @Test
    public void test3() {
        System.out.println("test3");
    }

    @AfterAll // 전체 테스트 마치고 종료하기 전에 1번만 실행 ~>  메서드는 static (ex. DB 연결 종료하거나 공통적으로 사용하는 자원 해제할 때)
    static void afterAll() {
        System.out.println("@AfterAll");
    }

    @AfterEach // 테스트 케이스를 종료하기 전 매번 실행 (ex. 테스트 이후에 특정 데이터 삭제해야 하는 경우)
    public void afterEach() {
        System.out.println("@AfterEach");
    }
}
