package it.polimi.db2.telco.exceptions.report;

public class ReportException extends RuntimeException {
    public ReportException(String message) {
        super(message);
    }

    public ReportException(){ super();}
}
