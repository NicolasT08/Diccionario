package co.edu.uptc.model;

public class Word {
    private String id;
    private String meaning;
    private String translation;

    public Word(String id, String meaning, String translation) {
        this.id = id;
        this.meaning = meaning;
        this.translation = translation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }


}
