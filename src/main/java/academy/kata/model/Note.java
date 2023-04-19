package academy.kata.model;

import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

public class Note {
    private Long id;
    private String text;
    private Set<String> labels;

    public Note(String text, Set<String> labels) {
        this.text = text;
        this.labels = labels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<String> getLabels() {
        return labels;
    }

    public void setLabels(Set<String> labels) {
        this.labels = labels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (!Objects.equals(id, note.id)) return false;
        if (!text.equals(note.text)) return false;
        return Objects.equals(labels, note.labels);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + text.hashCode();
        result = 31 * result + (labels != null ? labels.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Note.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("text='" + text + "'")
                .add("labels=" + labels)
                .toString();
    }
}
