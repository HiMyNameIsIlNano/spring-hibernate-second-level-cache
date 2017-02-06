import org.hibernate.SessionFactory;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StopWatch;

import com.daniele.hibernate.service.impl.PrintUtilsImpl;
import com.daniele.hibernate.service.impl.UserDetailsServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class EhCacheTest extends AbstractTransactionalJUnit4SpringContextTests
{   
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private PrintUtilsImpl printUtilsImpl;

    private TransactionTemplate tx;

    @Autowired
    public void setPtm(PlatformTransactionManager ptm) {
        tx = new TransactionTemplate(ptm);
    }

    @Test
    public void doTestCache() {
        // Using programmatic transaction management since we need 2 transactions
        // inside the same method

        // 1st attempt
        tx.execute(new TransactionCallbackWithoutResult() {
            public void doInTransactionWithoutResult(TransactionStatus status) {
                testCache();
            }
        });

        // 2nd attempt
        tx.execute(new TransactionCallbackWithoutResult() {
            public void doInTransactionWithoutResult(TransactionStatus status) {
                testCache();
            }
        });

    }

    public void testCache() {
        long numberOfUsers = userDetailsServiceImpl.getAllUsers().size();
        System.out.println("Number of rows :" + numberOfUsers);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        userDetailsServiceImpl.getAllUsers();
        stopWatch.stop();
        System.out.println("Query time : " + stopWatch.getTotalTimeSeconds());
        printUtilsImpl.printStats();;
     }      
 }