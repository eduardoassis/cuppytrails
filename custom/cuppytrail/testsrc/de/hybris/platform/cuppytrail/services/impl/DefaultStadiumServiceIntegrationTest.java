package de.hybris.platform.cuppytrail.services.impl;

import javax.annotation.Resource;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.cuppytrail.services.StadiumService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


@IntegrationTest
public class DefaultStadiumServiceIntegrationTest extends ServicelayerTransactionalTest
{

    @Resource
    private StadiumService stadiumService;

    @Resource
    private ModelService modelService;

    private StadiumModel stadiumModel;
    private final static String STADIUM_NAME = "wembley";
    private final static Integer STADIUM_CAPACITY = Integer.valueOf(12345);

    @Before
    public void setUp()
    {
        // This instance of a StadiumModel will be used by the tests
        stadiumModel = new StadiumModel();
        stadiumModel.setCode(STADIUM_NAME);
        stadiumModel.setCapacity(STADIUM_CAPACITY);
    }

    @Test(expected = UnknownIdentifierException.class)
    public void testFailBehavior()
    {
        stadiumService.getStadiumForCode(STADIUM_NAME);
    }

    /**
     * This test tests and demonstrates that the Service's getAllStadium method calls the DAOs' getAllStadium method and
     * returns the data it receives from it.
     */
    @Test
    public void testStadiumService()
    {
        List<StadiumModel> stadiumModels = stadiumService.getStadiums();
        final int size = stadiumModels.size();

        modelService.save(stadiumModel);

        stadiumModels = stadiumService.getStadiums();
        Assert.assertEquals(size + 1, stadiumModels.size());
        Assert.assertEquals("Unexpected stadium found", stadiumModel, stadiumModels.get(stadiumModels.size() - 1));

        final StadiumModel persistedStadiumModel = stadiumService.getStadiumForCode(STADIUM_NAME);
        Assert.assertNotNull("No stadium found", persistedStadiumModel);
        Assert.assertEquals("Different stadium found", stadiumModel, persistedStadiumModel);
    }

}