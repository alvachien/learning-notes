package com.alvachien.learning.java_tutorial.unit_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Executable;
import java.lang.Override;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.condition.*;

/**
 * Unit test for simple App.
 */
public class FactorialTest 
{
    /**
     * Rigourous Test :-)
     */
    @Test
    @DisplayName("testFact()")
    void testFact() {
        assertEquals(1, Factorial.fact(1));
        assertEquals(2, Factorial.fact(2));
        assertEquals(6, Factorial.fact(3));
        assertEquals(3628800, Factorial.fact(10));
        assertEquals(2432902008176640000L, Factorial.fact(20));
    }

    // @Test
    // void testNegative() {
    //     assertThrows(IllegalArgumentException.class, new Executable() {
    //         @Override
    //         public void execute() throws Throwable {
    //             Factorial.fact(-1);
    //         }
    //     });
    // }

    @Test
    void testNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            Factorial.fact(-1);
        });
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "DEBUG", matches = "true")
    void testOnlyOnDebugMode() {
        // TODO: this test is only run on DEBUG=true
    }

    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void testOnlyOn64bitSystem() {
        // TODO: this test is only run on 64 bit system
    }

    @Test
    @DisabledOnJre(JRE.JAVA_8)
    void testOnJava9OrAbove() {
        // TODO: this test is disabled on java 8
    }
    
    @Test
    @DisabledOnOs(OS.WINDOWS)
    void testOnNonWindowsOs() {
        // TODO: this test is disabled on windows
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 5, 100 })
    void testAbs(int x) {
        assertEquals(x, Math.abs(x));
    }
    
    @ParameterizedTest
    @ValueSource(ints = { -1, -5, -100 })
    void testAbsNegative(int x) {
        assertEquals(-x, Math.abs(x));
    }    
}
