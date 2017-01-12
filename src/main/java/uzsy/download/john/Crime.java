package uzsy.download.john;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Crime {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    private String title;
    private Date date;
    private boolean solved;

    /*@Lob
    private byte[] photo;*/


    public Crime() {
        // Generate a unique identifier
        uuid = UUID.randomUUID();
        date = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private UUID uuid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    @Override
    public String toString() {
        return title;
    }

}


