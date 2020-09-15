package com.lh.springboot.other;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @BeforeAll
    public static void init() {
        System.out.println("@BeforeAll 初始化数据(所有测试之前，相当于Junit4 @BeforeClass)");
    }

    @AfterAll
    public static void cleanup() {
        System.out.println("@AfterAll 清理数据");
    }

    @BeforeEach
    void setUp() {
        System.out.println("@BeforeEach 每个测试之前, 对应Junit4 @Before");
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("我的第一个测试")
    @Test
    void evaluate() {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        System.out.println("我的第一个测试开始测试");
        // 断言
//        assertEquals(6, sum);
        Assertions.assertEquals(6, sum); //新的断言
    }

    @DisplayName("我的第二个测试")
    @Test
    void testSecondTest() {
        System.out.println("我的第二个测试开始测试");
    }

    @DisplayName("我的第三个测试")
    @Disabled //该测试方法处于不可用，用于跳过某个测试方法
    @Test
    void testThirdTest() {
        System.out.println("我的第三个测试开始测试");
    }

    @DisplayName("重复测试")
    @RepeatedTest(value = 3)
    public void i_am_a_repeated_test() {
        System.out.println("执行测试");
    }
}

