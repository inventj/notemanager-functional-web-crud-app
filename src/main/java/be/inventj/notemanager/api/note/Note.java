package be.inventj.notemanager.api.note;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by j.peeters on 17/01/2018.
 */
@Document
public class Note {

    @Id
    private String id;
    private String title;
    private String content;

    public Note() {
    }

    public Note(@JsonProperty("title") String title, @JsonProperty("content") String content) {
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Note{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", content='" + content + '\'' +
               '}';
    }
}
