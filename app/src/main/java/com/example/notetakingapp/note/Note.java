package com.example.notetakingapp.note;

import java.time.LocalDate;

public class Note {


    private Long id;
    private LocalDate dateOfEdition;
    private String title;
    private String noteBody;
    private String noteBackgroundColor;

    public Note(Long id, LocalDate dateOfEdition, String title, String noteBody, String noteBackgroundColor) {
        this.id = id;
        this.dateOfEdition = dateOfEdition;
        this.title = title;
        this.noteBody = noteBody;
        this.noteBackgroundColor = noteBackgroundColor;
    }

    public Note() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfEdition() {
        return dateOfEdition;
    }

    public void setDateOfEdition(LocalDate dateOfEdition) {
        this.dateOfEdition = dateOfEdition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    public String getNoteBackgroundColor() {
        return noteBackgroundColor;
    }

    public void setNoteBackgroundColor(String noteBackgroundColor) {
        this.noteBackgroundColor = noteBackgroundColor;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", dateOfEdition=" + dateOfEdition +
                ", title='" + title + '\'' +
                ", noteBody='" + noteBody + '\'' +
                ", noteBackgroundColor='" + noteBackgroundColor + '\'' +
                '}';
    }
}
