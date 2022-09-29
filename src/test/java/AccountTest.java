import com.kata.account.Account;
import com.kata.account.OperationType;
import org.junit.Assert;
import org.junit.Test;
import org.hamcrest.core.StringContains;

import java.util.Optional;

public class AccountTest {

    @Test
    public void should_show_the_balance_of_my_account() {
        Account account = new Account();
        account.setBalance(1000);
        Assert.assertEquals(account.getBalance(),account.showBalance());
    }

    @Test
    public void should_deposit_amount() throws Exception{
        Account account = new Account();
        account.setBalance(1000);
        account.deposit(500);

        Assert.assertEquals(1500, account.getBalance());
        Assert.assertFalse(account.getOperations().isEmpty());
        Assert.assertEquals(500, account.getOperations().get(0).getAmount());
        Assert.assertEquals(OperationType.DEPOSIT,account.getOperations().get(0).getOperationType());
        Assert.assertEquals(1000, account.getOperations().get(0).getPreviousBalance());
        Assert.assertEquals(1500, account.getOperations().get(0).getNewBalance());
    }

    @Test
    public void should_withdraw_amount() throws Exception{
        Account account = new Account();
        account.setBalance(1000);
        account.withdraw(300);
        Assert.assertTrue(300 <= account.getBalance() );
        Assert.assertEquals(700, account.getBalance());
        Assert.assertFalse(account.getOperations().isEmpty());
        Assert.assertEquals(1000, account.getOperations().get(0).getPreviousBalance());
        Assert.assertEquals(300, account.getOperations().get(0).getAmount());
        Assert.assertEquals(OperationType.WITHDRAW,account.getOperations().get(0).getOperationType());
        Assert.assertEquals(700, account.getOperations().get(0).getNewBalance());
    }
    @Test(expected =Exception.class)
    public void should_throw_Exception_when_Amount_Is_Greater_Than_Balance() throws Exception{
        Account account = new Account();
        account.setBalance(1000);
        account.withdraw(1500);
    }

    @Test(expected =Exception.class)
    public void should_throw_Exception_when_Amount_Is_Negative() throws Exception{
        Account account = new Account();
        account.setBalance(1000);
        account.deposit(-500);
    }

    @Test
    public void should_get_History() throws Exception{
        Account account = new Account();
        account.setBalance(1000);
        account.deposit(500);
        account.withdraw(300);

        Assert.assertFalse(account.getOperations().isEmpty());
        Assert.assertThat(account.showHistory(), StringContains.containsString("Compte: MyBankAccout1    Type: WITHDRAW    Montant: 300    Solde avant:  1500    Solde apres: 1200"));
    }


}
