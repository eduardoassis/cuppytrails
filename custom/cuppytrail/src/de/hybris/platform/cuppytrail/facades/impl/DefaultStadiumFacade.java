package de.hybris.platform.cuppytrail.facades.impl;

import de.hybris.platform.cuppy.model.MatchModel;
import de.hybris.platform.cuppytrail.data.MatchSummaryData;
import de.hybris.platform.cuppytrail.data.StadiumData;
import de.hybris.platform.cuppytrail.facades.StadiumFacade;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.cuppytrail.services.StadiumService;
import org.springframework.beans.factory.annotation.Required;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DefaultStadiumFacade implements StadiumFacade {

    private StadiumService stadiumService;

    @Override
    public StadiumData getStadium(String name) {
        StadiumModel stadium = null;
        if (null != name) {
            stadium = stadiumService.getStadiumForCode(name);
            return populateStadiumData(stadium);
        } else {
            throw new IllegalArgumentException("Stadium with name " + name + " not found!");
        }
    }

    private StadiumData populateStadiumData(StadiumModel stadium) {
        final StadiumData stadiumData = new StadiumData();
        stadiumData.setName(stadium.getCode());
        stadiumData.setCapacity(stadium.getCapacity().toString());
        stadiumData.setMatches(populateMatchs(stadium));
        return stadiumData;
    }

    private List<MatchSummaryData> populateMatchs(StadiumModel stadium) {

        // Create a list of MatchSummaryData from the matches
        final List<MatchSummaryData> matchSummary = new ArrayList<MatchSummaryData>();
        if (stadium.getMatches() != null) {
            stadium.getMatches().forEach(match -> {
                final MatchSummaryData summary = new MatchSummaryData();
                final String homeTeam = match.getHomeTeam().getName();
                final String guestTeam = match.getGuestTeam().getName();
                final Date matchDate = match.getDate();
                // If no goals are specified provide a user-friendly "-" instead of null
                final String guestGoals = match.getGuestGoals() == null ? "-" : match.getGuestGoals().toString();
                final String homeGoals = match.getHomeGoals() == null ? "-" : match.getHomeGoals().toString();
                summary.setHomeTeam(homeTeam);
                summary.setGuestTeam(guestTeam);
                summary.setDate(matchDate);
                summary.setGuestGoals(guestGoals);
                summary.setHomeGoals(homeGoals);
                final String formattedDate = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(matchDate);
                final String matchSummaryFormatted = homeTeam + ":( " + homeGoals + " ) " + guestTeam + " ( " + guestGoals + " ) "
                        + formattedDate;
                summary.setMatchSummaryFormatted(matchSummaryFormatted);
                matchSummary.add(summary);

            });
        }
        return matchSummary;
    }

    @Override
    public List<StadiumData> getStadiums() {
        List<StadiumModel> stadiumServiceList = stadiumService.getStadiums();
        ArrayList<StadiumData> stadiumFacadeData = new ArrayList<StadiumData>();

        stadiumServiceList.forEach(stadiumModel -> {
            StadiumData stadiumData = new StadiumData();
            stadiumData.setName(stadiumModel.getCode());
            stadiumData.setCapacity(stadiumModel.getCapacity().toString());
            stadiumFacadeData.add(stadiumData);
        });
        return stadiumFacadeData;
    }

    @Required
    public void setStadiumService(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }
}
