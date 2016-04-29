package net.mgorski.brokenfilefinder.dto;

/**
 * @author Marcin Górski <mg@mgorski.net>
 */
public class ResultDto {

    private double match;
    private double total;

    public ResultDto(double match, double total) {
        this.match = match;
        this.total = total;
    }

    public double getMatch() {
        return match;
    }

    public void setMatch(double match) {
        this.match = match;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void increaseFail() {
        this.total += 1;
    }

    public void increaseSuccess() {
        this.match += 1;
        this.total += 1;
    }
}
