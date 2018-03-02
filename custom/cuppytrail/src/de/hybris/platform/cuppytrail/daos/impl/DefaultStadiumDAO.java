package de.hybris.platform.cuppytrail.daos.impl;

import de.hybris.platform.cuppytrail.daos.StadiumDAO;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "stadiumDAO")
public class DefaultStadiumDAO implements StadiumDAO {

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<StadiumModel> findStadiums() {

        final String queryString = "SELECT {p:" + StadiumModel.PK + "} " +
                "FROM { " + StadiumModel._TYPECODE + " AS p}";

        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(queryString);
        return flexibleSearchService.<StadiumModel> search(flexibleSearchQuery).getResult();
    }

    @Override
    public List<StadiumModel> findStadiumsByCode(String code) {

        final String queryString = "SELECT {p: " + StadiumModel.PK + "} " +
                "FROM { " + StadiumModel._TYPECODE + " AS p} " +
                "WHERE {p:" + StadiumModel.CODE + "}=?code";

        final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(queryString);
        flexibleSearchQuery.addQueryParameter("code", code);
        return this.flexibleSearchService.<StadiumModel> search(flexibleSearchQuery).getResult();
    }
}
