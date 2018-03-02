package de.hybris.platform.cuppytrail.facades.impl;

import de.hybris.platform.cuppytrail.data.StadiumData;
import de.hybris.platform.cuppytrail.facades.StadiumFacade;
import de.hybris.platform.cuppytrail.jalo.Stadium;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class DefaultStadiumFacadeIntegrationTest extends ServicelayerTransactionalTest{

    @Resource
    private StadiumFacade stadiumFacade;

    @Resource
    private ModelService modelService;

    private StadiumModel stadiumModel;
    private final String STADIUM_NAME = "wembley";
    private final Integer STADIUM_CAPACITY = Integer.valueOf(90000);

    @Before
    public void setUp(){
        // This instance of a Stadium Model will be used by the tests
        stadiumModel = new StadiumModel();
        stadiumModel.setCode(STADIUM_NAME);
        stadiumModel.setCapacity(STADIUM_CAPACITY);
    }

    /**
     * Tests exception behavior by getting a stadium which doesn't exist
     */
    @Test(expected = UnknownIdentifierException.class)
    public void testInvalidParameter() {
        stadiumFacade.getStadium(STADIUM_NAME);
    }

    /**
     * Tests exception behavior by passing in a null parameter
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullParameter(){
        stadiumFacade.getStadium(null);
    }

    /**
     * Tests and demonstrates the Facade's methods
     */
    @Test
    public void testStadiumFacade() {
        List<StadiumData> stadiumDataList = stadiumFacade.getStadiums();
        assertNotNull(stadiumDataList);

        final int size = stadiumDataList.size();
        modelService.save(stadiumModel);

        stadiumDataList = stadiumFacade.getStadiums();
        assertNotNull(stadiumDataList);
        assertEquals(size + 1, stadiumDataList.size());
        assertEquals(STADIUM_NAME, stadiumDataList.get(0).getName());
        assertEquals(STADIUM_CAPACITY.toString(), stadiumDataList.get(0).getCapacity());

        final StadiumData persistedStadiumData = stadiumFacade.getStadium(STADIUM_NAME);
        assertNotNull(persistedStadiumData);
        assertEquals(STADIUM_NAME, persistedStadiumData.getName());
        assertEquals(STADIUM_CAPACITY.toString(), persistedStadiumData.getCapacity());
    }
}