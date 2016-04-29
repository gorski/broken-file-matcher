package net.mgorski.brokenfilefinder.dto;

/**
 * @author Marcin Górski <mg@mgorski.net>
 */
public class MatchDto {

    private FileDto backedUp;
    private FileDto recovered;
    private double match;

    public MatchDto(FileDto backedUp, FileDto recovered, double match) {
        this.backedUp = backedUp;
        this.recovered = recovered;
        this.match = match;
    }


    public FileDto getBackedUp() {
        return backedUp;
    }

    public void setBackedUp(FileDto backedUp) {
        this.backedUp = backedUp;
    }

    public FileDto getRecovered() {
        return recovered;
    }

    public void setRecovered(FileDto recovered) {
        this.recovered = recovered;
    }

    public double getMatch() {
        return match;
    }

    public void setMatch(double match) {
        this.match = match;
    }
}
