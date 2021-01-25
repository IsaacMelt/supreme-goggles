package JavaClasses;

import java.io.Serializable;

public class ProgramBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Program_ID;
    private String Program_name;
    private String Language;
    private String Author;
    private String Release_year;
    private String Subscription_price;

    public ProgramBean(){ }

    public ProgramBean(String Program_ID, String Program_name, String Language, String Author, String Release_year, String Subscription_price) {

        this.Program_ID = Program_ID;
        this.Program_name = Program_name;
        this.Language = Language;
        this.Author = Author;
        this.Release_year = Release_year;
        this.Subscription_price = Subscription_price;
    }

    public String getProgram_ID() {
        return Program_ID;
    }

    public String getProgram_name() {
        return Program_name;
    }

    public String getLanguage() {
        return Language;
    }

    public String getAuthor() {
        return Author;
    }

    public String getRelease_year() {
        return Release_year;
    }

    public String getSubscription_price() {
        return Subscription_price;
    }
}