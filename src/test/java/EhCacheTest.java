import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StopWatch;

import com.daniele.hibernate.model.UserDetails;
import com.daniele.hibernate.service.UserDetailsService;
import com.daniele.hibernate.service.impl.PrintUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class EhCacheTest {  
	
	@Autowired
	private UserDetailsService userDetailsService;
	
    @Autowired
    private PrintUtils printUtils;
    
    private TransactionTemplate transaction;
    
    @Autowired
    public void setPtm(PlatformTransactionManager platformTransactionManager) {
        transaction = new TransactionTemplate(platformTransactionManager);
    }

    @Test
    public void testCountUsers() {
        Assert.assertEquals(5, userDetailsService.countUsers());
    }
    
    /*@Test
    public void testCache() {
    	// Using programmatic transaction management since we need 2 transactions
        // inside the same method
        
        // The first attempt should drag a User into the 1st Level Cache
        transaction.execute(new TransactionCallbackWithoutResult() {
            public void doInTransactionWithoutResult(TransactionStatus status) {
                testCacheNew();
            }
        });
        
        // The second attempt should hit the 2nd Level Cache
        transaction.execute(new TransactionCallbackWithoutResult() {
            public void doInTransactionWithoutResult(TransactionStatus status) {
            	testCacheNew();
            }
        });
    }*/

    public void testCacheNew() {
        UserDetails user = userDetailsService.getUserById(1);
        Assert.assertNotNull(user);
     }
    
    public void testCache(int i) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        UserDetails user = userDetailsService.getUserById(1);
        stopWatch.stop();
        System.out.println("Query time : " + stopWatch.getTotalTimeSeconds());
        System.out.println(user.toString());
        printUtils.printStats(i);
     }      
 }