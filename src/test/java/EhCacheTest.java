import org.hibernate.stat.Statistics;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.daniele.hibernate.model.UserDetails;
import com.daniele.hibernate.service.StatisticsUtils;
import com.daniele.hibernate.service.UserDetailsService;

public class EhCacheTest extends BaseDbTest {  
	
	@Autowired
	private UserDetailsService userDetailsService;
	
    @Autowired
    private StatisticsUtils statisticsUtils;
    
    private TransactionTemplate transaction;
    
    @Autowired
    public void setPtm(PlatformTransactionManager platformTransactionManager) {
        transaction = new TransactionTemplate(platformTransactionManager);
    }

    @Test
    public void testCountUsers() {
        Assert.assertEquals(5, userDetailsService.countUsers());
    }
    
    @Test
    public void testfindUser() {
        Assert.assertEquals(1, userDetailsService.getUserById(1).getId());
    }
    
    @Test
    public void testCache() {
    	// The first attempt should drag a User into the 1st Level Cache
        transaction.execute(new TransactionCallbackWithoutResult() {
            public void doInTransactionWithoutResult(TransactionStatus status) {
            	Statistics statistics = statisticsUtils.getStatitstics();
            	statistics.setStatisticsEnabled(true);
            	Assert.assertTrue(statisticsUtils.isStatitsticsEnabled());
            	            	
            	UserDetails user = userDetailsService.getUserById(1);
            	Assert.assertNotNull(user);
                Assert.assertTrue(statistics.getSecondLevelCacheHitCount() == 0);
            }
        });
        
        // The second attempt should hit the 2nd Level Cache
        transaction.execute(new TransactionCallbackWithoutResult() {
            public void doInTransactionWithoutResult(TransactionStatus status) {
            	Statistics statistics = statisticsUtils.getStatitstics();
            	statistics.setStatisticsEnabled(true);
            	Assert.assertTrue(statisticsUtils.isStatitsticsEnabled());
            	            	
            	UserDetails user = userDetailsService.getUserById(1);
            	Assert.assertNotNull(user);
                Assert.assertTrue(statistics.getSecondLevelCacheHitCount() == 1);
            }
        });
    }
}