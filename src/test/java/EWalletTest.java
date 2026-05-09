import static org.junit.jupiter.api.Assertions.*;

import org.example.EWallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EWalletTest {
    private EWallet myWallet;
    private EWallet friendWallet;

    @BeforeEach
    void setUp() {
        // Initialize wallets before each test
        myWallet = new EWallet(1000.0, "8888");
        friendWallet = new EWallet(500.0, "1234");
    }

    // --- Authentication (10 pts) ---

    @Test
    void testLoginCorrectPin() {
        assertTrue(myWallet.login("8888"), "Login should return true for the correct PIN.");
    }

    @Test
    void testLoginIncorrectPin() {
        assertFalse(myWallet.login("0000"), "Login should return false for an incorrect PIN.");
    }

    // --- Cash In (15 pts) ---

    @Test
    void testCashInValidAmount() {
        boolean success = myWallet.cashIn(500.0);
        assertTrue(success);
        assertEquals(1500.0, myWallet.getBalance(), "Balance should increase after cash-in.");
    }

    @Test
    void testCashInInvalidAmount() {
        assertFalse(myWallet.cashIn(0), "Zero amount should return false.");
        assertFalse(myWallet.cashIn(-50), "Negative amount should return false.");
        assertEquals(1000.0, myWallet.getBalance(), "Balance should remain unchanged.");
    }

    // --- Send Money (25 pts) ---

    @Test
    void testSendMoneyValid() {
        boolean success = myWallet.sendMoney(friendWallet, 200.0);
        assertTrue(success, "Transfer should be successful.");
        assertEquals(800.0, myWallet.getBalance(), "Sender balance should decrease.");
        assertEquals(700.0, friendWallet.getBalance(), "Receiver balance should increase.");
    }

    @Test
    void testSendMoneyInsufficientBalance() {
        boolean success = myWallet.sendMoney(friendWallet, 5000.0);
        assertFalse(success, "Transfer should fail if sender has insufficient funds.");
        assertEquals(1000.0, myWallet.getBalance(), "Sender balance should remain the same.");
        assertEquals(500.0, friendWallet.getBalance(), "Receiver balance should remain the same.");
    }

    @Test
    void testSendMoneyNegativeAmount() {
        boolean success = myWallet.sendMoney(friendWallet, -100.0);
        assertFalse(success, "Negative transfers should be blocked.");
    }

    // --- Part 5: Balance Verification (10 pts) ---

    @Test
    void testBalanceAfterMultipleTransactions() {
        myWallet.cashIn(1000.0); // 1000 + 1000 = 2000
        myWallet.sendMoney(friendWallet, 500.0); // 2000 - 500 = 1500

        assertEquals(1500.0, myWallet.getBalance(), "Final balance check failed.");
        assertEquals(1000.0, friendWallet.getBalance(), "Friend's final balance check failed.");
    }
}