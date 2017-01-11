package uzsy.download.john;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class Crime {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;


    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
//    private Photo mPhoto;
    private String mSuspect;

    public Crime() {
        // Generate a unique identifier
        mId = UUID.randomUUID();
        mDate = new Date();
    }


    @Override
    public String toString() {
        return mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

/*    public Photo getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Photo p){
        mPhoto = p;
    }*/

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }
}


