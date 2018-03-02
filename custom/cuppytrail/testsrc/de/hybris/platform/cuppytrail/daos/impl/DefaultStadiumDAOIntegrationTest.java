package de.hybris.platform.cuppytrail.daos.impl;

import de.hybris.platform.cuppytrail.daos.StadiumDAO;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefaultStadiumDAOIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    private StadiumDAO stadiumDAO;

    @Resource
    private ModelService modelService;

    /** Name of test stadium. */
    private static final String STADIUM_NAME = "wembley";

    /** Capacity of test stadium. */
    private static final Integer STADIUM_CAPACITY = Integer.valueOf(12345);

    @Test
    public void stadiumDAOTest() {

        List<StadiumModel> stadiumsByCode = this.stadiumDAO.findStadiumsByCode(STADIUM_NAME);
        assertTrue("No stadium should be returned.", stadiumsByCode.isEmpty());

        List<StadiumModel> allStadiums = this.stadiumDAO.findStadiums();
        final int size = allStadiums.size();

        final StadiumModel stadiumModel =  new StadiumModel();
        stadiumModel.setCode(STADIUM_NAME);
        stadiumModel.setCapacity(STADIUM_CAPACITY);
        modelService.save(stadiumModel);

        allStadiums = stadiumDAO.findStadiums();
        assertEquals(size +1, allStadiums.size());
        assertEquals("Unexpected stadium found", stadiumModel, allStadiums.get(allStadiums.size() - 1));

        stadiumsByCode = this.stadiumDAO.findStadiumsByCode(STADIUM_NAME);
        assertEquals("Did not find the Stadium we just saved.", 1, stadiumsByCode.size());
        assertEquals("Retrieved Stadium's name attribute incorrect", STADIUM_NAME, stadiumsByCode.get(0).getCode());
        assertEquals("Retrieved Stadium's capacity attribute incorrect", STADIUM_CAPACITY, stadiumsByCode.get(0).getCapacity());

    }

    @Test
    public void testFindStadiums_EmptyStringParam()
    {
        //calling findStadiumsByCode() with an empty String - returns no results
        final List<StadiumModel> stadiums = stadiumDAO.findStadiumsByCode("");
        assertTrue("No Stadium should be returned", stadiums.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testfindStadiums_NullParam()
    {
        //calling findStadiumByCode with null should throw an IllegalArgumentException
        stadiumDAO.findStadiumsByCode(null); //method's return value not captured
    }
}
