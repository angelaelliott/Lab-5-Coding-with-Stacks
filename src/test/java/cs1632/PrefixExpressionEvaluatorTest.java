package cs1632;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class PrefixExpressionEvaluatorTest {

    // helper method for testing different prefix expressions
    private ArrayList<Token> createTokenList (String... strings) {
        ArrayList<Token> list = new ArrayList<>();
        for (String s : strings) {
            list.add(new Token(s));
        }
        return list;
    }

    @Test
    public void testAddition() {
        ArrayList<Token> tokens = createTokenList("+", "3", "4", ";");
        assertEquals(7, PrefixExpressionEvaluator.evaluateExpression(tokens));
    }

    @Test
    public void testSubtraction() {
        ArrayList<Token> tokens = createTokenList("-", "3", "4", ";");
        assertEquals(-1, PrefixExpressionEvaluator.evaluateExpression(tokens));
    }

    @Test
    public void testDivision() {
        ArrayList<Token> tokens = createTokenList("/", "6", "2", ";");
        assertEquals(3, PrefixExpressionEvaluator.evaluateExpression(tokens));
    }

    @Test
    public void testMultiplication() {
        ArrayList<Token> tokens = createTokenList("*", "3", "4", ";");
        assertEquals(12, PrefixExpressionEvaluator.evaluateExpression(tokens));
    }

    @Test
    public void testMixedOperators() {
        ArrayList<Token> tokens = createTokenList("+", "2", "*", "3", "4", ";");
        assertEquals(14, PrefixExpressionEvaluator.evaluateExpression(tokens));
    }
}